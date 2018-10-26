package cn.caecc.erp.modules.service.serviceImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.caecc.erp.modules.dao.mybatis.entity.WellDaily;
import cn.caecc.erp.modules.dao.mybatis.entity.WellDailyExample;
import cn.caecc.erp.modules.dao.mybatis.entity.WellDailyExample.Criteria;
import cn.caecc.erp.modules.dao.mybatis.mapper.WellDailyMapper;
import cn.caecc.erp.modules.service.WellDailySumService;
@Service
public class WellDailySumServiceImpl implements WellDailySumService {

	@Autowired
	private WellDailyMapper wellDailySumMapper;

	@Override
	public List<WellDaily> getWellDailyList(Date logDate, Integer wellId) {
		WellDailyExample example=new WellDailyExample();
		Criteria criteria=example.createCriteria();
		if(null!=logDate){
			criteria.andLogdateEqualTo(logDate);
		}
		if(null!=wellId){
			criteria.andWellidEqualTo(wellId);
		}
		example.setOrderByClause("logdate desc");
		List<WellDaily> resultList=wellDailySumMapper.selectByExample(example);
		return resultList;
	}

}
