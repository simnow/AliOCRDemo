package cn.caecc.erp.support.shiro.session.listener;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import cn.caecc.erp.support.statistics.service.StatisticsService;
import cn.caecc.erp.support.workflow.service.WorkflowService;

public class MySessionListener extends  SessionListenerAdapter  {
	@Autowired @Qualifier("sessionStatisticsServiceImpl")
	private StatisticsService statisticsService;
	@Autowired
	private WorkflowService workflowService;
	@Override
	public void onExpiration(Session session) {
		// TODO Auto-generated method stub
    	statisticsService.removeLoginUser(session);
    	workflowService.clearAttachmentOssTempSessionData();
		
	}
	@Override
	public void onStart(Session session) {
		// TODO Auto-generated method stub	  
	}
	@Override
	public void onStop(Session session) {
		// TODO Auto-generated method stub
    	statisticsService.removeLoginUser(session);
    	workflowService.clearAttachmentOssTempSessionData();
	}
	
	public void onLogin(Session session)
	{
        statisticsService.addLoginUser(session);
	}
 
}
