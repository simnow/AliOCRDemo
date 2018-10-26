package com.bswebsite.modules.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bswebsite.modules.dao.mybatis.entity.WellInfo;
import com.bswebsite.modules.dao.mybatis.entity.WellInfoExample;
import com.bswebsite.modules.dao.mybatis.entity.WellInfoExample.Criteria;
import com.bswebsite.modules.dao.mybatis.mapper.WellInfoMapper;
import com.bswebsite.modules.service.WellinfoService;
import com.bswebsite.support.util.DateUtil;

@Service
public class WellinfoServiceImpl implements WellinfoService{
	@Autowired
	private WellInfoMapper wellInfoMapper;
	
	@Override
	public int insertWellInfo(WellInfo wellinfo) {
		wellinfo.setState(0);//0:正在钻井
		wellinfo.setCreatetime(DateUtil.getcurrentDateTime());
		return wellInfoMapper.insert(wellinfo);
	}

	@Override
	public boolean deleteWellInfo(int id) {
		if(wellInfoMapper.deleteByPrimaryKey(id)>0){
			return true;
		}
		return false;
	}

	@Override
	public boolean updateWellInfo(WellInfo wellinfo) {
		if(wellInfoMapper.updateByPrimaryKeySelective(wellinfo)>0){
			return true;
		}
		return false;
	}

	@Override
	public WellInfo selectWellInfoById(int id) {
		return wellInfoMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<WellInfo> selectWellInfoByState(int state) {
		WellInfoExample example=new WellInfoExample();
		Criteria criteria=example.createCriteria();
		criteria.andStateEqualTo(state);
		return wellInfoMapper.selectByExample(example);
	}

	@Override
	public boolean updateWellDepthById(int id, String depth) {
		WellInfo wellInfo=new WellInfo();
		wellInfo.setId(id);
		wellInfo.setCurrentdepth(depth);
		if(wellInfoMapper.updateByPrimaryKeySelective(wellInfo)>0){
			return true;
		}
		return false;
	}

}
