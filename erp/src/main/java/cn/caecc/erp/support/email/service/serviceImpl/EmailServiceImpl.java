/**
 * 
 */
package cn.caecc.erp.support.email.service.serviceImpl;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import cn.caecc.erp.support.email.service.EmailService;

/**
 * @author weizhen
 *
 */
@Service
public class EmailServiceImpl implements EmailService{

	@Autowired
	private JavaMailSender javaMailSender;  
	@Autowired
    private SimpleMailMessage simpleMailMessage;  
	/**
	 * 
	 */
	public EmailServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see cn.caecc.erp.support.email.service.EmailService#sendMail(java.lang.String, java.lang.String, boolean, java.lang.String)
	 */
	@Override
	public void sendMail(String subject, String content, boolean isHtml, String to) throws MessagingException {
		// TODO Auto-generated method stub
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();  
        MimeMessageHelper messageHelper =null;  
        if(isHtml)  
        {  
             messageHelper = new MimeMessageHelper(mimeMessage,true,"UTF-8");  
        }  
        else  
        {  
           messageHelper = new MimeMessageHelper(mimeMessage,true);  
        }  
        messageHelper.setFrom(simpleMailMessage.getFrom()); //设置发件人Email  
        messageHelper.setSubject(subject); //设置邮件主题  
        if(isHtml)  
        {  
             messageHelper.setText(content, true);   //设置邮件主题内容(html格式)  
        }  
        else  
        {  
             messageHelper.setText(content);   //设置邮件主题内容  
        }          
        messageHelper.setTo(to);          //设定收件人Email  
        javaMailSender.send(mimeMessage);    
		
	}

	/* (non-Javadoc)
	 * @see cn.caecc.erp.support.email.service.EmailService#sendMail(java.lang.String, java.lang.String, boolean, java.io.File, java.lang.String)
	 */
	@Override
	public void sendMail(String subject, String content, boolean isHtml, File fileAttachment, String to)
			throws MessagingException {
		// TODO Auto-generated method stub
		 MimeMessage mimeMessage = javaMailSender.createMimeMessage();  
         
         MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,true,"UTF-8");  
           
         messageHelper.setFrom(simpleMailMessage.getFrom()); //设置发件人Email  
         messageHelper.setSubject(subject); //设置邮件主题  

         if(isHtml)  
         {  
              messageHelper.setText(content,true);   //设置邮件主题内容(html格式)  
         }  
         else  
         {  
              messageHelper.setText(content);   //设置邮件主题内容  
         }  
         messageHelper.setTo(to);          //设定收件人Email  
         FileSystemResource file = new FileSystemResource(fileAttachment);  
         
          messageHelper.addAttachment(file.getFilename(), file); //添加附件  
         javaMailSender.send(mimeMessage);    
		
	}

	/* (non-Javadoc)
	 * @see cn.caecc.erp.support.email.service.EmailService#sendMail(java.lang.String, java.lang.String, boolean, org.springframework.core.io.ClassPathResource, java.lang.String)
	 */
	@Override
	public void sendMail(String subject, String content, boolean isHtml, ClassPathResource classPathResource, String to)
			throws MessagingException {
		// TODO Auto-generated method stub
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();  
         /** 
            * new MimeMessageHelper(mimeMessage,true)之true个人见解： 
            * 关于true参数,官方文档是这样解释的： 
            * use the true flag to indicate you need a multipart message 
            * 翻译过来就是：使用true,以表明你需要多个消息 
            * 再去翻一下MimeMessageHelper的API,找到这样一句话： 
            * supporting alternative texts, inline elements and attachments 
            * 也就是说,如果要支持内联元素和附件就必须给定第二个参数为true 
            * 否则抛出 java.lang.IllegalStateException 异常 
            */  
		 MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,true,"UTF-8");  
		 messageHelper.setFrom(simpleMailMessage.getFrom()); //设置发件人Email  
         messageHelper.setSubject(subject); //设置邮件主题  
         if(isHtml)  
         {  
              messageHelper.setText(content,true);   //设置邮件主题内容(html格式)  
         }  
         else  
         {  
              messageHelper.setText(content);   //设置邮件主题内容  
         }  
         messageHelper.setTo(to);          //设定收件人Email  
         /**  FileSystemResource file = new FileSystemResource(fileAttachment); 
        
          * ClassPathResource：很明显就是类路径资源,我这里的附件是在项目里的,所以需要用ClassPathResource 
          * 如果是系统文件资源就不能用ClassPathResource,而要用FileSystemResource,例： 
          *  
         
         ClassPathResource file = new ClassPathResource("attachment/Readme.txt"); 
           */  
       /** 
          * MimeMessageHelper的addAttachment方法： 
          * addAttachment(String attachmentFilename, InputStreamSource inputStreamSource) 
          * InputStreamSource是一个接口,ClassPathResource和FileSystemResource都实现了这个接口 
           
         
           //发送附件邮件 
         */  
         ClassPathResource file = classPathResource;  
         messageHelper.addAttachment(file.getFilename(), file); //添加附件  
         javaMailSender.send(mimeMessage);    
         
	}

}
