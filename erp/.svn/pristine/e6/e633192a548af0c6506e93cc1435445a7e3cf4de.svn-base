package cn.caecc.erp.modules.service.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.caecc.erp.modules.dao.ex.dto.InputRole;
import cn.caecc.erp.modules.dao.ex.dto.OutputRole;
import cn.caecc.erp.modules.dao.ex.mapper.DepartmentRoleRelationshipExMapper;
import cn.caecc.erp.modules.dao.mybatis.entity.DepartmentRoleRelationshipKey;
import cn.caecc.erp.modules.dao.mybatis.entity.Role;
import cn.caecc.erp.modules.service.DepartmentRoleRelationshipService;
import cn.caecc.erp.modules.service.RoleService;
@Service
public class DepartmentRoleRelationshipServiceImpl implements DepartmentRoleRelationshipService {
	
	@Autowired
	private DepartmentRoleRelationshipExMapper drExMapper;
	
	@Autowired
	private RoleService roleService;
	
	@Override
	public List<OutputRole> getDepartmentCanmodifyOutputRole(int departId) {
		List<Role> roleList = roleService.getRoleByCanmodify();
		List<DepartmentRoleRelationshipKey> departmentRoleList = drExMapper.findDepartRoleByDepartId(departId);
		List<OutputRole> OutputRoleList = new ArrayList<OutputRole>();
		
		Map<Integer, DepartmentRoleRelationshipKey> tmpDepartmentRoleMap = new HashMap<Integer, DepartmentRoleRelationshipKey>();
		for (DepartmentRoleRelationshipKey dRKey : departmentRoleList) {
			Integer rId = dRKey.getRid();
			tmpDepartmentRoleMap.put(rId, dRKey);
		}
		for (Role role : roleList) {
			Integer rId = role.getId();
			OutputRole OutputRole = new OutputRole(role.getDescription(), false, rId, departId);
			if (tmpDepartmentRoleMap.containsKey(rId)) {
				OutputRole.setExist(true);
			}
			OutputRoleList.add(OutputRole);
		}
		return OutputRoleList;
	}

	
	@Override
	public boolean modifyDepartmentRoles(InputRole InputRole) {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		Integer dId = InputRole.getId();
		List<Role> canDeleteRoleList = roleService.getRoleByCanmodify();
		List<Integer> canDeleteRIdList = new ArrayList<Integer>();
		for (Role role : canDeleteRoleList) {
			canDeleteRIdList.add(role.getId());
		}
		parameterMap.put("dId", dId);
		parameterMap.put("RIdList", canDeleteRIdList);
		drExMapper.deleteCanModifyDR(parameterMap);
		
		List<DepartmentRoleRelationshipKey> drList = new ArrayList<DepartmentRoleRelationshipKey>();
		if (InputRole.getRoleIds() != null) {
			for (Integer rid : InputRole.getRoleIds()) {
				DepartmentRoleRelationshipKey dr = new DepartmentRoleRelationshipKey();
				dr.setDid(InputRole.getId());
				dr.setRid(rid);
				drList.add(dr);
			}
		}
		if (drList != null && drList.size() > 0) {
			drExMapper.insertDMRoles(drList);
		}
		return true;
	}


}
