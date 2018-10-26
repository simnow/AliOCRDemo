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
import cn.caecc.erp.support.workflow.approver.service.TenderingApproverService;
import cn.caecc.erp.support.workflow.service.WorkflowService;

@Service
public class TenderingApproverServiceImpl extends BaseApproverService  implements TenderingApproverService {

	@Autowired
	private UserService userService;
	
	@Autowired
	private DeptmentService deptMentService;
	
	@Autowired
	private WorkflowService workflowService;
	
	@Autowired
	private HttpSession session;
	
	@Override
	public List<User> getApplyTaskNextApprover() {
		// TODO Auto-generated method stub
		List<User> userList = deptMentService.getDepartmentMasters(Contants.MANAGEMENTANDPROCUREMENT);
		return super.preProcessUsers(userList);
	}

	@Override
	public List<User> getManagementAndProcurementExaminationTaskNextApprover() {
		// TODO Auto-generated method stub
		Integer userId = (Integer)session.getAttribute(Contants.LOGINUSERID);
		List<User> userList = userService.findManagerLeader(userId);
		return super.preProcessUsers(userList);
	}

	@Override
	public List<User> getManagerTaskNextApprover() {
		// TODO Auto-generated method stub
		List<User> userList = deptMentService.getDepartmentMasters(Contants.INVITETENDERSCOMMITTEE);
		return super.preProcessUsers(userList);
	}

	@Override
	public List<User> getInviteTendersCommitteeLeaderTaskNextApprover(String processInstanceId) {
		// TODO Auto-generated method stub
		List<User> userList = new ArrayList<User>();
		String strUserId = workflowService.queryHistoryTaskOfRuntimeInstanceAssigneeId(processInstanceId,
				"ManagementAndProcurementExaminationTask");
		if (strUserId != null) {
			userList.add(userService.findDtoById(Integer.parseInt(strUserId)));
		}
		return super.preProcessUsers(userList);
	}

}
