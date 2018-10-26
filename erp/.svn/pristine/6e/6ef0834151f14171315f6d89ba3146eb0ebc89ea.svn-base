package cn.caecc.erp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.github.pagehelper.PageInfo;

import cn.caecc.erp.modules.dao.ex.dto.MessageDto;
import cn.caecc.erp.modules.dao.mybatis.entity.MessageType;
import cn.caecc.erp.modules.service.MessageService;
import cn.caecc.erp.modules.service.MessageTypeService;
import cn.caecc.erp.support.message.Message;

@Controller
@RequestMapping("/api/message")
public class MessageController {

	@Autowired
	private MessageService messageService;
	
	@Autowired
	private MessageTypeService messageTypeService;
	
	@ResponseBody
	@RequestMapping(value = "/hasread-status", method = RequestMethod.PUT)
	public Message updateHasReadStatus(@RequestParam("messageid") int messageId) {
		Message message = new Message();
		messageService.updateHasReadStatus(messageId);
		message.setSuccess(true);
		return message;
	}

	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public Message getMessageList(@RequestParam(value = "messagetypeid", required = false) Integer messageTypeId,
			@RequestParam(value = "pageNo", required = true) int pageNo,
			@RequestParam(value = "pageSize", required = true) int pageSize) {
		Message message = new Message();
		message.setSuccess(false);
		try {
			PageInfo<MessageDto> list = messageService.getMyMessageList(null, messageTypeId, pageNo, pageSize);
			message.setObj(list);
			message.setSuccess(true);
			if (list == null) {
				message.setMsg("未查询到相关数据");
			} else {
				message.setObj(list);
			}
		} catch (Exception ex) {
			message.setMsg(ex.getMessage());
		}
		return message;
	}

	@ResponseBody
	@RequestMapping(value = "/unread-list", method = RequestMethod.GET)
	public Message getNoReadMessageList(@RequestParam(value = "messagetypeid", required = false) Integer messageTypeId,
			@RequestParam(value = "pageNo", required = true) int pageNo,
			@RequestParam(value = "pageSize", required = true) int pageSize) {
		Message message = new Message();
		message.setSuccess(false);
		try {
			PageInfo<MessageDto> list = messageService.getMyMessageList(MessageService.StatusEnum.UnRead.ordinal(),
					messageTypeId, pageNo, pageSize);
			message.setObj(list);
			message.setSuccess(true);
			if (list == null) {
				message.setMsg("未查询到相关数据");
			} else {
				message.setObj(list);
			}
		} catch (Exception ex) {
			message.setMsg(ex.getMessage());
		}
		return message;
	}

	@ResponseBody
	@RequestMapping(value = "/hasread-list", method = RequestMethod.GET)
	public Message getHasReadMessageList(@RequestParam(value = "messagetypeid", required = false) Integer messageTypeId,
			@RequestParam(value = "pageNo", required = true) int pageNo,
			@RequestParam(value = "pageSize", required = true) int pageSize) {
		Message message = new Message();
		message.setSuccess(false);
		try {
			PageInfo<MessageDto> list = messageService.getMyMessageList(MessageService.StatusEnum.HasRead.ordinal(),
					messageTypeId, pageNo, pageSize);
			message.setObj(list);
			message.setSuccess(true);
			if (list == null) {
				message.setMsg("未查询到相关数据");
			} else {
				message.setObj(list);
			}
		} catch (Exception ex) {
			message.setMsg(ex.getMessage());
		}
		return message;
	}
	
	@ResponseBody
	@RequestMapping(value = "/type/list", method = RequestMethod.GET)
	public Message getMessageTypeList() {
		Message message = new Message();
		message.setSuccess(false);
		try {
			List<MessageType> list = messageTypeService.getMessageTypeList();
			message.setObj(list);
			message.setSuccess(true);
			if (list == null) {
				message.setMsg("未查询到相关数据");
			} else {
				message.setObj(list);
			}
		} catch (Exception ex) {
			message.setMsg(ex.getMessage());
		}
		return message;
	}
	
	@ResponseBody
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public Message getMessageById(@RequestParam(value = "messageid", required = true) int messageId) {
		Message message = new Message();
		message.setSuccess(false);
		try {
			cn.caecc.erp.modules.dao.mybatis.entity.Message message2 = messageService.getMessageById(messageId);
			message.setObj(message2);
			message.setSuccess(true);
		} catch (Exception ex) {
			message.setMsg(ex.getMessage());
		}
		return message;
	}
	
	@ResponseBody
	@RequestMapping(value = "/unread-count", method = RequestMethod.GET)
	public Message getUnreadMessageCountById() {
		Message message = new Message();
		message.setSuccess(false);
		try {
			int count = messageService.getUnreadMessageCountById();
			message.setObj(count);
			message.setSuccess(true);
		} catch (Exception ex) {
			message.setMsg(ex.getMessage());
		}
		return message;
	}
	
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.DELETE)
	public Message deleteMessageByIds(@RequestBody List<Integer> messageIdList) {
		Message message = new Message();
		message.setSuccess(false);
		try {
			int count = messageService.deleteMessageByIds(messageIdList);
			message.setObj(count);
			message.setSuccess(true);
		} catch (Exception ex) {
			message.setMsg(ex.getMessage());
		}
		return message;
	}
	
	@ResponseBody
	@RequestMapping(value = "/hasread", method = RequestMethod.PUT)
	public Message updateHasReadStatus(@RequestBody List<Integer> messageIdList) {
		Message message = new Message();
		message.setSuccess(false);
		try {
			int count = messageService.updateHasReadStatus(messageIdList);
			message.setObj(count);
			message.setSuccess(true);
		} catch (Exception ex) {
			message.setMsg(ex.getMessage());
		}
		return message;
	}
	
	@ResponseBody
	@RequestMapping(value = "/unread", method = RequestMethod.PUT)
	public Message updateUnReadStatus(@RequestBody List<Integer> messageIdList) {
		Message message = new Message();
		message.setSuccess(false);
		try {
			int count = messageService.updateUnReadStatus(messageIdList);
			message.setObj(count);
			message.setSuccess(true);
		} catch (Exception ex) {
			message.setMsg(ex.getMessage());
		}
		return message;
	}
}

