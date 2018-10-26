package cn.caecc.erp.support.workflow.service.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import javax.servlet.http.HttpSession;
import org.activiti.bpmn.model.Activity;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.FlowElement;
import org.activiti.bpmn.model.GraphicInfo;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricProcessInstanceQuery;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.impl.persistence.entity.CommentEntity;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.activiti.engine.task.Attachment;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.IdentityLink;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageInfo;
import cn.caecc.erp.modules.dao.ex.dto.UserDto;
import cn.caecc.erp.modules.dao.mybatis.entity.User;
import cn.caecc.erp.modules.service.UserService;
import cn.caecc.erp.support.constant.Contants;
import cn.caecc.erp.support.exception.CommonException;
import cn.caecc.erp.support.exception.MyActivitiException;
import cn.caecc.erp.support.oss.service.OssService;
import cn.caecc.erp.support.redis.service.RedisService;
import cn.caecc.erp.support.workflow.entity.AttachmentEntity;
import cn.caecc.erp.support.workflow.entity.ProcessDefinitionEntity;
import cn.caecc.erp.support.workflow.entity.ProcessIntanceEntity;
import cn.caecc.erp.support.workflow.entity.TaskEntity;
import cn.caecc.erp.support.workflow.entity.TaskEntity.UserItem;
import cn.caecc.erp.support.workflow.service.WorkflowService;

/**
 * @author weizhen
 *
 */
@Service
public class ActivitiServiceImpl implements WorkflowService {
	@Autowired
	private HistoryService historyService;

	@Autowired
	private TaskService taskService;

	@Autowired
	private IdentityService identityService;

	@Autowired
	private RepositoryService repositoryService;

	@Autowired
	private RuntimeService runtimeService;

	@Autowired
	private HttpSession session;

	@Autowired
	private OssService ossService;

	@Autowired
	private UserService userService;

	// private Map<String, String> userId2NameMap;

	@Autowired
	private RedisService redisService;

	private static final String OSSPREFIX = "workflow/attachment/";

	private static final String DATEFORMAT = "yyyy-MM-dd HH:mm:ss";

	/*
	 * @Autowired private ApproverAnalyzeService approverAnalyzeService;
	 */
	private final static Logger logger = LoggerFactory.getLogger(ActivitiServiceImpl.class);

	/**
	 * 内部函数，通过流程实例id查询历史流程实例
	 * 
	 * @param processInstanceId
	 * @return
	 */
	private HistoricProcessInstance queryHistoricProcessInstanceByIdInternal(String processInstanceId) {
		HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery()
				.processInstanceId(processInstanceId).singleResult();
		return historicProcessInstance;
	}

	/**
	 * 查询
	 * @param taskId
	 * @return
	 */
	private Task queryRuntimeTaskByIdInternal(String taskId) {
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		return task;
	}
	
	/**
	 * 内部函数，通过流程实例id查询运行时流程实例
	 * 
	 * @param processInstanceId
	 * @return
	 */
	private ProcessInstance queryRuntimeProcessInstanceByIdInternal(String processInstanceId) {
		ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
				.processInstanceId(processInstanceId).singleResult();
		return processInstance;

	}
	/**
	 * 详见接口文件
	 */
	@Override
	public String queryProcessDefinitionNameByRuntimeProcessInstanceId(String processInstanceId) {

		String processDefinitionName = null;
		ProcessInstance processInstance = queryRuntimeProcessInstanceByIdInternal(processInstanceId);
		// 发现在某种情况下即使运行时实例存在，但是却不能获取其定义名称，比如在service task的委托函数中，很奇怪，需要到历史记录中去获取
		if (processInstance != null) {
			processDefinitionName = processInstance.getProcessDefinitionName();
		}
		return processDefinitionName;

	}

	/**
	 * 详见接口文件
	 */
	@Override
	public String queryProcessDefinitionNameByHistoricProcessInstanceId(String processInstanceId) {
		String processDefinitionName = null;
		HistoricProcessInstance historicProcessInstance = queryHistoricProcessInstanceByIdInternal(processInstanceId);
		if (historicProcessInstance != null) {
			processDefinitionName = historicProcessInstance.getProcessDefinitionName();
		}
		return processDefinitionName;

	}

	/**
	 * 详见接口文件
	 */
	@Override
	public String queryProcessDefinitionKeyByRuntimeProcessInstanceId(String processInstanceId) {

		// 不能用运行时方法去查，因为在service task的委托中，如CompleteServiceDelegate中，查到的流程定义全部为null
		// 发现在某种情况下即使运行时实例存在，但是却不能获取其定义名称，比如在service task的委托函数中，很奇怪，需要到历史记录中去获取
		String processDefinitionKey = null;

		ProcessInstance processInstance = this.queryRuntimeProcessInstanceByIdInternal(processInstanceId);
		if (processInstance != null) {
			processDefinitionKey = processInstance.getProcessDefinitionKey();
		}
		return processDefinitionKey;
	}

	/**
	 * 详见接口文件
	 */
	@Override
	public String queryProcessDefinitionKeyByHistoricProcessInstanceId(String processInstanceId) {

		// 不能用运行时方法去查，因为在service task的委托中，如CompleteServiceDelegate中，查到的流程定义全部为null
		// 发现在某种情况下即使运行时实例存在，但是却不能获取其定义名称，比如在service task的委托函数中，很奇怪，需要到历史记录中去获取
		String processDefinitionKey = null;
		HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery()
				.processInstanceId(processInstanceId).singleResult();
		processDefinitionKey = historicProcessInstance.getProcessDefinitionKey();
		return processDefinitionKey;
	}

	/**
	 * 详见接口文件
	 */
	@Override
	public String queryTaskDefinitionKeyByRuntimTaskId(String taskId) {
		String taskDefinitionKey = null;
		Task task = this.queryRuntimeTaskByIdInternal(taskId);
		if (task != null) {
			taskDefinitionKey = task.getTaskDefinitionKey();
		}
		return taskDefinitionKey;
	}

	/**
	 * 认领任务
	 */
	private void claim(String taskId, String userId) {
		Task task = taskService.createTaskQuery().taskId(taskId).taskInvolvedUser(userId).singleResult();
		if (task != null) {
			taskService.claim(taskId, userId);
		} else {
			MyActivitiException activitiException = new MyActivitiException("您无权操作该任务");
			activitiException.setErrorCode(Contants.ErrorCodeEnum.Unknow);
			throw activitiException;
		}
	}

	/**
	 * 详见接口文件
	 */
	@Override
	public String queryHistoryTaskOfRuntimeInstanceAssigneeId(String processInstanceId, String taskDefinitionKey) {

		String assigneeId = null;
		if (processInstanceId != null && taskDefinitionKey != null) {
			List<HistoricTaskInstance> historicTaskInstanceList = historyService.createHistoricTaskInstanceQuery()
					.processInstanceId(processInstanceId).taskDefinitionKey(taskDefinitionKey)
					.orderByHistoricTaskInstanceEndTime().desc().list();
			if (historicTaskInstanceList != null && historicTaskInstanceList.size() > 0) {
				HistoricTaskInstance historicTaskInstance = historicTaskInstanceList.get(0);
				if (historicTaskInstance != null) {
					assigneeId = historicTaskInstance.getAssignee();
				}
			}
		}

		return assigneeId;
	}

	/**
	 * 详见接口文件
	 */
	@Override
	public String queryProcessDefinitionKeyByRuntimTaskId(String taskId) {
		String processDefinitionKey = null;
		Task task = this.queryRuntimeTaskByIdInternal(taskId);
		if (task != null) {
			ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
					.processInstanceId(task.getProcessInstanceId()).singleResult();
			processDefinitionKey = processInstance.getProcessDefinitionKey();
		}
		return processDefinitionKey;
	}

	/**
	 * 详见接口文件
	 */
	public String queryStartUserIdByRuntimeProcessInstanceId(String processInstanceId) {
		String startUserId = null;
		if (processInstanceId != null) {
			ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
					.processInstanceId(processInstanceId).singleResult();
			startUserId = processInstance.getStartUserId();
		}
		return startUserId;
	}

	/**
	 * 查询当前执行流的上一个任务
	 * 
	 * @param executionId
	 * @return
	 */
	private HistoricTaskInstance queryLastTaskByHistoricProcessInstanceId(String processInstanceId) {
		HistoricTaskInstance historicTaskInstance = null;
		List<HistoricTaskInstance> historicTaskInstanceList = historyService.createHistoricTaskInstanceQuery()
				.processInstanceId(processInstanceId).finished().orderByHistoricTaskInstanceEndTime().desc()
				.listPage(0, 1);
		if (historicTaskInstanceList != null && historicTaskInstanceList.size() > 0) {
			historicTaskInstance = historicTaskInstanceList.get(0);
		}
		return historicTaskInstance;
	}


	/**
	 * 用于获取正在完成的任务,监听器中使用
	 * @param executionId
	 * @return
	 */
	private Task queryLastRuntimeTaskByRuntimeExecutionId(String executionId) {
		List<Task> taskList = taskService.createTaskQuery().executionId(executionId).list();
		if (taskList != null && taskList.size() > 0) {
			return taskList.get(0);
		}
		return null;
	}

	/**
	 * 详见接口文件
	 */
	@Override
	public String queryLastTaskCommentMessageByRuntimeExecutionId(String executionId) {

		String commentMessage = null;
		Task task = queryLastRuntimeTaskByRuntimeExecutionId(executionId);
		if (task != null) {
			List<Comment> comments = taskService.getTaskComments(task.getId());
			if (comments != null && comments.size() > 0) {
				Comment comment = comments.get(0);
				commentMessage = comment.getFullMessage();
			}

		}
		return commentMessage;
	}

