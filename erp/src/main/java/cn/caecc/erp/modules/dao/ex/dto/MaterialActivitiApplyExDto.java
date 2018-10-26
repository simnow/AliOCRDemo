package cn.caecc.erp.modules.dao.ex.dto;

import java.util.List;

import cn.caecc.erp.modules.dao.mybatis.entity.MaterialActivitiApply;
import cn.caecc.erp.modules.dao.mybatis.entity.MaterialGoodsRelationshipActivitiApply;

public class MaterialActivitiApplyExDto extends MaterialActivitiApply {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<MaterialGoodsRelationshipActivitiApply> list;

	public List<MaterialGoodsRelationshipActivitiApply> getList() {
		return list;
	}

	public void setList(List<MaterialGoodsRelationshipActivitiApply> list) {
		this.list = list;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
