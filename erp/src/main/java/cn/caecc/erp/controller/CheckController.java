/**
 * 
 */
package cn.caecc.erp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.caecc.erp.support.message.Message;

/**
 * @author weizhen
 *
 */
@Controller
@RequestMapping(value="/api/check")
public class CheckController {

	/**
	 * 
	 */
	public CheckController() {
		// TODO Auto-generated constructor stub
	}

	@ResponseBody
	@RequestMapping(method = RequestMethod.GET)
	public Message check() {
		Message message = new Message();
		message.setSuccess(false);
		return message;
	}
}
