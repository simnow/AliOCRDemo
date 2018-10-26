package cn.caecc.erp.modules.service;

import java.util.Date;

import cn.caecc.erp.modules.dao.mybatis.entity.WellTime;
import cn.caecc.erp.support.message.Message;

public interface WellTimeService {

	public boolean insertWellTime(WellTime WellTime);
	
	public boolean deleteWellTime(int id);
	
	public WellTime getWellTimeById(int id);
	
	public Message listWellTime(String startDate,String endDate,Integer wellId,int pageNo,int pageSize);
	public Message listWellTimeNoPage(String startDate,String endDate,Integer wellId);
	
	public boolean updateWellTime(WellTime WellTime);

	public WellTime getOneDay(Date getcurrentDate, int wellId);
}
