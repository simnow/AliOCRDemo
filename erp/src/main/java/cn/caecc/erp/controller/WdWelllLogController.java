package cn.caecc.erp.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.caecc.erp.modules.dao.ex.dto.WellWdDailyListDto;
import cn.caecc.erp.modules.dao.mybatis.entity.WellWdDaily;
import cn.caecc.erp.modules.dao.mybatis.entity.WellWdQk;
import cn.caecc.erp.modules.service.WdDailyService;
import cn.caecc.erp.support.message.Message;
import cn.caecc.erp.support.util.DateUtil;
import cn.caecc.erp.support.constant.Contants;
/**
 * 网电
 * @author GaiNing
 *
 */
@Controller
@RequestMapping(value="api/wdlog")
public class WdWelllLogController {

	@Autowired
	public WdDailyService wdDailyService;
	
	/**
	 * 新增
	 * @param wdDaily
	 * @return
	 */
	@SuppressWarnings({ "unused" })
	@RequestMapping(value="",method=RequestMethod.POST)
	@ResponseBody
	@RequiresPermissions(Contants.NET_ELECTRIC_DAILY_ADD_PERMISSION)
	public Message insertWdDaily(@RequestBody WellWdDailyListDto wdDailyList ){
		Message message=new Message();
		if(null!=wdDailyList&&null!=wdDailyList.getList()){
			List<WellWdDaily> list=new ArrayList<>();
			//遍历日期
			for(WellWdDaily wdDaily:wdDailyList.getList()){
				wdDaily.setLogdate(DateUtil.getcurrentDate());
				wdDaily.setCreatetime(DateUtil.getcurrentDateTime());
				if(StringUtils.isNotBlank(wdDailyList.getHbr())){
					wdDaily.setHbr(wdDailyList.getHbr());
				}
				//激活区块，如果区块状态为0，修改为1（激活）
				WellWdQk wdqk=wdDailyService.selectWellWdQkByQkId(wdDaily.getQkid());
				if(null!=wdqk){
					/*if(wdqk.getState()==0){
						wdqk.setState(1);
						//更新
						wdDailyService.updateQk(wdqk);
					}*/
					wdDaily.setQkname(wdqk.getQkname());
				}
				list.add(wdDaily);
			}
			if(null==list&&list.size()>0){
				message.setSuccess(false);
				message.setMsg("无数据进行存储");
			}else{
				message= wdDailyService.insertWdDailyList(list);
			}
		}else{
			message.setSuccess(false);
			message.setMsg("缺少数据");
		}
		return message;
	}
	
	/**
	 * 通过日期查看信息
	 */
	@RequestMapping(method=RequestMethod.GET)
	@ResponseBody
	public Message getWdLogInfo(Date logDate){
		return wdDailyService.getWdLogInfoByLogDate(logDate);
	}
	
	/**
	 * 修改
	 */
	@RequestMapping(method=RequestMethod.PUT)
	@ResponseBody
	@RequiresPermissions(Contants.NET_ELECTRIC_DAILY_UPDATE_PERMISSION)
	public Message updateWdLog(@RequestBody WellWdDailyListDto wdDailyList){
		Message message=new Message();
		message.setSuccess(false);
		message.setMsg("缺少数据");
		if(null==wdDailyList||null==wdDailyList.getList()){
			return message;
		}else{
			return wdDailyService.updateWdLogInfoById(wdDailyList.getList(),wdDailyList.getHbr());
		}
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	@ResponseBody
	@RequiresPermissions(Contants.NET_ELECTRIC_DAILY_DELETE_PERMISSION)
	public Message deleteWdLogById(@PathVariable("id") int id){
		return wdDailyService.deleteWdLogInfoById(id);
	}
	
	/**
	 * 获取区块侧边栏信息
	 */
	@RequestMapping(value="/qk",method=RequestMethod.GET)
	@ResponseBody
	public Message getQkList(){
		return wdDailyService.selectQkList();
	}
	/**
	 * 添加
	 */
	@RequestMapping(value="/qk",method=RequestMethod.POST)
	@ResponseBody
	public Message insertQk(@RequestBody WellWdQk wellWdQk){
		return wdDailyService.insertQkInfo(wellWdQk);
	}
	/**
	 * 修改
	 */
	@RequestMapping(value="/qk",method=RequestMethod.PUT)
	@ResponseBody
	public Message updateQk(@RequestBody WellWdQk wellWdQk){
		return wdDailyService.updateQk(wellWdQk);
	}
	
}
