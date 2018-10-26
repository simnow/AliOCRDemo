package cn.caecc.erp.modules.service;

import java.util.List;
import java.util.Map;

import cn.caecc.erp.modules.dao.mybatis.entity.Department;
import cn.caecc.erp.modules.dao.mybatis.entity.WellInfo;
import cn.caecc.erp.support.message.Message;

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
	public boolean updateWellDepthById(int id,Double depth);
	/**
	 * 
	 * @param state
	 * @param wellcode
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	Message selectWellInfoListByState(int state, Integer wellId,int pageNo, int pageSize);
	/**
	 * 查询井队列表
	 * @return
	 */
	public List<Department> selectDidList();
	
	/**
	 * 通过井队获取正在钻井的定额（材料定额、润滑油定额）
	 * @param did
	 * @param state(0:正在钻井)
	 * @return
	 */
	public Map<String,Object> getWellInfoDe(int did,int state);
	List<WellInfo> getWellInfoBydid(Integer did);
}
