package cn.caecc.erp.modules.service;

import java.util.Date;

import cn.caecc.erp.modules.dao.mybatis.entity.WellDxDaily;
import cn.caecc.erp.support.message.Message;

public interface WellDxDailyService {

	Message insertDxWellDaily(WellDxDaily wellDxDaily);

	Message updateDxWellDaily(WellDxDaily wellDxDaily);
	
	Message deleteDxWellDaily(int id);
	
	/**
	 * 查询定向井某日
	 * @param logDate
	 * @param wellId
	 * @return
	 */
	Message selectDxWellDaily(Date logDate,Integer wellId);
	
	/**
	 * 通过井号，筛选日期
	 * @param startDate
	 * @param endDate
	 * @param wellId
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	Message selectAllDxWellDailyByWellId(String startDate,String endDate,Integer wellId,int pageNo,int pageSize);
	Message selectAllDxWellDailyByWellIdNoPage(String startDate,String endDate,Integer wellId);
	
	Message selectDxWellDaily(int id);

	WellDxDaily getLessThanOrEqualTo(Date subDay, int wellId);

}
