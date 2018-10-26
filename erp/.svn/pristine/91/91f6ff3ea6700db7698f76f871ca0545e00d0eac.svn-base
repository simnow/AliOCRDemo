/**
 * 
 */
package cn.caecc.erp.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * @author weizhen
 *
 */
@Configuration
@PropertySource("classpath:email.properties")
public class EmailConfig {

	/**
	 * 
	 */
	public EmailConfig() {
		// TODO Auto-generated constructor stub
	}

	@Value("${email.url}")
	private String url;

	@Value("${email.port}")
	private int port;

	@Value("${email.username}")
	private String username;

	@Value("${email.password}")
	private String password;

	@Bean
	public static PropertySourcesPlaceholderConfigurer getPropertySourcesPlaceholderConfigurer() {
		PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
		propertySourcesPlaceholderConfigurer.setIgnoreUnresolvablePlaceholders(true);
		return propertySourcesPlaceholderConfigurer;
	}

	@Bean(name = "mailSender")
	public JavaMailSenderImpl getMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost(url);
		mailSender.setUsername(username);
		mailSender.setPassword(password);

		String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
		Properties p = System.getProperties();
		p.setProperty("mail.smtp.auth", "true");
		p.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
		p.setProperty("mail.smtp.socketFactory.fallback", "false");
		// 邮箱发送服务器端口,这里设置为465端口
		p.setProperty("mail.smtp.port", String.valueOf(port));
		p.setProperty("mail.smtp.socketFactory.port", String.valueOf(port));
		mailSender.setJavaMailProperties(p);
		return mailSender;
	}

	@Bean(name = "simpleMailMessage")
	public SimpleMailMessage getSimpleMailMessage() {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setFrom(username);
		return simpleMailMessage;
	}

}
