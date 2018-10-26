package cn.caecc.erp.modules.service;

import cn.caecc.erp.modules.dao.mybatis.entity.WellDaily;

/**
 * 汇总操作
 * @author GaiNing
 *
 */
public interface WelldailyService {

	public int insertDailyWell(WellDaily dailyWell);
	
	public boolean updateDailyWell(WellDaily dailyWell);
}
