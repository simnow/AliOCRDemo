package cn.caecc.erp.modules.service;

import java.util.Date;
import cn.caecc.erp.modules.dao.mybatis.entity.WellLog;
import cn.caecc.erp.support.message.Message;

public interface WellLogService {

	/**
	 * 添加
	 * @param welllog
	 * @return
	 */
	public int insertWellLog(WellLog welllog);
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	public boolean deleteById(int id);
	
	/**
	 * 修改
	 * @param welllog
	 * @return
	 */
	public boolean updateWellLog(WellLog welllog);
	
	/**
	 * 获取某一天的钻井信息
	 * @param date
	 * @return
	 */
	public WellLog getOneDay(Date date,int wellId);
	
	/**
	 * 
	 * @param startdate
	 * @param endDate
	 * @param wellId
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Message getWellLogList(String startdate,String endDate, Integer wellId, int pageNo, int pageSize);

	/**
	 * 当个日报详情
	 * @param id
	 * @return
	 */
	public WellLog getWellInfoById(int id);
	
	public void deleteByWellIdDate(Integer wellid, Date logdate);

	public WellLog getGreaterThanOrEqualTo(Date day,int wellId);

	public WellLog getLessThanOrEqualTo(Date logday,int wellId);

	public boolean isExitDaily(int id);

	Message getWellLogListNoPage(String startdate, String endDate, Integer wellId);
}
