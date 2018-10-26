package cn.caecc.erp.modules.service;

import java.util.List;
import java.util.Map;

import cn.caecc.erp.modules.dao.ex.dto.InputRole;
import cn.caecc.erp.modules.dao.ex.dto.OutputRole;
import cn.caecc.erp.modules.dao.ex.dto.UserDto;
import cn.caecc.erp.modules.dao.mybatis.entity.UserRoleRelationshipKey;


public interface UserRoleRelationshipService {

//	public List<UserRoleRelationshipKey> findRelationsByUserId(int userId);
	/**
	 * 查询用户拥有的角色名
	 * @param userId
	 * @return
	 */
	public UserDto findUrByUserId(int userId);
	
	/**
	 * 删除用户角色
	 * @param userId
	 * @return
	 */
	public int deleteUrBynd(int userid,int roleid);
	
	/**
	 * 批量新增角色
	 * @param userId
	 * @return
	 */
	public int  batchAdd(Map<String,Object> params);
	
	/**
	 * 新增用户角色
	 * @param urKey
	 * @return
	 */
	public int add(UserRoleRelationshipKey urKey);
	
	
	public List<OutputRole> getUserCanmodifyOutputRole(int userId);
	
	
	public boolean modifyUserRoles(InputRole InputRole);
	
}
