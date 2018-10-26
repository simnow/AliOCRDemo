package cn.caecc.erp.support.constant;

public class Contants {
	
	//------------------生产环境配置--------------------------
	/*
	public static final String REDIS_CONFIG = "classpath:redis.prod.properties";
	public static final String DB_CONFIG = "classpath:db.prod.properties";
	public static final String OSS_CONFIG = "classpath:oss.prod.properties";
	public static final String STS_CONFIG = "classpath:sts.prod.properties";
	public static final String SMS_CONFIG = "classpath:sms.prod.properties";
	*/
	
	//------------------开发环境配置--------------------------
	
	public static final String REDIS_CONFIG = "classpath:redis.properties";
	public static final String DB_CONFIG = "classpath:db.properties";
	public static final String OSS_CONFIG = "classpath:oss.properties";
	public static final String STS_CONFIG = "classpath:sts.properties";
	public static final String SMS_CONFIG = "classpath:sms.properties";
	
	//-----------------------------------------------

	public static final int DEFAULTPAGESIZE = 15;

	// ----------session-----------------------
	public static final String LOGINUSERID = "loginUserId";

	public static final String LOGINLOGID = "loginLogId";
	// ----------session-----------------------


	//---------------------------shiro权限---------------------------------
	
	// 管理员
	public static final String ADMINROLE = "admin";
	// 用户
	public static final String USERROLE = "user";
	// 正职
	public static final String LEADERROLE = "leader";
	// 副职
	public static final String EPUTYROLE = "eputy";
	
	
	public static final String USER_SELECT_PERMISSION = "user_select_permission";
	
	public static final String USER_ADD_PERMISSION = "user_add_permission";
	
	public static final String USER_UPDATE_PERMISSION = "user_update_permission";
	
	public static final String USER_DEL_PERMISSION = "user_del_permission";
	
	
	public static final String DEPARTMENT_SELECT_PERMISSION = "department_select_permission";
	
	public static final String DEPARTMENT_ADD_PERMISSION = "department_add_permission";

	public static final String DEPARTMENT_UPDATE_PERMISSION = "department_update_permission";

	public static final String DEPARTMENT_DEL_PERMISSION = "department_del_permission";
	

	public static final String GOODS_SELECT_PERMISSION = "goods_select_permission";

	public static final String GOODS_ADD_PERMISSION = "goods_add_permission";

	public static final String GOODS_UPDATE_PERMISSION = "goods_update_permission";

	public static final String GOODS_DELETE_PERMISSION = "goods_delete_permission";
	
	

	public static final String SUPPLIER_SELECT_PERMISSION = "supplier_select_permission";

	public static final String SUPPLIER_ADD_PERMISSION = "supplier_add_permission";

	public static final String SUPPLIER_UPDATE_PERMISSION = "supplier_update_permission";

	public static final String SUPPLIER_DEL_PERMISSION = "supplier_del_permission";
	
	

	public static final String POSITION_SELECT_PERMISSION = "position_select_permission";

	public static final String POSITION_ADD_PERMISSION = "position_add_permission";

	public static final String POSITION_UPDATE_PERMISSION = "position_update_permission";

	public static final String POSITION_DELETE_PERMISSION = "position_delete_permission";
	
	

	public static final String CONTRACT_PROCESS_START_PERMISSION = "contract_process_start_permission";

	public static final String CONTRACT_SELECT_PERMISSION = "contract_select_permission";
	
	public static final String CONTRACT_ADD_PERMISSION = "contract_add_permission";
	
	public static final String CONTRACT_UPDATE_PERMISSION = "contract_update_permission";
	
	public static final String CONTRACT_DELETE_PERMISSION = "contract_delete_permission";
	
	

	public static final String PROJECT_PROCESS_START_PERMISSION = "project_process_start_permission";
	
	public static final String PROJECT_ADD_PERMISSION = "project_add_permission";
	
	public static final String PROJECT_UPDATE_PERMISSION = "project_update_permission";
	
