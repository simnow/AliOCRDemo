package cn.caecc.erp.modules.service.serviceImpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.caecc.erp.modules.dao.mybatis.entity.DepartmentType;
import cn.caecc.erp.modules.dao.mybatis.mapper.DepartmentTypeMapper;
import cn.caecc.erp.modules.service.DeptmentTypeService;

@Service
public class DeptmentTypeServiceImpl implements DeptmentTypeService {
	
	@Autowired
	private DepartmentTypeMapper  dtMapper;
	@Override
	public List<DepartmentType>  getDeptTypeList() {
		// TODO Auto-generated method stub
		return dtMapper.selectByExample(null);
	}
}
