/**
 * 
 */
package cn.caecc.erp.support.email.service;

import java.io.File;

import javax.mail.MessagingException;

import org.springframework.core.io.ClassPathResource;

/**
 * @author weizhen
 *
 */
public interface EmailService {

	/**
	 * 发送邮件
	 * @param subject
	 * @param content
	 * @param isHtml
	 * @param to
	 * @throws MessagingException
	 */
    public void sendMail(String subject, String content,boolean isHtml, String to) throws MessagingException;
    
    /**
     * 发送带附件邮件
     * @param subject
     * @param content
     * @param isHtml
     * @param fileAttachment
     * @param to
     * @throws MessagingException
     */
    public void sendMail(String subject, String content,boolean isHtml, File fileAttachment,String to) throws MessagingException;


    /**
     * 发送带附件邮件
     * @param subject
     * @param content
     * @param isHtml
     * @param classPathResource 附件文件(附加在项目内部时候)
     * @param to
     * @throws MessagingException
     */
    public void sendMail(String subject, String content,boolean isHtml, ClassPathResource classPathResource,String to) throws MessagingException;

}
    