	/**
	 * 查询当前执行流最后一次的任务结果
	 * 
	 * @param executionId
	 * @return
	 */
	@Override
	public String queryLastHistoricTaskResultByRuntimeExecutionId(String executionId) {
		String value = (String) runtimeService.getVariable(executionId, Contants.ISAPPROVE);
		return value;
	}

	/**
	 * 获取历史任务流程本地变量
	 * 
	 * @param taskId
	 * @param variableName
	 * @return
	 */
	private Object queryHistoricTaskInstanceLocalVariable(String taskId, String variableName) {

		Object result = null;
		HistoricVariableInstance historicVariableInstance = historyService.createHistoricVariableInstanceQuery()
				.taskId(taskId).variableName(variableName).singleResult();
		if (historicVariableInstance != null) {
			result = historicVariableInstance.getValue();
		}
		return result;
	}

	/*
	 * private String queryHistoricVariable(String processInstanceId, String
	 * variableName) { HistoricVariableInstance historicVariableInstance =
	 * historyService. createHistoricVariableInstanceQuery().
	 * processInstanceId(processInstanceId). variableName(variableName).
	 * singleResult(); String value = (String)historicVariableInstance.getValue();
	 * return value; }
	 */
	/**
	 * 根据id拿用户信息,保存在redis里，失效时间为24小时
	 * 
	 * @param userId
	 * @return
	 */
	private UserDto findUserById(String userId) {
		/*
		String redisKey = Contants.REDIS_ACTIVITI_USER + userId.toString();
		UserDto user = null;
		try {
			user = (UserDto) redisService.get(redisKey);
		} catch (Exception ex) {
			logger.warn(ex.getMessage());
			user = null;
			redisService.del(redisKey);
		}
		if (user == null) {
			String warnMsg = "找不到用户用户(id：" + userId + ")相关信息";
			try {
				user = userService.findDtoById(Integer.parseInt(userId));
				if (user != null) {
					redisService.set(redisKey, user, Contants.REDIS_ACTIVITI_USERS_EXPIRE_TIME);
				} else {
					logger.warn(warnMsg);
				}
			} catch (Exception ex) {
				logger.warn(warnMsg);
			}
		}
		*/
		
		UserDto user = null;
		try {
			user = userService.findDtoById(Integer.parseInt(userId));
		} catch (Exception ex) {
			
		}		
		return user;

	}

	/**
	 * 获取任务权限
	 * 
	 * @param taskId
	 * @return
	 */
	private Set<User> queryIdentityLinkUsersForTask(String taskId) {
		Set<User> userList = new HashSet<User>();
		try {
			List<IdentityLink> identityLinkList = taskService.getIdentityLinksForTask(taskId);
			if (identityLinkList != null) {
				for (IdentityLink link : identityLinkList) {
					String userId = link.getUserId();
					if (userId != null) {
						User user = findUserById(userId);
						userList.add(user);						
					}
				}
			}
		} catch (Exception ex) {

		}
		return userList;
	}

	/**
	 * 检查某个任务是否有受理人
	 * 
	 * @param taskId
	 * @return
	 */
	private boolean checkIfTaskHasIdentityLinkUsers(String taskId) {
		List<IdentityLink> identityLinkList = taskService.getIdentityLinksForTask(taskId);
		if (identityLinkList != null && identityLinkList.size() > 0) {
			return true;
		}
		return false;
	}
	

	/**
	 * 详见接口文件
	 */
	@Override
	public String queryLastRuntimeTaskAssigneeByRuntimeExecutionId(String executionId) {
		String assignee = null;
		Task task = queryLastRuntimeTaskByRuntimeExecutionId(executionId);
		if (task != null) {
			assignee = task.getAssignee();
		}
		return assignee;
	}

	/**
	 * 创建oss附件
	 * 
	 * @param taskId
	 * @param attachmentName
	 * @param attachmentDescription
	 * @param url
	 * @return
	 */
	private Attachment createOssAttachmentInternal(String taskId, String attachmentName, String attachmentDescription,
			String url) {
		Attachment attachment = null;
		// boolean flag = ossService.doesKeyExist(url);
		// if (flag) {
		// 只要上传附件 就将该任务的受理人设置为当前用户，防止其他人设置该受理人
		String processInstanceId = this.queryRuntimeProcessInstanceIdByTaskId(taskId);
		if (processInstanceId != null) {
			String userId = ((Integer) session.getAttribute(Contants.LOGINUSERID)).toString();
			identityService.setAuthenticatedUserId(userId);
			attachment = taskService.createAttachment(Contants.ATTACHMENTTYPE, taskId, processInstanceId,
					attachmentName, attachmentDescription, url);
		}
		// }
		return attachment;
	}

	@Override
	public void deleteOssAttachment(String attachmentId, String ossKey) {
		if (StringUtils.isBlank(ossKey) && StringUtils.isBlank(attachmentId)) {
			throw new CommonException("文件不能为空");
		}
		if (!StringUtils.isBlank(attachmentId)) { // 在流程中删除附件
			Attachment attachment = taskService.getAttachment(attachmentId);
			String url = attachment.getUrl();
			ossKey = url.replaceAll(ossService.getUrlPrefix(), "");
			boolean ret = ossService.deleteObject(ossKey);
			if (ret == false) {
				throw new CommonException("附件删除失败");
			}
			taskService.deleteAttachment(attachmentId);
		} else if (!StringUtils.isBlank(ossKey)) { // 在流程开启页面删除附件
			boolean ret = ossService.deleteObject(ossKey);
			if (ret == false) {
				throw new CommonException("附件删除失败");
			}
		}
	}

	/**
	 * 详见接口文档
	 */
	@Override
	public Attachment createOssAttachment(String taskId, String attachmentName, String attachmentDescription,
			String url) {
		Attachment attachment = null;

		if (taskId != null && attachmentName != null && attachmentDescription != null && url != null
				&& !StringUtils.isBlank(taskId) && !StringUtils.isBlank(url)) {
			attachment = createOssAttachmentInternal(taskId, attachmentName, attachmentDescription, url);
		}
		return attachment;
	}

	/**
	 * 生成临时目录
	 * 
	 * @return
	 */
	private String getAttachmentOssProcessTempDirectoryInternal(String processDefinitionKey) {

		String sessionId = session.getId();
		String directory = OSSPREFIX + "Temp/" + sessionId + "/" + processDefinitionKey;
		return directory;
	}

	private String getAttachmentOssTempDirectoryInternal() {
		String directory = null;
		try {
			directory = OSSPREFIX + "Temp/";
		} catch (Exception ex) {

		}
		return directory;
	}

	/**
	 * 获取临时目录
	 * 
	 * @return
	 */
	private String getAttachmentOssTempSessionDirectoryInternal() {
		String directory = null;
		try {
			String sessionId = session.getId();
			directory = getAttachmentOssTempDirectoryInternal() + sessionId;
		} catch (Exception ex) {

		}
		return directory;
	}

	/**
	 * 详见接口文档
	 */
	@Override
	public void clearAttachmentOssTempDirectoryDaily() {
		String directory = this.getAttachmentOssTempDirectoryInternal();
		ossService.deleteDirectory(directory);
	}

	/**
	 * 详见接口文档
	 */
	@Override
	public void clearAttachmentOssTempSessionProcessDirectory(String processDefinitionKey) {
		String directory = this.getAttachmentOssProcessTempDirectoryInternal(processDefinitionKey);
		ossService.deleteDirectory(directory);
	}

	/**
	 * 详见接口文档
	 */
	@Override
	public void clearAttachmentOssTempSessionData() {
		// TODO Auto-generated method stub
		String directory = getAttachmentOssTempSessionDirectoryInternal();
		if (directory != null) {
			ossService.deleteDirectory(directory);
		}
	}

