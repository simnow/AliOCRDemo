/**
 * 
 */
package cn.caecc.erp.support.workflow.approver.service.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.caecc.erp.modules.dao.mybatis.entity.User;
import cn.caecc.erp.modules.service.DeptmentService;
import cn.caecc.erp.modules.service.UserService;
import cn.caecc.erp.support.constant.Contants;
import cn.caecc.erp.support.workflow.approver.service.MaterialApproverService;
import cn.caecc.erp.support.workflow.service.WorkflowService;

/**
 * @author GaiNing
 *
 */
@Service
public class MaterialApproverServiceImpl extends BaseApproverService implements MaterialApproverService {

	/**
	 * 
	 */
	@Autowired
	private UserService userService;
	
	@Autowired
	private DeptmentService deptMentService;
	
	@Autowired
	private WorkflowService workflowService;
	
	@Autowired
	private HttpSession session;
	public MaterialApproverServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see cn.caecc.erp.support.workflow.approver.service.MaterialApproverService#getApplyTaskNextApprover()
	 */
	@Override
	public List<User> getApplyTaskNextApprover() {
		// TODO Auto-generated method stub
		List<User> userList = deptMentService.getDepartmentUsers(Contants.SUUPLYCENTRE);
		return super.preProcessUsers(userList);
	}

	/* (non-Javadoc)
	 * @see cn.caecc.erp.support.workflow.approver.service.MaterialApproverService#getSupplyCentreTask1NextApprover()
	 */
	@Override
	public List<User> getSupplyCentreTask1NextApprover() {
		// TODO Auto-generated method stub
		
		List<User> userList = userService.getList();
		return super.preProcessUsers(userList);
	}

	/* (non-Javadoc)
	 * @see cn.caecc.erp.support.workflow.approver.service.MaterialApproverService#getBusinessDepartmentTask1NextApprover()
	 */
	@Override
	public List<User> getBusinessDepartmentTask1NextApprover() {
		// TODO Auto-generated method stub
		
		Integer userId = (Integer)session.getAttribute(Contants.LOGINUSERID);
		List<User> userList = userService.findManagerLeader(userId);
		return super.preProcessUsers(userList);
	}

	/* (non-Javadoc)
	 * @see cn.caecc.erp.support.workflow.approver.service.MaterialApproverService#getManagerTaskNextApprover(java.lang.String)
	 */
	@Override
	public List<User> getManagerTaskNextApprover(String processInstanceId) {
		// TODO Auto-generated method stub
		List<User> userList = null;
		String strStartUserId = workflowService.queryHistoryTaskOfRuntimeInstanceAssigneeId(processInstanceId,
				"BusinessDepartmentTask1");
		if (strStartUserId != null) {
			userList = userService.findAssignedManagerLeader(Integer.parseInt(strStartUserId));
		}
		return super.preProcessUsers(userList);
	}

	/* (non-Javadoc)
	 * @see cn.caecc.erp.support.workflow.approver.service.MaterialApproverService#getAssignedManagerTaskNextApprover(java.lang.String)
	 */
	@Override
	public List<User> getAssignedManagerTaskNextApprover(String processInstanceId, Integer condition) {
		// TODO Auto-generated method stub
		List<User> userList = null;
		if (condition == 1) {
			userList = deptMentService.getDepartmentUsers(Contants.GENERALMENERALMANAGER);
			
		} else if (condition == 2) {
			String strUserId = workflowService.queryHistoryTaskOfRuntimeInstanceAssigneeId(processInstanceId,
					"BusinessDepartmentTask1");
			if (strUserId != null) {
				userList = new ArrayList<User>();
				User user = userService.findDtoById(Integer.parseInt(strUserId));
				userList.add(user);
			}
			
		} else if (condition == 3) {
			String strUserId = workflowService.queryHistoryTaskOfRuntimeInstanceAssigneeId(processInstanceId,
					"SupplyCentreTask1");
			if (strUserId != null) {
				userList = new ArrayList<User>();
				User user = userService.findDtoById(Integer.parseInt(strUserId));
				userList.add(user);
			}
		}		
		return super.preProcessUsers(userList);
	}

	/* (non-Javadoc)
	 * @see cn.caecc.erp.support.workflow.approver.service.MaterialApproverService#getGeneralManagerTaskNextApprover()
	 */
	@Override
	public List<User> getGeneralManagerTaskNextApprover() {
		// TODO Auto-generated method stub
		List<User> userList = deptMentService.getDepartmentUsers(Contants.SUUPLYCENTRE);
		return super.preProcessUsers(userList);
	}

	/* (non-Javadoc)
	 * @see cn.caecc.erp.support.workflow.approver.service.MaterialApproverService#getBusinessDepartmentTask2NextApprover()
	 */
	@Override
	public List<User> getBusinessDepartmentTask2NextApprover() {
		// TODO Auto-generated method stub
		List<User> userList = deptMentService.getDepartmentUsers(Contants.GENERALMENERALMANAGER);
		return super.preProcessUsers(userList);
	}

	/* (non-Javadoc)
	 * @see cn.caecc.erp.support.workflow.approver.service.MaterialApproverService#getSupplyCentreTask2NextApprover()
	 */
	@Override
	public List<User> getSupplyCentreTask2NextApprover(String processInstanceId) {
		// TODO Auto-generated method stub
		List<User> userList = new ArrayList<User>();
		String strStartUserId = workflowService.queryHistoryTaskOfRuntimeInstanceAssigneeId(processInstanceId,
				"ApplyTask");
		if (strStartUserId != null) {
			userList.add(userService.findDtoById(Integer.parseInt(strStartUserId)));
		}
		return super.preProcessUsers(userList);
	}

}
