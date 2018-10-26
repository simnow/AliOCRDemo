package cn.caecc.erp.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.caecc.erp.modules.dao.ex.dto.ConferenceArrangementDto;
import cn.caecc.erp.modules.dao.ex.dto.ConferenceArrangementSaveDto;
import cn.caecc.erp.modules.service.ConferenceArrangementService;
import cn.caecc.erp.support.constant.Contants;
import cn.caecc.erp.support.message.Message;

@Controller
@RequestMapping(value = "/api/carrangement")
public class ConferenceArrangementController {

	@Autowired
	private ConferenceArrangementService cArrangementService;

	/**
	 * FunName:addConferenceArrangement Description : 添加申请
	 * 
	 * @param：ConferenceArrangement
	 * @return Message
	 * @Author:shh
	 * @Create Date: 2018-07-03
	 */
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	@RequiresPermissions(Contants.CONFERENCE_ADD_PERMISSION)
	public Message addConferenceArrangement(@RequestBody ConferenceArrangementSaveDto conferenceArrangementSaveDto) {
		Message message = new Message();
		try {
			message.setObj(cArrangementService.addConferenceArrangement(conferenceArrangementSaveDto));
		} catch (Exception ex) {
			message.setMsg(ex.getMessage());
			message.setSuccess(false);
		}
		return message;
	}

	/**
	 * FunName:updatConferenceArrangement Description : 修改
	 * 
	 * @param：ConferenceArrangement
	 * @return Message
	 * @Author:shh
	 * @Create Date: 2018-07-03
	 */
	@RequestMapping(method = RequestMethod.PUT)
	@ResponseBody
	@RequiresPermissions(Contants.CONFERENCE_UPDATE_PERMISSION)
	public Message updateConferenceArrangement(@RequestBody ConferenceArrangementSaveDto conferenceArrangementSaveDto) {
		Message message = new Message();
		try {
			cArrangementService.updateConferenceArrangement(conferenceArrangementSaveDto);
		} catch (Exception ex) {
			message.setSuccess(false);
			message.setMsg(ex.getMessage());
		}
		return message;
	}

	/**
	 * FunName:deleteConferenceArrangement Description : 删除
	 * 
	 * @param：int
	 * @return Message
	 * @Author:shh
	 * @Create Date: 2018-07-03
	 */
	@RequestMapping(value="/{id}",method = RequestMethod.DELETE)
	@ResponseBody
	@RequiresPermissions(Contants.CONFERENCE_DELETE_PERMISSION)
	public Message deleteConferenceArrangement(@PathVariable("id") int id) {
		Message message = new Message();
		try {
			cArrangementService.deleteConferenceArrangement(id);
		} catch (Exception ex) {
			message.setSuccess(false);
			message.setMsg(ex.getMessage());
		}
		return message;
	}

	/**
	 * FunName:getConferenceArrangementByType Description : 查询
	 * 
	 * @param：int
	 * @return Message
	 * @Author:shh
	 * @Create Date: 2018-07-03
	 */
	@RequestMapping(value = "/{type}", method = RequestMethod.GET)
	@ResponseBody
	public Message getConferenceArrangementByType(@PathVariable("type") int type) {
		Message message = new Message();
		message.setSuccess(false);
		try {
			List<ConferenceArrangementDto> conferenceArrangementList = cArrangementService
					.getConferenceArrangementByType(type);
			message.setObj(conferenceArrangementList);
			message.setSuccess(true);
		} catch (Exception ex) {
			message.setMsg(ex.getMessage());
		}
		return message;
	}

	/**
	 * FunName:getConferenceArrangementById Description : 查询
	 * 
	 * @param：int
	 * @return Message
	 * @Author:shh
	 * @Create Date: 2018-07-03
	 */
	@RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Message getConferenceArrangementById(@PathVariable("id") int id) {
		Message message = new Message();
		try {
			ConferenceArrangementDto conferenceArrangement = cArrangementService.getConferenceArrangementById(id);
			message.setObj(conferenceArrangement);
		} catch (Exception ex) {
			message.setSuccess(false);
			message.setMsg(ex.getMessage());
		}
		return message;
	}
}
