package cn.caecc.erp.modules.service.serviceImpl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.caecc.erp.modules.dao.ex.mapper.WellTimeExMapper;
import cn.caecc.erp.modules.dao.mybatis.entity.WellTime;
import cn.caecc.erp.modules.dao.mybatis.entity.WellTimeExample;
import cn.caecc.erp.modules.dao.mybatis.entity.WellTimeExample.Criteria;
import cn.caecc.erp.modules.dao.mybatis.mapper.WellTimeMapper;
import cn.caecc.erp.modules.service.WellTimeService;
import cn.caecc.erp.support.message.Message;
import cn.caecc.erp.support.util.DateUtil;
@Service
public class WellTimeServiceImpl implements WellTimeService {

	@Autowired
	private WellTimeMapper WellTimeMapper;
	@Autowired
	private WellTimeExMapper WellTimeExMapper;
	
	@Override
	public boolean insertWellTime(WellTime WellTime) {
		WellTime.setCreatetime(DateUtil.getcurrentDateTime());
		if(WellTimeExMapper.insertWellTime(WellTime)>0){
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteWellTime(int id) {
		if(WellTimeMapper.deleteByPrimaryKey(id)>0){
			return true;
		}
		return false;
	}

	@Override
	public WellTime getWellTimeById(int id) {
		return WellTimeMapper.selectByPrimaryKey(id);
	}

	@Override
	public Message listWellTime(String startDate, String endDate, Integer wellId, int pageNo, int pageSize) {
		PageHelper.startPage(pageNo, pageSize, "logdate desc");
		WellTimeExample example=new WellTimeExample();
		Criteria criteria=example.createCriteria();
		if(StringUtils.isNotBlank(startDate)){
			criteria.andLogdateGreaterThanOrEqualTo(DateUtil.convertStringToDate(startDate, DateUtil.YYYY_MM_DD));
		}
		if(StringUtils.isNotBlank(endDate)){
			criteria.andLogdateLessThanOrEqualTo(DateUtil.convertStringToDate(endDate, DateUtil.YYYY_MM_DD));
		}
		if(null!=wellId){
			criteria.andWellidEqualTo(wellId);
		}
		Message message=new Message();
		List<WellTime> resultlist=WellTimeMapper.selectByExample(example);
		if(resultlist!=null&&resultlist.size()>0){
			PageInfo<WellTime> pageInfo=new PageInfo<>(resultlist);
			message.setObj(pageInfo);
		}
		return message;
	}
	@Override
	public Message listWellTimeNoPage(String startDate, String endDate, Integer wellId) {
		WellTimeExample example=new WellTimeExample();
		Criteria criteria=example.createCriteria();
		example.setOrderByClause("logdate desc");
		if(StringUtils.isNotBlank(startDate)){
			criteria.andLogdateGreaterThanOrEqualTo(DateUtil.convertStringToDate(startDate, DateUtil.YYYY_MM_DD));
		}
		if(StringUtils.isNotBlank(endDate)){
			criteria.andLogdateLessThanOrEqualTo(DateUtil.convertStringToDate(endDate, DateUtil.YYYY_MM_DD));
		}
		if(null!=wellId){
			criteria.andWellidEqualTo(wellId);
		}
		Message message=new Message();
		List<WellTime> resultlist=WellTimeMapper.selectByExample(example);
		if(resultlist!=null&&resultlist.size()>0){
			message.setObj(resultlist);
		}
		return message;
	}

	@Override
	public boolean updateWellTime(WellTime WellTime) {
		if(WellTimeMapper.updateByPrimaryKeySelective(WellTime)>0){
			return true;
		}
		return false;
	}
	
	@Override
	public WellTime getOneDay(Date date,int wellId) {
		WellTimeExample example=new WellTimeExample();
		Criteria criteria=example.createCriteria();
		criteria.andLogdateEqualTo(date);
		criteria.andWellidEqualTo(wellId);
		List<WellTime> list=WellTimeMapper.selectByExample(example);
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}

}
