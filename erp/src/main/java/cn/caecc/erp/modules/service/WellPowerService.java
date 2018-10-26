package cn.caecc.erp.modules.service;

import java.util.Date;

import cn.caecc.erp.modules.dao.mybatis.entity.WellPower;
import cn.caecc.erp.support.message.Message;

public interface WellPowerService {

	public boolean insertWellPower(WellPower wellPower);
	
	public boolean deleteWellPower(int id);
	
	public WellPower getWellPowerById(int id);
	
	public Message listWellPower(String startDate,String endDate,Integer wellId,int pageNo,int pageSize);
	public Message listWellPowerNoPage(String startDate,String endDate,Integer wellId);
	
	public boolean updateWellPower(WellPower wellPower);

	public WellPower getOneDay(Date getcurrentDate, int wellId);

	WellPower getGreaterThanOrEqualToLogDate(Date logday, int wellId);
	
	WellPower getAllWellPowerByWellId(int wellId,Date date);

	boolean updateWellPowerByWellIdAndDate(WellPower wellPower);
}
