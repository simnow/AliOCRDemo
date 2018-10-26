package cn.caecc.erp.modules.dao.ex.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.caecc.erp.modules.dao.ex.dto.UserDto;
import cn.caecc.erp.modules.dao.mybatis.entity.User;

public interface UserExMapper {

	List<UserDto> findUserList(UserDto userDto);
	
	List<UserDto> findByNameLike(String userName);
	
	int insertUsers(@Param("users")List<User> userList); 
		
	List<User> getDeptAusers(Map<String, Object> params);
	
	int cleanDept(int deptid);
	
	List<User> getLeaderByUser(Map<String, Object> params);
	
	List<User> findManagerLeader(Map<String, Object> params);
	/**
	 * 通过用户ID查询用户部门详细
	 * **/
	UserDto findById(int id);
}
