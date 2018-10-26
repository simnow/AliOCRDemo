package cn.caecc.erp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.caecc.erp.modules.dao.mybatis.entity.SafetyGrade;
import cn.caecc.erp.modules.service.SafetyGradeService;
import cn.caecc.erp.support.message.Message;

@Controller
@RequestMapping(value = "/api/safetygrade")
public class SafetyGradeController {

	@Autowired
	private SafetyGradeService safetyGradeService;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public Message getSafetyGradeList() {
		Message message = new Message();
		try {

			List<SafetyGrade> resultList = safetyGradeService.getSafetyGradeList();
			message.setObj(resultList);

		} catch (Exception e) {
			// TODO: handle exception
			message.setMsg(e.getMessage());
			message.setSuccess(false);
		}
		return message;

	}

}
