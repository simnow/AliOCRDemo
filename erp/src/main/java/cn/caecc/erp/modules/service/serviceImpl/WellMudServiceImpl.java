package cn.caecc.erp.modules.service.serviceImpl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.caecc.erp.modules.dao.ex.mapper.WellMudExMapper;
import cn.caecc.erp.modules.dao.mybatis.entity.WellMud;
import cn.caecc.erp.modules.dao.mybatis.entity.WellMudExample;
import cn.caecc.erp.modules.dao.mybatis.entity.WellMudExample.Criteria;
import cn.caecc.erp.modules.dao.mybatis.mapper.WellMudMapper;
import cn.caecc.erp.modules.service.WellMudService;
import cn.caecc.erp.support.message.Message;
import cn.caecc.erp.support.util.DateUtil;
@Service
public class WellMudServiceImpl implements WellMudService {

	@Autowired
	private WellMudMapper wellMudMapper;
	@Autowired
	private WellMudExMapper wellMudExMapper;
	
	@Override
	public int insertWellMud(WellMud wellMud) {
		wellMud.setCreatetime(DateUtil.getcurrentDateTime());
		int result=wellMudExMapper.inserWellMud(wellMud);
		if(result>0){
			return result;
		}
		return 0;
	}

	@Override
	public boolean updateWellMud(WellMud wellMud) {
		if(wellMudMapper.updateByPrimaryKeySelective(wellMud)>0){
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteById(int id) {
		if(wellMudMapper.deleteByPrimaryKey(id)>0){
			return true;
		}
		return false;
	}

	@Override
	public WellMud selectById(int id) {
		return wellMudMapper.selectByPrimaryKey(id);
	}

	@Override
	public Message getWellMudList(String startdate, String endDate, Integer wellId, int pageNo, int pageSize) {
		PageHelper.startPage(pageNo, pageSize, "logdate desc");
		WellMudExample example=new WellMudExample();
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
		Message message=new Message();
		List<WellMud> result=wellMudMapper.selectByExample(example);
		if(result!=null&&result.size()>0){
			PageInfo<WellMud> pageinfo=new PageInfo<>(result);
			message.setObj(pageinfo);
		}
		return message;
	}
	@Override
	public Message getWellMudListNoPage(String startdate, String endDate, Integer wellId) {
		WellMudExample example=new WellMudExample();
		Criteria criteria=example.createCriteria();
		example.setOrderByClause("Logdate desc");
		if(StringUtils.isNotBlank(startdate)){
			criteria.andLogdateGreaterThanOrEqualTo(DateUtil.convertStringToDate(startdate, DateUtil.YYYY_MM_DD));
		}
		if(StringUtils.isNotBlank(endDate)){
			criteria.andLogdateLessThanOrEqualTo(DateUtil.convertStringToDate(endDate, DateUtil.YYYY_MM_DD));
		}
		if(null!=wellId){
			criteria.andWellidEqualTo(wellId);
		}
		Message message=new Message();
		List<WellMud> result=wellMudMapper.selectByExample(example);
		if(result!=null&&result.size()>0){
			message.setObj(result);
		}
		return message;
	}

	@Override
	public void deleteByWellIdDate(Integer wellid, Date logdate) {
		WellMudExample example=new WellMudExample();
		Criteria criteria=example.createCriteria();
		criteria.andLogdateEqualTo(logdate);
		criteria.andWellidEqualTo(wellid);
		wellMudMapper.deleteByExample(example);
	}
	
	@Override
	public WellMud getOneDay(Date date,int wellId) {
		WellMudExample example=new WellMudExample();
		Criteria criteria=example.createCriteria();
		criteria.andLogdateEqualTo(date);
		criteria.andWellidEqualTo(wellId);
		List<WellMud> list=wellMudMapper.selectByExample(example);
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public boolean updateWellPowerByWellIdAndDate(WellMud wellMud) {
		WellMudExample example=new WellMudExample();
		Criteria criteria=example.createCriteria();
		criteria.andLogdateEqualTo(wellMud.getLogdate());
		criteria.andWellidEqualTo(wellMud.getWellid());
		if(wellMudMapper.updateByExampleSelective(wellMud, example)>0){
			return true;
		}
		return false;
	}

}
