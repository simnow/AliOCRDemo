package cn.caecc.erp.controller;
import cn.caecc.erp.support.constant.Contants;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.caecc.erp.modules.dao.mybatis.entity.WellInfo;
import cn.caecc.erp.modules.dao.mybatis.entity.WellLog;
import cn.caecc.erp.modules.dao.mybatis.entity.WellXwDaily;
import cn.caecc.erp.modules.service.WellLogService;
import cn.caecc.erp.modules.service.WellXwDailyService;
import cn.caecc.erp.modules.service.WellinfoService;
import cn.caecc.erp.support.message.Message;
import cn.caecc.erp.support.util.DateUtil;
/**
 * 下午日报
 * @author GaiNing
 *
 */
@Controller
@RequestMapping(value="api/xwlog")
public class WellXwDailyController {

	@Autowired
	public WellXwDailyService wellXwDailyService;
	@Autowired
	public WellinfoService wellInfoService;
	@Autowired
	public WellLogService wellLogService;
	/**
	 * 新增
	 * @param WellXwDaily
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	@RequiresPermissions(Contants.AFTERNOON_DAILY_LIST_ADD_PERMISSION)
	public Message insertWellXwDaily(@RequestBody WellXwDaily WellXwDaily){
		if(null==WellXwDaily.getWellid()){
			return new Message(false,"井号为空");
		}
		Message message=new Message();
		//存储井队编号
		WellInfo wellInfo=wellInfoService.selectWellInfoById(WellXwDaily.getWellid());
		if(wellInfo!=null){
			WellXwDaily.setDid(wellInfo.getDid());
		}
		
		if(wellXwDailyService.insertWellXwDaily(WellXwDaily)){
			//更新基础数据井深
			if(!wellInfoService.updateWellDepthById(WellXwDaily.getWellid(), WellXwDaily.getTdepth())){
				message.setSuccess(false);
				message.setMsg("操作失败");
			}
			return message;
		}else{
			message.setSuccess(false);
			message.setMsg("存储失败");
			return message;
		}
	}
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	@ResponseBody
	@RequiresPermissions(Contants.AFTERNOON_DAILY_LIST_DELETE_PERMISSION)
	public Message deleteWellXwDaily(@PathVariable("id") int id){
		if(wellXwDailyService.deleteWellXwDaily(id)){
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
	public Message selectWellXwDailyById(@PathVariable("id") int id){
		Message message=new Message();
		message.setObj(wellXwDailyService.getWellXwDailyById(id));
		return message;
	}
	/**
	 * 查询列表
	 * @param startDate
	 * @param endDate
	 * @param wellId
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.GET)
	@ResponseBody
	public Message getWellXwDailyList(String startDate,String endDate,int wellId,int pageNo,int pageSize){
		return wellXwDailyService.listWellXwDaily(startDate, endDate, wellId, pageNo, pageSize);
	}
	
	/**
	 * 修改
	 * @param WellXwDaily
	 * @return
	 */
	@RequestMapping(method=RequestMethod.PUT)
	@ResponseBody
	@RequiresPermissions(Contants.AFTERNOON_DAILY_LIST_UPDATE_PERMISSION)
	public Message updateWellXwDaily(@RequestBody WellXwDaily WellXwDaily){
		Message message=new Message();
		//只能修改当天的
		/*if(!DateUtil.getcurrentDate().equals(WellXwDaily.getLogdate())){
			message.setSuccess(false);
			message.setMsg("只可当日进行修改");
		}*/
		if(StringUtils.isBlank(WellXwDaily.getId()+"")){
			message.setSuccess(false);
			message.setMsg("缺少数据");
		}
		if(wellXwDailyService.updateWellXwDaily(WellXwDaily)){
			//更新基础数据井深
			if(!wellInfoService.updateWellDepthById(WellXwDaily.getWellid(), WellXwDaily.getTdepth())){
				message.setSuccess(false);
				message.setMsg("操作失败");
			}
			return message;
		}
		message.setSuccess(false);
		message.setMsg("操作失败");
		return message;
	}
	/**
	 * 插入之前，查询下基础数据中的井深
	 */
	@RequestMapping(value="/getJs",method=RequestMethod.GET)
	@ResponseBody
	public Message selectJs(int wellId){
		Message message=new Message();
		message.setObj(0.00);
		//返回上午井深
		WellLog logLost=wellLogService.getOneDay(DateUtil.getcurrentDate(), wellId);
		if(null!=logLost&&null!=logLost.getTdepth()){
			message.setObj(logLost.getTdepth());
		}else{
			double wellDepth1=0.00;
			double wellDepth2=0.00;
			//查询昨天下午的，和上午的
			WellLog wellLog1=wellLogService.getLessThanOrEqualTo(DateUtil.getSubDay("day"),wellId);
			WellXwDaily daily=wellXwDailyService.getLessThanOrEqualTo(DateUtil.getSubDay("day"),wellId);
			if(null!=wellLog1&&null!=wellLog1.getTdepth()){
				wellDepth1=wellLog1.getTdepth();
			}
			if(null!=daily&&null!=daily.getTdepth()){
				wellDepth2=daily.getTdepth();
			}
			//返回大的
			if(wellDepth1>=wellDepth2){
				message.setObj(wellDepth1);
			}else{
				message.setObj(wellDepth2);
			}
			
		}
		return message;
	}
	/**
	 * 根据logDate,wellId查看
	 * @return
	 */
	@RequestMapping(value="/record",method=RequestMethod.GET)
	@ResponseBody
	public Message selectXwWellDaily(Date logDate,Integer wellId){
		return wellXwDailyService.selectXwWellDaily(logDate, wellId);
	}
}
