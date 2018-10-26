package cn.caecc.erp.support.workflow.remind.entity;

import java.io.Serializable;

import cn.caecc.erp.modules.dao.mybatis.entity.User;

public class RemindInfoEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String processInstanceId;
	
	private String processDefinitionName;
	
	private String executionId;
	
	/**
	 * 最后环节审批人
	 */
	private User lastAssigneeUser;
	
	/**
	 * 最后一步审批意见
	 */
	private String lastCommentMessage;
	
	private User initiatorUser;

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public String getProcessDefinitionName() {
		return processDefinitionName;
	}

	public void setProcessDefinitionName(String processDefinitionName) {
		this.processDefinitionName = processDefinitionName;
	}

	public String getExecutionId() {
		return executionId;
	}

	public void setExecutionId(String executionId) {
		this.executionId = executionId;
	}

	public User getLastAssigneeUser() {
		return lastAssigneeUser;
	}

	public void setLastAssigneeUser(User lastAssigneeUser) {
		this.lastAssigneeUser = lastAssigneeUser;
	}

	public String getLastCommentMessage() {
		return lastCommentMessage;
	}

	public void setLastCommentMessage(String lastCommentMessage) {
		this.lastCommentMessage = lastCommentMessage;
	}

	public User getInitiatorUser() {
		return initiatorUser;
	}

	public void setInitiatorUser(User initiatorUser) {
		this.initiatorUser = initiatorUser;
	}

}
