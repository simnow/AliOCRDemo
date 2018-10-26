package cn.caecc.erp.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.caecc.erp.modules.dao.mybatis.entity.WellBit;
import cn.caecc.erp.modules.dao.mybatis.entity.WellDaily;
import cn.caecc.erp.modules.dao.mybatis.entity.WellInfo;
import cn.caecc.erp.modules.dao.mybatis.entity.WellLog;
import cn.caecc.erp.modules.dao.mybatis.entity.WellMud;
import cn.caecc.erp.modules.dao.mybatis.entity.WellPower;
import cn.caecc.erp.modules.dao.mybatis.entity.WellTime;
import cn.caecc.erp.modules.service.WellBitService;
import cn.caecc.erp.modules.service.WellDailySumService;
import cn.caecc.erp.modules.service.WellLogService;
import cn.caecc.erp.modules.service.WellMudService;
import cn.caecc.erp.modules.service.WellPowerService;
import cn.caecc.erp.modules.service.WellTimeService;
import cn.caecc.erp.modules.service.WellinfoService;
import cn.caecc.erp.support.message.Message;
import cn.caecc.erp.support.util.DateUtil;

/**
 * 日报汇总查询
 * @author GaiNing
 *
 */
@RequestMapping(value="api/welldaily")
@Controller
public class WellDailySumController {

	@Autowired
	public WellinfoService wellinfoService;
	@Autowired
	public WellLogService wellLogService;
	@Autowired
	public WellMudService wellMudService;
	@Autowired
	public WellPowerService wellPowerService;
	@Autowired
	public WellTimeService wellTimeService;
	@Autowired
	public WellBitService WellBitService;
	@Autowired
	public WellDailySumService wellDailySumService;
	