	public static final String PROJECT_DELETE_PERMISSION = "project_delete_permission";
	
	public static final String PROJECT_SELECT_PERMISSION = "project_select_permission";
	
	
	

	public static final String TENDERING_PROCESS_START_PERMISSION = "tendering_process_start_permission";
	
	public static final String TENDERING_SELECT_PERMISSION = "tendering_select_permission";
	
	public static final String TENDERING_ADD_PERMISSION = "tendering_add_permission";
	
	public static final String TENDERING_UPDATE_PERMISSION = "tendering_update_permission";
	
	public static final String TENDERING_DELETE_PERMISSION = "tendering_delete_permission";
	
	

	public static final String WORKLOAD_PROCESS_START_PERMISSION = "workload_process_start_permission";
	
	public static final String WORKLOAD_SELECT_PERMISSION = "workload_select_permission";
	
	public static final String WORKLOAD_ADD_PERMISSION = "workload_add_permission";
	
	public static final String WORKLOAD_UPDATE_PERMISSION = "workload_update_permission";
	
	public static final String WORKLOAD_DELETE_PERMISSION = "workload_delete_permission";
	
	

	public static final String MATERIAL_PROCESS_START_PERMISSION = "material_process_start_permission";
	
	public static final String MATERIAL_SELECT_PERMISSION = "material_select_permission";
	
	public static final String MATERIAL_ADD_PERMISSION = "material_add_permission";
	
	public static final String MATERIAL_UPDATE_PERMISSION = "material_update_permission";
	
	public static final String MATERIAL_DELETE_PERMISSION = "material_delete_permission";
	
	

	public static final String FIXEDASSETS_PROCESS_START_PERMISSION = "fixedassets_process_start_permission";
	
	public static final String FIXEDASSETS_SELECT_PERMISSION = "fixedassets_select_permission";
	
	public static final String FIXEDASSETS_ADD_PERMISSION = "fixedassets_add_permission";
	
	public static final String FIXEDASSETS_UPDATE_PERMISSION = "fixedassets_update_permission";
	
	public static final String FIXEDASSETS_DELETE_PERMISSION = "fixedassets_delete_permission";
	
	

	public static final String OFFICESUPPLY_PROCESS_START_PERMISSION = "officesupply_process_start_permission";
	
	public static final String OFFICESUPPLY_SELECT_PERMISSION = "officesupply_select_permission";
	
	public static final String OFFICESUPPLY_ADD_PERMISSION = "officesupply_add_permission";
	
	public static final String OFFICESUPPLY_UPDATE_PERMISSION = "officesupply_update_permission";
	
	public static final String OFFICESUPPLY_DELETE_PERMISSION = "officesupply_delete_permission";

	public static final String ROLE_MANAGE_PERMISSION = "role_manage_permission";

	
	public static final String CONFERENCE_ADD_PERMISSION = "conference_add_permission";
	
	public static final String CONFERENCE_UPDATE_PERMISSION = "conference_update_permission";
	
	public static final String CONFERENCE_DELETE_PERMISSION = "conference_delete_permission";
	
	public static final String CONFERENCE_SELECT_PERMISSION = "conference_select_permission";
	

	public static final String WORK_PLAN_ADD_PERMISSION = "work_plan_add_permission";
	
	public static final String WORK_PLAN_UPDATE_PERMISSION = "work_plan_update_permission";
	
	public static final String WORK_PLAN_DELETE_PERMISSION = "work_plan_delete_permission";

	public static final String WORK_SUMMARY_ADD_PERMISSION = "work_summary_add_permission";
	
	public static final String WORK_SUMMARY_UPDATE_PERMISSION = "work_summary_update_permission";
	
	public static final String WORK_SUMMARY_DELETE_PERMISSION = "work_summary_delete_permission";

	public static final String TASK_ADD_PERMISSION = "task_add_permission";
	
	public static final String TASK_UPDATE_PERMISSION = "task_update_permission";
	
	public static final String TASK_DELETE_PERMISSION = "task_delete_permission";
	
	
	public static final String CARD_UPDATE_PERMISSION = "card_update_permission";
	
