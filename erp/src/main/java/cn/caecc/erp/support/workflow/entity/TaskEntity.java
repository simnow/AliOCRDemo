package cn.caecc.erp.support.workflow.entity;

import java.io.Serializable;
import java.util.List;

import org.activiti.bpmn.model.GraphicInfo;
/**
 * @author weizhen
 *
 */
public class TaskEntity  implements Serializable {
	
	
	public class UserItem implements Serializable{
		
		private static final long serialVersionUID = 1L;
		
		private int id;
		
		private String name;
		
		public UserItem(int id, String name) {
			this.id = id;
			this.name = name;
		}
		/**
		 * @return the id
		 */
		public int getId() {
			return id;
		}
		/**
		 * @param id the id to set
		 */
		public void setId(int id) {
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
		
	}
	private static final long serialVersionUID = 1L;
	
	/**
	 * 任务id
	 */
	private String taskId;
	
	/**
	 * 任务定义key
	 */
	private String taskDefinitionKey;

	/**
	 * 任务定义名称
	 */
	private String taskDefinitionName;
	
	/**
	 * 批注
	 */
	private String comment;
	
	private String createTime;
	/**
	 * 审批完成时间
	 */
	private String endTime;
	
	/**
	 * 图形信息
	 */
	private GraphicInfo graphicInfo;
	
	/**
	 * 对于历史任务的审批人，已经审批完成
	 */
//	private UserDto assigneeUser;
	
	private String assigneeName;
	
	private int assigneeId;
	
	private String departmentName;
	
	/**
	 * 对于当前运行时任务的待审批人
	 */
//	private List<User> invokeUserList;
	
	
	/**
	 * 是否通过
	 */
	private int isApprove;
	
	/*
	 * 当前执行流上一次的审批结果
	 */
	private int lastApprove;
	
///	private String showInvokeUsersName;
	
	private List<UserItem> invokeUserItems;
	
	
	public TaskEntity()
	{
		
	}
	
	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}


	/**
	 * @return the assigneeId
	 */
	public int getAssigneeId() {
		return assigneeId;
	}

	/**
	 * @param assigneeId the assigneeId to set
	 */
	public void setAssigneeId(int assigneeId) {
		this.assigneeId = assigneeId;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	
/*
	public UserDto getAssigneeUser() {
		return assigneeUser;
	}

	public void setAssigneeUser(UserDto assigneeUser) {
		this.assigneeUser = assigneeUser;
	}
*/

	/**
	 * @return the invokeUserItems
	 */
	public List<UserItem> getInvokeUserItems() {
		return invokeUserItems;
	}

	/**
	 * @param invokeUserItems the invokeUserItems to set
	 */
	public void setInvokeUserItems(List<UserItem> invokeUserItems) {
		this.invokeUserItems = invokeUserItems;
	}

	/**
	 * @return the departmentName
	 */
	public String getDepartmentName() {
		return departmentName;
	}


	/**
	 * @param departmentName the departmentName to set
	 */
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}


	public String getAssigneeName() {
		return assigneeName;
	}


	public void setAssigneeName(String assigneeName) {
		this.assigneeName = assigneeName;
	}


/*
	public List<User> getInvokeUserList() {
		return invokeUserList;
	}
	
	public void setInvokeUserList(List<User> invokeUserList) {
		this.invokeUserList = invokeUserList;
	}
	*/

	/**
	 * @return the lastApprove
	 */
	public int getLastApprove() {
		return lastApprove;
	}

	/**
	 * @param lastApprove the lastApprove to set
	 */
	public void setLastApprove(int lastApprove) {
		this.lastApprove = lastApprove;
	}


	public GraphicInfo getGraphicInfo() {
		return graphicInfo;
	}
	
	public void setGraphicInfo(GraphicInfo graphicInfo) {
		this.graphicInfo = graphicInfo;
	}

	public String getTaskId() {
		return taskId;
	}


	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	
	public String getTaskDefinitionKey() {
		return taskDefinitionKey;
	}

	public void setTaskDefinitionKey(String taskDefinitionKey) {
		this.taskDefinitionKey = taskDefinitionKey;
	}

	public String getTaskDefinitionName() {
		return taskDefinitionName;
	}

	public void setTaskDefinitionName(String taskDefinitionName) {
		this.taskDefinitionName = taskDefinitionName;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getIsApprove() {
		return isApprove;
	}

	public void setIsApprove(int isApprove) {
		this.isApprove = isApprove;
	}
	
}