	/**
	 * 更改路径
	 * 
	 * @return
	 */
	private void moveAttachmentOssTempFile(String taskId, String processDefinitionKey) {
		String directory = getAttachmentOssProcessTempDirectoryInternal(processDefinitionKey);
		try {
			List<String> fileKeys = ossService.listDirectory(directory);
			for (String oldkey : fileKeys) {
				String[] splits = oldkey.split("--with-source-name:");
				String attachmentName = splits[splits.length - 1];
				String newKey = generateAttachmentOssKeyInternal(taskId, processDefinitionKey, attachmentName);
				ossService.moveFile(oldkey, newKey);
				String newUrl = ossService.getUrlPrefix() + newKey;
				createOssAttachmentInternal(taskId, attachmentName, "", newUrl);
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			MyActivitiException activitiException = new MyActivitiException("无法上传附件");
			activitiException.setErrorCode(Contants.ErrorCodeEnum.Unknow);
			throw activitiException;
		}
	}

	/**
	 * 生成oss url 内部函数s
	 * 
	 * @param processInstanceId
	 * @param taskId
	 * @param name
	 * @return
	 */
	private String generateAttachmentOssKeyInternal(String taskId, String processDefinitionKey, String attachmentName) {
		String url = null;
		UUID uuid = UUID.randomUUID();
		String userId = ((Integer) session.getAttribute(Contants.LOGINUSERID)).toString();
		if (StringUtils.isBlank(taskId)) {
			String directory = getAttachmentOssProcessTempDirectoryInternal(processDefinitionKey);
			url = directory + "/" + uuid + "--with-source-name:" + attachmentName;
		} else {
			Task task = this.queryRuntimeTaskByIdInternal(taskId);
			if (task != null) {
				String taskDefinitionKey = task.getTaskDefinitionKey();
				String processInstanceId = task.getProcessInstanceId();
				ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
						.processInstanceId(processInstanceId).singleResult();
				if (processInstance != null) {
					processDefinitionKey = processInstance.getProcessDefinitionKey();
					url = OSSPREFIX + processDefinitionKey + "/" + processInstanceId + "/" + taskDefinitionKey + "/"
							+ taskId + "/" + userId + "/" + uuid + "--with-source-name:" + attachmentName;
				} else {
					logger.warn("任务id: " + taskId + "不存在");
				}
			}
		}
		return url;
	}

	/**
	 * 详见接口文档
	 */
	@Override
	public String getAttachmentOssKey(String taskId, String processDefinitionKey, String attachmentName) {
		String url = this.generateAttachmentOssKeyInternal(taskId, processDefinitionKey, attachmentName);
		return url;
	}

	private List<Attachment> queryAttachmentiByProcessInstanceId(String processInstanceId) {
		return taskService.getProcessInstanceAttachments(processInstanceId);
	}
	
	/**
	 * 详见接口文档
	 */
	@Override
	public List<AttachmentEntity> queryAttachmentyListByProcessInstanceId(String processInstanceId) {
		List<AttachmentEntity>  attachmentEntityList = new ArrayList<AttachmentEntity>();
		List<Attachment> attachmentList = this.queryAttachmentiByProcessInstanceId(processInstanceId);
		for (Attachment attachment : attachmentList) {
			AttachmentEntity attachmentEntity = this.createAttachmentEntityByAttachment(attachment);
			attachmentEntityList.add(attachmentEntity);
		}
		return attachmentEntityList;
	}

	/**
	 * 详见接口文档
	 */
	@Override
	public AttachmentEntity queryLastAttachmentyByProcessInstanceId(String processInstanceId) {
		List<Attachment> attachmentList = this.queryAttachmentiByProcessInstanceId(processInstanceId);
		Date lastTime = null;
		Attachment lastAttachment = null;
		for (Attachment attachment : attachmentList) {
			Date time = attachment.getTime();
			if (lastTime == null || lastTime.getTime() < time.getTime()) {
				lastTime = time;
				lastAttachment = attachment;
			}			
		}
		AttachmentEntity attachmentEntity = this.createAttachmentEntityByAttachment(lastAttachment);
		return attachmentEntity;
	}
	
	
	/**
	 * 详见接口文件
	 */
	@Override
	public String queryRuntimeProcessInstanceIdByTaskId(String taskId) {
		String processInstanceId = null;
		Task task = this.queryRuntimeTaskByIdInternal(taskId);
		if (task != null) {
			processInstanceId = task.getProcessInstanceId();
		}
		return processInstanceId;
	}

	/**
	 * 查询所有运行时流程实例
	 * 
	 * @return
	 */
	@Override
	public List<String> queryAllRuntimeProcessInstanceId() {
		List<ProcessInstance> processInstanceList = runtimeService.createProcessInstanceQuery().list();
		List<String> processInstanceIdList = new ArrayList<String>();
		for (ProcessInstance processInstance : processInstanceList) {
			processInstanceIdList.add(processInstance.getId());
		}
		return processInstanceIdList;
	}

	/**
	 * 查询所有历史流程实例
	 * 
	 * @return
	 */
	@Override
	public List<String> queryAllHistoricProcessInstance() {
		List<HistoricProcessInstance> historicProcessInstanceList = historyService.createHistoricProcessInstanceQuery()
				.finished().list();
		List<String> historicProcessInstanceIdList = new ArrayList<String>();
		for (HistoricProcessInstance historicProcessInstance : historicProcessInstanceList) {
			historicProcessInstanceIdList.add(historicProcessInstance.getId());
		}
		return historicProcessInstanceIdList;
	}

	/**
	 * 通过流程实例创建自定义流程实例实体
	 * 
	 * @param processInstance
	 * @return
	 */
	private ProcessIntanceEntity createRuntimeProcessIntanceEntityByProcessIntance(ProcessInstance processInstance,
			List<TaskEntity> runtimeTaskEntities) {
		ProcessIntanceEntity processIntanceEntity = new ProcessIntanceEntity();
		processIntanceEntity.setProcessInstanceId(processInstance.getProcessInstanceId());
		processIntanceEntity.setProcessDefinitionKey(processInstance.getProcessDefinitionKey());
		processIntanceEntity.setProcessDefinitionName(processInstance.getProcessDefinitionName());
		int version = processInstance.getProcessDefinitionVersion();
		processIntanceEntity.setProcessDefinitionVersion(version);
		String userId = processInstance.getStartUserId();
		User user = findUserById(userId);
		if (user != null) {
			String userName = user.getName();
			processIntanceEntity.setStartUserName(userName);
			processIntanceEntity.setStartUserId(Integer.parseInt(userId));
		}
		SimpleDateFormat formatter = new SimpleDateFormat(DATEFORMAT);
		Date startTime = processInstance.getStartTime();
		String dateString = null;
		if (startTime != null) {
			dateString = formatter.format(startTime);
			processIntanceEntity.setStartTime(dateString);
		}
		Integer bussinessKey = Integer.parseInt(processInstance.getBusinessKey());
		processIntanceEntity.setBussinessKey(bussinessKey);

		if (runtimeTaskEntities == null) {
			runtimeTaskEntities = this
					.queryRuntimeTaskEntitysByProcessInstanceIdInternal(processInstance.getProcessInstanceId());
		}
		processIntanceEntity.setRuntimeTaskEntities(runtimeTaskEntities);
		return processIntanceEntity;
	}

	/**
	 * 内部函数，查询正在运行的流程信息
	 * 
	 * @param InitiatorId
	 *            发起人id
	 * @param processInstanceId
	 *            流程id
	 * @param involvedUserId
	 *            参与人id
	 * @param processDefinitionKey
	 *            流程定义
	 * @param pageNo
	 *            第几页
	 * @param pageSize
	 *            页最大显示数
	 * @return
	 */
	private PageInfo<ProcessIntanceEntity> queryRuntimeProcessInstanceInternal(String InitiatorId,
			String processInstanceId, String involvedUserId, String processDefinitionKey, Date startDate1,
			Date startDate2, int pageNo, int pageSize) {
		ProcessInstanceQuery processInstanceQuery = runtimeService.createProcessInstanceQuery();
		if (InitiatorId != null) {
			InitiatorId = InitiatorId.trim();
			if (InitiatorId.length() > 0) {
				processInstanceQuery = processInstanceQuery.startedBy(InitiatorId);
			}
		}
		if (processInstanceId != null) {
			processInstanceId = processInstanceId.trim();
			if (processInstanceId.length() > 0) {
				processInstanceQuery = processInstanceQuery.processInstanceId(processInstanceId);
			}
		}
		if (processDefinitionKey != null) {
			processDefinitionKey = processDefinitionKey.trim();
			if (processDefinitionKey.length() > 0) {
				processInstanceQuery = processInstanceQuery.processDefinitionKey(processDefinitionKey);
			}
		}
		if (startDate1 != null) {
			processInstanceQuery = processInstanceQuery.startedAfter(startDate1);
		}
		if (startDate2 != null) {
			processInstanceQuery = processInstanceQuery.startedBefore(startDate2);
		}
		if (involvedUserId != null) {
			involvedUserId = involvedUserId.trim();
			if (involvedUserId.length() > 0) {
				processInstanceQuery = processInstanceQuery.involvedUser(involvedUserId);
				/* 以下代码是用于将未处理的任务过滤，只查询已完成的任务 */
				List<HistoricTaskInstance> historicTaskInstanceList = historyService.createHistoricTaskInstanceQuery()
						.processUnfinished().finished().taskInvolvedUser(involvedUserId).list();
				Set<String> tmpProcessInstanceIds = new HashSet<String>();
				int size = historicTaskInstanceList.size();
				if (size > 0) {
					for (HistoricTaskInstance historicTaskInstance : historicTaskInstanceList) {
						String tmpProcessInstanceId = historicTaskInstance.getProcessInstanceId();
						tmpProcessInstanceIds.add(tmpProcessInstanceId);
					}
				}
				if (tmpProcessInstanceIds.size() == 0) {
					tmpProcessInstanceIds.add("0");
				}
				processInstanceQuery = processInstanceQuery.processInstanceIds(tmpProcessInstanceIds);
			}
		}
		pageNo = (pageNo < 1) ? 1 : pageNo;
		pageSize = (pageSize < 1) ? Contants.DEFAULTPAGESIZE : pageSize;
		int firstResult = (pageNo - 1) * pageSize;
		int maxResults = pageSize;
		long total = processInstanceQuery.count();

		List<ProcessInstance> processInstanceList = processInstanceQuery.orderByProcessDefinitionKey().desc()
				.listPage(firstResult, maxResults);

		List<ProcessIntanceEntity> processIntanceEntityList = new ArrayList<ProcessIntanceEntity>();
		for (ProcessInstance processInstance : processInstanceList) {
			ProcessIntanceEntity processIntanceEntity = createRuntimeProcessIntanceEntityByProcessIntance(
					processInstance, null);
			processIntanceEntityList.add(processIntanceEntity);
		}

		PageInfo<ProcessIntanceEntity> processIntanceEntityPageInfo = new PageInfo<>(processIntanceEntityList);
		processIntanceEntityPageInfo.setTotal(total);
		processIntanceEntityPageInfo.setPageNum(pageNo);
		processIntanceEntityPageInfo.setPageSize(pageSize);

		return processIntanceEntityPageInfo;
	}

	/**
	 * 通过历史流程实例创建自定义流程实例实体
	 * 
	 * @param historicProcessInstance
	 * @return
	 */
	private ProcessIntanceEntity createProcessIntanceEntityByHistoricProcessIntance(
			HistoricProcessInstance historicProcessInstance) {
		ProcessIntanceEntity processIntanceEntity = new ProcessIntanceEntity();
		String processInstanceId = historicProcessInstance.getId();
		processIntanceEntity.setProcessInstanceId(processInstanceId);
		processIntanceEntity.setProcessDefinitionKey(historicProcessInstance.getProcessDefinitionKey());
		processIntanceEntity.setProcessDefinitionName(historicProcessInstance.getProcessDefinitionName());
		int version = historicProcessInstance.getProcessDefinitionVersion();
		processIntanceEntity.setProcessDefinitionVersion(version);
		
		SimpleDateFormat formatter = new SimpleDateFormat(DATEFORMAT);
		Date startTime = historicProcessInstance.getStartTime();
		String dateString = null;
		if (startTime != null) {
			dateString = formatter.format(startTime);
			processIntanceEntity.setStartTime(dateString);
		}
		Date endTime = historicProcessInstance.getEndTime();
		if (endTime != null) {
			dateString = formatter.format(endTime);
			processIntanceEntity.setEndTime(dateString);
		}

		String userId = historicProcessInstance.getStartUserId();
		User user = findUserById(userId);
		if (user != null) {
			String userName = user.getName();
			processIntanceEntity.setStartUserName(userName);
			processIntanceEntity.setStartUserId(Integer.parseInt(userId));
		}
		Integer bussinessKey = Integer.parseInt(historicProcessInstance.getBusinessKey());
		processIntanceEntity.setBussinessKey(bussinessKey);

		processIntanceEntity.setResultApprove(1);
		HistoricTaskInstance taskInstance = this.queryLastTaskByHistoricProcessInstanceId(processInstanceId);
		Object oIsApproveLocal = queryHistoricTaskInstanceLocalVariable(taskInstance.getId(), Contants.ISAPPROVELOCAL);
		if (oIsApproveLocal != null) {
			Integer isApproveLocal = (Integer) oIsApproveLocal;
			processIntanceEntity.setResultApprove(isApproveLocal);
		}
		return processIntanceEntity;
	}

	/**
	 * 内部函数，查询历史流程信息
	 * 
	 * @param InitiatorId
	 *            发起人id
	 * @param processInstanceId
	 *            流程id
	 * @param involvedUserId
	 *            参与人id
	 * @param processDefinitionKey
	 *            流程定义
	 * @param pageNo
	 *            第几页
	 * @param pageSize
	 *            页最大显示数
	 * @return
	 */
	private PageInfo<ProcessIntanceEntity> queryHistoricProcessInstanceInternal(String InitiatorId,
			String processInstanceId, String involvedUserId, String processDefinitionKey, Date startDate1,
			Date startDate2, int pageNo, int pageSize) {

		HistoricProcessInstanceQuery historicProcessInstanceQuery = historyService.createHistoricProcessInstanceQuery()
				.finished();
		if (InitiatorId != null) {
			InitiatorId = InitiatorId.trim();
			if (InitiatorId.length() > 0) {
				historicProcessInstanceQuery = historicProcessInstanceQuery.startedBy(InitiatorId);
			}
		}
		if (processInstanceId != null) {
			processInstanceId = processInstanceId.trim();
			if (processInstanceId.length() > 0) {
				historicProcessInstanceQuery = historicProcessInstanceQuery.processInstanceId(processInstanceId);
			}
		}
		if (involvedUserId != null) {
			involvedUserId = involvedUserId.trim();
			if (involvedUserId.length() > 0) {
				historicProcessInstanceQuery = historicProcessInstanceQuery.involvedUser(involvedUserId);
			}
		}
		if (processDefinitionKey != null) {
			processDefinitionKey = processDefinitionKey.trim();
			if (processDefinitionKey.length() > 0) {
				historicProcessInstanceQuery = historicProcessInstanceQuery.processDefinitionKey(processDefinitionKey);
			}
		}
		if (startDate1 != null) {
			historicProcessInstanceQuery = historicProcessInstanceQuery.startedAfter(startDate1);
		}
		if (startDate2 != null) {
			historicProcessInstanceQuery = historicProcessInstanceQuery.startedBefore(startDate2);
		}
		pageNo = (pageNo < 1) ? 1 : pageNo;
		pageSize = (pageSize < 1) ? Contants.DEFAULTPAGESIZE : pageSize;
		int firstResult = (pageNo - 1) * pageSize;
		int maxResults = pageSize;
		long total = historicProcessInstanceQuery.count();

		List<HistoricProcessInstance> historicProcessInstanceList = historicProcessInstanceQuery
				.orderByProcessInstanceStartTime().desc().listPage(firstResult, maxResults);
		List<ProcessIntanceEntity> processIntanceEntityList = new ArrayList<ProcessIntanceEntity>();
		for (HistoricProcessInstance historicProcessInstance : historicProcessInstanceList) {

			ProcessIntanceEntity processIntanceEntity = createProcessIntanceEntityByHistoricProcessIntance(
					historicProcessInstance);
			processIntanceEntityList.add(processIntanceEntity);
		}

		PageInfo<ProcessIntanceEntity> processIntanceEntityPageInfo = new PageInfo<>(processIntanceEntityList);
		processIntanceEntityPageInfo.setTotal(total);
		processIntanceEntityPageInfo.setPageNum(pageNo);
		processIntanceEntityPageInfo.setPageSize(pageSize);
		return processIntanceEntityPageInfo;
	}

	/**
	 * 内部函数，查询当前任务列表
	 * 
	 * @param involvedUser
	 *            参与人
	 * @param pageNo
	 *            第几页
	 * @param pageSize
	 *            页最大显示数
	 * @return
	 */
	private PageInfo<Task> queryRuntimeTasksInternal(String involvedUserId, String processInstanceId,
			String processDefinitionKey, int pageNo, int pageSize) {

		TaskQuery taskQuery = taskService.createTaskQuery();
		if (involvedUserId != null) {
			involvedUserId = involvedUserId.trim();
			if (involvedUserId.length() > 0) {
				taskQuery = taskQuery.taskInvolvedUser(involvedUserId);
			}
		}
		if (processInstanceId != null) {
			processInstanceId = processInstanceId.trim();
			if (processInstanceId.length() > 0) {

				taskQuery = taskQuery.processInstanceId(processInstanceId);
			}
		}
		if (processDefinitionKey != null) {
			processDefinitionKey = processDefinitionKey.trim();
			if (processDefinitionKey.length() > 0) {
				taskQuery = taskQuery.processDefinitionKey(processDefinitionKey);
			}
		}

		pageNo = (pageNo < 1) ? 1 : pageNo;
		pageSize = (pageSize < 1) ? Contants.DEFAULTPAGESIZE : pageSize;
		int firstResult = (pageNo - 1) * pageSize;
		int maxResults = pageSize;
		long total = taskQuery.count();
		List<Task> taskList = taskQuery.orderByTaskCreateTime().desc().listPage(firstResult, maxResults);

		PageInfo<Task> taskPageInfo = new PageInfo<>(taskList);
		taskPageInfo.setTotal(total);
		taskPageInfo.setPageNum(pageNo);
		taskPageInfo.setPageSize(pageSize);

		return taskPageInfo;
	}

	/**
	 * 通过Task构建TaskEntity
	 * 
	 * @param task
	 * @return
	 */
	private TaskEntity createRuntimeTaskEntityByTask(Task task) {
		String taskId = task.getId();
		TaskEntity taskEntity = new TaskEntity();
		taskEntity.setTaskId(taskId);
		taskEntity.setTaskDefinitionKey(task.getTaskDefinitionKey());
		String taskDefinitionName = task.getName();
		taskEntity.setTaskDefinitionName(taskDefinitionName);
		Set<User> invokeUserList = this.queryIdentityLinkUsersForTask(taskId);
		List<UserItem> userItemList = new ArrayList<UserItem>();
		if (invokeUserList != null) {
			for (User user : invokeUserList) {
				UserItem userItem = taskEntity.new UserItem(user.getId(), user.getName());
				userItemList.add(userItem);
			}
			taskEntity.setInvokeUserItems(userItemList);
		}
		GraphicInfo graphicInfo = this.queryRuntimeTaskGraphicInfoByTaskIdInternal(taskId);
		taskEntity.setGraphicInfo(graphicInfo);
		SimpleDateFormat formatter = new SimpleDateFormat(DATEFORMAT);
		Date createTime = task.getCreateTime();
		if (createTime != null) {
			String dateString = formatter.format(createTime);
			taskEntity.setCreateTime(dateString);
		}
		int lastApprove = Contants.ResultEnum.Approve.ordinal();
		boolean ret = runtimeService.hasVariable(task.getExecutionId(), Contants.ISAPPROVE);
		if (ret) {
			lastApprove = (int) runtimeService.getVariable(task.getExecutionId(), Contants.ISAPPROVE);
		}
		taskEntity.setLastApprove(lastApprove);
		return taskEntity;
	}

	/**
	 * 从历史任务构造本地任务实体,不写入图形信息
	 * 
	 * @param historicTaskInstance
	 * @return
	 */
	private TaskEntity createHistoricTaskEntityWithoutGraphicInfo(HistoricTaskInstance historicTaskInstance) {
		String taskId = historicTaskInstance.getId();
		TaskEntity taskEntity = new TaskEntity();
		taskEntity.setTaskId(taskId);
		String taskDefinitionKey = historicTaskInstance.getTaskDefinitionKey();
		taskEntity.setTaskDefinitionKey(taskDefinitionKey);
		String taskDefinitionName = historicTaskInstance.getName();
		taskEntity.setTaskDefinitionName(taskDefinitionName);
		String comment = this.queryCommentsByTaskIdInternal(taskId);
		taskEntity.setComment(comment);
		String assigneeId = historicTaskInstance.getAssignee();
		UserDto user = findUserById(assigneeId);
		if (user != null) {
			String assigneeName = user.getName();
			String departmentName = user.getDname();
			taskEntity.setAssigneeId(user.getId());
			taskEntity.setAssigneeName(assigneeName);
			taskEntity.setDepartmentName(departmentName);
		}
		SimpleDateFormat formatter = new SimpleDateFormat(DATEFORMAT);
		String dateString = null;
		Date createTime = historicTaskInstance.getCreateTime();
		if (createTime != null) {
			dateString = formatter.format(createTime);
			taskEntity.setCreateTime(dateString);
		}
		Date endTime = historicTaskInstance.getEndTime();
		if (endTime != null) {
			dateString = formatter.format(endTime);
			taskEntity.setEndTime(dateString);
		}
		Object oIsApprove = queryHistoricTaskInstanceLocalVariable(taskId, Contants.ISAPPROVELOCAL);
		if (oIsApprove != null) {
			int isApprove = (int) oIsApprove;
			taskEntity.setIsApprove(isApprove);
		}
		
		Object oLastIsApprove = queryHistoricTaskInstanceLocalVariable(taskId, Contants.LASTISAPPROVELOCAL);
		if (oLastIsApprove != null) {
			int lastIsApprove = (int) oLastIsApprove;
			taskEntity.setLastApprove(lastIsApprove);
		}
		
		return taskEntity;
	}

	/**
	 * 创建历史任务实体，包含图形信息
	 * 
	 * @param processInstanceId
	 * @param historicTaskInstance
	 * @return
	 */
	private TaskEntity createHistoricTaskEntityWithGraphicInfo(String processInstanceId,
			HistoricTaskInstance historicTaskInstance) {
		TaskEntity taskEntity = createHistoricTaskEntityWithoutGraphicInfo(historicTaskInstance);
		String taskDefinitionKey = historicTaskInstance.getTaskDefinitionKey();
		Map<String, GraphicInfo> graphicInfoMap = queryHistoricTaskInstanceGraphicInfoMapByProcessInstanceIdInternal(
				processInstanceId);
		if (graphicInfoMap != null) {
			GraphicInfo graphicInfo = graphicInfoMap.get(taskDefinitionKey);
			taskEntity.setGraphicInfo(graphicInfo);
		}
		return taskEntity;
	}

	/**
	 * 生成自己的附件实体
	 * 
	 * @param attachment
	 * @return
	 */
	private AttachmentEntity createAttachmentEntityByAttachment(Attachment attachment) {
		AttachmentEntity attachmentEntity = null;
		if (attachment != null) {
			attachmentEntity = new AttachmentEntity();
			attachmentEntity.setContentId(attachment.getContentId());
			attachmentEntity.setDescription(attachment.getDescription());
			attachmentEntity.setId(attachment.getId());
			attachmentEntity.setName(attachment.getName());
			attachmentEntity.setProcessInstanceId(attachment.getProcessInstanceId());
			attachmentEntity.setAttachmentId(attachment.getId());
			attachmentEntity.setTaskId(attachment.getTaskId());
			attachmentEntity.setTime(attachment.getTime());
			attachmentEntity.setType(attachment.getType());
			attachmentEntity.setUrl(attachment.getUrl());
			String userId = attachment.getUserId();
			if (userId != null) {
				User user = this.findUserById(userId);
				if (user != null) {
					String userName = user.getName();
					attachmentEntity.setUserName(userName);
				}
			}
		}
		return attachmentEntity;
	}

	/**
	 * 查询指定流程所有代办任务
	 * 
	 * @param processInstanceId
	 * @return
	 */
	private List<Task> queryRuntimeTaskByProcessInstanceIdInternal(String processInstanceId) {
		List<Task> taskList = taskService.createTaskQuery().processInstanceId(processInstanceId).orderByTaskCreateTime()
				.desc().list();
		return taskList;
	}

	/**
	 * 通过流程实例获取当前任务列表
	 * 
	 * @param processInstanceId
	 * @return
	 */
	private List<TaskEntity> queryRuntimeTaskEntitysByProcessInstanceIdInternal(String processInstanceId) {
		List<Task> taskList = taskService.createTaskQuery().processInstanceId(processInstanceId).orderByTaskCreateTime()
				.desc().list();
		List<TaskEntity> taskEntityList = new ArrayList<TaskEntity>();
		for (Task task : taskList) {
			TaskEntity taskEntity = createRuntimeTaskEntityByTask(task);
			taskEntityList.add(taskEntity);
		}
		return taskEntityList;
	}

	/**
	 * 检查下个任务是否有审批人，如果没有抛出异常，回滚
	 * 
	 * @param processInstanceId
	 * @throws Exception
	 */
	private boolean checkTaskHasIdentityLinkUsersofProcessInstance(String processInstanceId) throws Exception {
		if (processInstanceId != null) {
			List<Task> nextTaskList = this.queryRuntimeTaskByProcessInstanceIdInternal(processInstanceId);
			if (nextTaskList != null) {
				for (Task task : nextTaskList) {
					if (!checkIfTaskHasIdentityLinkUsers(task.getId())) {
						return false;
					}
				}
				return true;
			}

		}
		return false;
	}

	/**
	 * 检查下一环节审批人设置是否正确
	 * 
	 * @param variables
	 */
	@SuppressWarnings("unchecked")
	private void checkNextCandidateUsersList(Map<String, Object> variables) {

		List<String> nextCandidateUsersList = null;
		try {
			nextCandidateUsersList = (List<String>) variables.get(Contants.NEXTCANDIDATEUSERS);
		} catch (Exception ex) {
			MyActivitiException activitiException = new MyActivitiException("审批人格式设置错误");
			activitiException.setErrorCode(Contants.ErrorCodeEnum.Unknow);
			throw activitiException;
		}
		if (nextCandidateUsersList == null) {
			MyActivitiException activitiException = new MyActivitiException("审批人格式设置错误");
			activitiException.setErrorCode(Contants.ErrorCodeEnum.Unknow);
			throw activitiException;
		}

		for (String strNextCandidateUser : nextCandidateUsersList) {
			try {
				Integer.parseInt(strNextCandidateUser);
			} catch (Exception ex) {
				MyActivitiException activitiException = new MyActivitiException("审批人格式设置错误");
				activitiException.setErrorCode(Contants.ErrorCodeEnum.Unknow);
				throw activitiException;
			}
			User user = findUserById(strNextCandidateUser);
			if (user == null) {
				MyActivitiException activitiException = new MyActivitiException("审批人不存在");
				activitiException.setErrorCode(Contants.ErrorCodeEnum.Unknow);
				throw activitiException;
			}

		}

	}

	/**
	 * 内部函数，处理一个任务
	 * 
	 * @param taskId
	 *            任务id
	 * @param comments
	 *            批注
	 * @param variables
	 *            自定义变量
	 * @param resultType
	 *            同意或者拒绝
	 * @throws Exception
	 *             异常
	 */
	private void handleTaskInternal(String taskId, String comments, Map<String, Object> variables, // 某些特殊变量
			int isApprove) throws Exception // 下一步为会签多任务
	{
		if (StringUtils.isBlank(comments)) {
			MyActivitiException activitiException = new MyActivitiException("审批意见不能为空");
			activitiException.setErrorCode(Contants.ErrorCodeEnum.ActivitiTaskHasNoComments);
			throw activitiException;
		}
		String processInstanceId = null;
		if (taskId != null) {
			Task task = this.queryRuntimeTaskByIdInternal(taskId);
			if (task == null) {
				throw new CommonException("该任务不存在，可能已经被他人处理");
			}
			processInstanceId = task.getProcessInstanceId();
			String executionId = task.getExecutionId();
			Execution execution = runtimeService.createExecutionQuery().executionId(executionId)
					.singleResult();
			String userId = ((Integer) session.getAttribute(Contants.LOGINUSERID)).toString();
			// String userId = "1000000";
			// 受理该任务
			this.claim(taskId, userId);
			
			// 设置任务本地变量Contants.ISAPPROVELOCAL 为同意
			taskService.setVariableLocal(taskId, Contants.ISAPPROVELOCAL, isApprove);			
			//上一步审批结果
			Integer lastIsApprove = null;
			Object obj = runtimeService.getVariable(executionId, Contants.ISAPPROVE);
			if (obj != null) {
				lastIsApprove = (Integer)obj;
			}
			taskService.setVariableLocal(taskId, Contants.LASTISAPPROVELOCAL, lastIsApprove);
			// 考虑到并行任务的情况，不能设置流程级变量,这些变量都是为该执行流后面的任务准备的
			runtimeService.setVariableLocal(executionId, Contants.ISAPPROVE, isApprove);
			// 如果没有设置下一环节审批人，默认设置为null
			if (!variables.containsKey(Contants.NEXTCANDIDATEUSERS)) {
				variables.put(Contants.NEXTCANDIDATEUSERS, null);
			} else { // 验证格式
				checkNextCandidateUsersList(variables);
			}
			runtimeService.setVariablesLocal(execution.getId(), variables);
			

			identityService.setAuthenticatedUserId(userId);
			taskService.addComment(taskId, processInstanceId, comments);
			taskService.complete(taskId);
		}

		// 当同意时测试下环节任务是否有审批人
		if (!checkTaskHasIdentityLinkUsersofProcessInstance(processInstanceId)) {
			MyActivitiException activitiException = new MyActivitiException("未设置审批人");
			activitiException.setErrorCode(Contants.ErrorCodeEnum.ActivitiTaskHasNoApprover);
			throw activitiException;

		}
	}

	/**
	 * 详见接口声明
	 */
	@Override
	public void handleRuntimeTask(String taskId, Map<String, Object> variables, String comments, int isApprove)
			throws Exception {
		handleTaskInternal(taskId, comments, variables, isApprove);
	}

	/**
	 * 查询某个任务的批注
	 * 
	 * @param taskId
	 * @return
	 */
	private String queryCommentsByTaskIdInternal(String taskId) {
		String commentMessage = null;
		List<Comment> comments = taskService.getTaskComments(taskId);
		if (comments != null && comments.size() > 0) {
			CommentEntity commentEntity = (CommentEntity) comments.get(0);
			commentMessage = commentEntity.getMessage();
		}
		return commentMessage;
	}

	/*
	 * 检查该业务主键是否是新的
	 */
	private boolean checkBussinessKeyIsNew(String processDefinitionKey, String bussinessKey) {
		long count = historyService.createHistoricProcessInstanceQuery().processDefinitionKey(processDefinitionKey)
				.processInstanceBusinessKey(bussinessKey).count();
		return count == 0 ? true : false;
	}

	/**
	 * 内部函数，开启一个流程实例
	 * 
	 * @param processDefinitionKey
	 *            流程定义
	 * @param bussinessKey
	 *            业务表主键
	 * @param variables
	 *            自定义变量
	 * @return
	 * @throws Exception
	 *             异常
	 */
	private String startProcessInternal(String processDefinitionKey, String bussinessKey, Map<String, Object> variables)
			throws Exception {

		boolean isNewBussinessKey = checkBussinessKeyIsNew(processDefinitionKey, bussinessKey);
		if (!isNewBussinessKey) {
			MyActivitiException activitiException = new MyActivitiException("您不能再次开启流程");
			activitiException.setErrorCode(Contants.ErrorCodeEnum.ActivitiBussinessKeyDuplicate);
			throw activitiException;
		}
		ProcessInstance processInstance = null;
		// String userId = "1000000";
		String userId = ((Integer) session.getAttribute(Contants.LOGINUSERID)).toString();
		// 设置流程发起人
		identityService.setAuthenticatedUserId(userId);
		processInstance = runtimeService.startProcessInstanceByKey(processDefinitionKey, bussinessKey);
		// 由于流程图中开始时间后马上是自己的业务，所以开始业务之后马上申请完成马上查询自己的业务并通过
		Task task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).taskAssignee(userId)
				.singleResult();
		String taskId = null;
		if (task != null) {
			taskId = task.getId();
			// 如果申请时创建过附件，则将附件从临时目录移动到正确目录
			moveAttachmentOssTempFile(taskId, processDefinitionKey);
		}
		handleTaskInternal(taskId, Contants.APPLYCOMMENT, variables, Contants.ResultEnum.Approve.ordinal());
		return processInstance.getId();
	}

