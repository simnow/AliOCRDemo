package com.bswebsite.modules.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bswebsite.modules.dao.mybatis.entity.Person;
import com.bswebsite.modules.dao.mybatis.entity.PersonExample;
import com.bswebsite.modules.dao.mybatis.entity.PersonExample.Criteria;
import com.bswebsite.modules.dao.mybatis.mapper.PersonMapper;
import com.bswebsite.modules.service.PersonService;
import com.github.pagehelper.PageHelper;
@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonMapper persionMapper;
	@Override
	public List<Person> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String,Object> getPersionInfoByName(String name,int pageNo,int pageSize) {
		Map<String,Object> map=new HashMap<>();
		map.put("persontotal", 0);
		PersonExample example=new PersonExample();
		Criteria criteria=example.createCriteria();
		name = "%" + name + "%";  
		criteria.andNameLike(name);
		List<Person> result=persionMapper.selectByExample(example);
		if(result!=null&&result.size()>0){
			map.put("persontotal", result.size());
			PageHelper.startPage(pageNo, pageSize);
			List<Person> result2=persionMapper.selectByExample(example);
			if(result2!=null&&result2.size()>0){
				map.put("person", result2);
			}
		}
		return map;
	}
	@Override
	public Map<String,Object> getPersionInfoByName(String name) {
		Map<String,Object> map=new HashMap<>();
		map.put("persontotal", 0);
		PersonExample example=new PersonExample();
		Criteria criteria=example.createCriteria();
		name = "%" + name + "%";  
		criteria.andNameLike(name);
		List<Person> result=persionMapper.selectByExample(example);
		if(result!=null&&result.size()>0){
			map.put("persontotal", result.size());
			map.put("person", result);
		}
		return map;
	}
	@Override
	public Map<String,Object> getAllByType(String type, int pageNo, int pageSize) {
		Map<String,Object> map=new HashMap<>();
		map.put("persontotal", 0);
		PersonExample example=new PersonExample();
		Criteria criteria=example.createCriteria();
		criteria.andTypeEqualTo(type);
		example.setOrderByClause("ordernum");
		List<Person> result=persionMapper.selectByExample(example);
		if(result!=null&&result.size()>0){
			map.put("persontotal", result.size());
			PageHelper.startPage(pageNo, pageSize);
			List<Person> result2=persionMapper.selectByExample(example);
			if(result2!=null&&result.size()>0){
				map.put("person", result2);
			}
		}
		return map;
	}

}
