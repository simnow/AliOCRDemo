package cn.caecc.erp.controller;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.caecc.erp.modules.dao.ex.dto.OfficesupplyGoodsRelationshipActivitiApplyDto;
import cn.caecc.erp.modules.service.OfficesupplyGoodsRelationshipActivitiApplyService;
import cn.caecc.erp.support.constant.Contants;
import cn.caecc.erp.support.message.Message;

@Controller
@RequestMapping("/api/officesupply-goods-relationship-activiti-apply")
public class OfficesupplyGoodsRelationshipActivitiApplyController{

	@Autowired
	private OfficesupplyGoodsRelationshipActivitiApplyService ogrActivitiApplyService;
	
	/**
	 * 查询详情
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/detail/{id}",method = RequestMethod.GET)
	@RequiresPermissions(Contants.OFFICESUPPLY_SELECT_PERMISSION)
	public Message findDetail(@PathVariable("id")int id) {
		Message message = new Message();
		message.setSuccess(false);
		OfficesupplyGoodsRelationshipActivitiApplyDto ogrActivitiApplyDto = ogrActivitiApplyService.findDetail(id);
		if (ogrActivitiApplyDto != null) {
			message.setObj(ogrActivitiApplyDto);
			message.setSuccess(true);
		}else {
			message.setMsg("未查询到相关数据");
		}
		return message;
	}
}
