/**
 * 
 */
package cn.caecc.erp.modules.dao.ex.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.caecc.erp.modules.dao.ex.dto.UserDto;
import cn.caecc.erp.modules.dao.mybatis.entity.UserRoleRelationshipKey;

/**
 * @author weizhen
 *
 */
public interface UserRoleRelationshipExMapper {

	
	/**
	 * 根据用户id，连表查询用户详情及其对应的角色
	 * @param id
	 * @return
	 */
	UserDto findUserRoleRelationshipByUserId(Integer id);
	/**
	 * 批量插入
	 * @param uid, rid
	 * @return
	 */
	int batchAdd(Map<String, Object> params);
	
	/**
	 * 新增用户角色
	 * @param urKey
	 * @return
	 */
	int add(UserRoleRelationshipKey urKey);
	
	
	/**
	 * 根据用户id，查询用户对应的角色
	 * @param id
	 * @return
	 */
	List<UserRoleRelationshipKey> findUserRoleByUserId(Integer id);
	/**
	 * 批量插入数据
	 * @param list<UserRoleRelationshipKey>
	 * @return
	 */
	int insertRoles(@Param("roles")List<UserRoleRelationshipKey> roleList);
	/**
	 * 根据userid删除相应的关联
	 * @param userid
	 * @return
	 */
	int deleteCanModifyUR(Map<String, Object> parameterMap);
	
}
