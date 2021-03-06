/**
 * 
 */
package cn.caecc.erp.support.workflow.approver.service.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.caecc.erp.modules.dao.mybatis.entity.User;
import cn.caecc.erp.modules.service.DeptmentService;
import cn.caecc.erp.modules.service.UserService;
import cn.caecc.erp.support.constant.Contants;
import cn.caecc.erp.support.workflow.approver.service.QualityFeedbackApproverService;
import cn.caecc.erp.support.workflow.service.WorkflowService;

/**
 * @author weizhen
 *
 */
@Service
public class QualityFeedbackApproverServiceImpl extends BaseApproverService  implements QualityFeedbackApproverService {

	/**
	 * 
	 */
	@Autowired
	private UserService userService;
	
	@Autowired
	private DeptmentService deptMentService;
	
	@Autowired
	private WorkflowService workflowService;
	
	public QualityFeedbackApproverServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see cn.caecc.erp.support.workflow.approver.service.QualityApproverService#getApplyTaskNextApprover()
	 */
	@Override
	public List<User> getApplyTaskNextApprover() {
		// TODO Auto-generated method stub
		List<User> userList = deptMentService.getDepartmentMasters(Contants.MANAGEMENTANDPROCUREMENT);
		return super.preProcessUsers(userList);
	}

	/* (non-Javadoc)
	 * @see cn.caecc.erp.support.workflow.approver.service.QualityApproverService#getManagementAndProcurementProcessTaskNextApprover()
	 */
	@Override
	public List<User> getManagementAndProcurementProcessTaskNextApprover() {
		// TODO Auto-generated method stub
		List<User> userList = userService.getList();
		return super.preProcessUsers(userList);
	}

	/* (non-Javadoc)
	 * @see cn.caecc.erp.support.workflow.approver.service.QualityApproverService#getBusinessDepartmentTaskNextApprover()
	 */
	@Override
	public List<User> getBusinessDepartmentTaskNextApprover(String processInstanceId) {
		// TODO Auto-generated method stub
		List<User> userList = null;
		String strStartUserId = workflowService.queryHistoryTaskOfRuntimeInstanceAssigneeId(processInstanceId,
				"ManagementAndProcurementProcessTask");
		if (strStartUserId != null) {
			userList = new ArrayList<User>();
			User user = userService.findDtoById(Integer.parseInt(strStartUserId));			
			userList.add(user);
		}
		return super.preProcessUsers(userList);
	}

	/* (non-Javadoc)
	 * @see cn.caecc.erp.support.workflow.approver.service.QualityApproverService#getManagementAndProcurementConfirmTaskNextApprover()
	 */
	@Override
	public List<User> getManagementAndProcurementConfirmTaskNextApprover(String processInstanceId) {
		// TODO Auto-generated method stub
		List<User> userList = null;
		String strStartUserId = workflowService.queryHistoryTaskOfRuntimeInstanceAssigneeId(processInstanceId,
				"ApplyTask");
		if (strStartUserId != null) {
			userList = new ArrayList<User>();
			User user = userService.findDtoById(Integer.parseInt(strStartUserId));			
			userList.add(user);
		}
		return super.preProcessUsers(userList);
	}

}
