package cn.caecc.erp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.caecc.erp.modules.dao.ex.dto.WellInfoDto;
import cn.caecc.erp.modules.dao.mybatis.entity.WellDxInfo;
import cn.caecc.erp.modules.service.DxWellInfoService;
import cn.caecc.erp.support.message.Message;

/**
 * 定向井基础
 * @author GaiNing
 *
 */
@RequestMapping(value="/api/dxwell")
@Controller
public class DxWellInfoController {
	@Autowired
	private DxWellInfoService wellInfoService;
	/**
	 * 添加井信息
	 * @param wellinfo
	 */
	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public Message insertWellInfo(@RequestBody WellDxInfo wellinfo){
		Message message=new Message();
		int result=wellInfoService.insertDxWellInfo(wellinfo);
		if(result>0){
			message.setObj(result);
			return message;
		}else{
			message.setSuccess(false);
			message.setMsg("存储失败");
		}
		return message;
	}
	/**
	 * 更新
	 * @param wellinfo
	 * @return
	 */
	@RequestMapping(method=RequestMethod.PUT)
	@ResponseBody
	public Message updateWellInfo(@RequestBody WellDxInfo wellinfo){
		Message message=new Message();
		if(wellInfoService.updateDxWellInfo(wellinfo)){
			return message;
		}else{
			message.setMsg("更新失败");
			message.setSuccess(false);
			return message;
		}
	}
	/**
	 * 查询单井
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	@ResponseBody
	public Message selectWellInfoById(@PathVariable("id") int id){
		Message message=new Message();
		WellDxInfo wellinfo=wellInfoService.selectDxWellInfoById(id);
		message.setObj(wellinfo);
		return message;
	}
	/**
	 * 查询状态井列表(state:0在钻井)
	 * @param state
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.GET)
	@ResponseBody
	public Message selectWellInfoByState(int state,Integer wellcode,int pageNo,int pageSize){
		return wellInfoService.selectDxWellInfoListByState(state,wellcode,pageNo,pageSize);
	}
	
	/**
	 * 查询单号列表
	 * @param state,0在钻井
	 * @return
	 */
	@RequestMapping(value="/wellcode",method=RequestMethod.GET)
	@ResponseBody
	public Message selectAllWellcode(int state){
		Message message=new Message();
		List<WellDxInfo> wellinfo=wellInfoService.selectDxWellInfoByState(state);
		if(wellinfo!=null&&wellinfo.size()>0){
			List<WellInfoDto> dtolist=new ArrayList<>();
			for(int i=0;i<wellinfo.size();i++){
				WellInfoDto dto=new WellInfoDto();
				dto.setId(wellinfo.get(i).getId());
				dto.setWellcode(wellinfo.get(i).getWellcode());
				dto.setDid(wellinfo.get(i).getDid());
				dtolist.add(dto);
			}
			message.setObj(dtolist);
		}	
		return message;
	}
}
