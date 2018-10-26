/**
 * 
 */
package cn.caecc.erp.support.workflow.entity;

import java.io.Serializable;
import java.util.Date;
/**
 * @author weizhen
 *
 */
public class AttachmentEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String attachmentId;
	
	private String id;
	
	private String name;
	
	private String description;
	
	private String type;
	
	private String taskId;
	
	private String processInstanceId;
	
	private String url;
		
//	private User user;
	private String userName;
	
	private Date time;
	
	private String contentId;
	/**
	 * 
	 */
	public AttachmentEntity() {
		// TODO Auto-generated constructor stub
	}
	
	
	/**
	 * @return the attachmentId
	 */
	public String getAttachmentId() {
		return attachmentId;
	}


	/**
	 * @param attachmentId the attachmentId to set
	 */
	public void setAttachmentId(String attachmentId) {
		this.attachmentId = attachmentId;
	}


	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the taskId
	 */
	public String getTaskId() {
		return taskId;
	}
	/**
	 * @param taskId the taskId to set
	 */
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	/**
	 * @return the processInstanceId
	 */
	public String getProcessInstanceId() {
		return processInstanceId;
	}
	/**
	 * @param processInstanceId the processInstanceId to set
	 */
	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	
	/**
	 * @return the time
	 */
	public Date getTime() {
		return time;
	}
	/*
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	*/
	
	/**
	 * @param time the time to set
	 */
	public void setTime(Date time) {
		this.time = time;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the contentId
	 */
	public String getContentId() {
		return contentId;
	}
	/**
	 * @param contentId the contentId to set
	 */
	public void setContentId(String contentId) {
		this.contentId = contentId;
	}
	

}
