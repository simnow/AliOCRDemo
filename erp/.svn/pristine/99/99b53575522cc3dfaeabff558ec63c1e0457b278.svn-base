package cn.caecc.erp.modules.service;

import java.util.Date;

import cn.caecc.erp.modules.dao.mybatis.entity.WellXwDaily;
import cn.caecc.erp.support.message.Message;

public interface WellXwDailyService {

	boolean insertWellXwDaily(WellXwDaily wellXwDaily);

	boolean deleteWellXwDaily(int id);

	WellXwDaily getWellXwDailyById(int id);

	Message listWellXwDaily(String startDate, String endDate, Integer wellId, int pageNo, int pageSize);
	Message listWellXwDailyNoPage(String startDate, String endDate, Integer wellId);

	boolean updateWellXwDaily(WellXwDaily wellXwDaily);

	Message selectXwWellDaily(Date logDate, Integer wellId);

	WellXwDaily getLessThanOrEqualTo(Date logday, int wellId);

}