	/**
	 * 详见接口声明
	 */
	@Override
	public String startProcess(String processDefinitionKey, String bussinessKey, Map<String, Object> variables)
			throws Exception {
		return startProcessInternal(processDefinitionKey, bussinessKey, variables);
	}

	/**
	 * 通过流程定义创建流程定义实体
	 * 
	 * @param processDefinition
	 * @return
	 */
	private ProcessDefinitionEntity createProcessDefinitionEntityByProcessDefinition(
			ProcessDefinition processDefinition) {

		ProcessDefinitionEntity processDefinitionEntity = new ProcessDefinitionEntity();
		processDefinitionEntity.setProcessDefinitionKey(processDefinition.getKey());
		processDefinitionEntity.setProcessDefinitionName(processDefinition.getName());
		return processDefinitionEntity;
	}

	/**
	 * 查询所有的流程定义
	 * 
	 * @return
	 */
	private PageInfo<ProcessDefinitionEntity> queryProcessDefinitionsInternal(int pageNo, int pageSize) {
		List<ProcessDefinitionEntity> processDefinitionEntityList = new ArrayList<ProcessDefinitionEntity>();
		pageNo = (pageNo < 1) ? 1 : pageNo;
		pageSize = (pageSize < 1) ? Contants.DEFAULTPAGESIZE : pageSize;
		int firstResult = (pageNo - 1) * pageSize;
		int maxResults = pageSize;
		ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery()
				.latestVersion();
		long total = processDefinitionQuery.count();
		List<ProcessDefinition> processDefinitionList = processDefinitionQuery.listPage(firstResult, maxResults);

		for (ProcessDefinition processDefinition : processDefinitionList) {
			ProcessDefinitionEntity processDefinitionEntity = createProcessDefinitionEntityByProcessDefinition(
					processDefinition);
			processDefinitionEntityList.add(processDefinitionEntity);
		}
		PageInfo<ProcessDefinitionEntity> processDefinitionEntityPageInfo = new PageInfo<>(processDefinitionEntityList);
		processDefinitionEntityPageInfo.setTotal(total);
		processDefinitionEntityPageInfo.setPageNum(pageNo);
		processDefinitionEntityPageInfo.setPageSize(pageSize);
		return processDefinitionEntityPageInfo;
	}

