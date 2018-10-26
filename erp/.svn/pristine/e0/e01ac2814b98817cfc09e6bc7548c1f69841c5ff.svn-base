package cn.caecc.erp.modules.service;

import java.util.List;

import cn.caecc.erp.modules.dao.mybatis.entity.WellDxInfo;
import cn.caecc.erp.support.message.Message;

public interface DxWellInfoService {

	/**
	 * 插入井信息
	 * @param dxWellinfo
	 * @return
	 */
	public int insertDxWellInfo(WellDxInfo dxWellinfo);
	/*
	 * 通过id查询井信息
	 */
	public boolean deleteDxWellInfo(int id);
	/**
	 * 更新井信息
	 * @param dxWellinfo
	 * @return
	 */
	public boolean updateDxWellInfo(WellDxInfo dxWellinfo);
	/**
	 * 通过井id查询单井信息
	 * @param id
	 * @return
	 */
	public WellDxInfo selectDxWellInfoById(int id);
	
	/**
	 * 通过状态查询井（在钻井0，完成井1）
	 * @param state
	 * @return
	 */
	public List<WellDxInfo> selectDxWellInfoByState(int state);
	
	/**
	 * 通过id更新当前井深
	 * @param id
	 * @param depth
	 * @return
	 */
	public boolean updateDxWellDepthById(int id,Double depth);
	/**
	 * 
	 * @param state
	 * @param wellcode
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	Message selectDxWellInfoListByState(int state, Integer wellId,int pageNo, int pageSize);
}
