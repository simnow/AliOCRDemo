/**
 * 
 */
package cn.caecc.erp.support.workflow.service.listener;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

/**
 * @author weizhen
 *
 */
public class SetAssigneeOnAssignmentTaskListener implements TaskListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public SetAssigneeOnAssignmentTaskListener() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see org.activiti.engine.delegate.TaskListener#notify(org.activiti.engine.delegate.DelegateTask)
	 */
	@Override
	public void notify(DelegateTask delegateTask) {

		DelegateExecution execution = delegateTask.getExecution();
		String taskDefinitionKey = delegateTask.getTaskDefinitionKey();
		execution.setVariableLocal(taskDefinitionKey + "Assignee", delegateTask.getAssignee());
		
	}

}
