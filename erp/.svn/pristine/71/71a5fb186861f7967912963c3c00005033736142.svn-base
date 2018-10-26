package cn.caecc.erp.support.workflow.service.listener;

import java.io.Serializable;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.caecc.erp.support.constant.Contants;
import cn.caecc.erp.support.context.springcontext.SpringContextUtil;
import cn.caecc.erp.support.workflow.remind.service.RemindService;

public class RejectExecutionListener implements Serializable, ExecutionListener {

	/**
	 * 
	 */
	private RemindService remindService;
	private static final long serialVersionUID = 1L;
	private final static Logger logger = LoggerFactory.getLogger(RejectExecutionListener.class);

	public RejectExecutionListener() {
		remindService = (RemindService) SpringContextUtil.getBean("remindServiceImpl");
	}

	@Override
	public void notify(DelegateExecution execution) {
		// TODO Auto-generated method stub
		try {
			Object oIsApprove = execution.getVariable(Contants.ISAPPROVE);
			if (oIsApprove != null) { // 等于null说明是刚刚申请
				int isApprove = (int) oIsApprove;
				if (isApprove == Contants.ResultEnum.Reject.ordinal()) {
					remindService.sendRemind(execution, false);
				}
			}

		} catch (Exception ex) {
			logger.warn("发送驳回提醒短信或邮件失败");
		}

	}

}