	/**
	 * 详见接口声明
	 */
	@Override
	public PageInfo<ProcessDefinitionEntity> queryProcessDefinitions(int pageNo, int pageSize) {

		PageInfo<ProcessDefinitionEntity> processDefinitionEntityPageInfo = queryProcessDefinitionsInternal(pageNo,
				pageSize);
		return processDefinitionEntityPageInfo;
	}

	/**
	 * 通过任务查询任务名称
	 * 
	 * @param taskDefinitionKey
	 * @return
	 */
	/*
	 * private String queryTaskDefinitionNameByTask(Task task) {
	 * 
	 * String taskDefinitionKey = task.getTaskDefinitionKey(); String
	 * processDefinitionId = task.getProcessDefinitionId(); BpmnModel bpmnModel =
	 * repositoryService.getBpmnModel(processDefinitionId); UserTask userTask =
	 * (UserTask) bpmnModel.getFlowElement(taskDefinitionKey); return
	 * userTask.getName(); return task.getName(); }
	 */
	/**
	 * 通过历史任务实例查流程定义
	 * 
	 * @param task
	 * @return
	 */

	/*
	 * private String queryTaskDefinitionNameByTask(HistoricTaskInstance task) {
	 * 
	 * String taskDefinitionKey = task.getTaskDefinitionKey(); String
	 * processDefinitionId = task.getProcessDefinitionId(); BpmnModel bpmnModel =
	 * repositoryService.getBpmnModel(processDefinitionId); UserTask userTask =
	 * (UserTask) bpmnModel.getFlowElement(taskDefinitionKey);
	 * 
	 * return task.getName(); }
	 */
	/**
	 * 详见接口声明
	 */
	@Override
	public PageInfo<ProcessIntanceEntity> queryRuntimeProcessInstance(String InitiatorId, String processInstanceId,
			String involvedUserId, String processDefinitionKey, Date startDate1, Date startDate2, int pageNo,
			int pageSize) {

		PageInfo<ProcessIntanceEntity> processIntanceEntityPageInfo = queryRuntimeProcessInstanceInternal(InitiatorId,
				processInstanceId, involvedUserId, processDefinitionKey, startDate1, startDate2, pageNo, pageSize);

		return processIntanceEntityPageInfo;
	}

