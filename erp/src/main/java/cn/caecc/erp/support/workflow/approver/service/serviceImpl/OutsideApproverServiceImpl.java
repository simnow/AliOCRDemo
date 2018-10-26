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
import cn.caecc.erp.support.workflow.approver.service.OutsideApproverService;
import cn.caecc.erp.support.workflow.service.WorkflowService;

/**
 * @author weizhen
 *
 */
@Service
public class OutsideApproverServiceImpl extends BaseApproverService implements OutsideApproverService {

	@Autowired
	private UserService userService;
	
	@Autowired
	private DeptmentService deptMentService;
	
	@Autowired
	private WorkflowService workflowService;
	
	@Autowired
	private HttpSession session;
	/**
	 * 
	 */
	public OutsideApproverServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see cn.caecc.erp.support.workflow.service.OutsideApproverService#getApplyTaskApprover()
	 */
	@Override
	public List<User> getApplyTaskNextApprover(int startUserId)
	{
		List<User> userList = deptMentService.getDepartmentUsers(Contants.GENERALMENERALMANAGEROFFICE);

		return super.preProcessUsers(userList);
	}

	/* (non-Javadoc)
	 * @see cn.caecc.erp.support.workflow.service.OutsideApproverService#getGeneralManagerOfficeTaskApprover()
	 */
	@Override
	public List<User> getGeneralManagerOfficeTaskNextApprover() {
		// TODO Auto-generated method stub
		/*
		List<String> types = new ArrayList<String>();
		types.add("主管领导");
		List<DepartmentDto> departMentDtoList = deptMentService.getDeptInTypes(types);
		*/
		return null;
	}

	/* (non-Javadoc)
	 * @see cn.caecc.erp.support.workflow.service.OutsideApproverService#getManagerAboveLeaderTaskApprover()
	 */
	@Override
	public List<User> getManagerAboveLeaderTaskNextApprover() {
		// TODO Auto-generated method stub
		
		List<User> userList = userService.getList();
		return super.preProcessUsers(userList);
	}

	public List<User> getGeneralManagerOfficeLeaderTaskNextApprover() {
		Integer userId = (Integer)session.getAttribute(Contants.LOGINUSERID);
		List<User> userList = userService.findDepartmentLeader(userId);
		return super.preProcessUsers(userList);
	}
	
	public List<User> getProcesserSummaryTaskNextApprover(String processInstanceId) {
		List<User> userList = new ArrayList<User>();
		String strStartUserId = workflowService.queryHistoryTaskOfRuntimeInstanceAssigneeId(processInstanceId,
				"GeneralManagerOfficeTask");
		if (strStartUserId != null) {
			User user = userService.findDtoById(Integer.parseInt(strStartUserId));
			userList.add(user);
		}
		return super.preProcessUsers(userList);
	}
	

}