	public static final String CARD_SELECT_PERMISSION = "card_select_permission";
	
	public static final String CARD_PROCESS_START_PERMISSION = "card_process_start_permission";
	
	public static final String CARD_DELETE_PERMISSION = "card_delete_permission";
	
	public static final String CARD_ADD_PERMISSION = "card_add_permission";
	

	public static final String DISPATCH_PROCESS_START_PERMISSION = "dispatch_process_start_permission";
	
	public static final String DISPATCH_SELECT_PERMISSION = "dispatch_select_permission";
	
	public static final String DISPATCH_ADD_PERMISSION = "dispatch_add_permission";
	
	public static final String DISPATCH_UPDATE_PERMISSION = "dispatch_update_permission";
	
	public static final String DISPATCH_DELETE_PERMISSION = "dispatch_delete_permission";
	

	public static final String EVALUATE_PROCESS_START_PERMISSION = "evaluate_process_start_permission";
	
	public static final String EVALUATE_SELECT_PERMISSION = "evaluate_select_permission";
	
	public static final String EVALUATE_ADD_PERMISSION = "evaluate_add_permission";
	
	public static final String EVALUATE_UPDATE_PERMISSION = "evaluate_update_permission";
	
	public static final String EVALUATE_DELETE_PERMISSION = "evaluate_delete_permission";

	
	
	public static final String EVENT_PROCESS_START_PERMISSION = "event_process_start_permission";
	
	public static final String EVENT_SELECT_PERMISSION = "event_select_permission";
	
	public static final String EVENT_ADD_PERMISSION = "event_add_permission";
	
	public static final String EVENT_UPDATE_PERMISSION = "event_update_permission";
	
	public static final String EVENT_DELETE_PERMISSION = "event_delete_permission";

	
	public static final String OUTSIDE_PROCESS_START_PERMISSION = "outside_process_start_permission";
	
	public static final String OUTSIDE_SELECT_PERMISSION = "outside_select_permission";
	
	public static final String OUTSIDE_ADD_PERMISSION = "outside_add_permission";
	
	public static final String OUTSIDE_UPDATE_PERMISSION = "outside_update_permission";
	
	public static final String OUTSIDE_DELETE_PERMISSION = "outside_delete_permission";
	

	public static final String QUALITY_FEEDBACK_PROCESS_START_PERMISSION = "quality_feedback_process_start_permission";
	
	public static final String QUALITY_FEEDBACK_SELECT_PERMISSION = "quality_feedback_select_permission";
	
	public static final String QUALITY_FEEDBACK_ADD_PERMISSION = "quality_feedback_add_permission";
	
	public static final String QUALITY_FEEDBACK_UPDATE_PERMISSION = "quality_feedback_update_permission";
	
	public static final String QUALITY_FEEDBACK_DELETE_PERMISSION = "quality_feedback_delete_permission";

	
	public static final String SEAL_PROCESS_START_PERMISSION = "seal_process_start_permission";
	
	public static final String SEAL_SELECT_PERMISSION = "seal_select_permission";
	
	public static final String SEAL_ADD_PERMISSION = "seal_add_permission";
	
	public static final String SEAL_UPDATE_PERMISSION = "seal_update_permission";
	
	public static final String SEAL_DELETE_PERMISSION = "seal_delete_permission";
		
	
	public static final String VEHICLE_PROCESS_START_PERMISSION = "vehicle_process_start_permission";
	
	public static final String VEHICLE_SELECT_PERMISSION = "vehicle_select_permission";
	
	public static final String VEHICLE_ADD_PERMISSION = "vehicle_add_permission";
	
	public static final String VEHICLE_UPDATE_PERMISSION = "vehicle_update_permission";
	
	public static final String VEHICLE_DELETE_PERMISSION = "vehicle_delete_permission";


	//------------井管理--------------
	public static final String Well_PT="pt"; //普通钻井
	
	public static final String Well_DX="dx"; //定向井服务
	