	/**
	 * 详见接口声明
	 */
	@Override
	public PageInfo<ProcessIntanceEntity> queryHistoricProcessInstance(String InitiatorId, String processInstanceId,
			String involvedUserId, String processDefinitionKey, Date startDate1, Date startDate2, int pageNo,
			int pageSize) {

		PageInfo<ProcessIntanceEntity> processIntanceEntityPageInfo = queryHistoricProcessInstanceInternal(InitiatorId,
				processInstanceId, involvedUserId, processDefinitionKey, startDate1, startDate2, pageNo, pageSize);
		return processIntanceEntityPageInfo;
	}

	/**
	 * 详见接口声明
	 */
	@Override
	public PageInfo<ProcessIntanceEntity> queryRuntimeTasks(String involvedUserId, String processInstanceId,
			String processDefinitionKey, int pageNo, int pageSize) {
		PageInfo<Task> taskPageInfo = queryRuntimeTasksInternal(involvedUserId, processInstanceId, processDefinitionKey,
				pageNo, pageSize);

		List<ProcessIntanceEntity> processIntanceEntityList = new ArrayList<ProcessIntanceEntity>();
		List<Task> taskList = taskPageInfo.getList();
		for (Task task : taskList) {
			ProcessInstance processInstance = this.queryRuntimeProcessInstanceByIdInternal(task.getProcessInstanceId());
			if (processInstance != null) {

				List<TaskEntity> runtimeTaskEntities = new ArrayList<TaskEntity>();
				TaskEntity taskEntity = createRuntimeTaskEntityByTask(task);
				runtimeTaskEntities.add(taskEntity);
				ProcessIntanceEntity processIntanceEntity = createRuntimeProcessIntanceEntityByProcessIntance(
						processInstance, runtimeTaskEntities);
				processIntanceEntityList.add(processIntanceEntity);

			}
		}

		PageInfo<ProcessIntanceEntity> processIntanceEntityPageInfo = new PageInfo<>(processIntanceEntityList);
		processIntanceEntityPageInfo.setTotal(taskPageInfo.getTotal());
		processIntanceEntityPageInfo.setPageNum(taskPageInfo.getPageNum());
		processIntanceEntityPageInfo.setPageSize(pageSize);

		return processIntanceEntityPageInfo;
	}

	

