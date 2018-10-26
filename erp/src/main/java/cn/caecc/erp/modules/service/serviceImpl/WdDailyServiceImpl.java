package cn.caecc.erp.modules.service.serviceImpl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.caecc.erp.modules.dao.ex.mapper.WellWdDailyExMapper;
import cn.caecc.erp.modules.dao.mybatis.entity.WellWdDaily;
import cn.caecc.erp.modules.dao.mybatis.entity.WellWdDailyExample;
import cn.caecc.erp.modules.dao.mybatis.entity.WellWdDailyExample.Criteria;
import cn.caecc.erp.modules.dao.mybatis.entity.WellWdQk;
import cn.caecc.erp.modules.dao.mybatis.entity.WellWdQkExample;
import cn.caecc.erp.modules.dao.mybatis.mapper.WellWdDailyMapper;
import cn.caecc.erp.modules.dao.mybatis.mapper.WellWdQkMapper;
import cn.caecc.erp.modules.service.WdDailyService;
import cn.caecc.erp.support.message.Message;
import cn.caecc.erp.support.util.DateUtil;
@Service
public class WdDailyServiceImpl implements WdDailyService {

	@Autowired
	private WellWdDailyMapper wellWdDailyMapper;
	@Autowired
	private WellWdQkMapper wellWdQkMapper;
	@Autowired
	private WellWdDailyExMapper wellWdDailyExMapper;
	@Override
	public Message insertWdDaily(WellWdDaily wdDaily) {
		Message message=new Message();
		wdDaily.setCreatetime(DateUtil.getcurrentDateTime());
		int result=wellWdDailyMapper.insertSelective(wdDaily);
		if(result>0){
			message.setObj(wdDaily.getId());
		}else{
			message.setSuccess(false);
			message.setMsg("存储失败");
		}
		return message;
	}
	@Override
	public Message insertWdDailyList(List<WellWdDaily> wdDaily) {
		Message message=new Message();
		//如果今天有日志就不插入
		WellWdDailyExample example=new WellWdDailyExample();
		Criteria criteria=example.createCriteria();
		criteria.andLogdateEqualTo(wdDaily.get(0).getLogdate());
		List<WellWdDaily> resultList=wellWdDailyMapper.selectByExample(example);
		if(null!=resultList&&resultList.size()>0){
			message.setSuccess(false);
			message.setMsg("存储失败,已存在当日日报");
			return message;
		}
		int result=wellWdDailyExMapper.batchAdd(wdDaily);
		if(result<=0){
			message.setSuccess(false);
			message.setMsg("存储失败");
		}
		return message;
	}
	@Override
	public Message getWdLogInfoByLogDate(Date logDate) {
		Message message=new Message();
		WellWdDailyExample example=new WellWdDailyExample();
		Criteria criteria=example.createCriteria();
		criteria.andLogdateEqualTo(logDate);
		List<WellWdDaily> resultList=wellWdDailyMapper.selectByExample(example);
		if(null!=resultList&&resultList.size()>0){
			//循环查询区块名称，如果没有就直接取历史数据，有的话覆盖
			for(WellWdDaily daily:resultList){
				String qkname=selectQkNameByQkId(daily.getQkid());
				if(StringUtils.isNotBlank(qkname)){
					daily.setQkname(qkname);
				}
			}
			message.setObj(resultList);
		}
		return message;
	}
	@Override
	public Message updateWdLogInfoById(List<WellWdDaily> record,String hbr) {
		Message message=new Message();
		//删除
		deleteWdLogInfoByLogDate(record.get(0).getLogdate());
		//循环更新
		for(WellWdDaily well:record){
			WellWdQk wdqk=selectWellWdQkByQkId(well.getQkid());
			if(null!=wdqk){
				well.setQkname(wdqk.getQkname());
			}
			/*
			//激活区块，如果区块状态为0，修改为1（激活）
			if(null!=wdqk){
				if(wdqk.getState()==0){
					wdqk.setState(1);
					//更新
					updateQk(wdqk);
				}
			}*/
			/*if(null!=well.getId()){
				if(StringUtils.isNotBlank(hbr)){
					well.setHbr(hbr);
				}
				int result=wellWdDailyMapper.updateByPrimaryKeySelective(well);
				if(result<=0){
					message.setSuccess(false);
					message.setMsg("修改失败");
					return message;
				}
			}else{*/
				//新增
				well.setLogdate(DateUtil.getcurrentDate());
				well.setCreatetime(DateUtil.getcurrentDateTime());
				if(StringUtils.isNotBlank(hbr)){
					well.setHbr(hbr);
				}
				int result=wellWdDailyMapper.insertSelective(well);
				if(result<=0){
					message.setSuccess(false);
					message.setMsg("修改失败");
				}
//			}
		}
		return message;
	}
	@Override
	public Message deleteWdLogInfoById(int id) {
		Message message=new Message();
		int result=wellWdDailyMapper.deleteByPrimaryKey(id);
		if(result<=0){
			message.setSuccess(false);
			message.setMsg("删除失败");
		}
		return message;
	}
	private boolean deleteWdLogInfoByLogDate(Date logDate) {
		WellWdDailyExample example=new WellWdDailyExample();
		Criteria criteria=example.createCriteria();
		criteria.andLogdateEqualTo(logDate);
		int result=wellWdDailyMapper.deleteByExample(example);
		if(result<=0){
			return false;
		}
		return true;
	}
	@Override
	public Message selectQkList() {
		Message message=new Message();
		WellWdQkExample example=new WellWdQkExample();
//		cn.caecc.erp.modules.dao.mybatis.entity.WellWdQkExample.Criteria criteria=example.createCriteria();
//		criteria.andStateEqualTo(1);
		message.setObj(wellWdQkMapper.selectByExample(example));
		return message;
	}
	
