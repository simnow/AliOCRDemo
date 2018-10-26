/**
 * 
 */
package cn.caecc.erp.support.workflow.approver.service.serviceImpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.caecc.erp.modules.dao.mybatis.entity.User;
import cn.caecc.erp.modules.service.DeptmentService;
import cn.caecc.erp.modules.service.UserService;
import cn.caecc.erp.support.constant.Contants;
import cn.caecc.erp.support.workflow.approver.service.VehicleApproverService;

/**
 * @author weizhen
 *
 */
@Service
public class VehicleApproverServiceImpl extends BaseApproverService implements VehicleApproverService {

	@Autowired
	private UserService userService;
	
	@Autowired
	private DeptmentService deptMentService;
	/**
	 * 
	 */
	public VehicleApproverServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see cn.caecc.erp.support.workflow.approver.service.VehicleApproverService#getApplyTaskNextApprover(int)
	 */
	@Override
	public List<User> getApplyTaskNextApprover(int startUserId) {
		// TODO Auto-generated method stub
		List<User> userList = userService.findDepartmentLeader(startUserId);
		return super.preProcessUsers(userList);
	}

	/* (non-Javadoc)
	 * @see cn.caecc.erp.support.workflow.approver.service.VehicleApproverService#getDepartmentalLeaderTaskNextApprover()
	 */
	@Override
	public List<User> getDepartmentalLeaderTaskNextApprover() {
		// TODO Auto-generated method stub
		List<User> userList = deptMentService.getDepartmentUsers(Contants.GENERALMENERALMANAGEROFFICE);
		return super.preProcessUsers(userList);
	}

	/* (non-Javadoc)
	 * @see cn.caecc.erp.support.workflow.approver.service.VehicleApproverService#getGeneralManagerOfficeTaskNextApprover()
	 */
	@Override
	public List<User> getGeneralManagerOfficeTaskNextApprover() {
		// TODO Auto-generated method stub

		return getDepartmentalLeaderTaskNextApprover();
	}



}
