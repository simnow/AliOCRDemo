package cn.caecc.erp.support.workflow.entity;

import java.io.Serializable;

/**
 * @author weizhen
 *
 */
public class TaskAttachmentsEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 任务id
	 */
	private String taskId;
	
	/**
	 * 附件名称
	 */
	private String attachmentName;
	
	/**
	 * 附件描述
	 */
	private String attachmentDescription;
	
	/**
	 * 附件oss地址
	 */
	private String url;
	
	public TaskAttachmentsEntity()
	{
		
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getAttachmentName() {
		return attachmentName;
	}

	public void setAttachmentName(String attachmentName) {
		this.attachmentName = attachmentName;
	}

	public String getAttachmentDescription() {
		return attachmentDescription;
	}

	public void setAttachmentDescription(String attachmentDescription) {
		this.attachmentDescription = attachmentDescription;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	

}