	@Override
	public String selectQkNameByQkId(int id) {
		WellWdQkExample example=new WellWdQkExample();
		cn.caecc.erp.modules.dao.mybatis.entity.WellWdQkExample.Criteria criteria=example.createCriteria();
		criteria.andIdEqualTo(id);
		WellWdQk wdqk=wellWdQkMapper.selectByPrimaryKey(id);
		if(null!=wdqk){
			return wdqk.getQkname();
		}
		return "";
	}
	@Override
	public WellWdQk selectWellWdQkByQkId(int id) {
		WellWdQkExample example=new WellWdQkExample();
		cn.caecc.erp.modules.dao.mybatis.entity.WellWdQkExample.Criteria criteria=example.createCriteria();
		criteria.andIdEqualTo(id);
		WellWdQk wdqk=wellWdQkMapper.selectByPrimaryKey(id);
		if(null!=wdqk){
			return wdqk;
		}
		return null;
	}
	@Override
	public Message insertQkInfo(WellWdQk record) {
		record.setCreatetime(DateUtil.getcurrentDateTime());
//		record.setState(0);
		Message message=new Message();
		//判断区块名称是否已经存在
		if(isExit(record.getQkname())){
			message.setMsg("区块名称已经存在");
			message.setSuccess(false);
			return message;
		}
		int result=wellWdQkMapper.insert(record);
		if(result<=0){
			message.setMsg("存储失败");
			message.setSuccess(false);
		}else{
			message.setObj(record.getId());
		}
		return message;
	}
	@Override
	public Message updateQk(WellWdQk wellWdQk) {
		Message message=new Message();
//		if(null!=wellWdQk.getState()){
//			wellWdQk.setState(wellWdQk.getState());
//		}else{
//			wellWdQk.setState(0);
//		}
		//判断区块名称是否已经存在
		if(isExit(wellWdQk.getQkname())){
			message.setMsg("区块名称已经存在");
			message.setSuccess(false);
			return message;
		}
		if(wellWdQkMapper.updateByPrimaryKeySelective(wellWdQk)<=0){
			message.setMsg("修改失败");
			message.setSuccess(false);
		}
		return message;
	}
	
	private boolean isExit(String name){
		WellWdQkExample example=new WellWdQkExample();
		cn.caecc.erp.modules.dao.mybatis.entity.WellWdQkExample.Criteria criteria=example.createCriteria();
		criteria.andQknameEqualTo(name);
		List<WellWdQk> wdqklist=wellWdQkMapper.selectByExample(example);
		if(null!=wdqklist&&wdqklist.size()>0){
			return true;
		}else{
			return false;
		}
	}

}