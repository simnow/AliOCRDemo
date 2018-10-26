package cn.caecc.erp.modules.dao.ex.dto;

import java.io.Serializable;
import java.util.List;

public class InputRole implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*部门或者用户id值*/
	private Integer id;
	private List<Integer>roleIds;
	public InputRole(Integer id, List<Integer> roleIds) {
		super();
		this.id = id;
		this.roleIds = roleIds;
	}
	public InputRole() {
		super();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public List<Integer> getRoleIds() {
		return roleIds;
	}
	public void setRoleIds(List<Integer> roleIds) {
		this.roleIds = roleIds;
	}
	
}
