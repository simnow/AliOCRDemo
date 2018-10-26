package cn.caecc.erp.modules.dao.ex.dto;

import java.util.List;

import cn.caecc.erp.modules.dao.mybatis.entity.OfficesupplyActivitiApply;
import cn.caecc.erp.modules.dao.mybatis.entity.OfficesupplyGoodsRelationshipActivitiApply;

public class OfficesupplyActivitiApplyExDto extends OfficesupplyActivitiApply {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<OfficesupplyGoodsRelationshipActivitiApply> list;

	public List<OfficesupplyGoodsRelationshipActivitiApply> getList() {
		return list;
	}
	
	public void setList(List<OfficesupplyGoodsRelationshipActivitiApply> list) {
		this.list = list;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
