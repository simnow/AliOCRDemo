package com.partymasses.support.mq.service;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.jms.Destination;
@Service
public class NoticeMessageProducerService {
	private final static Logger logger = LoggerFactory.getLogger(NoticeMessageProducerService.class);

	@Autowired @Qualifier("noticeJmsTemplate")
	private JmsTemplate noticeJmsTemplate;
	
	
	public void sendMessage(final String msg){
		
		Destination destination = noticeJmsTemplate.getDefaultDestination();
		logger.info("Send " + msg + " to Destination " + destination.toString());
		
		 MessageCreator messageCreator = new MessageCreator(){
			 public Message createMessage(Session session) throws JMSException {
				 return session.createTextMessage(msg);
				 }
		 };
		         
		 noticeJmsTemplate.send(destination, messageCreator);
		 }
}
