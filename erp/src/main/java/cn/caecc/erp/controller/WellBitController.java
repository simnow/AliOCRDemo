package cn.caecc.erp.controller;


import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.caecc.erp.modules.dao.mybatis.entity.WellBit;
import cn.caecc.erp.modules.dao.mybatis.entity.WellInfo;
import cn.caecc.erp.modules.service.WellBitService;
import cn.caecc.erp.modules.service.WellinfoService;
import cn.caecc.erp.support.message.Message;

/**
 * 钻头记录
 * @author GaiNing
 *
 */
@RequestMapping(value="api/wellbit")
@Controller
public class WellBitController {

	@Autowired
	public WellBitService WellBitService;
	@Autowired
	public WellinfoService wellinfoService;
	/**
	 * 新增
	 * @param WellBit
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public Message insertWellBit(@RequestBody WellBit WellBit){
		if(null==WellBit.getWellid()){
			return new Message(false,"井号为空");
		}
		Message message=new Message();
		//存储井队编号
		WellInfo wellInfo=wellinfoService.selectWellInfoById(WellBit.getWellid());
		if(wellInfo!=null){
			WellBit.setDid(wellInfo.getDid());
		}
		int result=WellBitService.insertWellBit(WellBit);
		if(result>0){
			message.setObj(WellBit.getId());
		}else{
			message.setSuccess(false);
			message.setMsg("存储失败");
		}
		return message;
	}
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	@ResponseBody
	public Message deleteWellBit(@PathVariable("id") int id){
		if(WellBitService.deleteWellBit(id)){
			return new Message();
		}else{
			Message message=new Message();
			message.setSuccess(false);
			message.setMsg("删除失败");
			return message;
		}
	}
	/**
	 * 查看
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	@ResponseBody
	public Message selectWellBitById(@PathVariable("id") int id){
		Message message=new Message();
		message.setObj(WellBitService.getWellBitById(id));
		return message;
	}
	/**
	 * 查询列表
	 * @param wellId
	 * @return
	 */
	@RequestMapping(value="/record",method=RequestMethod.GET)
	@ResponseBody
	public Message getWellBitList(int wellId){
		return WellBitService.listWellBit(wellId);
	}
	/**
	 * 查询列表
	 * @param wellId
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.GET)
	@ResponseBody
	public Message getWellBitListByWellId(int wellId,int pageNo,int pageSize){
		return WellBitService.listWellBitByWellId(wellId,pageNo,pageSize);
	}
	
	/**
	 * 修改
	 * @param WellBit
	 * @return
	 */
	@RequestMapping(method=RequestMethod.PUT)
	@ResponseBody
	public Message updateWellBit(@RequestBody WellBit WellBit){
		Message message=new Message();
		if(StringUtils.isBlank(WellBit.getId()+"")){
			message.setSuccess(false);
			message.setMsg("缺少数据");
		}
		if(WellBitService.updateWellBit(WellBit)){
			return new Message();
		}
		message.setSuccess(false);
		message.setMsg("操作失败");
		return message;
	}
}
