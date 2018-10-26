package cn.caecc.erp.modules.service.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.caecc.erp.modules.dao.ex.dto.DepartmentDto;
import cn.caecc.erp.modules.dao.ex.mapper.DepartmentExMapper;
import cn.caecc.erp.modules.dao.mybatis.entity.Department;
import cn.caecc.erp.modules.dao.mybatis.entity.DepartmentExample;
import cn.caecc.erp.modules.dao.mybatis.entity.DepartmentExample.Criteria;
import cn.caecc.erp.modules.dao.mybatis.entity.Role;
import cn.caecc.erp.modules.dao.mybatis.entity.User;
import cn.caecc.erp.modules.dao.mybatis.entity.UserRoleRelationshipKey;
import cn.caecc.erp.modules.dao.mybatis.mapper.DepartmentMapper;
import cn.caecc.erp.modules.dao.mybatis.mapper.UserRoleRelationshipMapper;
import cn.caecc.erp.modules.service.DeptmentService;
import cn.caecc.erp.modules.service.RoleService;
import cn.caecc.erp.modules.service.UserRoleRelationshipService;
import cn.caecc.erp.support.constant.Contants;
import cn.caecc.erp.support.exception.CommonException;
import cn.caecc.erp.support.util.DateUtil;

@Service
public class DeptmentServiceImpl implements DeptmentService {

	@Autowired
	private DepartmentMapper departMentMapper;
	@Autowired
	private DepartmentExMapper departMentExMapper;
	@Autowired
	private UserRoleRelationshipMapper urRelationMapper;
	@Autowired
	private RoleService roleService;
	@Autowired
	private UserRoleRelationshipService urService;
//	@Autowired
//	private RedisService redisService;
	@Override
	public DepartmentDto getDeptById(int id) {
		// TODO Auto-generated method stub
		return departMentExMapper.findDeptDetail(id);
	}

	@Override
	public List<Department> getDeptList() {
		// TODO Auto-generated method stub
		List<Department> resultList = departMentMapper.selectByExample(null);
		return resultList;
	}
	
	@Override
	public List<Department> getSelectDeptList() {
		List<Department> departmentList = getDeptList();
		List<Department> resultList = new ArrayList<Department>();
		for (Department department : departmentList) {
			Department selectDepartment = new Department();
			selectDepartment.setId(department.getId());
			selectDepartment.setName(department.getName());
			resultList.add(selectDepartment);
		}
		return resultList;
	}

	@Override
	public int deleteDeptById(int id) {
		// TODO Auto-generated method stub

		DepartmentExample departMentExample = new DepartmentExample();
		Criteria criteria = departMentExample.createCriteria();
		criteria.andPidEqualTo(id);
		List<Department> pidList = departMentMapper.selectByExample(departMentExample);
		if (pidList != null && pidList.size() > 0) {

			throw new CommonException("存在下属部门，无法进行删除操作");
		}
		List<User> userList = departMentExMapper.findUserBydept(id);
		if (userList.size() > 0 && userList.get(0) != null) {
			throw new CommonException("部门下存在员工，无法进行删除操作");
		}
		int result = departMentMapper.deleteByPrimaryKey(id);
		if (result == 0) {
			throw new CommonException("未找到相关记录，或记录已删除");
		}
		return result;
	}
	
	@Override
	public PageInfo<DepartmentDto> getDeptByNameLike(int pageNo, int pageSize, String departmentName) {
		PageHelper.startPage(pageNo, pageSize);
		List<DepartmentDto> pageList = departMentExMapper.findDeptByNameLike(departmentName);
		PageInfo<DepartmentDto> pageInfo = new PageInfo<>(pageList);
		return pageInfo;
	}
	
