package cn.caecc.erp.modules.service.serviceImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.caecc.erp.modules.dao.mybatis.entity.WellBit;
import cn.caecc.erp.modules.dao.mybatis.entity.WellBitExample;
import cn.caecc.erp.modules.dao.mybatis.entity.WellBitExample.Criteria;
import cn.caecc.erp.modules.dao.mybatis.mapper.WellBitMapper;
import cn.caecc.erp.modules.service.WellBitService;
import cn.caecc.erp.support.message.Message;
import cn.caecc.erp.support.util.DateUtil;
@Service
public class WellBitServiceImpl implements WellBitService {
	@Autowired
	private WellBitMapper wellBitMapper;
	
	@Override
	public int insertWellBit(WellBit wellBit) {
		wellBit.setCreatetime(DateUtil.getcurrentDateTime());
		return wellBitMapper.insertSelective(wellBit);
	}

	@Override
	public boolean deleteWellBit(int id) {
		if(wellBitMapper.deleteByPrimaryKey(id)>0){
			return true;
		}
		return false;
	}

	@Override
	public WellBit getWellBitById(int id) {
		return wellBitMapper.selectByPrimaryKey(id);
	}

	@Override
	public Message listWellBit(int wellId) {
		Message message=new Message();
		WellBitExample example=new WellBitExample();
		Criteria criteria=example.createCriteria();
		criteria.andWellidEqualTo(wellId);
		example.setOrderByClause("CreateTime desc");
		List<WellBit> wellBitList=wellBitMapper.selectByExample(example);
		message.setObj(wellBitList);
		return message;
	}
	@Override
	public Message listWellBitByWellId(int wellId,int pageNo,int pageSize) {
		PageHelper.startPage(pageNo, pageSize);
		Message message=new Message();
		WellBitExample example=new WellBitExample();
		Criteria criteria=example.createCriteria();
		criteria.andWellidEqualTo(wellId);
		example.setOrderByClause("CreateTime desc");
		List<WellBit> wellBitList=wellBitMapper.selectByExample(example);
		PageInfo<WellBit> pageList=new PageInfo<>(wellBitList);
		message.setObj(pageList);
		return message;
	}

	@Override
	public boolean updateWellBit(WellBit wellBit) {
		if(wellBitMapper.updateByPrimaryKeySelective(wellBit)>0){
			return true;
		}
		return false;
	}

	@Override
	public WellBit getOneDay(Date date,int wellId) {
		WellBitExample example=new WellBitExample();
		Criteria criteria=example.createCriteria();
		criteria.andWellidEqualTo(wellId);
		example.setOrderByClause("CreateTime desc");
		List<WellBit> list=wellBitMapper.selectByExample(example);
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}
}
