package cn.caecc.erp.support.workflow.service.listener;

import java.io.Serializable;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.caecc.erp.modules.service.CardActivitiApplyService;
import cn.caecc.erp.modules.service.ContractActivitiApplyService;
import cn.caecc.erp.modules.service.DispatchActivitiApplyService;
import cn.caecc.erp.modules.service.EvaluateActivitiApplyService;
import cn.caecc.erp.modules.service.EventActivitiApplyService;
import cn.caecc.erp.modules.service.FixedassetsActivitiApplyService;
import cn.caecc.erp.modules.service.MaterialActivitiApplyService;
import cn.caecc.erp.modules.service.OfficesupplyActivitiApplyService;
import cn.caecc.erp.modules.service.OutsideActivitiApplyService;
import cn.caecc.erp.modules.service.ProjectActivitiApplyService;
import cn.caecc.erp.modules.service.QualityfeedbackActivitiApplyService;
import cn.caecc.erp.modules.service.SealActivitiApplyService;
import cn.caecc.erp.modules.service.TenderingActivitiApplyService;
import cn.caecc.erp.modules.service.VehicleActivitiApplyService;
import cn.caecc.erp.modules.service.WorkloadActivitiApplyService;
import cn.caecc.erp.support.context.springcontext.SpringContextUtil;
import cn.caecc.erp.support.workflow.remind.service.RemindService;
import cn.caecc.erp.support.workflow.service.WorkflowService;

public class ApproveExecutionListener implements Serializable, ExecutionListener {

	/**
	 * 
	 */
	private WorkflowService workflowService;
	private RemindService remindService;

	private ProjectActivitiApplyService projectActivitiApplyService;
	private SealActivitiApplyService sealActivitiApplyService;
	private CardActivitiApplyService cardActivitiApplyService;
	private ContractActivitiApplyService contractActivitiApplyService;
	private DispatchActivitiApplyService dispatchActivitiApplyService;
	private EvaluateActivitiApplyService evaluateActivitiApplyService;
	private EventActivitiApplyService eventActivitiApplyService;
	private FixedassetsActivitiApplyService fixedassetsActivitiApplyService;
	private MaterialActivitiApplyService materialActivitiApplyService;
	private OfficesupplyActivitiApplyService officesupplyActivitiApplyService;
	private OutsideActivitiApplyService outsideActivitiApplyService;
	private QualityfeedbackActivitiApplyService qualityfeedbackActivitiApplyService;
	private TenderingActivitiApplyService tenderingActivitiApplyService;
	private VehicleActivitiApplyService vehicleActivitiApplyService;
	private WorkloadActivitiApplyService workloadActivitiApplyService;

	private static final long serialVersionUID = 1L;
	private final static Logger logger = LoggerFactory.getLogger(ApproveExecutionListener.class);

	public ApproveExecutionListener() {
		workflowService = (WorkflowService) SpringContextUtil.getBean("activitiServiceImpl");
		remindService = (RemindService) SpringContextUtil.getBean("remindServiceImpl");
		projectActivitiApplyService = (ProjectActivitiApplyService) SpringContextUtil
				.getBean("projectActivitiApplyServiceImpl");
		sealActivitiApplyService = (SealActivitiApplyService) SpringContextUtil.getBean("sealActivitiApplyServiceImpl");
		cardActivitiApplyService = (CardActivitiApplyService) SpringContextUtil.getBean("cardActivitiApplyServiceImpl");
		contractActivitiApplyService = (ContractActivitiApplyService) SpringContextUtil
				.getBean("contractActivitiApplyServiceImpl");
		dispatchActivitiApplyService = (DispatchActivitiApplyService) SpringContextUtil
				.getBean("dispatchActivitiApplyServiceImpl");
		evaluateActivitiApplyService = (EvaluateActivitiApplyService) SpringContextUtil
				.getBean("evaluateActivitiApplyServiceImpl");
		eventActivitiApplyService = (EventActivitiApplyService) SpringContextUtil
				.getBean("eventActivitiApplyServiceImpl");
		fixedassetsActivitiApplyService = (FixedassetsActivitiApplyService) SpringContextUtil
				.getBean("fixedassetsActivitiApplyServiceImpl");
		materialActivitiApplyService = (MaterialActivitiApplyService) SpringContextUtil
				.getBean("materialActivitiApplyServiceImpl");
		officesupplyActivitiApplyService = (OfficesupplyActivitiApplyService) SpringContextUtil
				.getBean("officesupplyActivitiApplyServiceImpl");
		outsideActivitiApplyService = (OutsideActivitiApplyService) SpringContextUtil
				.getBean("outsideActivitiApplyServiceImpl");
		qualityfeedbackActivitiApplyService = (QualityfeedbackActivitiApplyService) SpringContextUtil
				.getBean("qualityfeedbackActivitiApplyServiceImpl");
		tenderingActivitiApplyService = (TenderingActivitiApplyService) SpringContextUtil
				.getBean("tenderingActivitiApplyServiceImpl");
		workloadActivitiApplyService = (WorkloadActivitiApplyService) SpringContextUtil
				.getBean("workloadActivitiApplyServiceImpl");
	}

