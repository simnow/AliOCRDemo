package cn.caecc.erp.support.workflow.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import org.activiti.bpmn.model.GraphicInfo;

/**
 * @author weizhen
 *
 */
public class ProcessIntanceEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/**
	 * 创建日期
	 */
	private String startTime;
	
	/*
	 * 完成日期
	 */
	private String endTime;
	
	/**
	 * 业务主键
	 */
	private int bussinessKey; 
	
	/**
	 * 流程实例id
	 */
	private String  processInstanceId;
	
	/**
	 * 流程定义名称
	 */
	private String  processDefinitionName;
	
	/**
	 * 流程定义key
	 */
	private String  processDefinitionKey;
	
	/*
	 * 版本
	 */
	private int processDefinitionVersion;
	
	/**
	 * 发起人id
	 */
	//private User startUser;
	
	
	private int startUserId;
	
	private String startUserName;

	/**
	 * 对于历史任务需要知道最后一次任务的执行结果
	 */
	private int resultApprove;

	/**
	 * 历史任务列表
	 */
	private List<TaskEntity> historicTaskEntities;
	
	/**
	 * 最近历史任务列表
	 */
	private List<TaskEntity> newestHistoricTaskEntities;
	
	/**
	 * 运行时任务实体列表
	 */
	private List<TaskEntity> runtimeTaskEntities;

	
	/**
	 * 附件列表
	 */
	private List<AttachmentEntity> attachmentList;
	
	/**
	 * 图形列表
	 */
	private Map<String, GraphicInfo> graphicInfoMap;

	
	public ProcessIntanceEntity()
	{
		
	}
	

	/**
	 * @return the resultApprove
	 */
	public int getResultApprove() {
		return resultApprove;
	}


	/**
	 * @param resultApprove the resultApprove to set
	 */
	public void setResultApprove(int resultApprove) {
		this.resultApprove = resultApprove;
	}


	/**
	 * @return the startTime
	 */
	public String getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	

	/**
	 * @return the endTime
	 */
	public String getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}


	public List<TaskEntity> getRuntimeTaskEntities() {
		return runtimeTaskEntities;
	}


	public void setRuntimeTaskEntities(List<TaskEntity> runtimeTaskEntities) {
		this.runtimeTaskEntities = runtimeTaskEntities;
	}


	/**
	 * @return the bussinessKey
	 */
	public int getBussinessKey() {
		return bussinessKey;
	}


	/**
	 * @param bussinessKey the bussinessKey to set
	 */
	public void setBussinessKey(int bussinessKey) {
		this.bussinessKey = bussinessKey;
	}
	

	public Map<String, GraphicInfo> getGraphicInfoMap() {
		return graphicInfoMap;
	}

	public void setGraphicInfoMap(Map<String, GraphicInfo> graphicInfoMap) {
		this.graphicInfoMap = graphicInfoMap;
	}
	
	public List<AttachmentEntity> getAttachmentList() {
		return attachmentList;
	}


	public void setAttachmentList(List<AttachmentEntity> attachmentList) {
		this.attachmentList = attachmentList;
	}

	public String getProcessDefinitionKey() {
		return processDefinitionKey;
	}

	public void setProcessDefinitionKey(String processDefinitionKey) {
		this.processDefinitionKey = processDefinitionKey;
	}

	public List<TaskEntity> getHistoricTaskEntities() {
		return historicTaskEntities;
	}

	public void setHistoricTaskentities(List<TaskEntity> historicTaskentities) {
		this.historicTaskEntities = historicTaskentities;
	}

	public List<TaskEntity> getNewestHistoricTaskEntities() {
		return newestHistoricTaskEntities;
	}


	public void setHistoricTaskEntities(List<TaskEntity> historicTaskEntities) {
		this.historicTaskEntities = historicTaskEntities;
	}


	public void setNewestHistoricTaskEntities(List<TaskEntity> newestHistoricTaskentities) {
		this.newestHistoricTaskEntities = newestHistoricTaskentities;
	}


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


	public String getStartUserName() {
		return startUserName;
	}


	public void setStartUserName(String startUserName) {
		this.startUserName = startUserName;
	}


	public int getProcessDefinitionVersion() {
		return processDefinitionVersion;
	}


	public void setProcessDefinitionVersion(int processDefinitionVersion) {
		this.processDefinitionVersion = processDefinitionVersion;
	}


	/**
	 * @return the startUserId
	 */
	public int getStartUserId() {
		return startUserId;
	}


	/**
	 * @param startUserId the startUserId to set
	 */
	public void setStartUserId(int startUserId) {
		this.startUserId = startUserId;
	}


/*
	public User getStartUser() {
		return startUser;
	}


	public void setStartUser(User startUser) {
		this.startUser = startUser;
	}
*/
}
