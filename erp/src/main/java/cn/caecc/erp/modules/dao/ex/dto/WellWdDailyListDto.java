package cn.caecc.erp.modules.dao.ex.dto;

import java.util.List;

import cn.caecc.erp.modules.dao.mybatis.entity.WellWdDaily;

public class WellWdDailyListDto {

	private List<WellWdDaily> list;
	private String hbr;
	
	
	
	public List<WellWdDaily> getList() {
		return list;
	}
	public void setList(List<WellWdDaily> list) {
		this.list = list;
	}
	public String getHbr() {
		return hbr;
	}
	public void setHbr(String hbr) {
		this.hbr = hbr;
	}
	
	
}