	//---------------生产技术模块权限
	public static final String WELL_SELECT_PERMISSION = "well_select_permission";	//查询井权限
	
	public static final String WELL_ADD_PERMISSION = "well_add_permission";  		//添加井权限
	
	public static final String WELL_UPDATE_PERMISSION = "well_update_permission";	//更新井权限
	
	public static final String WELL_DELETE_PERMISSION = "well_delete_permission";	//删除井权限
	
	public static final String DRILLING_DAILY_LIST_SELECT_PERMISSION = "drilling_daily_list_select_permission";		//查看钻井日报权限
	
	public static final String DRILLING_DAILY_LIST_ADD_PERMISSION = "drilling_daily_list_add_permission";			//添加钻井日报权限
	
	public static final String DRILLING_DAILY_LIST_UPDATE_PERMISSION = "drilling_daily_list_update_permission";		//更新钻井日报权限
	
	public static final String DRILLING_DAILY_LIST_DELETE_PERMISSION = "drilling_daily_list_delete_permission";		//删除钻井日报权限
	
	public static final String AFTERNOON_DAILY_LIST_SELECT_PERMISSION = "afternoon_daily_list_select_permission";	//查看下午日报权限
	
	public static final String AFTERNOON_DAILY_LIST_ADD_PERMISSION = "afternoon_daily_list_add_permission";			//添加下午日报权限
	
	public static final String AFTERNOON_DAILY_LIST_UPDATE_PERMISSION = "afternoon_daily_list_update_permission";			//更新下午日报权限
	
	public static final String AFTERNOON_DAILY_LIST_DELETE_PERMISSION = "afternoon_daily_list_delete_permission";			//删除下午日报权限
	
	public static final String DIRECTED_DAILY_LIST_SELECT_PERMISSION = "directed_daily_list_select_permission";			//查看定向日报权限
	
	public static final String DIRECTED_DAILY_LIST_ADD_PERMISSION = "directed_daily_list_add_permission";			//添加定向日报权限
	
	public static final String DIRECTED_DAILY_LIST_UPDATE_PERMISSION = "directed_daily_list_update_permission";			//更新定向日报权限
	
	public static final String DIRECTED_DAILY_LIST_DELETE_PERMISSION = "directed_daily_list_delete_permission";			//删除定向日报权限
	
	public static final String NET_ELECTRIC_DAILY_SELECT_PERMISSION = "net_electric_daily_select_permission";			//查看网电日报权限
	
	public static final String NET_ELECTRIC_DAILY_ADD_PERMISSION = "net_electric_daily_add_permission";			//添加查看网电日报权限
	
	public static final String NET_ELECTRIC_DAILY_UPDATE_PERMISSION = "net_electric_daily_update_permission";			//更新网电日报权限
	
	public static final String NET_ELECTRIC_DAILY_DELETE_PERMISSION = "net_electric_daily_delete_permission";			//删除网电日报权限
	
	public static final String COMPLETED_WELL_LIST_SELECT_PERMISSION = "completed_well_list_select_permission";			//完井信息查看权限
	
	
	// --------------start activiti---------------------

	public static final String STARTUSERID = "startUserId";
	
	public static final String NEXTCANDIDATEUSERS = "nextCandidateUsers";

	public static final String ATTACHMENTTYPE = "oss type";

	public static final String ATTACHMENTLISTNAME = "ActivitAttachmentList";
	
	public static final String APPLYCOMMENT = "申请";
	
	public enum ErrorCodeEnum {
		NoError, // 无错误
		UnAuthentication, // 1,未认证
		UnAuthorization, // 2,未授权
		ActivitiGroupNotFound, // 组不存在
		ActivitiObjectNotFound, // 任务不存在
		ActivitiTaskAlreadyClaimed,
		ActivitiTaskHasNoApprover,
		ActivitiBussinessKeyDuplicate,
		ActivitiTaskHasNoComments,
		OssKeyDoesNotExist, //oss 文件不存在
		Unknow
	}
	/**
	 * 保存最近一次驳回后的任务列表
	 */
	public static final String NEWESTHISTORICTASKLIST = "NewestHistoricTaskList";

