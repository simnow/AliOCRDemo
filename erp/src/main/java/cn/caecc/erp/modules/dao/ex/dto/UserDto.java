package cn.caecc.erp.modules.dao.ex.dto;

import java.io.Serializable;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import cn.caecc.erp.modules.dao.mybatis.entity.Permission;
import cn.caecc.erp.modules.dao.mybatis.entity.Role;
import cn.caecc.erp.modules.dao.mybatis.entity.User;
@JsonIgnoreProperties({ "menuList", "password" })
public class UserDto extends User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String dname;//部门名称
	
	private String pname;//职位名称
	
	private String poname;//政治面貌名称
	
	private String cname;//创建人
	
	private String uname;//更新人
	
	private String dcode; //部门编码，队号
	
	/**
	 * 角色
	 */
	private List<Role> roleList;
	
	private Map<String, Role> roleMap;
	
	/**
	 * 权限
	 */
	private List<Permission> permissionList;
	
	private Map<String, Permission> permissionMap;

	
	/**
	 * @return the dcode
	 */
	public String getDcode() {
		return dcode;
	}

	/**
	 * @param dcode the dcode to set
	 */
	public void setDcode(String dcode) {
		this.dcode = dcode;
	}

	private List<MenuDto> menuList;

	private Map<String, List<MenuDto>> functionMap;

	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getPoname() {
		return poname;
	}

	public void setPoname(String poname) {
		this.poname = poname;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}
	
	/**
	 * @return the roleList
	 */
	public List<Role> getRoleList() {
		return roleList;
	}
	/**
	 * @param roleList the roleList to set
	 */
	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}
	/**
	 * 
	 */
	public List<Permission> getPermissionList() {
		return permissionList;
	}
	/**
	 * 
	 * @param permissionList
	 */
	public void setPermissionList(List<Permission> permissionList) {
		this.permissionList = permissionList;
	}


	/**
	 * @return the functionMap
	 */
	public Map<String, List<MenuDto>> getFunctionMap() {
		return functionMap;
	}

	/**
	 * @param functionMap the functionMap to set
	 */
	public void setFunctionMap(Map<String, List<MenuDto>> functionMap) {
		this.functionMap = functionMap;
	}

	/**
	 * @return the menuList
	 */
	public List<MenuDto> getMenuList() {
		return menuList;
	}

	/**
	 * @param menuList the menuList to set
	 */
	public void setMenuList(List<MenuDto> menuList) {
		this.menuList = menuList;
	}

	/**
	 * @return the roleMap
	 */
	public Map<String, Role> getRoleMap() {
		return roleMap;
	}

	/**
	 * @param roleMap the roleMap to set
	 */
	public void setRoleMap(Map<String, Role> roleMap) {
		this.roleMap = roleMap;
	}

	/**
	 * @return the permissionMap
	 */
	public Map<String, Permission> getPermissionMap() {
		return permissionMap;
	}

	/**
	 * @param permissionMap the permissionMap to set
	 */
	public void setPermissionMap(Map<String, Permission> permissionMap) {
		this.permissionMap = permissionMap;
	}
	
	
	
}
