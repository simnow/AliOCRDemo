package cn.caecc.erp.modules.service.serviceImpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.caecc.erp.modules.dao.mybatis.entity.SafetyGrade;
import cn.caecc.erp.modules.dao.mybatis.mapper.SafetyGradeMapper;
import cn.caecc.erp.modules.service.SafetyGradeService;

@Service
public class SafetyGradeServiceImpl implements SafetyGradeService {
	@Autowired
	private SafetyGradeMapper SafetyGradeMapper;

	@Override
	public List<SafetyGrade> getSafetyGradeList() {
		// TODO Auto-generated method stub

		return SafetyGradeMapper.selectByExample(null);

	}

}
