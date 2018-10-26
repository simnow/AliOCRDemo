package com.partymasses.modules.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.partymasses.modules.dao.dto.BaseAreaDto;
import com.partymasses.modules.dao.mybatis.entity.BaseArea;
import com.partymasses.modules.dao.mybatis.entity.BaseAreaExample;
import com.partymasses.modules.dao.mybatis.entity.BaseAreaExample.Criteria;
import com.partymasses.modules.dao.mybatis.mapper.BaseAreaMapper;
import com.partymasses.modules.service.BaseAreaService;
import com.partymasses.support.message.Message;
@Service
public class BaseAreaServiceImpl implements BaseAreaService{
	@Autowired
	private BaseAreaMapper baseAreaMapper;
	@Override
	public Message getAreaList(String gid) {
		Message message=new Message();
		List<BaseAreaDto> areaList=new ArrayList<BaseAreaDto>();
		if(gid.equals("all")){
			areaList=baseAreaMapper.getAreaListALL();
		}
		else{
			areaList=baseAreaMapper.getArealistById(gid);
		}
		message.setObj(areaList);
		return message;
		
	}
	@Override
	public Message getAreabylevel() {
		// TODO Auto-generated method stub
		Message message=new Message();
		BaseAreaExample baseAreaExample=new BaseAreaExample();
		Criteria criteria=baseAreaExample.createCriteria();
		criteria.andGLevelEqualTo("00");
		List<BaseArea> resultList=baseAreaMapper.selectByExample(baseAreaExample);
		message.setObj(resultList);
		return  message;
	}
	@Override
	public Message getAreabyId(String id) {
		// TODO Auto-generated method stub
		Message message=new Message();
		BaseAreaExample baseAreaExample=new BaseAreaExample();
		Criteria criteria=baseAreaExample.createCriteria();
		criteria.andPGidEqualTo(id);
		List<BaseArea> resultList=baseAreaMapper.selectByExample(baseAreaExample);
		message.setObj(resultList);
		return  message;
	}
}
