package cn.caecc.erp.support.sms.service;

public interface SmsService {

	/**
	 * 发送登录验证码
	 * 
	 * @param phonenumber
	 * @return
	 * @throws Exception
	 */
	public boolean sendLoginSms(String phonenumber) throws Exception;

	/**
	 * 发送修改密码验证码
	 * 
	 * @param phonenumber
	 * @return
	 * @throws Exception
	 */
	public boolean sendModifyPasswordSms(String phonenumber) throws Exception;

	/**
	 * 验证登录验证码
	 * 
	 * @param phonenumber
	 * @param code
	 * @return
	 */
	public boolean checkLoginSms(String phonenumber, String code) throws Exception;

	/**
	 * 验证修改密码验证码
	 * 
	 * @param phonenumber
	 * @param code
	 * @return
	 */
	public boolean checkModifyPasswordSms(String phonenumber, String code) throws Exception;

	/**
	 * 
	 * @param phonenumber
	 * @param name
	 * @param processName
	 * @param who
	 * @param result
	 * @return
	 */
	public boolean sendWorkflowRejectSms(String phonenumber, String name, String processDefinitionName, String who,
			String result) throws Exception;

	/**
	 * 发送同意流程通知
	 * 
	 * @param processName
	 * @return
	 */
	public boolean sendWorkflowApproveSms(String phonenumber, String name, String processDefinitionName)
			throws Exception;

}
