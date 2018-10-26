package com.bswebsite.modules.service;

import java.util.List;
import java.util.Map;

import com.bswebsite.modules.dao.mybatis.entity.Person;

public interface PersonService {

	/**
	 * 获取所有，通过ordernum排序
	 * @return
	 */
	public List<Person> getAll();
	
	/**
	 * 通过名字查询详细信息
	 */
	public Map<String,Object> getPersionInfoByName(String name,int pageNo,int pageSize);

	public Map<String,Object> getAllByType(String type, int pageNo, int pageSize);

	Map<String, Object> getPersionInfoByName(String name);
}
