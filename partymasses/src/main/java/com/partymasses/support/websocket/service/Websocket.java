package com.partymasses.support.websocket.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.partymasses.support.websocket.config.GetHttpSessionConfigurator;
import com.partymasses.modules.dao.mybatis.entity.User;
import com.partymasses.support.constant.Contants;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Vector;
 

@Component
@ServerEndpoint(value="/websocket",configurator=GetHttpSessionConfigurator.class)
public class Websocket {
	private final static Logger logger = LoggerFactory.getLogger(Websocket.class);

	private static final HashMap<String, Vector<Websocket> > websocketMap = new HashMap<String, Vector<Websocket>>();;  
	private static final HashMap<Websocket, String>  userNameMap = new HashMap<Websocket, String>();  

    private Session session;  

    public Websocket()  
    {  
    
    }  
    @OnOpen
    public void onOpen(Session session, EndpointConfig config){
    	logger.info("onOpen");

    	HttpSession httpSession =(HttpSession) config.getUserProperties().get(  
                HttpSession.class.getName());  
    	User user = (User) httpSession.getAttribute(Contants.LOGINUSER); 
    	String userName = user.getMobile();
    	if (userName != null)
    	{
	    	this.session = session;
	
	        if (websocketMap.containsKey(userName))
	        {
	        	websocketMap.get(userName).add(this);
	        }
	        else
	        {
	        	Vector<Websocket> vertor = new Vector<Websocket>();
	        	vertor.add(this);
	        	websocketMap.put(userName, vertor);
	        }
	        userNameMap.put(this, userName);
    	}
    	else
    	{
    		try
    		{
    			session.close();
    		}
    		catch(IOException e)
    		{
                e.printStackTrace();
    		}
    	}
    	
    }
 
    @OnClose
    public void onClose(){
    	logger.info("onClose");
        String userName = userNameMap.get(this); 
    	userNameMap.remove(this);
    	if (userName != null)
    	{
    		if (websocketMap.containsKey(userName))
        	{
                System.out.print(userName +" removeSession");  

                websocketMap.get(userName).remove(this);  
        	}
    	} 	
    }
 
    @OnMessage
    public void onMessage(String message,Session session){
    	logger.info("onMessage" + message);
        try {
            session.getBasicRemote().sendText("收到");
        } catch (IOException e) {
            e.printStackTrace();
        }
           
    }
 
 
    public static void sendMessage(String userName, String msg)
    {
    	logger.info("sendMessage");

    	Vector<Websocket> vertor = websocketMap.get(userName);
    	if (vertor==null) {
			return;
		}
        for (Websocket websocket : vertor)  
        {
        	try {
        		websocket.session.getBasicRemote().sendText(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
                         
        }  
    	
    }
    
    
 
}