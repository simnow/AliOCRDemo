package cn.caecc.erp.modules.dao.ex.mapper;

import java.util.List;
import java.util.Map;

import cn.caecc.erp.modules.dao.ex.dto.DepartmentDto;
import cn.caecc.erp.modules.dao.mybatis.entity.Department;
import cn.caecc.erp.modules.dao.mybatis.entity.User;

public interface DepartmentExMapper {
	
	List<DepartmentDto> findDeptAll();
    
    List<User> findUserBydept(int id);
    
    List<DepartmentDto> findDeptPage(Map<String,Object> params);
    
    DepartmentDto findDeptDetail(int id);
    
    Department findDeptByName(String name);
    
    List<DepartmentDto> findDeptByNameLike(String departmentName);
    
    int updateLeaderId(int id);

    List<User> findUsersByDeptName(String name);
    
    List<User> findMastersByDeptName(String name);

    List<Department> selectDeptByDto(DepartmentDto departMentDto);
    
    
    List<DepartmentDto> getDeptInTypes(List<String> types);
 }