	@Override
	public void notify(DelegateExecution execution) {
		// TODO Auto-generated method stub

		String businessKey = execution.getProcessInstanceBusinessKey();
		String processInstanceId = execution.getProcessInstanceId();
		String processDefinitionKey = workflowService
				.queryProcessDefinitionKeyByHistoricProcessInstanceId(processInstanceId);
		Integer iBusinessKey = Integer.parseInt(businessKey);

		logger.info("流程" + processDefinitionKey + "成功完成，准备设置sucess");
		switch (processDefinitionKey) {
		case "ProjectProcess":
			completeProjectProcess(iBusinessKey);
			break;
		case "SealProcess":
			completeSealProcess(iBusinessKey);
			break;
		case "CardProcess":
			completeCardProcess(iBusinessKey);
			break;
		case "ContractProcess":
			completeContractProcess(iBusinessKey);
			break;
		case "DispatchProcess":
			completeDispatchProcess(iBusinessKey);
			break;
		case "EvaluateProcess":
			completeEvaluateProcess(iBusinessKey);
			break;
		case "EventProcess":
			completeEventProcess(iBusinessKey);
			break;
		case "FixedassetsProcess":
			completeFixedassetsProcess(iBusinessKey);
			break;
		case "MaterialProcess":
			completeMaterialProcess(iBusinessKey);
			break;
		case "OfficesupplyProcess":
			completeOfficesupplyProcess(iBusinessKey);
			break;
		case "OutsideProcess":
			completeOutsideProcess(iBusinessKey);
			break;
		case "QualityFeedbackProcess":
			completeQualityfeedbackProcess(iBusinessKey);
			break;
		case "TenderingProcess":
			completeTenderingProcess(iBusinessKey);
			break;
		case "VehicleProcess":
			completeVehicleProcess(iBusinessKey);
			break;
		case "WorkloadProcess":
			completeWorkloadProcess(iBusinessKey);
			break;
		default:
			logger.warn("未知流程:" + processInstanceId);
			break;
		}
		logger.info("流程" + processDefinitionKey + "设置sucess成功");

		try {
			remindService.sendRemind(execution, true);
		} catch (Exception ex) {
			logger.warn("发送成功提醒失败");
		}

	}

	private void completeProjectProcess(int businessKey) {
		if (projectActivitiApplyService != null) {
			projectActivitiApplyService.setSuccess(businessKey);
		}
	}

	private void completeSealProcess(int businessKey) {
		if (sealActivitiApplyService != null) {
			sealActivitiApplyService.setSuccess(businessKey);
		}
	}

	private void completeCardProcess(int businessKey) {
		if (cardActivitiApplyService != null) {
			cardActivitiApplyService.setSuccess(businessKey);
		}
	}

	private void completeContractProcess(int businessKey) {
		if (contractActivitiApplyService != null) {
			contractActivitiApplyService.setSuccess(businessKey);
		}
	}

	private void completeDispatchProcess(int businessKey) {
		if (dispatchActivitiApplyService != null) {
			dispatchActivitiApplyService.setSuccess(businessKey);
		}
	}

	private void completeEvaluateProcess(int businessKey) {
		if (evaluateActivitiApplyService != null) {
			evaluateActivitiApplyService.setSuccess(businessKey);
		}
	}

	private void completeEventProcess(int businessKey) {
		if (eventActivitiApplyService != null) {
			eventActivitiApplyService.setSuccess(businessKey);
		}
	}

	private void completeFixedassetsProcess(int businessKey) {
		if (fixedassetsActivitiApplyService != null) {
			fixedassetsActivitiApplyService.setSuccess(businessKey);
		}
	}

	private void completeMaterialProcess(int businessKey) {
		if (materialActivitiApplyService != null) {
			materialActivitiApplyService.setSuccess(businessKey);
		}
	}

	private void completeOfficesupplyProcess(int businessKey) {
		if (officesupplyActivitiApplyService != null) {
			officesupplyActivitiApplyService.setSuccess(businessKey);
		}
	}

	private void completeOutsideProcess(int businessKey) {
		if (outsideActivitiApplyService != null) {
			outsideActivitiApplyService.setSuccess(businessKey);
		}
	}

	private void completeQualityfeedbackProcess(int businessKey) {
		if (qualityfeedbackActivitiApplyService != null) {
			qualityfeedbackActivitiApplyService.setSuccess(businessKey);
		}
	}

	private void completeTenderingProcess(int businessKey) {
		if (tenderingActivitiApplyService != null) {
			tenderingActivitiApplyService.setSuccess(businessKey);
		}
	}

	private void completeVehicleProcess(int businessKey) {
		if (vehicleActivitiApplyService != null) {
			vehicleActivitiApplyService.setSuccess(businessKey);
		}
	}

	private void completeWorkloadProcess(int businessKey) {
		if (workloadActivitiApplyService != null) {
			workloadActivitiApplyService.setSuccess(businessKey);
		}
	}
}
