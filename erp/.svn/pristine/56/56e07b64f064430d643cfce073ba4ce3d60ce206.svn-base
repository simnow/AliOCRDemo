package cn.caecc.erp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.caecc.erp.support.message.Message;
import cn.caecc.erp.support.statistics.entity.OnlineDepartmentUserEntity;
import cn.caecc.erp.support.statistics.service.StatisticsService;

@Controller
@RequestMapping(value="/api/online-statistics")
public class OnlineStatisticsController {
	
	@Autowired @Qualifier("sessionStatisticsServiceImpl")
	private  StatisticsService  statisticsService;
	
	@RequestMapping(value="/user",method=RequestMethod.GET)
	@ResponseBody
	public  Message  getOnlineDepartmentUserList() {
		Message message = new Message();
		message.setSuccess(false);
		try {
			List<OnlineDepartmentUserEntity> onlineDepartmentUserEntityList = statisticsService.updateAndGetOnlineDepartmentUserList();
			message.setObj(onlineDepartmentUserEntityList);
			message.setSuccess(true);
			
		} catch (Exception ex) {
			message.setMsg(ex.getMessage());
		}
		return message;	
	}
	
	@RequestMapping(value="/user-count",method=RequestMethod.GET)
	@ResponseBody
	public  Message  getOnlineUserCount() {
		Message message = new Message();
		message.setSuccess(false);
		try {
			long similarOnlineUserCount  = statisticsService.getSimilarOnlineUserCount();
			message.setObj(similarOnlineUserCount);
			message.setSuccess(true);
			
		} catch (Exception ex) {
			message.setMsg(ex.getMessage());
		}
		return message;	
	}

}
