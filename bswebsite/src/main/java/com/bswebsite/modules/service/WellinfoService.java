package com.bswebsite.modules.service;

import java.util.List;

import com.bswebsite.modules.dao.mybatis.entity.WellInfo;

public interface WellinfoService {

	/**
	 * 插入井信息
	 * @param wellinfo
	 * @return
	 */
	public int insertWellInfo(WellInfo wellinfo);
	/*
	 * 通过id查询井信息
	 */
	public boolean deleteWellInfo(int id);
	/**
	 * 更新井信息
	 * @param wellinfo
	 * @return
	 */
	public boolean updateWellInfo(WellInfo wellinfo);
	/**
	 * 通过井id查询单井信息
	 * @param id
	 * @return
	 */
	public WellInfo selectWellInfoById(int id);
	
	/**
	 * 通过状态查询井（在钻井0，完成井1）
	 * @param state
	 * @return
	 */
	public List<WellInfo> selectWellInfoByState(int state);
	
	/**
	 * 通过id更新当前井深
	 * @param id
	 * @param depth
	 * @return
	 */
	public boolean updateWellDepthById(int id,String depth);
}
