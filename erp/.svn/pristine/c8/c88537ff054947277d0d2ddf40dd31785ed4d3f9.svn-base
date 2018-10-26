/**
 * 
 */
package cn.caecc.erp.support.workflow.service.listener.Material;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

/**
 * @author weizhen
 *
 */
public class SetAssigneeVariableListener implements TaskListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public SetAssigneeVariableListener() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see org.activiti.engine.delegate.TaskListener#notify(org.activiti.engine.delegate.DelegateTask)
	 */
	@Override
	public void notify(DelegateTask delegateTask) {

		DelegateExecution execution = delegateTask.getExecution();
		execution.setVariable("BusinessDepartmentTask1" + "Assignee", null);
		
	}

}
