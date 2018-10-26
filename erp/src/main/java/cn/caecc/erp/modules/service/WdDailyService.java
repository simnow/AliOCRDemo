package cn.caecc.erp.modules.service;

import java.util.Date;
import java.util.List;

import cn.caecc.erp.modules.dao.mybatis.entity.WellWdDaily;
import cn.caecc.erp.modules.dao.mybatis.entity.WellWdQk;
import cn.caecc.erp.support.message.Message;

public interface WdDailyService {

	Message insertWdDaily(WellWdDaily wdDaily);

	Message getWdLogInfoByLogDate(Date logDate);

	Message deleteWdLogInfoById(int id);

	Message selectQkList();

	Message insertQkInfo(WellWdQk record);

	Message updateQk(WellWdQk wellWdQk);

	Message insertWdDailyList(List<WellWdDaily> wdDaily);

	Message updateWdLogInfoById(List<WellWdDaily> record,String hbr);

	String selectQkNameByQkId(int id);

	WellWdQk selectWellWdQkByQkId(int id);

}
