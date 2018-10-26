package cn.caecc.erp.support.mq.service.serviceImpl;
import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import javax.jms.Destination;
import cn.caecc.erp.modules.dao.mybatis.entity.User;
import cn.caecc.erp.support.mq.service.MessageProducerService;
//@Service
public class NoticeMessageProducerService implements MessageProducerService{
	//private final static Logger logger = LoggerFactory.getLogger(NoticeMessageProducerService.class);

	@Autowired @Qualifier("noticeJmsTemplate")
	private JmsTemplate noticeJmsTemplate;
	
	@Override
	public void sendMessage(User user){
		Destination destination = noticeJmsTemplate.getDefaultDestination();		
		 MessageCreator messageCreator = new MessageCreator(){
			 public ObjectMessage createMessage(Session session) throws JMSException {
				 ObjectMessage  objectMessage  = session.createObjectMessage();
				 objectMessage.setObject(user);
				 return objectMessage;
				 }
			 
		 };
		 noticeJmsTemplate.send(destination, messageCreator);
	}
}

