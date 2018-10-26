package cn.caecc.erp.modules.service.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.caecc.erp.modules.dao.mybatis.entity.Role;
import cn.caecc.erp.modules.dao.mybatis.entity.RoleExample;
import cn.caecc.erp.modules.dao.mybatis.entity.RoleExample.Criteria;
import cn.caecc.erp.modules.dao.mybatis.mapper.RoleMapper;
import cn.caecc.erp.modules.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleMapper roleMapper;
	
	@Override
	public Role findById(int id) {
		return roleMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Role> getRoleAll() {
		RoleExample roleExample = new RoleExample();
		Criteria createCriteria = roleExample.createCriteria();
		createCriteria.andIdIsNotNull();
		List<Role> list = roleMapper.selectByExample(roleExample);
		return list;
	}

	@Override
	public List<Role> getRoleByName(String name) {
		// TODO Auto-generated method stub
		RoleExample roleExample = new RoleExample();
		Criteria criteria = roleExample.createCriteria();
		criteria.andNameEqualTo(name);
		List<Role> roleList = roleMapper.selectByExample(roleExample);
		return roleList;

	}

	@Override
	public List<Role> getRoleByCanmodify() {
		// TODO Auto-generated method stub
		List<Role> roleList = getRoleAll();
		List<Role> canmodifyRoleList = new ArrayList<Role>();
		for (Role role : roleList) {
			if (role.getCanmodify() != 0) {
				canmodifyRoleList.add(role);
			}
		}
		return canmodifyRoleList;
	}
}
