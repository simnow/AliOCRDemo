package cn.caecc.erp.modules.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.caecc.erp.modules.dao.mybatis.entity.WellDaily;
import cn.caecc.erp.modules.dao.mybatis.entity.WellDailyExample;
import cn.caecc.erp.modules.dao.mybatis.entity.WellDailyExample.Criteria;
import cn.caecc.erp.modules.dao.mybatis.mapper.WellDailyMapper;
import cn.caecc.erp.modules.service.WelldailyService;
import cn.caecc.erp.support.util.DateUtil;
@Service
public class WelldailyServiceImpl implements WelldailyService {

	@Autowired
	private WellDailyMapper wellDailyMapper;
	
	@Override
	public int insertDailyWell(WellDaily dailyWell) {
		dailyWell.setCreatetime(DateUtil.getcurrentDateTime());
		//查询是否已经存在
		int result=wellDailyMapper.insertSelective(dailyWell);
		if(result>0){
			return result;
		}
		return 0;
		
	}

	/**
	 * 通过条件进行更新
	 */
	@Override
	public boolean updateDailyWell(WellDaily dailyWell) {
		WellDailyExample example=new WellDailyExample();
		Criteria criteria=example.createCriteria();
		criteria.andLogdateEqualTo(dailyWell.getLogdate());
		criteria.andWellidEqualTo(dailyWell.getWellid());
		if(wellDailyMapper.updateByExampleSelective(dailyWell, example)>0){
			return true;
		}
		return false;
	}

}
