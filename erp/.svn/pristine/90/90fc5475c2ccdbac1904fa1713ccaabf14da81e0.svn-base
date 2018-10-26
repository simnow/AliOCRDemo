/**
 * 
 */
package cn.caecc.erp.modules.dao.ex.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.caecc.erp.modules.dao.ex.dto.UserDto;
import cn.caecc.erp.modules.dao.mybatis.entity.DepartmentRoleRelationshipKey;

/**
 * @author weizhen
 *
 */
public interface DepartmentRoleRelationshipExMapper {

	
	/**
	 * 根据用户id，连表查询用户详情及其对应的角色
	 * @param id
	 * @return
	 */
	UserDto findUserRoleRelationshipByUserId(Integer id);
	
	/**
	 * 根据部门id，查询部门角色
	 * @param id
	 * @return
	 */
	List<DepartmentRoleRelationshipKey> findDepartRoleByDepartId(Integer id);
	/**
	 * 批量插入数据
	 * @param list<DepartmentRoleRelationshipKey>
	 * @return
	 */
	int insertDMRoles(@Param("roles")List<DepartmentRoleRelationshipKey> roleList);
	/**
	 * 
	 * @param departid
	 * @return
	 */
	int deleteCanModifyDR(Map<String, Object> parameterMap);

}