	@Override
	public int updateDeptById(Department departMent) {
		// TODO Auto-generated method stub
		// 查询数据原部门
		Department exitsDpet = departMentMapper.selectByPrimaryKey(departMent.getId());
		//判断是否有上级部门
		if(departMent.getPid()==null){
			departMent.setGrade(1);
		}
		else{
		//获取原部门等级
		DepartmentExample departMentExample=new DepartmentExample();
		Criteria criteria=departMentExample.createCriteria();
		criteria.andIdEqualTo(departMent.getPid());
		List<Department> pidDept=departMentMapper.selectByExample(departMentExample);
		departMent.setGrade(pidDept.get(0).getGrade()+1);
		}
		// 修改原部门数据
		Integer loginUserId = (Integer) SecurityUtils.getSubject().getSession().getAttribute(Contants.LOGINUSERID);
		departMent.setUpdatetime(DateUtil.getcurrentDateTime());
		departMent.setUpdateuserid(loginUserId);
		int result = departMentMapper.updateByPrimaryKey(departMent);
		if (result > 0) {

			// 未进行原存在领导人而现未存在领导人的判断
			if (departMent.getLeaderid() == null) {
				return result;
			} else {
				// 查询角色ID 传入领导人常量 如果与原来相等不进行任何操作
				if (exitsDpet.getLeaderid() != null && exitsDpet.getLeaderid() == departMent.getLeaderid()) {
					return result;
				}
				// 查询角色ID
				List<Role> roleList = roleService.getRoleByName(Contants.LEADERROLE);
				int roleid = roleList.get(0).getId();
				// 创建变量实体
				UserRoleRelationshipKey urRelation = new UserRoleRelationshipKey();
				urRelation.setId(departMent.getLeaderid());
				// 设置角色ID
				urRelation.setRid(roleid);
				//清除原有副职角色
				List<Role> enputyList = roleService.getRoleByName(Contants.EPUTYROLE);
				urService.deleteUrBynd(departMent.getLeaderid(),enputyList.get(0).getId());
				// 第一次变更部门领导
				if (exitsDpet.getLeaderid() == null) {
					urRelationMapper.insert(urRelation);
				}
				// 变更已存部门领导
				if (exitsDpet.getLeaderid() != null && exitsDpet.getLeaderid() != departMent.getLeaderid()) {
					// 删除已存在的领导关系
					urService.deleteUrBynd(exitsDpet.getLeaderid(), roleid);
					urRelationMapper.insert(urRelation);
				}
			}
		}
		return result;
	}

	@Override
	public int addDept(Department departMent) {
		// TODO Auto-generated method stub
		Integer loginUserId = (Integer) SecurityUtils.getSubject().getSession().getAttribute(Contants.LOGINUSERID);

		departMent.setCreatetime(DateUtil.getcurrentDateTime());
		departMent.setCreateuserid(loginUserId);
		int result = departMentMapper.insertSelective(departMent);
		return result;
	}

	@Override
	public List<DepartmentDto>  getDeptAll() {
		// TODO Auto-generated method stub
		//String redisKey = Contants.REDIS_TEMP_DEPARTMENT_TREE;
		List<DepartmentDto> resultList = null;
		resultList = departMentExMapper.findDeptAll();

		/*
		try {
			resultList = (List<DepartmentDto>)redisService.get(redisKey);
		} catch (Exception ex) {
			redisService.del(redisKey);
		}
		if (resultList == null) {
			resultList = departMentExMapper.findDeptAll();
			redisService.set(redisKey, resultList, Contants.REDIS_TEMP_EXPIRE_TIME);
		}*/
		return resultList;
	}

	public PageInfo<DepartmentDto> getPageList(int pageNo, int pageSize, int id) {
		if (pageNo == 0) {
			throw new CommonException("请选中要查询页数");
		}
		if (pageSize == 0) {
			throw new CommonException("页数大小不能为0");
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		PageHelper.orderBy("CreateTime DESC");
		// TODO Auto-generated method stub
		PageHelper.startPage(pageNo, pageSize);
		List<DepartmentDto> pageList = departMentExMapper.findDeptPage(params);
		PageInfo<DepartmentDto> pageInfo = new PageInfo<>(pageList);
		return pageInfo;

	}

	@Override
	public List<User> getDepartmentUsers(String departmentName) {
		return departMentExMapper.findUsersByDeptName(departmentName);
	}

	@Override
	public List<User> getDepartmentMasters(String departmentName) {
		return departMentExMapper.findMastersByDeptName(departmentName);
	}
	
	public  List<DepartmentDto>   getDeptInTypes(List<String> types) {
		return departMentExMapper.getDeptInTypes(types);


	}


}