	/**
	 * 查询当前任务个数
	 * 
	 * @param involvedUserId
	 * @param processInstanceId
	 * @param processDefinitionKey
	 * @return
	 */
	private long queryRuntimeTasksCountInternal(String involvedUserId, String processInstanceId,
			String processDefinitionKey) {
		TaskQuery taskQuery = taskService.createTaskQuery();
		if (involvedUserId != null) {
			involvedUserId = involvedUserId.trim();
			if (involvedUserId.length() > 0) {
				taskQuery = taskQuery.taskInvolvedUser(involvedUserId);
			}
		}
		if (processInstanceId != null) {
			processInstanceId = processInstanceId.trim();
			if (processInstanceId.length() > 0) {
				taskQuery = taskQuery.processInstanceId(processInstanceId);
			}
		}
		if (processDefinitionKey != null) {
			processDefinitionKey = processDefinitionKey.trim();
			if (processDefinitionKey.length() > 0) {
				taskQuery = taskQuery.processDefinitionKey(processDefinitionKey);
			}
		}

		long count = taskQuery.count();
		return count;
	}

	
	/**
	 * 详见接口声明
	 */
	@Override
	public long queryRuntimeTasksCount(String involvedUserId, String processInstanceId, String processDefinitionKey) {
		long count = this.queryRuntimeTasksCountInternal(involvedUserId, processInstanceId, processDefinitionKey);
		return count;
	}

	/**
	 * 查询流程个数内部函数
	 * @param InitiatorId
	 * @param involvedUserId
	 * @param processInstanceId
	 * @param processDefinitionKey
	 * @return
	 */
	private long queryRuntimeProcessInstanceCountInternal(String InitiatorId, String involvedUserId, String processInstanceId, String processDefinitionKey) {
		ProcessInstanceQuery processInstanceQuery = runtimeService.createProcessInstanceQuery();
		if (InitiatorId != null) {
			InitiatorId = InitiatorId.trim();
			if (InitiatorId.length() > 0) {
				processInstanceQuery = processInstanceQuery.startedBy(InitiatorId);
			}
		}
		if (processInstanceId != null) {
			processInstanceId = processInstanceId.trim();
			if (processInstanceId.length() > 0) {
				processInstanceQuery = processInstanceQuery.processInstanceId(processInstanceId);
			}
		}
		if (processDefinitionKey != null) {
			processDefinitionKey = processDefinitionKey.trim();
			if (processDefinitionKey.length() > 0) {
				processInstanceQuery = processInstanceQuery.processDefinitionKey(processDefinitionKey);
			}
		}
		if (involvedUserId != null) {
			involvedUserId = involvedUserId.trim();
			if (involvedUserId.length() > 0) {
				processInstanceQuery = processInstanceQuery.involvedUser(involvedUserId);
				/* 以下代码是用于将未处理的任务过滤，只查询已完成的任务 */
				List<HistoricTaskInstance> historicTaskInstanceList = historyService.createHistoricTaskInstanceQuery()
						.processUnfinished().finished().taskInvolvedUser(involvedUserId).list();
				Set<String> tmpProcessInstanceIds = new HashSet<String>();
				if (historicTaskInstanceList != null && historicTaskInstanceList.size() > 0) {
					for (HistoricTaskInstance historicTaskInstance : historicTaskInstanceList) {
						String tmpProcessInstanceId = historicTaskInstance.getProcessInstanceId();
						tmpProcessInstanceIds.add(tmpProcessInstanceId);
					}
				}
				if (tmpProcessInstanceIds.size() == 0) {
					tmpProcessInstanceIds.add("0");
				}
				processInstanceQuery = processInstanceQuery.processInstanceIds(tmpProcessInstanceIds);
			}
		}
		
		long count = processInstanceQuery.count();
		return count;
	}
	
	/**
	 * 详见接口声明
	 */
	@Override
	public long queryRuntimeProcessInstanceCount(String InitiatorId, String involvedUserId, String processInstanceId, String processDefinitionKey) {
		long count = this.queryRuntimeProcessInstanceCountInternal(InitiatorId, involvedUserId, processInstanceId, processDefinitionKey);
		return count;
	}
	
	/**
	 * 通过流程实例id查询历史任务
	 * 
	 * @param processInstanceId
	 * @return
	 */
	private List<HistoricTaskInstance> queryHistoricTaskInstancesInternalBy(String processInstanceId) {

		List<HistoricTaskInstance> historicTaskInstanceList = historyService.createHistoricTaskInstanceQuery()
				.processInstanceId(processInstanceId).finished().orderByHistoricTaskInstanceEndTime().asc().list();
		return historicTaskInstanceList;
	}

	/**
	 * 判断一个流程实例是否完成
	 * 
	 * @param processInstanceId
	 * @return
	 */
	private boolean processInstanceHasEnded(String processInstanceId) {
		ProcessInstance processInstance = this.queryRuntimeProcessInstanceByIdInternal(processInstanceId);
		if (processInstance != null) {
			return processInstance.isEnded();
		}
		return true;
	}

	/*
	private HistoricTaskInstance queryBeforeHistoricTaskInstanceInExecution(HistoricTaskInstance historicTaskInstance) {
		HistoricTaskInstance beforeHistoricTaskInstance = null;
		String executionId = historicTaskInstance.getExecutionId();
		Date endTime = historicTaskInstance.getEndTime();
		List<HistoricTaskInstance> historicTaskInstanceList = historyService.createHistoricTaskInstanceQuery()
				.executionId(executionId).taskCompletedBefore(endTime).orderByHistoricTaskInstanceEndTime().desc()
				.list();
		if (historicTaskInstanceList != null && historicTaskInstanceList.size() > 0) {
			beforeHistoricTaskInstance = historicTaskInstanceList.get(0);
		}
		return beforeHistoricTaskInstance;
	}
	*/
	
	/**
	 * 查询已完成历史任务，约定驳回时不能驳回到多实例，且必须驳回到主执行流
	 * 
	 * @param processInstanceId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<TaskEntity> queryNewestHistoricTasksInternal(String processInstanceId) {
		List<TaskEntity> taskEntityList = null;
		boolean isEndedProcessInstance = processInstanceHasEnded(processInstanceId);
		String redisKey = Contants.REDIS_ACTIVITI_NEWEST_HISTORIC_TASK + processInstanceId;
		if (isEndedProcessInstance) {
			try {
				taskEntityList = (List<TaskEntity>) redisService.get(redisKey);
			} catch (Exception ex) {
				logger.warn(ex.getMessage());
				taskEntityList = null;
				redisService.del(redisKey);
			}
			if (taskEntityList != null) {
				return taskEntityList;
			}
		}
		List<HistoricTaskInstance> newestHistoricTaskList = new ArrayList<HistoricTaskInstance>();
		// 先获取全部已完成的历史任务列表
		List<HistoricTaskInstance> historicTaskList = queryHistoricTaskInstancesInternalBy(processInstanceId);
		// 最新的已通过审批的任务列表 对于单实例节点，用于记录是否是否存在任务定义相同任务的容器
		HashMap<String, HistoricTaskInstance> historicTaskMap = new HashMap<String, HistoricTaskInstance>();
		for (HistoricTaskInstance historicTaskInstance : historicTaskList) {
			String taskDef = historicTaskInstance.getTaskDefinitionKey();
			if (this.isMultiTaskInstance(historicTaskInstance)) { // 假如说是多实例
				if (!historicTaskMap.containsKey(taskDef)) { // 如果历史中不存在，说明是第一个多实例，将其加入历史，否则不再加入
					historicTaskMap.put(taskDef, historicTaskInstance);
				}
			} else { // 如果不是多实例
				if (historicTaskMap.containsKey(taskDef)) { // 说明当前任务之前存在
					int lastIsApproveLocal = Contants.ResultEnum.Approve.ordinal();
					Object obj = this.queryHistoricTaskInstanceLocalVariable(historicTaskInstance.getId(), Contants.LASTISAPPROVELOCAL);
					if (obj != null) {
						lastIsApproveLocal = (Integer)obj;
					}
					if (lastIsApproveLocal == Contants.ResultEnum.Reject.ordinal()) { //如果是驳回的
						// 如果存在找到该之前的任务
						HistoricTaskInstance oldTask = historicTaskMap.get(taskDef);
						// 删除该节点（包括该节点）之后所有的节点
						int fromIndex = newestHistoricTaskList.indexOf(oldTask);
						if (fromIndex != -1) {
							int toIndex = newestHistoricTaskList.size();
	
							for (int i = fromIndex; i < toIndex; i++) {
								HistoricTaskInstance tmpTask = newestHistoricTaskList.get(i);
								String tmpTaskDef = tmpTask.getTaskDefinitionKey();
								historicTaskMap.remove(tmpTaskDef);
							}
							newestHistoricTaskList.subList(fromIndex, toIndex).clear();
						}
					}
				}
				if (!historicTaskMap.containsKey(taskDef)) {
					historicTaskMap.put(taskDef, historicTaskInstance);
				}
			}
			newestHistoricTaskList.add(historicTaskInstance);
		}

		taskEntityList = new ArrayList<TaskEntity>();
		for (HistoricTaskInstance historicTaskInstance : newestHistoricTaskList) {
			TaskEntity taskEntity = this.createHistoricTaskEntityWithoutGraphicInfo(historicTaskInstance);
			taskEntityList.add(taskEntity);
		}
		if (isEndedProcessInstance) {
			redisService.set(redisKey, taskEntityList, Contants.REDIS_ACTIVITI_HISTORY_EXPIRE_TIME);
		}
		return taskEntityList;
	}

	/**
	 * 查询流程详情
	 * 
	 * @param processInstanceId
	 * @return
	 */
	private ProcessIntanceEntity queryProcessInstanceWithDetailByIdInternal(String processInstanceId) {

		ProcessIntanceEntity processIntanceEntity = null;
		boolean isEndedProcessInstance = processInstanceHasEnded(processInstanceId);
		String redisKey = Contants.REDIS_ACTIVITI_PROCESSINSTANCE_DETAIL + processInstanceId;
		if (isEndedProcessInstance) {
			try {
				processIntanceEntity = (ProcessIntanceEntity) redisService.get(redisKey);
			} catch (Exception ex) {
				logger.warn(ex.getMessage());
				processIntanceEntity = null;
				redisService.del(redisKey);
			}
			if (processIntanceEntity != null) {
				return processIntanceEntity;
			}
		}

		HistoricProcessInstance historicProcessInstance = queryHistoricProcessInstanceByIdInternal(processInstanceId);

		if (historicProcessInstance != null) {
			processIntanceEntity = createProcessIntanceEntityByHistoricProcessIntance(historicProcessInstance);

			// 查询在该流程实例下已完成的历史任务
			List<TaskEntity> newestHistoricTaskentities = queryNewestHistoricTasksInternal(processInstanceId);
			processIntanceEntity.setNewestHistoricTaskEntities(newestHistoricTaskentities);

			List<TaskEntity> runtimeTaskEntities = this
					.queryRuntimeTaskEntitysByProcessInstanceIdInternal(processInstanceId);
			processIntanceEntity.setRuntimeTaskEntities(runtimeTaskEntities);

			List<Attachment> attachmentiList = queryAttachmentiByProcessInstanceId(processInstanceId);
			List<AttachmentEntity> attachmentEntityList = new ArrayList<AttachmentEntity>();
			for (Attachment attachment : attachmentiList) {
				AttachmentEntity attachmentEntity = this.createAttachmentEntityByAttachment(attachment);
				attachmentEntityList.add(attachmentEntity);
			}
			processIntanceEntity.setAttachmentList(attachmentEntityList);

		}
		if (isEndedProcessInstance) {
			redisService.set(redisKey, processIntanceEntity, Contants.REDIS_ACTIVITI_HISTORY_EXPIRE_TIME);
		}
		return processIntanceEntity;
	}

