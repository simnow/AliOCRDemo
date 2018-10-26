/**
 * 
 */
package cn.caecc.erp.support.workflow.entity;

import java.io.Serializable;

/**
 * @author weizhen
 *
 */
public class ProcessDefinitionEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 流程定义名称
	 */
	private String  processDefinitionName;
	
	/**
	 * 流程定义key
	 */
	private String  processDefinitionKey;
	
	private int version;
	
	/**
	 * 
	 */
	public ProcessDefinitionEntity() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @return the processDefinitionName
	 */
	public String getProcessDefinitionName() {
		return processDefinitionName;
	}

	/**
	 * @param processDefinitionName the processDefinitionName to set
	 */
	public void setProcessDefinitionName(String processDefinitionName) {
		this.processDefinitionName = processDefinitionName;
	}

	/**
	 * @return the processDefinitionKey
	 */
	public String getProcessDefinitionKey() {
		return processDefinitionKey;
	}

	/**
	 * @param processDefinitionKey the processDefinitionKey to set
	 */
	public void setProcessDefinitionKey(String processDefinitionKey) {
		this.processDefinitionKey = processDefinitionKey;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
	
	

}
