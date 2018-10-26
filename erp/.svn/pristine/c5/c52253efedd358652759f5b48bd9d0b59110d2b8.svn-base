/**
 * 
 */
package cn.caecc.erp.support.workflow.approver.service;

import java.util.List;
import cn.caecc.erp.modules.dao.mybatis.entity.User;

/**
 * @author weizhen
 *
 */
public interface FixedassetsApproverService {
	/**
	 * 获取申请任务的下一环节审批人
	 */
	public List<User> getApplyTaskNextApprover(int startUserId, int departmentCode);


	public List<User> getGeneralManagerOfficeOrEquipmentExaminationTaskNextApprover(String processInstanceId);
	
	
	public List<User> getManagerTaskNextApprover();

	public List<User> getManagementAndProcurementTaskNextApprover();

	public List<User> getFinancialManagementTaskNextApprover();

	public List<User> getFinancialOfficerTaskApprover();

	public List<User> getGeneralManagerTaskNextApprover(String processInstanceId, int unitPrice);

	public List<User> getChairmanTaskNextApprover(String processInstanceId, int isSecuritiesAffairs);

}