	/**
	 * 某日井报汇总（某日所有井队汇总，某个井的汇总）
	 * @param logDate
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.GET)
	@ResponseBody
	public Message getAll(Integer wellId,Date logDate){
		Message message=new Message();
		Map<String,Object> resultMap=new HashMap<>();
		resultMap.put("wellList", null);
		//开钻井数量，井号
		resultMap.put("kzjsl", 0);
		resultMap.put("kzjjh", "");
		//完钻井数量，井号
		resultMap.put("wzjsl", 0);
		resultMap.put("wzjjh", "");
		//交井 数量，井号
		resultMap.put("jjsl", 0);
		resultMap.put("jjjh", "");
		//钻井动态
		resultMap.put("zcsc", 0);
		resultMap.put("wjzy", 0);
		resultMap.put("fz", 0);
		resultMap.put("banan", 0);
		resultMap.put("daiming", 0);
		resultMap.put("shiyou", 0);

		Double rjc=0.00;//日进尺
		Double ylj=0.00;//月累计
		Double nlj=0.00;//年累计
		List<WellDaily> welllist= wellDailySumService.getWellDailyList(logDate,wellId);
		if(null!=welllist&&welllist.size()>0){
			for(WellDaily well:welllist){
				rjc=rjc+well.getRjc();
				ylj=nlj+well.getYjc();
				nlj=nlj+well.getNljjc();
				//取最新的数据
				WellInfo wellinfo=wellinfoService.selectWellInfoById(well.getWellid());
				//--start 更新最新状态-----
				if(well.getLogdate()!=null&&wellinfo.getFirstkzsj()!=null){
					well.setSjt((int)DateUtil.getDaySub(wellinfo.getFirstkzsj(),well.getLogdate()));//实际天（当日日报时间-第一次开钻时间）
				}
				if(well.getLogdate()!=null&&wellinfo.getWjrq()!=null){
					well.setSyt((int)DateUtil.getDaySub(wellinfo.getWjrq(),well.getLogdate()));//试油天数（当日日报时间-完井时间）
				}
				well.setZbt(wellinfo.getHtzq());//中标天-基础信息合同周期
				well.setSjjs(wellinfo.getSjjs());//基础数据设计井深
				well.setKzrq(wellinfo.getFirstkzsj());//开钻井日期
				//--end 结束更新
				if(null!=wellinfo.getWzrq()){ //完钻井数量
					int wzjsl=(int) resultMap.get("wzjsl");
					String wellCodelist=""+resultMap.get("wzjjh");
					resultMap.put("wzjsl", wzjsl+1);
						//查询获取wellcode
						resultMap.put("wzjjh", wellCodelist+wellinfo.getWellcode()+"/");
				}else{
					int kzjsl=(int) resultMap.get("kzjsl");
					String wellCodelist=""+resultMap.get("kzjjh");
					resultMap.put("kzjsl", kzjsl+1);
					resultMap.put("kzjjh", wellCodelist+wellinfo.getWellcode()+"/");
				}
				if(null!=wellinfo.getJjrq()&&wellinfo.getJjrq().equals(well.getLogdate())){ //交井数量
					//查询获取wellcode
					int jjsl=(int) resultMap.get("jjsl");
					String wellCodelist=""+resultMap.get("jjjh");
					resultMap.put("jjsl", jjsl+1);
					resultMap.put("jjjh", wellCodelist+wellinfo.getWellcode()+"/");
				}
				if(null!=wellinfo.getZjdt()){
					if(1==wellinfo.getZjdt()){
						int zcsc=(int) resultMap.get("zcsc");
						resultMap.put("zcsc", zcsc+1);
					}
					if(2==wellinfo.getZjdt()){
						int wjzy=(int) resultMap.get("wjzy");
						resultMap.put("wjzy", wjzy+1);
					}
					if(3==wellinfo.getZjdt()){
						int fz=(int) resultMap.get("fz");
						resultMap.put("fz", fz+1);
					}
					if(4==wellinfo.getZjdt()){
						int banan=(int) resultMap.get("banan");
						resultMap.put("banan", banan+1);
					}
					if(5==wellinfo.getZjdt()){
						int daiming=(int) resultMap.get("daiming");
						resultMap.put("daiming", daiming+1);
					}
					if(6==wellinfo.getZjdt()){
						int shiyou=(int) resultMap.get("shiyou");
						resultMap.put("shiyou", shiyou+1);
					}
				}
			}
			resultMap.put("SumRjc", rjc);
			resultMap.put("SumYlj", ylj);
			resultMap.put("SumNlj", nlj);
			resultMap.put("wellList", welllist);
		}
		message.setObj(resultMap);
		return message;
		
		
		
		
	}
	/**
	 * 5个业务获取今日信息
	 * @return
	 */
	@RequestMapping(value="",method=RequestMethod.GET)
	@ResponseBody
	public Message getToday(String type,int wellId,Date logDate){
		Message message=new Message();
		Map<String,Object> resultMap=new HashMap<>();
		//默认没填过
		resultMap.put("isHasLog", false);
		//先判断今日的钻井日志是否填过，没填返回失败，开始填
		WellLog wellLog=wellLogService.getOneDay(logDate, wellId);
		if(null==wellLog){
			return oldDate(resultMap,wellId);
		}
		resultMap.put("isHasLog", true);
		//今日已经填过，返回今日信息
		resultMap.put("welllog", wellLog);
		if("welllog".equals(type)){
			return oldDate(resultMap,wellId);
		}
		if("wellmud".equals(type)){
			WellMud wellmud=wellMudService.getOneDay(logDate, wellId);
			resultMap.put("wellmud", wellmud);
		}
		if("wellpower".equals(type)){
			WellPower wellPower=wellPowerService.getOneDay(logDate, wellId);
			//判断获取，如果不为空获取月柴油消耗，年柴油消耗，月用电量，年用电量
			resultMap.put("wellPower", wellPower);
			return oldWellPowerDate(resultMap,wellId,logDate);
		}
		if("welltime".equals(type)){
			WellTime wellTime=wellTimeService.getOneDay(logDate, wellId);
			resultMap.put("welltime", wellTime);
		}
		if("wellbit".equals(type)){
			WellBit wellBit=WellBitService.getOneDay(DateUtil.getcurrentDate(), wellId);
			resultMap.put("wellbit", wellBit);
		}
		message.setObj(resultMap);
		return message;	
	}
	private Message oldWellPowerDate(Map<String, Object> resultMap, int wellId,Date date) {
		Message message=new Message();
//		resultMap.put("lastMonthRydl", null); //日用电量
//		resultMap.put("lastMonthCyxh", null); //柴油消耗
		Double lastYearCyxh=0.00;
		Double lastYearRydl=0.00;
		resultMap.put("lastYearCyxh", lastYearCyxh); //柴油消耗
		resultMap.put("lastYearRydl", lastYearRydl); //日用电量
		//月(>=)
		/*WellPower wellPower=wellPowerService.getGreaterThanOrEqualToLogDate(DateUtil.getSubDay("month"), wellId);
		if(null!=wellPower){
			resultMap.put("lastMonthCyxh", wellPower.getCyxh()); //柴油消耗
			resultMap.put("lastMonthRydl", wellPower.getRydl()); //日用电量
		}*/
		//年(>=)
		WellPower wellPower=wellPowerService.getAllWellPowerByWellId(wellId,date);
		if(null!=wellPower){
			lastYearCyxh=wellPower.getCjlj();//井柴油消耗
			lastYearRydl=wellPower.getRjlj();//电量井累计
			resultMap.put("lastYearCyxh", lastYearCyxh); //柴油消耗
			resultMap.put("lastYearRydl", lastYearRydl); //日用电量
		}
		message.setObj(resultMap);
		return message;
	}
	private Message oldDate(Map<String, Object> resultMap, int wellId) {
		Message message=new Message();
		resultMap.put("yesterdayJs", null);//昨日井深
		resultMap.put("lastMonthJs", null);//前一个月井深
		resultMap.put("lastYearJs", null);//前一年井深
		resultMap.put("lastZjzh", null);//上一次的钻具组合
		//日(<=)
		WellLog wellLog1=wellLogService.getLessThanOrEqualTo(DateUtil.getSubDay("day"),wellId);
		if(null!=wellLog1){
			resultMap.put("yesterdayJs", wellLog1.getTdepth());//昨日井深
			resultMap.put("lastZjzh", wellLog1.getZjzh());//上一次的钻具组合
		}
		//月(<=) 并且是满月
		WellLog wellLog2=wellLogService.getLessThanOrEqualTo(DateUtil.getMonthEarly(),wellId);
		if(null!=wellLog2){
			resultMap.put("lastMonthJs", wellLog2.getTdepth());//前一个月井深
		}
		//年(<=)
		WellLog wellLog3=wellLogService.getLessThanOrEqualTo(DateUtil.getYearEarly(),wellId);
		if(null!=wellLog3){
			resultMap.put("lastYearJs", wellLog3.getTdepth());//前一年井深
		}
		message.setObj(resultMap);
		return message;
	}
}
