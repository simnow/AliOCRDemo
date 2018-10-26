package com.partymasses.support.mq.service;
import javax.jms.JMSException;
import javax.jms.Message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
@Service
public class NoticeMessageCustomerServiceListener implements MessageListener{
	private final static Logger logger = LoggerFactory.getLogger(NoticeMessageCustomerServiceListener.class);

	
	public void onMessage(Message message) {
        TextMessage textMsg = (TextMessage) message;
        
        
        try {
        	logger.info("接收者受到消息：" + textMsg.getText());
            
        } catch (JMSException e) {
            e.printStackTrace();
        }
        
    }
}
