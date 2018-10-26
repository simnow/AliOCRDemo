package cn.caecc.erp.modules.service.serviceImpl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.caecc.erp.modules.dao.ex.mapper.WellLogExMapper;
import cn.caecc.erp.modules.dao.mybatis.entity.WellLog;
import cn.caecc.erp.modules.dao.mybatis.entity.WellLogExample;
import cn.caecc.erp.modules.dao.mybatis.entity.WellLogExample.Criteria;
import cn.caecc.erp.modules.dao.mybatis.mapper.WellLogMapper;
import cn.caecc.erp.modules.service.WellLogService;
import cn.caecc.erp.support.message.Message;
import cn.caecc.erp.support.util.DateUtil;
@Service
public class WellLogServiceImpl implements WellLogService{

	@Autowired
	private WellLogMapper wellLogMapper;
	@Autowired
	private WellLogExMapper wellLogExMapper;
	
	@Override
	public int insertWellLog(WellLog welllog) {
		welllog.setCreatetime(DateUtil.getcurrentDateTime());
		int result=wellLogExMapper.inserWellLog(welllog);
		if(result>0){
			return result;
		}
		return 0;
	}

	@Override
	public boolean deleteById(int id) {
		if(wellLogMapper.deleteByPrimaryKey(id)>0){
			return true;
		}
		return false;
	}

	@Override
	public boolean updateWellLog(WellLog welllog) {
		if(wellLogMapper.updateByPrimaryKeySelective(welllog)>0){
			return true;
		}
		return false;
	}

	@Override
	public WellLog getOneDay(Date date,int wellId) {
		WellLogExample example=new WellLogExample();
		Criteria criteria=example.createCriteria();
		criteria.andLogdateEqualTo(date);
		criteria.andWellidEqualTo(wellId);
		List<WellLog> list=wellLogMapper.selectByExample(example);
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public Message getWellLogList(String startdate,String endDate, Integer wellId, int pageNo, int pageSize) {
		PageHelper.startPage(pageNo, pageSize, "logdate desc");
		WellLogExample example=new WellLogExample();
		Criteria criteria=example.createCriteria();
		if(StringUtils.isNotBlank(startdate)){
			criteria.andLogdateGreaterThanOrEqualTo(DateUtil.convertStringToDate(startdate, DateUtil.YYYY_MM_DD));
		}
		if(StringUtils.isNotBlank(endDate)){
			criteria.andLogdateLessThanOrEqualTo(DateUtil.convertStringToDate(endDate, DateUtil.YYYY_MM_DD));
		}
		if(null!=wellId){
			criteria.andWellidEqualTo(wellId);
		}
		List<WellLog> list=wellLogMapper.selectByExample(example);
		Message message=new Message();
		if(list!=null&&list.size()>0){
			PageInfo<WellLog> pageInfo = new PageInfo<>(list);
			message.setObj(pageInfo);
		}
		return message;
	}
	@Override
	public Message getWellLogListNoPage(String startdate,String endDate, Integer wellId) {
		WellLogExample example=new WellLogExample();
		Criteria criteria=example.createCriteria();
		example.setOrderByClause("logdate desc");
		if(StringUtils.isNotBlank(startdate)){
			criteria.andLogdateGreaterThanOrEqualTo(DateUtil.convertStringToDate(startdate, DateUtil.YYYY_MM_DD));
		}
		if(StringUtils.isNotBlank(endDate)){
			criteria.andLogdateLessThanOrEqualTo(DateUtil.convertStringToDate(endDate, DateUtil.YYYY_MM_DD));
		}
		if(null!=wellId){
			criteria.andWellidEqualTo(wellId);
		}
		List<WellLog> list=wellLogMapper.selectByExample(example);
		Message message=new Message();
		if(list!=null&&list.size()>0){
			message.setObj(list);
		}
		return message;
	}

	@Override
	public WellLog getWellInfoById(int id) {
		return wellLogMapper.selectByPrimaryKey(id);
	}

	@Override
	public void deleteByWellIdDate(Integer wellid, Date logdate) {
		WellLogExample example=new WellLogExample();
		Criteria criteria=example.createCriteria();
		criteria.andWellidEqualTo(wellid);
		criteria.andLogdateEqualTo(logdate);
		wellLogMapper.deleteByExample(example);
	}

	@Override
	public WellLog getGreaterThanOrEqualTo(Date logday,int wellId) {
		WellLogExample example=new WellLogExample();
		Criteria criteria=example.createCriteria();
		criteria.andLogdateGreaterThanOrEqualTo(logday);
		criteria.andWellidEqualTo(wellId);
		example.setOrderByClause("LogDate asc");
		List<WellLog> list=wellLogMapper.selectByExample(example);
		if(null!=list&&list.size()>0){
			return list.get(0);
		}
		return null;
	}
	@Override
	public WellLog getLessThanOrEqualTo(Date logday,int wellId) {
		WellLogExample example=new WellLogExample();
		Criteria criteria=example.createCriteria();
		criteria.andLogdateLessThanOrEqualTo(logday);
		criteria.andWellidEqualTo(wellId);
		example.setOrderByClause("LogDate desc");
		List<WellLog> list=wellLogMapper.selectByExample(example);
		if(null!=list&&list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public boolean isExitDaily(int wellId) {
		WellLogExample example=new WellLogExample();
		Criteria criteria=example.createCriteria();
		criteria.andWellidEqualTo(wellId);
		List<WellLog> list=wellLogMapper.selectByExample(example);
		if(null!=list&&list.size()>0){
			return true;
		}
		return false;
	}

}