	/**
	 * 详见接口声明
	 */
	@Override
	public ProcessIntanceEntity queryProcessInstanceWithDetailById(String processInstanceId) {

		ProcessIntanceEntity processIntanceEntity = queryProcessInstanceWithDetailByIdInternal(processInstanceId);
		return processIntanceEntity;
	}

	/**
	 * 查询已完成历史任务,按完成时间的升序排列
	 * 
	 * @param processInstanceId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<TaskEntity> queryHistoricTasksEntitiesInternal(String processInstanceId) {

		List<TaskEntity> taskEntityList = null;
		boolean isEndedProcessInstance = processInstanceHasEnded(processInstanceId);
		String redisKey = Contants.REDIS_ACTIVITI_HISTORIC_TASK + processInstanceId;
		if (isEndedProcessInstance) {
			try {
				taskEntityList = (List<TaskEntity>) redisService.get(redisKey);
			} catch (Exception ex) {
				logger.warn(ex.getMessage());
				taskEntityList = null;
				redisService.del(redisKey);
			}
			if (taskEntityList != null) {
				return taskEntityList;
			}
		}

		List<HistoricTaskInstance> historicTaskInstanceList = queryHistoricTaskInstancesInternalBy(processInstanceId);

		taskEntityList = new ArrayList<TaskEntity>();
		for (HistoricTaskInstance historicTaskInstance : historicTaskInstanceList) {
			TaskEntity taskEntity = this.createHistoricTaskEntityWithGraphicInfo(processInstanceId,
					historicTaskInstance);
			taskEntityList.add(taskEntity);
		}
		if (isEndedProcessInstance) {
			redisService.set(redisKey, taskEntityList, Contants.REDIS_ACTIVITI_HISTORY_EXPIRE_TIME);
		}
		return taskEntityList;
	}

	/**
	 * 获取流程所有历史任务走过的节点的坐标
	 * 
	 * @param processInstanceId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private Map<String, GraphicInfo> queryHistoricTaskInstanceGraphicInfoMapByProcessInstanceIdInternal(
			String processInstanceId) {
		Map<String, GraphicInfo> graphicInfoMap = null;
		HistoricProcessInstance processInstance = queryHistoricProcessInstanceByIdInternal(processInstanceId);
		if (processInstance == null) {
			return null;
		}
		// 获取流程定义model
		boolean isEndedProcessInstance = processInstanceHasEnded(processInstanceId);
		String redisKey = Contants.REDIS_ACTIVITI_TASK_GRAPHICINFO_MAP + processInstanceId;
		if (isEndedProcessInstance) {
			try {
				graphicInfoMap = (Map<String, GraphicInfo>) redisService.get(redisKey);
			} catch (Exception ex) {
				logger.warn(ex.getMessage());
				graphicInfoMap = null;
				redisService.del(redisKey);
			}
			if (graphicInfoMap != null) {
				return graphicInfoMap;
			}
		}
		graphicInfoMap = new HashMap<String, GraphicInfo>();
		String processDefinitionId = processInstance.getProcessDefinitionId();
		List<HistoricTaskInstance> historicTaskInstanceList = queryHistoricTaskInstancesInternalBy(processInstanceId);
		BpmnModel bpmnModel = repositoryService.getBpmnModel(processDefinitionId);

		for (HistoricTaskInstance historicTaskInstance : historicTaskInstanceList) {
			FlowElement currentFlowElement = bpmnModel.getFlowElement(historicTaskInstance.getTaskDefinitionKey());
			GraphicInfo currentFlowElementGraphicInfo = bpmnModel.getGraphicInfo(currentFlowElement.getId());
			graphicInfoMap.put(currentFlowElement.getId(), currentFlowElementGraphicInfo);
		}
		if (isEndedProcessInstance) {
			redisService.set(redisKey, graphicInfoMap, Contants.REDIS_ACTIVITI_HISTORY_EXPIRE_TIME);

		}
		return graphicInfoMap;
	}
	
	/**
	 * 查询运行时task图形信息
	 * @param taskId
	 * @return
	 */
	private GraphicInfo queryRuntimeTaskGraphicInfoByTaskIdInternal(String taskId) {
		GraphicInfo graphicInfo = null;
		Task task = this.queryRuntimeTaskByIdInternal(taskId);
		String processInstanceId = task.getProcessInstanceId();
		HistoricProcessInstance processInstance = queryHistoricProcessInstanceByIdInternal(processInstanceId);
		String processDefinitionId = processInstance.getProcessDefinitionId();
		BpmnModel bpmnModel = repositoryService.getBpmnModel(processDefinitionId);
		FlowElement flowElement = bpmnModel.getFlowElement(task.getTaskDefinitionKey());
		graphicInfo = bpmnModel.getGraphicInfo(flowElement.getId());
		return graphicInfo;
	}

	/**
	 * 查询流程实例，携带所有流转的历史任务
	 * 
	 * @param processInstanceId
	 * @return
	 */
	private ProcessIntanceEntity queryHistoricProcessInstanceWithAllHistoricTaskInstacnesInternal(
			String processInstanceId) {
		ProcessIntanceEntity processIntanceEntity = null;
		boolean isEndedProcessInstance = processInstanceHasEnded(processInstanceId);
		String redisKey = Contants.REDIS_ACTIVITI_PROCESSINSTANCE_WITH_HISTORIC_TASK + processInstanceId;
		if (isEndedProcessInstance) {
			try {
				processIntanceEntity = (ProcessIntanceEntity) redisService.get(redisKey);
			} catch (Exception ex) {
				logger.warn(ex.getMessage());
				processIntanceEntity = null;
				redisService.del(redisKey);
			}
			if (processIntanceEntity != null) {
				return processIntanceEntity;
			}
		}
		processIntanceEntity = new ProcessIntanceEntity();

		HistoricProcessInstance historicProcessInstance = this
				.queryHistoricProcessInstanceByIdInternal(processInstanceId);
		if (historicProcessInstance != null) {
			processIntanceEntity = createProcessIntanceEntityByHistoricProcessIntance(historicProcessInstance);

			List<TaskEntity> historicTaskentities = queryHistoricTasksEntitiesInternal(processInstanceId);
			processIntanceEntity.setHistoricTaskentities(historicTaskentities);
			Map<String, GraphicInfo> graphicInfoMap = queryHistoricTaskInstanceGraphicInfoMapByProcessInstanceIdInternal(
					processInstanceId);
			processIntanceEntity.setGraphicInfoMap(graphicInfoMap);
		}
		if (isEndedProcessInstance) {
			redisService.set(redisKey, processIntanceEntity, Contants.REDIS_ACTIVITI_HISTORY_EXPIRE_TIME);
		}
		return processIntanceEntity;
	}

	/**
	 * 详见接口文档
	 */
	@Override
	public String queryRuntimeTaskInstanceExecutionId(String taskId) {
		Task task = this.queryRuntimeTaskByIdInternal(taskId);
		return task.getExecutionId();
	}

	/**
	 * 详见接口文档
	 */
	@Override
	public Object queryRuntimeExecutionLocalVariable(String executionId, String variableName) {
		Object obj = runtimeService.getVariable(executionId, variableName);
		return obj;
	}

	/**
	 * 详见接口文档
	 */
	@Override
	public ProcessIntanceEntity queryProcessInstanceWithAllHistoricTasks(String processInstanceId) {

		ProcessIntanceEntity processIntanceEntity = queryHistoricProcessInstanceWithAllHistoricTaskInstacnesInternal(
				processInstanceId);
		return processIntanceEntity;
	}

	/**
	 * 判断任务是否是多实例
	 * 
	 * @param taskId
	 * @return
	 */
	private boolean isMultiTaskInstance(HistoricTaskInstance historicTaskInstance) {
		String processDefinitionId = historicTaskInstance.getProcessDefinitionId();
		BpmnModel bpmnModel = repositoryService.getBpmnModel(processDefinitionId);
		String taskDefinitionKey = historicTaskInstance.getTaskDefinitionKey();
		FlowElement taskElement = bpmnModel.getFlowElement(taskDefinitionKey);
		Activity activity = (Activity) taskElement;
		boolean flag = activity.hasMultiInstanceLoopCharacteristics();
		return flag;
	}

}
