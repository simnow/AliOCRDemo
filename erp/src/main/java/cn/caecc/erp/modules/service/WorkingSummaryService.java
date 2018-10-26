package cn.caecc.erp.modules.service;

import com.github.pagehelper.PageInfo;

import cn.caecc.erp.modules.dao.ex.dto.WorkingSummaryDto;
import cn.caecc.erp.modules.dao.mybatis.entity.WorkingSummary;

public interface WorkingSummaryService {
	
	public  WorkingSummary  addWorkingSummary(WorkingSummary workingSummary) ;
	
	public  WorkingSummary  updateWorkingSummary(WorkingSummary workingSummary);
	
	public  int  deleteWorkingSummary(int id);
	
	public  WorkingSummaryDto  getWorkingSummaryById(int id);	
	
	public  PageInfo<WorkingSummaryDto> getTodayWorkingSummary(int pagesize,int pageno);
	
	public  PageInfo<WorkingSummaryDto> getFourWorkingSummary(int pagesize,int pageno);
	
	

}
