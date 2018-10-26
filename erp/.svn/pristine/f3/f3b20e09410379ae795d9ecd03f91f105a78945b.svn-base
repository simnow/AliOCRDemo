package cn.caecc.erp.modules.service;

import java.util.List;
import com.github.pagehelper.PageInfo;
import cn.caecc.erp.modules.dao.ex.dto.DepartmentDto;
import cn.caecc.erp.modules.dao.mybatis.entity.Department;
import cn.caecc.erp.modules.dao.mybatis.entity.User;

public interface DeptmentService {

	public DepartmentDto getDeptById(int id);

	public List<Department> getDeptList();
	
	public List<Department> getSelectDeptList();

	public int deleteDeptById(int id);

	public int updateDeptById(Department departMent);

	public int addDept(Department departMent);

	public List<DepartmentDto> getDeptAll();

	public List<DepartmentDto> getDeptInTypes(List<String> types);

	public PageInfo<DepartmentDto> getPageList(int page, int size, int id);

	public PageInfo<DepartmentDto> getDeptByNameLike(int pageNo, int pageSize, String departmentName);

	/**
	 * 根据部门名称获取该部门所有员工
	 * 
	 * @param departmentName
	 * @return
	 */
	public List<User> getDepartmentUsers(String departmentName);

	/**
	 * 根据部门名称获取该部门所有领导层
	 * 
	 * @param departmentName
	 * @return
	 */
	public List<User> getDepartmentMasters(String departmentName);

}
