package cn.caecc.erp.modules.service.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import cn.caecc.erp.modules.dao.ex.dto.VehicleActivitiApplyDto;
import cn.caecc.erp.modules.dao.ex.mapper.VehicleActivitiApplyExMapper;
import cn.caecc.erp.modules.dao.mybatis.entity.VehicleActivitiApply;
import cn.caecc.erp.modules.dao.mybatis.mapper.VehicleActivitiApplyMapper;
import cn.caecc.erp.modules.service.VehicleActivitiApplyService;
import cn.caecc.erp.support.constant.Contants;
import cn.caecc.erp.support.exception.CommonException;
import cn.caecc.erp.support.util.DateUtil;
import cn.caecc.erp.support.workflow.service.WorkflowService;

@Service
public class VehicleActivitiApplyServiceImpl implements VehicleActivitiApplyService {

	@Autowired
	private VehicleActivitiApplyMapper veMapper;
	@Autowired
	private VehicleActivitiApplyExMapper veExMapper;
	@Autowired
	private WorkflowService workflowService;

	@Override
	public VehicleActivitiApply addVehicleActivitiApply(VehicleActivitiApply vehicleActivitiApply) {
		// TODO Auto-generated method stub
		Integer loginUserId = (Integer) SecurityUtils.getSubject().getSession().getAttribute(Contants.LOGINUSERID);

		// 判断参数
		if (StringUtils.isBlank(vehicleActivitiApply.getAddress())
				|| StringUtils.isBlank(vehicleActivitiApply.getCause())
				|| StringUtils.isBlank(vehicleActivitiApply.getPlannedroute())) {

			throw new CommonException("参数异常");

		}
		if (vehicleActivitiApply.getUid() == null || vehicleActivitiApply.getNumberofhours() == null
				|| vehicleActivitiApply.getNumberofpepole() == null) {

			throw new CommonException("参数异常");

		}

		// 判断是保存还是修改
		if (vehicleActivitiApply.getId() == null) {
			vehicleActivitiApply.setCreateuserid(loginUserId);
			vehicleActivitiApply.setCreatetime(DateUtil.getcurrentDateTime());
			veMapper.insertSelective(vehicleActivitiApply);
		} else {
			// 查询数据库数据
			VehicleActivitiApply exsits = veMapper.selectByPrimaryKey(vehicleActivitiApply.getId());
			// 判断是否存在流程
			if (exsits.getProcessinstanceid() == null) {
				veMapper.updateByPrimaryKeySelective(vehicleActivitiApply);
			} else {
				throw new CommonException("流程中无法修改");

			}
		}

		return vehicleActivitiApply;
	}

	@Override
	public int deleteVehicleActivitiApply(int id) {

		// TODO Auto-generated method stub
		int result = 0;

		VehicleActivitiApply vehicleActivitiApply = veMapper.selectByPrimaryKey(id);
		if (vehicleActivitiApply == null) {
			throw new CommonException("当前记录不存在");

		}
		if (StringUtils.isBlank(vehicleActivitiApply.getProcessinstanceid())) {
			result = veMapper.deleteByPrimaryKey(id);
		} else {
			throw new CommonException("存在流程信息，无法删除");

		}
		return result;

	}

	@Override
	public PageInfo<VehicleActivitiApply> getVehicleApplyPageList(int state,int pagesize,int pageno,int uid) {
		// TODO Auto-generated method stub
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("state",state);
		params.put("uid", uid);
		PageHelper.startPage(pageno,pagesize);
		List<VehicleActivitiApply> resultList = veExMapper.getVePageList(params);
		PageInfo<VehicleActivitiApply> pageInfo = new PageInfo<>(resultList);

		return pageInfo;
	}

	@Override
	public int startVehicleActivitiApply(String processDefinitionKey, String bussinessKey,
			Map<String, Object> variables) throws Exception {
		// TODO Auto-generated method stub

		// 判断参数是否存在
		if (StringUtils.isBlank(bussinessKey) || StringUtils.isBlank(processDefinitionKey)) {
			throw new CommonException("参数异常");
		}

		// 开启流程
		String processInstanceId = workflowService.startProcess(processDefinitionKey, bussinessKey, variables);
		// 判断流程开启成功
		if (StringUtils.isBlank(processInstanceId)) {
			throw new CommonException("流程开启失败");

		}
		// 获取业务表信息
		VehicleActivitiApply vehicleActivitiApply = veMapper.selectByPrimaryKey(Integer.parseInt(bussinessKey));
		vehicleActivitiApply.setProcessinstanceid(processInstanceId);
		// 更新
		veMapper.updateByPrimaryKey(vehicleActivitiApply);
		// 返回成功
		return 1;

	}

	@Override
	public VehicleActivitiApplyDto getVehicleActivitiApply(int id) {
		// TODO Auto-generated method stub

		return veExMapper.getCardApplyById(id);

	}

	@Override
	public VehicleActivitiApply updateVehicleApply(VehicleActivitiApply vehicleActivitiApply) {
		// TODO Auto-generated method stub
		// 判断参数
		if (StringUtils.isBlank(vehicleActivitiApply.getAddress())
				|| StringUtils.isBlank(vehicleActivitiApply.getCause())
				|| StringUtils.isBlank(vehicleActivitiApply.getPlannedroute())) {
			throw new CommonException("参数异常");
		}
		if (vehicleActivitiApply.getUid() == null || vehicleActivitiApply.getNumberofhours() == null
				|| vehicleActivitiApply.getNumberofpepole() == null) {
			throw new CommonException("参数异常");
		}
		veMapper.updateByPrimaryKeySelective(vehicleActivitiApply);
		return vehicleActivitiApply;

	}

	@Override
	public void setSuccess(int id) {
		// TODO Auto-generated method stub
		VehicleActivitiApply vehicleActivitiApply = new VehicleActivitiApply();
		vehicleActivitiApply.setId(id);
		vehicleActivitiApply.setIssuccess(1);
		veMapper.updateByPrimaryKeySelective(vehicleActivitiApply);

	}

}