	/**
	 * 保存最后一个的通过状态
	 */
	public static final String ISAPPROVE = "isApprove";

	/**
	 * 任务本地变量，保存本次任务执行结果
	 */
	public static final String ISAPPROVELOCAL = "isApproveLocal";
	
	
	public static final String LASTISAPPROVELOCAL = "lastIsApproveLocal";

	
	// -----------start 与会签相关的变量-----------------------------
	/**
	 * 对于多实例节点，记录通过次数
	 */
	// public static final String APPROVECOUNT = "approveCount";

	// -----------end 与会签相关的变量-----------------------------

	public enum ResultEnum {
		Reject(0), // 驳回
		Approve(1); // 通过
		private int value = 0;
		public int getValue() {
			return value;
		}
		public void setValue(int value) {
			this.value = value;
		}
		private ResultEnum(int value) { // 必须是private的，否则编译错误
			this.value = value;
		}
	}
	// -------------end activiti-------------------------

	// -------------start redis---------------------
	
	//易失对象存储，过期时间为5分钟
	public static final String REDIS_TEMP_DEPARTMENT_TREE = "temp_department_tree:";
	
    public static final int REDIS_TEMP_EXPIRE_TIME = 60 * 5;	
	
    public static final int REDIS_ACTIVITI_USERS_EXPIRE_TIME = 60 * 60 * 24;
    
    public static final int REDIS_ACTIVITI_HISTORY_EXPIRE_TIME = 60 * 60 * 24 ; //历史信息过期时间1天

	public static final String REDIS_ACTIVITI_SEQUENCEFLOW_BETWEEN = "activiti_sequenceflow_between:";
	
	public static final String REDIS_ACTIVITI_TASK_GRAPHICINFO_MAP = "activiti_task_graphicInfo_map_of:";
	
	public static final String REDIS_ACTIVITI_PROCESSINSTANCE_COMMENT = "activiti_processinstance_comment_of:";
	
	public static final String REDIS_LOGINUSERS_BY_WEBSOCKET = "login_users_by_websocket";
	
	public static final String REDIS_LOGINUSERS_BY_SESSION = "login_users_by_session";
	
	public static final String REDIS_ACTIVITI_NEWEST_HISTORIC_TASK = "activiti_newest_historic-tasks_of:";
	
	public static final String REDIS_ACTIVITI_HISTORIC_TASK = "activiti_historic-tasks_of:";
	
	public static final String REDIS_ACTIVITI_PROCESSINSTANCE_DETAIL = "activiti_process_instance_detail_of:";
	
	public static final String REDIS_ACTIVITI_PROCESSINSTANCE_WITH_HISTORIC_TASK = "activiti_process_instance_with_historic_task_of:";
	
	public static final String REDIS_ACTIVITI_USER = "activiti_user_of:";
	
	public static final String REDIS_ONLINE_USER_COUNT = "online_user_count";

	
	// ---------end redis-------------------------
	
	// 部门类型
	public static final String MANAGER = "主管领导";

	public static final String ASSIGNEDMANAGER = "分管领导";
	
	public static final String GENERALMENERALMANAGEROFFICE = "总经理办公室";
	
	public static final String GENERALMENERALMANAGER = "总经理";
	
	public static final String HUMANRESOURCE = "人力资源部";
	
	public static final String MANAGEMENTANDPROCUREMENT = "经营采办管理部";
	
	public static final String SECURITIESAFFAIRS = "证券事务部";

	public static final String FINANCIALMANAGEMENT = "财务管理部";
	
	public static final String FINANCIALOFFICER = "财务总监";

	public static final String EQUIPMENTEXAMINATION = "设备管理部";

	public static final String CHAIRMAN = "董事会";
	
	public static final String DRILLINGCREW = "井队";
	
	public static final String SUUPLYCENTRE = "供应站";
			
	public static final String INVITETENDERSCOMMITTEE = "招投标委员会";
	
}
