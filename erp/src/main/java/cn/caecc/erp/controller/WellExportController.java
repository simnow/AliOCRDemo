package cn.caecc.erp.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFSimpleShape;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.PrintSetup;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.caecc.erp.modules.dao.ex.dto.DepartmentDto;
import cn.caecc.erp.modules.dao.ex.dto.SsxhListDto;
import cn.caecc.erp.modules.dao.ex.mapper.DepartmentExMapper;
import cn.caecc.erp.modules.dao.mybatis.entity.Department;
import cn.caecc.erp.modules.dao.mybatis.entity.WellBit;
import cn.caecc.erp.modules.dao.mybatis.entity.WellDaily;
import cn.caecc.erp.modules.dao.mybatis.entity.WellDxDaily;
import cn.caecc.erp.modules.dao.mybatis.entity.WellDxInfo;
import cn.caecc.erp.modules.dao.mybatis.entity.WellInfo;
import cn.caecc.erp.modules.dao.mybatis.entity.WellLog;
import cn.caecc.erp.modules.dao.mybatis.entity.WellMud;
import cn.caecc.erp.modules.dao.mybatis.entity.WellPower;
import cn.caecc.erp.modules.dao.mybatis.entity.WellTime;
import cn.caecc.erp.modules.dao.mybatis.entity.WellWdDaily;
import cn.caecc.erp.modules.dao.mybatis.entity.WellXwDaily;
import cn.caecc.erp.modules.service.DxWellInfoService;
import cn.caecc.erp.modules.service.WdDailyService;
import cn.caecc.erp.modules.service.WellBitService;
import cn.caecc.erp.modules.service.WellDxDailyService;
import cn.caecc.erp.modules.service.WellLogService;
import cn.caecc.erp.modules.service.WellMudService;
import cn.caecc.erp.modules.service.WellPowerService;
import cn.caecc.erp.modules.service.WellTimeService;
import cn.caecc.erp.modules.service.WellinfoService;
import cn.caecc.erp.modules.service.WellXwDailyService;
import cn.caecc.erp.support.message.Message;
import cn.caecc.erp.support.util.DateUtil;
import cn.caecc.erp.support.util.JacksonUtil;

/**
 * 井列表、汇总导出
 * @author GaiNing
 *
 */
@RequestMapping(value="api/wellexport")
@Controller
public class WellExportController {
	@Autowired
	public WellDailySumController wellDailySumCOntroller;
	@Autowired
	public WellinfoService wellInfoSerivce;
	@Autowired
	public DxWellInfoService wellDxInfoSerivce;
	@Autowired
	public WellLogService wellLogService;
	@Autowired
	public WellMudService wellMudService;
	@Autowired
	public WellPowerService wellPowerService; 
	@Autowired
	public WellTimeService wellTimeService;
	@Autowired
	public WellBitService wellBitService;
	@Autowired
	public WellDxDailyService wellDxDailyService;
	@Autowired
	public WellXwDailyService WellXwDailyService;
	@Autowired
	public DepartmentExMapper departMentExMapper;
	@Autowired
	public WdDailyService wdDailyService;
	
	@RequestMapping(value="list")
	public ResponseEntity<byte[]> listexport(String startDate,String endDate,Integer wellId,String type,HttpServletRequest req) throws IOException {
		ResponseEntity<byte[]> entity = null;
		Message message=new Message();
		String name="";
		ByteArrayInputStream is =null;
		try {
			if("welllog".equals(type)){
				message=wellLogService.getWellLogListNoPage(startDate, endDate, wellId);
				name="钻井日志列表";
			}
			if("wellmud".equals(type)){
				message=wellMudService.getWellMudListNoPage(startDate, endDate, wellId);
				name="泥浆性能日报列表";
			}
			if("wellpower".equals(type)){
				name="动力性能日报列表";
				message=wellPowerService.listWellPowerNoPage(startDate, endDate, wellId);
			}
			if("welltime".equals(type)){
				name="钻井时效日报列表";
				message=wellTimeService.listWellTimeNoPage(startDate, endDate, wellId);
			}
			if("wellbit".equals(type)){
				name="钻头记录日报列表";
				message=wellBitService.listWellBit(wellId);
			}
			if("dxwell".equals(type)){
				name="定向井日报列表";
				message=wellDxDailyService.selectAllDxWellDailyByWellIdNoPage(startDate, endDate, wellId);
			}
			if("xwlog".equals(type)){
				name="下午日报列表";
				message=WellXwDailyService.listWellXwDailyNoPage(startDate, endDate, wellId);
			}
			is = makeLogData(type,message.getObj(),name);
			byte[] body = new byte[is.available()];
			is.read(body);
			HttpHeaders headers = new HttpHeaders();           
			headers.setContentType(MediaType.MULTIPART_FORM_DATA);
			name = URLEncoder.encode(name, "UTF-8");//encode编码UTF-8 解决大多数中文乱码
//			if (req.getHeader("user-agent").toLowerCase().contains("ie")|| req.getHeader("user-agent").toLowerCase().contains("edge")) {
//		        // IE
//				headers.add("Content-Disposition", "attachment;filename="+new String(name.getBytes("utf-8"))+".xls");
//			}else{
				headers.add("Content-Disposition", "attachment;filename="+new String(name.getBytes("utf-8"),"iso8859-1")+".xls");
//			}
			HttpStatus statusCode = HttpStatus.OK;
			entity = new ResponseEntity<byte[]>(body, headers, statusCode);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(null!=is){
				is.close();
			}
		}
		return entity;
		
	}
	@SuppressWarnings("unchecked")
	private ByteArrayInputStream makeLogData(String type, Object obj, String name) throws IOException {
		HSSFWorkbook workBook = new HSSFWorkbook();  
		HSSFSheet sheet = workBook.createSheet(name);
		HSSFCellStyle cellStyle = workBook.createCellStyle();   
		cellStyle.setAlignment(HorizontalAlignment.CENTER);// 居中  
		cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);//垂直
		HSSFFont font = workBook.createFont();  
		font.setFontName("Arial");  
		font.setFontHeightInPoints((short) 11);//设置字体大小  
		cellStyle.setFont(font);
		cellStyle.setWrapText(true);//设置自动换行  
		if("welllog".equals(type)){
			if(null!=obj){
				List<WellLog> list=(List<WellLog>) obj;
				HSSFRow row = sheet.createRow(0);
			    HSSFCell cell = row.createCell(0, CellType.STRING);  
			    cell.setCellValue(name);
			    cell.setCellStyle(cellStyle);
			    row.setHeight((short) 700);// 设置行高   
			    //合并
			    CellRangeAddress region1 = new CellRangeAddress(0, 0, (short) 0, (short) 22); 
			    sheet.addMergedRegion(region1);
			    //第二行
			    row=sheet.createRow(1);
			    cell = row.createCell(0, CellType.STRING);
				cell.setCellValue("序号");
				cell.setCellStyle(cellStyle);
				cell = row.createCell(1, CellType.STRING);
				cell.setCellValue("井号");
				cell.setCellStyle(cellStyle);
				cell = row.createCell(2, CellType.STRING);
				cell.setCellValue("井队");
				sheet.setColumnWidth(2, 2800); //设置列宽
				cell.setCellStyle(cellStyle);
				cell = row.createCell(3, CellType.STRING);
				cell.setCellValue("日期");
				sheet.setColumnWidth(3, 2750); //设置列宽
				cell.setCellStyle(cellStyle);
				cell = row.createCell(4, CellType.STRING);
				sheet.setColumnWidth(4, 2800); //设置列宽
				cell.setCellValue("设计井深");
				cell.setCellStyle(cellStyle);
				cell = row.createCell(5, CellType.STRING);
				sheet.setColumnWidth(5, 2800); //设置列宽
				cell.setCellValue("当日井深");
				cell.setCellStyle(cellStyle);
				cell = row.createCell(6, CellType.STRING);
				cell.setCellValue("层位");
				cell.setCellStyle(cellStyle);
				cell = row.createCell(7, CellType.STRING);
				sheet.setColumnWidth(7, 2800); //设置列宽
				cell.setCellValue("主要工作");
				cell.setCellStyle(cellStyle);
				cell = row.createCell(8, CellType.STRING);
				sheet.setColumnWidth(8, 2800); //设置列宽
				cell.setCellValue("下步计划");
				cell.setCellStyle(cellStyle);
				cell = row.createCell(9, CellType.STRING);
				sheet.setColumnWidth(9, 2800); //设置列宽
				cell.setCellValue("工程简况");
				cell.setCellStyle(cellStyle);
				cell = row.createCell(10, CellType.STRING);
				cell.setCellValue("钻压");
				cell.setCellStyle(cellStyle);
				cell = row.createCell(11, CellType.STRING);
				cell.setCellValue("转速");
				cell.setCellStyle(cellStyle);
				cell = row.createCell(12, CellType.STRING);
				cell.setCellValue("泵压");
				cell.setCellStyle(cellStyle);
				cell = row.createCell(13, CellType.STRING);
				cell.setCellValue("排量");
				cell.setCellStyle(cellStyle);
				cell = row.createCell(14, CellType.STRING);
				cell.setCellValue("日进尺");
				cell.setCellStyle(cellStyle);
				cell = row.createCell(15, CellType.STRING);
				cell.setCellValue("月进尺");
				cell.setCellStyle(cellStyle);
				cell = row.createCell(16, CellType.STRING);
				sheet.setColumnWidth(16, 2800); //设置列宽
				cell.setCellValue("年累进尺");
				cell.setCellStyle(cellStyle);
				cell = row.createCell(17, CellType.STRING);
				sheet.setColumnWidth(17, 2800); //设置列宽
				cell.setCellValue("目前进度");
				cell.setCellStyle(cellStyle);
				cell = row.createCell(18, CellType.STRING);
				sheet.setColumnWidth(18, 2800); //设置列宽
				cell.setCellValue("钻头尺寸");
				cell.setCellStyle(cellStyle);
				cell = row.createCell(19, CellType.STRING);
				sheet.setColumnWidth(19, 2800); //设置列宽
				cell.setCellValue("套管结构");
				cell.setCellStyle(cellStyle);
				cell = row.createCell(20, CellType.STRING);
				sheet.setColumnWidth(20, 2800); //设置列宽
				cell.setCellValue("钻具组合");
				cell.setCellStyle(cellStyle);
				cell = row.createCell(21, CellType.STRING);
				sheet.setColumnWidth(21, 2800); //设置列宽
				cell.setCellValue("工程简况");
				cell.setCellStyle(cellStyle);
				cell = row.createCell(22, CellType.STRING);
				sheet.setColumnWidth(22, 2800); //设置列宽
				cell.setCellValue("钻井汇报负责人");
				cell.setCellStyle(cellStyle);
				for(int i=0;i<list.size();i++){
					row=sheet.createRow(i+2);
					cell = row.createCell(0, CellType.STRING);
					cell.setCellValue(i+1); 
					//通过井编号查询井号
					WellInfo wellInfo=wellInfoSerivce.selectWellInfoById(list.get(i).getWellid());
			        cell = row.createCell(1, CellType.STRING);
			        cell.setCellValue(wellInfo.getWellcode()); 
			        cell = row.createCell(2, CellType.STRING);
			        if(null!=list.get(i).getDid()){
			        	cell.setCellValue(getDeptName(list.get(i).getDid())); 
			        }else{
			        	cell.setCellValue(""); 
			        }
			        cell = row.createCell(3, CellType.STRING);
			        if(null!=DateUtil.DateToString(list.get(i).getLogdate(),DateUtil.YYYY_MM_DD)){
			        	cell.setCellValue(DateUtil.DateToString(list.get(i).getLogdate(),DateUtil.YYYY_MM_DD));
			        }else{
			        	cell.setCellValue("");
			        }
			        cell = row.createCell(4, CellType.STRING);
			        if(null!=wellInfo.getSjjs()){
			        	cell.setCellValue(wellInfo.getSjjs());
			        }else{
			        	cell.setCellValue("");
			        }
			        cell = row.createCell(5, CellType.STRING);
			        if(null!=list.get(i).getTdepth()){
			        	cell.setCellValue(list.get(i).getTdepth());
			        }else{
			        	cell.setCellValue("");
			        }
			        cell = row.createCell(6, CellType.STRING);
			        cell.setCellValue(list.get(i).getJdcw());
			        cell = row.createCell(7, CellType.STRING);
			        cell.setCellValue(list.get(i).getZygz());
			        cell = row.createCell(8, CellType.STRING);
			        cell.setCellValue(list.get(i).getXbjh());
			        cell = row.createCell(9, CellType.STRING);
			        cell.setCellValue(list.get(i).getGcgk());
			        cell = row.createCell(10, CellType.STRING);
			        cell.setCellValue(list.get(i).getZy());
			        cell = row.createCell(11, CellType.STRING);
			        cell.setCellValue(list.get(i).getZs());
			        cell = row.createCell(12, CellType.STRING);
			        cell.setCellValue(list.get(i).getPy());
			        cell = row.createCell(13, CellType.STRING);
			        cell.setCellValue(list.get(i).getPl());
			        cell = row.createCell(14, CellType.STRING);
			        if(null!=list.get(i).getRjc()){
			        	cell.setCellValue(list.get(i).getRjc());
			        }else{
			        	cell.setCellValue("");
			        }
			        cell = row.createCell(15, CellType.STRING);
			        if(null!=list.get(i).getYjc()){
			        	cell.setCellValue(list.get(i).getYjc());
			        }else{
			        	cell.setCellValue("");
			        }
			        cell = row.createCell(16, CellType.STRING);
			        if(null!=list.get(i).getNljjc()){
			        	cell.setCellValue(list.get(i).getNljjc());
			        }else{
			        	cell.setCellValue("");
			        }
			        cell = row.createCell(17, CellType.STRING);
			        cell.setCellValue(list.get(i).getMqjd());
			        //钻头尺寸（钻头外径*型号）
			        cell = row.createCell(18, CellType.STRING);
			        cell.setCellValue(list.get(i).getZtwj()+"*"+list.get(i).getZtxh());
			        //套管结构（套管外径*套管深度）
			        cell = row.createCell(19, CellType.STRING);
			        cell.setCellValue(list.get(i).getTgwj()+"*"+list.get(i).getTgsd());
			        cell = row.createCell(20, CellType.STRING);
			        cell.setCellValue(list.get(i).getZjzh());
			        cell = row.createCell(21, CellType.STRING);
			        cell.setCellValue(list.get(i).getGcgk());
			        cell = row.createCell(22, CellType.STRING);
			        cell.setCellValue(list.get(i).getZjhbfzr());
				}
			}
			
		}
		if("wellmud".equals(type)){
			if(null!=obj){
				List<WellMud> list=(List<WellMud>) obj;
				HSSFRow row = sheet.createRow(0);
			    HSSFCell cell = row.createCell(0, CellType.STRING);  
			    cell.setCellValue(name);
			    cell.setCellStyle(cellStyle);
			    row.setHeight((short) 700);// 设置行高   
			    //合并
			    CellRangeAddress region1 = new CellRangeAddress(0, 0, (short) 0, (short) 47); 
			    sheet.addMergedRegion(region1);
			    //第二行
			    row=sheet.createRow(1);
			    cell = row.createCell(0, CellType.STRING);
				cell.setCellValue("序号");
				cell.setCellStyle(cellStyle);
				cell = row.createCell(1, CellType.STRING);
				cell.setCellValue("井号");
				cell.setCellStyle(cellStyle);
				cell = row.createCell(2, CellType.STRING);
				cell.setCellValue("日期");
				sheet.setColumnWidth(2, 2750); //设置列宽
				cell.setCellStyle(cellStyle);
				cell = row.createCell(3, CellType.STRING);
				cell.setCellValue("井深(m)");
				cell.setCellStyle(cellStyle);
				cell = row.createCell(4, CellType.STRING);
				cell.setCellValue("层位");
				cell.setCellStyle(cellStyle);
				cell = row.createCell(5, CellType.STRING);
				sheet.setColumnWidth(5, 2800); //设置列宽
				cell.setCellValue("取样地点");
				cell.setCellStyle(cellStyle);
				cell = row.createCell(6, CellType.STRING);
				sheet.setColumnWidth(6, 2800); //设置列宽
				cell.setCellValue("取样时间");
				cell.setCellStyle(cellStyle);
				cell = row.createCell(7, CellType.STRING);
				sheet.setColumnWidth(7, 2800); //设置列宽
				cell.setCellValue("出口温度");
				cell.setCellStyle(cellStyle);
				cell = row.createCell(8, CellType.STRING);
				cell.setCellValue("密度");
				cell.setCellStyle(cellStyle);
				cell = row.createCell(9, CellType.STRING);
				cell.setCellValue("粘度(s)");
				cell.setCellStyle(cellStyle);
				cell = row.createCell(10, CellType.STRING);
				cell.setCellValue("塑性粘度(Mpa.s)");
				sheet.setColumnWidth(10, 2800); //设置列宽
				cell.setCellStyle(cellStyle);
				cell = row.createCell(11, CellType.STRING);
				cell.setCellValue("屈服度(pa)");
				cell.setCellStyle(cellStyle);
				cell = row.createCell(12, CellType.STRING);
				cell.setCellValue("切力(pa)");
				cell.setCellStyle(cellStyle);
				cell = row.createCell(13, CellType.STRING);
				cell.setCellValue("失水(ml)");
				cell.setCellStyle(cellStyle);
				cell = row.createCell(14, CellType.STRING);
				cell.setCellValue("泥饼(mm)");
				cell.setCellStyle(cellStyle);
				cell = row.createCell(15, CellType.STRING);
				sheet.setColumnWidth(15, 2800); //设置列宽
				cell.setCellValue("HTHP失水(ml)");
				cell.setCellStyle(cellStyle);
				cell = row.createCell(16, CellType.STRING);
				sheet.setColumnWidth(16, 2800); //设置列宽
				cell.setCellValue("HTHP泥饼(ml)");
				cell.setCellStyle(cellStyle);
				cell = row.createCell(17, CellType.STRING);
				sheet.setColumnWidth(17, 2800); //设置列宽
				cell.setCellValue("HTHP温度");
				cell.setCellStyle(cellStyle);
				cell = row.createCell(18, CellType.STRING);
				sheet.setColumnWidth(18, 2800); //设置列宽
				cell.setCellValue("摩擦系数");
				cell.setCellStyle(cellStyle);
				cell = row.createCell(19, CellType.STRING);
				sheet.setColumnWidth(19, 2800); //设置列宽
				cell.setCellValue("固相含量(v%)");
				cell.setCellStyle(cellStyle);
				cell = row.createCell(20, CellType.STRING);
				cell.setCellValue("水量(v%)");
				cell.setCellStyle(cellStyle);
				cell = row.createCell(21, CellType.STRING);
				cell.setCellValue("油量(v%)");
				cell.setCellStyle(cellStyle);
				cell = row.createCell(22, CellType.STRING);
				sheet.setColumnWidth(22, 2800); //设置列宽
				cell.setCellValue("含砂量(v%)");
				cell.setCellStyle(cellStyle);
				cell = row.createCell(23, CellType.STRING);
				sheet.setColumnWidth(23, 2800); //设置列宽
				cell.setCellValue("膨润土含量(v%)");
				cell.setCellStyle(cellStyle);
				cell = row.createCell(24, CellType.STRING);
				cell.setCellValue("PH");
				cell.setCellStyle(cellStyle);
				cell = row.createCell(25, CellType.STRING);
				sheet.setColumnWidth(25, 2500); //设置列宽
				cell.setCellValue("钻井液碱度");
				cell.setCellStyle(cellStyle);
				cell = row.createCell(26, CellType.STRING);
				sheet.setColumnWidth(26, 2500); //设置列宽
				cell.setCellValue("氯根含量(mg/l)");
				cell.setCellStyle(cellStyle);
				cell = row.createCell(27, CellType.STRING);
				sheet.setColumnWidth(27, 2500); //设置列宽
				cell.setCellValue("钙离子含量(mg/l)");
				cell.setCellStyle(cellStyle);
				cell = row.createCell(28, CellType.STRING);
				sheet.setColumnWidth(28, 2500); //设置列宽
				cell.setCellValue("钾离子含量(mg/l)");
				cell.setCellStyle(cellStyle);
				cell = row.createCell(29, CellType.STRING);
				sheet.setColumnWidth(29, 2800); //设置列宽
				cell.setCellValue("3转读数");
				cell.setCellStyle(cellStyle);
				cell = row.createCell(30, CellType.STRING);
				sheet.setColumnWidth(30, 2800); //设置列宽
				cell.setCellValue("6转读数");
				cell.setCellStyle(cellStyle);
				cell = row.createCell(31, CellType.STRING);
				sheet.setColumnWidth(31, 2800); //设置列宽
				cell.setCellValue("100转读数");
				cell.setCellStyle(cellStyle);
				cell = row.createCell(32, CellType.STRING);
				sheet.setColumnWidth(32, 2800); //设置列宽
				cell.setCellValue("200转读数");
				cell.setCellStyle(cellStyle);
				cell = row.createCell(33, CellType.STRING);
				sheet.setColumnWidth(33, 2800); //设置列宽
				cell.setCellValue("300转读数");
				cell.setCellStyle(cellStyle);
				cell = row.createCell(34, CellType.STRING);
				sheet.setColumnWidth(34, 2800); //设置列宽
				cell.setCellValue("600转读数");
				cell.setCellStyle(cellStyle);
				cell = row.createCell(35, CellType.STRING);
				sheet.setColumnWidth(35, 2800); //设置列宽
				cell.setCellValue("低密度固相含量");
				cell.setCellStyle(cellStyle);
				cell = row.createCell(36, CellType.STRING);
				cell.setCellValue("MBT值");
				cell.setCellStyle(cellStyle);
				cell = row.createCell(37, CellType.STRING);
				sheet.setColumnWidth(37, 2800); //设置列宽
				cell.setCellValue("含油量(油基)");
				cell.setCellStyle(cellStyle);
				cell = row.createCell(38, CellType.STRING);
				sheet.setColumnWidth(38, 2800); //设置列宽
				cell.setCellValue("酚酞碱度");
				cell.setCellStyle(cellStyle);
				cell = row.createCell(39, CellType.STRING);
				sheet.setColumnWidth(39, 2500); //设置列宽
				cell.setCellValue("甲基橙碱度");
				cell.setCellStyle(cellStyle);
				cell = row.createCell(40, CellType.STRING);
				cell.setCellValue("油水比");
				cell.setCellStyle(cellStyle);
				cell = row.createCell(41, CellType.STRING);
				sheet.setColumnWidth(41, 2500); //设置列宽
				cell.setCellValue("钻井液活度");
				cell.setCellStyle(cellStyle);
				cell = row.createCell(42, CellType.STRING);
				sheet.setColumnWidth(42, 2800); //设置列宽
				cell.setCellValue("破乳电压");
				cell.setCellStyle(cellStyle);
				cell = row.createCell(43, CellType.STRING);
				sheet.setColumnWidth(43, 2500); //设置列宽
				cell.setCellValue("钻井液体系");
				cell.setCellStyle(cellStyle);
				cell = row.createCell(44, CellType.STRING);
				sheet.setColumnWidth(44, 2500); //设置列宽
				cell.setCellValue("重浆储备量-储备量");
				cell.setCellStyle(cellStyle);
				cell = row.createCell(45, CellType.STRING);
				sheet.setColumnWidth(45, 2500); //设置列宽
				cell.setCellValue("重浆储备量-最低密度");
				cell.setCellStyle(cellStyle);
				cell = row.createCell(46, CellType.STRING);
				sheet.setColumnWidth(46, 2500); //设置列宽
				cell.setCellValue("加重材料储备");
				cell.setCellStyle(cellStyle);
				cell = row.createCell(47, CellType.STRING);
				sheet.setColumnWidth(47, 2500); //设置列宽
				cell.setCellValue("泥浆处理简况");
				cell.setCellStyle(cellStyle);
//				cell = row.createCell(48, CellType.STRING);
//				sheet.setColumnWidth(48, 2500); //设置列宽
//				cell.setCellValue("每日实时消耗");
//				cell.setCellStyle(cellStyle);
				for(int i=0;i<list.size();i++){
					row=sheet.createRow(i+2);
					cell = row.createCell(0, CellType.STRING);
					cell.setCellValue(i+1); 
					//通过井编号查询井号
					WellInfo wellInfo=wellInfoSerivce.selectWellInfoById(list.get(i).getWellid());
			        cell = row.createCell(1, CellType.STRING);
			        cell.setCellValue(wellInfo.getWellcode()); 
			        cell = row.createCell(2, CellType.STRING);
			        if(null!=DateUtil.DateToString(list.get(i).getLogdate(),DateUtil.YYYY_MM_DD)){
			        	cell.setCellValue(DateUtil.DateToString(list.get(i).getLogdate(),DateUtil.YYYY_MM_DD));
			        }else{
			        	cell.setCellValue("");
			        }
			        cell = row.createCell(3, CellType.STRING);
			        if(null!=list.get(i).getQyjs()){
			        	cell.setCellValue(list.get(i).getQyjs());
			        }else{
			        	cell.setCellValue("");
			        }
			        cell = row.createCell(4, CellType.STRING);
			        cell.setCellValue(list.get(i).getJdcw());
			        cell = row.createCell(5, CellType.STRING);
			        cell.setCellValue(list.get(i).getQydd());
			        cell = row.createCell(6, CellType.STRING);
			        cell.setCellValue(list.get(i).getQysj());
			        cell = row.createCell(7, CellType.STRING);
			        cell.setCellValue(list.get(i).getCkwd());
			        cell = row.createCell(8, CellType.STRING);
			        cell.setCellValue(list.get(i).getNjmd());
			        cell = row.createCell(9, CellType.STRING);
			        cell.setCellValue(list.get(i).getLdnd());
			        cell = row.createCell(10, CellType.STRING);
			        cell.setCellValue(list.get(i).getSxnd());
			        cell = row.createCell(11, CellType.STRING);
			        cell.setCellValue(list.get(i).getQfd());
			        //切力
			        cell = row.createCell(12, CellType.STRING);
			        cell.setCellValue(list.get(i).getCq()+"/"+list.get(i).getZq());
			        cell = row.createCell(13, CellType.STRING);
			        cell.setCellValue(list.get(i).getSs());
			        cell = row.createCell(14, CellType.STRING);
			        cell.setCellValue(list.get(i).getNb());
			        cell = row.createCell(15, CellType.STRING);
			        cell.setCellValue(list.get(i).getHthpss());
			        cell = row.createCell(16, CellType.STRING);
			        cell.setCellValue(list.get(i).getHthpnb());
			        cell = row.createCell(17, CellType.STRING);
			        cell.setCellValue(list.get(i).getHthpwd());
			        cell = row.createCell(18, CellType.STRING);
			        cell.setCellValue(list.get(i).getMcxx());
			        cell = row.createCell(19, CellType.STRING);
			        cell.setCellValue(list.get(i).getGxhl());
			        cell = row.createCell(20, CellType.STRING);
			        cell.setCellValue(list.get(i).getSl());
			        cell = row.createCell(21, CellType.STRING);
			        cell.setCellValue(list.get(i).getYl());
			        cell = row.createCell(22, CellType.STRING);
			        cell.setCellValue(list.get(i).getHsl());
			        cell = row.createCell(23, CellType.STRING);
			        cell.setCellValue(list.get(i).getPrthl());
			        cell = row.createCell(24, CellType.STRING);
			        cell.setCellValue(list.get(i).getPhz());
			        cell = row.createCell(25, CellType.STRING);
			        cell.setCellValue(list.get(i).getZjyjd());
			        cell = row.createCell(26, CellType.STRING);
			        cell.setCellValue(list.get(i).getLghl());
			        cell = row.createCell(27, CellType.STRING);
			        cell.setCellValue(list.get(i).getGlzhl());
			        cell = row.createCell(28, CellType.STRING);
			        cell.setCellValue(list.get(i).getJlzhl());
			        cell = row.createCell(29, CellType.STRING);
			        cell.setCellValue(list.get(i).getThreez());
			        cell = row.createCell(30, CellType.STRING);
			        cell.setCellValue(list.get(i).getSixz());
			        cell = row.createCell(31, CellType.STRING);
			        cell.setCellValue(list.get(i).getOnehundredz());
			        cell = row.createCell(32, CellType.STRING);
			        cell.setCellValue(list.get(i).getTwohundredz());
			        cell = row.createCell(33, CellType.STRING);
			        cell.setCellValue(list.get(i).getThreehundredz());
			        cell = row.createCell(34, CellType.STRING);
			        cell.setCellValue(list.get(i).getSixhundredz());
			        cell = row.createCell(35, CellType.STRING);
			        cell.setCellValue(list.get(i).getDmdgxhl());
			        
			        cell = row.createCell(36, CellType.STRING);
					cell.setCellValue(list.get(i).getMbtz());
					cell = row.createCell(37, CellType.STRING);
					cell.setCellValue(list.get(i).getHyl());
					cell = row.createCell(38, CellType.STRING);
					cell.setCellValue(list.get(i).getFtjd());
					cell = row.createCell(39, CellType.STRING);
					cell.setCellValue(list.get(i).getJjcjd());
					cell = row.createCell(40, CellType.STRING);
					cell.setCellValue(list.get(i).getYsb());
					cell = row.createCell(41, CellType.STRING);
					cell.setCellValue(list.get(i).getZjyhd());
					cell = row.createCell(42, CellType.STRING);
					cell.setCellValue(list.get(i).getPrdy());
					cell = row.createCell(43, CellType.STRING);
					cell.setCellValue(list.get(i).getZjytx());
					cell = row.createCell(44, CellType.STRING);
					cell.setCellValue(list.get(i).getZjcbl());
					cell = row.createCell(45, CellType.STRING);
					cell.setCellValue(list.get(i).getZjzdmd());
					cell = row.createCell(46, CellType.STRING);
					cell.setCellValue(list.get(i).getJzclcb());
					cell = row.createCell(47, CellType.STRING);
					cell.setCellValue(list.get(i).getNjcljk());
//					cell = row.createCell(48, CellType.STRING);
//					cell.setCellValue(list.get(i).getSsxh());
				}   
				}
		}
		if("wellpower".equals(type)){
			if(null!=obj){
				List<WellPower> list=(List<WellPower>)obj;
				HSSFRow row = sheet.createRow(0);
			    HSSFCell cell = row.createCell(0, CellType.STRING);  
			    cell.setCellValue(name);
			    cell.setCellStyle(cellStyle);
			    row.setHeight((short) 700);// 设置行高   
			    //合并
			    CellRangeAddress region1 = new CellRangeAddress(0, 0, (short) 0, (short) 26); 
			    sheet.addMergedRegion(region1);
			    //第二行
			    row=sheet.createRow(1);
			    cell = row.createCell(0, CellType.STRING);
				cell.setCellValue("序号");
				cell.setCellStyle(cellStyle);
				cell = row.createCell(1, CellType.STRING);
				cell.setCellValue("井号");
				cell.setCellStyle(cellStyle);
				cell = row.createCell(2, CellType.STRING);
				cell.setCellValue("日期");
				sheet.setColumnWidth(2, 2750); //设置列宽
				cell.setCellStyle(cellStyle);
				cell = row.createCell(3, CellType.STRING);
				cell.setCellValue("井队");
				cell.setCellStyle(cellStyle);
				sheet.setColumnWidth(3, 2800); //设置列宽
				cell = row.createCell(4, CellType.STRING);
				cell.setCellValue("动力源");
				cell.setCellStyle(cellStyle);
				cell = row.createCell(5, CellType.STRING);
				cell.setCellValue("主要工作");
				sheet.setColumnWidth(5, 2800); //设置列宽
				cell.setCellStyle(cellStyle);
				cell = row.createCell(6, CellType.STRING);
				cell.setCellValue("目前进度");
				sheet.setColumnWidth(6, 2800); //设置列宽
				cell.setCellStyle(cellStyle);
				cell = row.createCell(7, CellType.STRING);
				cell.setCellValue("钻头外径");
				sheet.setColumnWidth(7, 2800); //设置列宽
				cell.setCellStyle(cellStyle);
				cell = row.createCell(8, CellType.STRING);
				cell.setCellValue("泵压");
				cell.setCellStyle(cellStyle);
				cell = row.createCell(9, CellType.STRING);
				cell.setCellValue("排量");
				cell.setCellStyle(cellStyle);
				cell = row.createCell(10, CellType.STRING);
				cell.setCellValue("泵冲");
				cell.setCellStyle(cellStyle);
				cell = row.createCell(11, CellType.STRING);
				cell.setCellValue("缸径");
				cell.setCellStyle(cellStyle);
				cell = row.createCell(12, CellType.STRING);
				cell.setCellValue("悬重");
				cell.setCellStyle(cellStyle);
				cell = row.createCell(13, CellType.STRING);
				cell.setCellValue("绞车档位");
				sheet.setColumnWidth(13, 2800); //设置列宽
				cell.setCellStyle(cellStyle);
				cell = row.createCell(14, CellType.STRING);
				cell.setCellValue("绞车电机运行台数");
				sheet.setColumnWidth(14, 2800); //设置列宽
				cell.setCellStyle(cellStyle);
				cell = row.createCell(15, CellType.STRING);
				cell.setCellValue("转速");
				cell.setCellStyle(cellStyle);
				cell = row.createCell(16, CellType.STRING);
				cell.setCellValue("转盘档位");
				sheet.setColumnWidth(16, 2800); //设置列宽
				cell.setCellStyle(cellStyle);
				cell = row.createCell(17, CellType.STRING);
				cell.setCellValue("柴油机/网电电机转速");
				sheet.setColumnWidth(17, 2800); //设置列宽
				cell.setCellStyle(cellStyle);
				cell = row.createCell(18, CellType.STRING);
				cell.setCellValue("柴油机运行台数");
				sheet.setColumnWidth(18, 2800); //设置列宽
				cell.setCellStyle(cellStyle);
				cell = row.createCell(19, CellType.STRING);
				cell.setCellValue("发电机运行台数");
				sheet.setColumnWidth(19, 2800); //设置列宽
				cell.setCellStyle(cellStyle);
				cell = row.createCell(20, CellType.STRING);
				cell.setCellValue("柴油消耗");
				sheet.setColumnWidth(20, 2800); //设置列宽
				cell.setCellStyle(cellStyle);
				cell = row.createCell(21, CellType.STRING);
				cell.setCellValue("月累计");
				cell.setCellStyle(cellStyle);
				cell = row.createCell(22, CellType.STRING);
				cell.setCellValue("井累计");
				cell.setCellStyle(cellStyle);
				cell = row.createCell(23, CellType.STRING);
				cell.setCellValue("日用电量");
				sheet.setColumnWidth(23, 2800); //设置列宽
				cell.setCellStyle(cellStyle);
				cell = row.createCell(24, CellType.STRING);
				cell.setCellValue("月累计");
				cell.setCellStyle(cellStyle);
				cell = row.createCell(25, CellType.STRING);
				cell.setCellValue("井累计");
				cell.setCellStyle(cellStyle);
				cell = row.createCell(26, CellType.STRING);
				cell.setCellValue("消耗情况");
				sheet.setColumnWidth(26, 2800); //设置列宽
				cell.setCellStyle(cellStyle);
				for(int i=0;i<list.size();i++){
					row=sheet.createRow(i+2);
					cell = row.createCell(0, CellType.STRING);
					cell.setCellValue(i+1); 
					//通过井编号查询井号
					WellInfo wellInfo=wellInfoSerivce.selectWellInfoById(list.get(i).getWellid());
			        cell = row.createCell(1, CellType.STRING);
			        cell.setCellValue(wellInfo.getWellcode()); 
			        cell = row.createCell(2, CellType.STRING);
			        if(null!=DateUtil.DateToString(list.get(i).getLogdate(),DateUtil.YYYY_MM_DD)){
			        	cell.setCellValue(DateUtil.DateToString(list.get(i).getLogdate(),DateUtil.YYYY_MM_DD));
			        }else{
			        	cell.setCellValue("");
			        }
					cell = row.createCell(3, CellType.STRING);
					if(null!=list.get(i).getDid()){
			        	cell.setCellValue(getDeptName(list.get(i).getDid())); 
			        }else{
			        	cell.setCellValue(""); 
			        }
					cell = row.createCell(4, CellType.STRING);
					cell.setCellValue(list.get(i).getDly());
					
					cell = row.createCell(5, CellType.STRING);
					cell.setCellValue(list.get(i).getZygz());
					
					cell = row.createCell(6, CellType.STRING);
					cell.setCellValue(list.get(i).getMqjd());
					
					cell = row.createCell(7, CellType.STRING);
					cell.setCellValue(list.get(i).getZtwj());
					
					cell = row.createCell(8, CellType.STRING);
					cell.setCellValue(list.get(i).getPy());
					
					cell = row.createCell(9, CellType.STRING);
					cell.setCellValue(list.get(i).getPl());
					
					cell = row.createCell(10, CellType.STRING);
					cell.setCellValue(list.get(i).getBc());
					
					cell = row.createCell(11, CellType.STRING);
					cell.setCellValue(list.get(i).getGj());
					
					cell = row.createCell(12, CellType.STRING);
					cell.setCellValue(list.get(i).getXz());
					
					cell = row.createCell(13, CellType.STRING);
					cell.setCellValue(list.get(i).getJcdw());
					
					
					cell = row.createCell(14, CellType.STRING);
					cell.setCellValue(list.get(i).getJcddjyxts());
					
					
					cell = row.createCell(15, CellType.STRING);
					cell.setCellValue(list.get(i).getZs());
					
					cell = row.createCell(16, CellType.STRING);
					cell.setCellValue(list.get(i).getZpdw());
					
					
					cell = row.createCell(17, CellType.STRING);
					cell.setCellValue(list.get(i).getDjzs());
					
					cell = row.createCell(18, CellType.STRING);
					cell.setCellValue(list.get(i).getCyjyxts());
					
					
					cell = row.createCell(19, CellType.STRING);
					cell.setCellValue(list.get(i).getFdjyxts());
					
					cell = row.createCell(20, CellType.STRING);
					if(null!=list.get(i).getCyxh()){
						cell.setCellValue(list.get(i).getCyxh());
					}else{
						cell.setCellValue("");
					}
					
					cell = row.createCell(21, CellType.STRING);
					if(null!=list.get(i).getCylj()){
						cell.setCellValue(list.get(i).getCylj());
					}else{
						cell.setCellValue("");
					}
					
					cell = row.createCell(22, CellType.STRING);
					if(null!=list.get(i).getCjlj()){
						cell.setCellValue(list.get(i).getCjlj());
					}else{
						cell.setCellValue("");
					}
					
					cell = row.createCell(23, CellType.STRING);
					if(null!=list.get(i).getRydl()){
						cell.setCellValue(list.get(i).getRydl());
					}else{
						cell.setCellValue("");
					}
					
					cell = row.createCell(24, CellType.STRING);
					if(null!=list.get(i).getRylj()){
						cell.setCellValue(list.get(i).getRylj());
					}else{
						cell.setCellValue("");
					}
					
					cell = row.createCell(25, CellType.STRING);
					if(null!=list.get(i).getRjlj()){
						cell.setCellValue(list.get(i).getRjlj());
					}else{
						cell.setCellValue("");
					}
					
					cell = row.createCell(26, CellType.STRING);
					cell.setCellValue(list.get(i).getXhqk());
					
				}
			}
		}
		if("welltime".equals(type)){
			if(null!=obj){
				List<WellTime> list=(List<WellTime>)obj;
				HSSFRow row = sheet.createRow(0);
			    HSSFCell cell = row.createCell(0, CellType.STRING);  
			    cell.setCellValue(name+"(单位:小时)");
			    cell.setCellStyle(cellStyle);
			    row.setHeight((short) 700);// 设置行高   
			    //合并
			    CellRangeAddress region1 = new CellRangeAddress(0, 0, (short) 0, (short) 31); 
			    sheet.addMergedRegion(region1);
			    //第二行
			    row=sheet.createRow(1);
			    cell = row.createCell(0, CellType.STRING);
				cell.setCellValue("序号");
				cell.setCellStyle(cellStyle);
				cell = row.createCell(1, CellType.STRING);
				cell.setCellValue("井号");
				cell.setCellStyle(cellStyle);
				cell = row.createCell(2, CellType.STRING);
				cell.setCellValue("日期");
				sheet.setColumnWidth(2, 2750); //设置列宽
				cell.setCellStyle(cellStyle);
				cell = row.createCell(3, CellType.STRING);
				cell.setCellValue("纯钻时间");
				sheet.setColumnWidth(3, 1800); //设置列宽
				cell.setCellStyle(cellStyle);
				cell = row.createCell(4, CellType.STRING);
				cell.setCellValue("起下钻时间");
				sheet.setColumnWidth(4, 1800); //设置列宽
				cell.setCellStyle(cellStyle);
				cell = row.createCell(5, CellType.STRING);
				cell.setCellValue("接单根");
				sheet.setColumnWidth(5, 1800); //设置列宽
				cell.setCellStyle(cellStyle);
				cell = row.createCell(6, CellType.STRING);
				cell.setCellValue("扩划眼	");
				sheet.setColumnWidth(6, 1800); //设置列宽
				cell.setCellStyle(cellStyle);
				cell = row.createCell(7, CellType.STRING);
				cell.setCellValue("换钻头");
				sheet.setColumnWidth(7, 1800); //设置列宽
				cell.setCellStyle(cellStyle);
				cell = row.createCell(8, CellType.STRING);
				cell.setCellValue("循环");
				sheet.setColumnWidth(8, 1800); //设置列宽
				cell.setCellStyle(cellStyle);
				cell = row.createCell(9, CellType.STRING);
				cell.setCellValue("候凝");
				sheet.setColumnWidth(9, 1800); //设置列宽
				cell.setCellStyle(cellStyle);
				cell = row.createCell(10, CellType.STRING);
				cell.setCellValue("测井");
				sheet.setColumnWidth(10, 1800); //设置列宽
				cell.setCellStyle(cellStyle);
				cell = row.createCell(11, CellType.STRING);
				cell.setCellValue("甩钻具");
				sheet.setColumnWidth(11, 1800); //设置列宽
				cell.setCellStyle(cellStyle);
				cell = row.createCell(12, CellType.STRING);
				cell.setCellValue("下套管");
				sheet.setColumnWidth(12, 1800); //设置列宽
				cell.setCellStyle(cellStyle);
				cell = row.createCell(13, CellType.STRING);
				cell.setCellValue("固井");
				sheet.setColumnWidth(13, 1800); //设置列宽
				cell.setCellStyle(cellStyle);
				cell = row.createCell(14, CellType.STRING);
				cell.setCellValue("试压");
				sheet.setColumnWidth(14, 1800); //设置列宽
				cell.setCellStyle(cellStyle);
				cell = row.createCell(15, CellType.STRING);
				cell.setCellValue("辅助工作");
				sheet.setColumnWidth(15, 1800); //设置列宽
				cell.setCellStyle(cellStyle);
				cell = row.createCell(16, CellType.STRING);
				cell.setCellValue("生产时间");
				sheet.setColumnWidth(16, 1800); //设置列宽
				cell.setCellStyle(cellStyle);
				cell = row.createCell(17, CellType.STRING);
				cell.setCellValue("事故");
				sheet.setColumnWidth(17, 1800); //设置列宽
				cell.setCellStyle(cellStyle);
				cell = row.createCell(18, CellType.STRING);
				cell.setCellValue("组停");
				sheet.setColumnWidth(18, 1800); //设置列宽
				cell.setCellStyle(cellStyle);
				cell = row.createCell(19, CellType.STRING);
				cell.setCellValue("修理");
				sheet.setColumnWidth(19, 1800); //设置列宽
				cell.setCellStyle(cellStyle);
				cell = row.createCell(20, CellType.STRING);
				cell.setCellValue("自然灾害");
				sheet.setColumnWidth(20, 1800); //设置列宽
				cell.setCellStyle(cellStyle);
				cell = row.createCell(21, CellType.STRING);
				cell.setCellValue("复杂");
				sheet.setColumnWidth(21, 1800); //设置列宽
				cell.setCellStyle(cellStyle);
				cell = row.createCell(22, CellType.STRING);
				cell.setCellValue("其他");
				sheet.setColumnWidth(22, 1300); //设置列宽
				cell.setCellStyle(cellStyle);
				cell = row.createCell(23, CellType.STRING);
				cell.setCellValue("非生产时间");
				sheet.setColumnWidth(23, 1800); //设置列宽
				cell.setCellStyle(cellStyle);
				cell = row.createCell(24, CellType.STRING);
				cell.setCellValue("中途测试");
				sheet.setColumnWidth(24, 1800); //设置列宽
				cell.setCellStyle(cellStyle);
				cell = row.createCell(25, CellType.STRING);
				cell.setCellValue("VSP测井");
				sheet.setColumnWidth(25, 1800); //设置列宽
				cell.setCellStyle(cellStyle);
				cell = row.createCell(26, CellType.STRING);
				cell.setCellValue("完井测试");
				sheet.setColumnWidth(26, 1800); //设置列宽
				cell.setCellStyle(cellStyle);
				cell = row.createCell(27, CellType.STRING);
				cell.setCellValue("搬迁安装");
				sheet.setColumnWidth(27, 1800); //设置列宽
				cell.setCellStyle(cellStyle);
				cell = row.createCell(28, CellType.STRING);
				cell.setCellValue("井眼准备");
				sheet.setColumnWidth(28, 1800); //设置列宽
				cell.setCellStyle(cellStyle);
				cell = row.createCell(29, CellType.STRING);
				cell.setCellValue("拆甩设备");
				sheet.setColumnWidth(29, 1800); //设置列宽
				cell.setCellStyle(cellStyle);
				cell = row.createCell(30, CellType.STRING);
				cell.setCellValue("非钻井时间");
				sheet.setColumnWidth(30, 1800); //设置列宽
				cell.setCellStyle(cellStyle);
				cell = row.createCell(31, CellType.STRING);
				cell.setCellValue("合计");
				sheet.setColumnWidth(31, 1800); //设置列宽
				cell.setCellStyle(cellStyle);
				for(int i=0;i<list.size();i++){
					row=sheet.createRow(i+2);
					cell = row.createCell(0, CellType.STRING);
					cell.setCellValue(i+1); 
					//通过井编号查询井号
					WellInfo wellInfo=wellInfoSerivce.selectWellInfoById(list.get(i).getWellid());
			        cell = row.createCell(1, CellType.STRING);
			        cell.setCellValue(wellInfo.getWellcode()); 
			        cell = row.createCell(2, CellType.STRING);
			        if(null!=DateUtil.DateToString(list.get(i).getLogdate(),DateUtil.YYYY_MM_DD)){
			        	cell.setCellValue(DateUtil.DateToString(list.get(i).getLogdate(),DateUtil.YYYY_MM_DD));
			        }else{
			        	cell.setCellValue("");
			        }
			        cell = row.createCell(3, CellType.STRING);
			        if(null!=list.get(i).getCzsj()){
			        	cell.setCellValue(""+list.get(i).getCzsj());
			        }else{
			        	cell.setCellValue("");
			        }
					
					cell = row.createCell(4, CellType.STRING);
					if(null!=list.get(i).getQxzsj()){
			        	cell.setCellValue(""+list.get(i).getQxzsj());
			        }else{
			        	cell.setCellValue("");
			        }
					
					cell = row.createCell(5, CellType.STRING);
					if(null!=list.get(i).getJdgsj()){
			        	cell.setCellValue(""+list.get(i).getJdgsj());
			        }else{
			        	cell.setCellValue("");
			        }
					
					cell = row.createCell(6, CellType.STRING);
					if(null!=list.get(i).getKhy()){
			        	cell.setCellValue(""+list.get(i).getKhy());
			        }else{
			        	cell.setCellValue("");
			        }
					
					cell = row.createCell(7, CellType.STRING);
					if(null!=list.get(i).getHzt()){
						cell.setCellValue(""+list.get(i).getHzt());
					}else{
						cell.setCellValue("");
					}
					
					cell = row.createCell(8, CellType.STRING);
					if(null!=list.get(i).getXh()){
						cell.setCellValue(""+list.get(i).getXh());
					}else{
						cell.setCellValue("");
					}
					
					cell = row.createCell(9, CellType.STRING);
					if(null!=list.get(i).getHn()){
						cell.setCellValue(""+list.get(i).getHn());
					}else{
						cell.setCellValue("");
					}
				
					cell = row.createCell(10, CellType.STRING);
					if(null!=list.get(i).getCj()){
						cell.setCellValue(""+list.get(i).getCj());
					}else{
						cell.setCellValue("");
					}
					
					cell = row.createCell(11, CellType.STRING);
					if(null!=list.get(i).getSzj()){
						cell.setCellValue(""+list.get(i).getSzj());
					}else{
						cell.setCellValue("");
					}
					
					cell = row.createCell(12, CellType.STRING);
					if(null!=list.get(i).getXtg()){
						cell.setCellValue(""+list.get(i).getXtg());
					}else{
						cell.setCellValue("");
					}
					
					cell = row.createCell(13, CellType.STRING);
					if(null!=list.get(i).getGj()){
						cell.setCellValue(""+list.get(i).getGj());
					}else{
						cell.setCellValue("");
					}
					
					cell = row.createCell(14, CellType.STRING);
					if(null!=list.get(i).getSy()){
						cell.setCellValue(""+list.get(i).getSy());
					}else{
						cell.setCellValue("");
					}
					
					cell = row.createCell(15, CellType.STRING);
					if(null!=list.get(i).getFzgz()){
						cell.setCellValue(""+list.get(i).getFzgz());
					}else{
						cell.setCellValue("");
					}
					
					cell = row.createCell(16, CellType.STRING);
					if(null!=list.get(i).getScsj()){
						cell.setCellValue(""+list.get(i).getScsj());
					}else{
						cell.setCellValue("");
					}
					
					cell = row.createCell(17, CellType.STRING);
					if(null!=list.get(i).getSg()){
						cell.setCellValue(""+list.get(i).getSg());
					}else{
						cell.setCellValue("");
					}
					
					cell = row.createCell(18, CellType.STRING);
					if(null!=list.get(i).getZt()){
						cell.setCellValue(""+list.get(i).getZt());
					}else{
						cell.setCellValue("");
					}
					
					cell = row.createCell(19, CellType.STRING);
					if(null!=list.get(i).getXl()){
						cell.setCellValue(""+list.get(i).getXl());
					}else{
						cell.setCellValue("");
					}
					
					cell = row.createCell(20, CellType.STRING);
					if(null!=list.get(i).getZyzh()){
						cell.setCellValue(""+list.get(i).getZyzh());
					}else{
						cell.setCellValue("");
					}
					
					cell = row.createCell(21, CellType.STRING);
					if(null!=list.get(i).getFz()){
						cell.setCellValue(""+list.get(i).getFz());
					}else{
						cell.setCellValue("");
					}
					
					cell = row.createCell(22, CellType.STRING);
					if(null!=list.get(i).getQt()){
						cell.setCellValue(""+list.get(i).getQt());
					}else{
						cell.setCellValue("");
					}
					
					cell = row.createCell(23, CellType.STRING);
					if(null!=list.get(i).getFscsj()){
						cell.setCellValue(""+list.get(i).getFscsj());
					}else{
						cell.setCellValue("");
					}
					
					cell = row.createCell(24, CellType.STRING);
					if(null!=list.get(i).getZtcs()){
						cell.setCellValue(""+list.get(i).getZtcs());
					}else{
						cell.setCellValue("");
					}
					cell = row.createCell(25, CellType.STRING);
					if(null!=list.get(i).getVspcj()){
						cell.setCellValue(""+list.get(i).getVspcj());
					}else{
						cell.setCellValue("");
					}
					
					cell = row.createCell(26, CellType.STRING);
					if(null!=list.get(i).getVscs()){
						cell.setCellValue(""+list.get(i).getVscs());
					}else{
						cell.setCellValue("");
					}
					
					cell = row.createCell(27, CellType.STRING);
					if(null!=list.get(i).getBqaz()){
						cell.setCellValue(""+list.get(i).getBqaz());
					}else{
						cell.setCellValue("");
					}
					
					cell = row.createCell(28, CellType.STRING);
					if(null!=list.get(i).getJyzb()){
						cell.setCellValue(""+list.get(i).getJyzb());
					}else{
						cell.setCellValue("");
					}
					
					cell = row.createCell(29, CellType.STRING);
					if(null!=list.get(i).getCxsb()){
						cell.setCellValue(""+list.get(i).getCxsb());
					}else{
						cell.setCellValue("");
					}
					
					cell = row.createCell(30, CellType.STRING);
					if(null!=list.get(i).getFzjsj()){
						cell.setCellValue(""+list.get(i).getFzjsj());
					}else{
						cell.setCellValue("");
					}
					
					cell = row.createCell(31, CellType.STRING);
					if(null!=list.get(i).getHj()){
						cell.setCellValue(""+list.get(i).getHj());
					}else{
						cell.setCellValue("");
					}
					
				}
			}
		}
		if("wellbit".equals(type)){
			if(null!=obj){
				List<WellBit> list=(List<WellBit>)obj;
				HSSFRow row = sheet.createRow(0);
			    HSSFCell cell = row.createCell(0, CellType.STRING);  
			    cell.setCellValue(name);
			    cell.setCellStyle(cellStyle);
			    row.setHeight((short) 700);// 设置行高   
			    //合并
			    CellRangeAddress region1 = new CellRangeAddress(0, 0, (short) 0, (short) 19); 
			    sheet.addMergedRegion(region1);
			    //第二行
			    row=sheet.createRow(1);
			    cell = row.createCell(0, CellType.STRING);
				cell.setCellValue("序号");
				cell.setCellStyle(cellStyle);
				cell = row.createCell(1, CellType.STRING);
				cell.setCellValue("井号");
				cell.setCellStyle(cellStyle);
				cell = row.createCell(2, CellType.STRING);
				cell.setCellValue("入井序号");
				sheet.setColumnWidth(2, 2500); //设置列宽
				cell.setCellStyle(cellStyle);
				cell = row.createCell(3, CellType.STRING);
				cell.setCellValue("钻头尺寸");
				sheet.setColumnWidth(3, 2500); //设置列宽
				cell.setCellStyle(cellStyle);
				cell = row.createCell(4, CellType.STRING);
				cell.setCellValue("钻头型号");
				sheet.setColumnWidth(4, 2500); //设置列宽
				cell.setCellStyle(cellStyle);
				cell = row.createCell(5, CellType.STRING);
				cell.setCellValue("钻头编号");
				sheet.setColumnWidth(5, 2500); //设置列宽
				cell.setCellStyle(cellStyle);
				cell = row.createCell(6, CellType.STRING);
				cell.setCellValue("生产厂家");
				sheet.setColumnWidth(6, 2500); //设置列宽
				cell.setCellStyle(cellStyle);
				cell = row.createCell(7, CellType.STRING);
				cell.setCellValue("起始井深");
				sheet.setColumnWidth(7, 2500); //设置列宽
				cell.setCellStyle(cellStyle);
				cell = row.createCell(8, CellType.STRING);
				cell.setCellValue("终止井深");
				sheet.setColumnWidth(8, 2500); //设置列宽
				cell.setCellStyle(cellStyle);
				cell = row.createCell(9, CellType.STRING);
				cell.setCellValue("入井时间");
				sheet.setColumnWidth(9, 2500); //设置列宽
				cell.setCellStyle(cellStyle);
				cell = row.createCell(10, CellType.STRING);
				cell.setCellValue("起出时间");
				sheet.setColumnWidth(10, 2500); //设置列宽
				cell.setCellStyle(cellStyle);
				cell = row.createCell(11, CellType.STRING);
				cell.setCellValue("入井新度");
				sheet.setColumnWidth(11, 2500); //设置列宽
				cell.setCellStyle(cellStyle);
				cell = row.createCell(12, CellType.STRING);
				cell.setCellValue("出井新度");
				sheet.setColumnWidth(12, 2500); //设置列宽
				cell.setCellStyle(cellStyle);
				cell = row.createCell(13, CellType.STRING);
				cell.setCellValue("层位");
				cell.setCellStyle(cellStyle);
				cell = row.createCell(14, CellType.STRING);
				cell.setCellValue("主要岩性");
				sheet.setColumnWidth(14, 2500); //设置列宽
				cell.setCellStyle(cellStyle);
				cell = row.createCell(15, CellType.STRING);
				cell.setCellValue("钻头水眼");
				sheet.setColumnWidth(15, 2500); //设置列宽
				cell.setCellStyle(cellStyle);
				cell = row.createCell(16, CellType.STRING);
				cell.setCellValue("磨损情况");
				sheet.setColumnWidth(16, 2500); //设置列宽
				cell.setCellStyle(cellStyle);
				cell = row.createCell(17, CellType.STRING);
				cell.setCellValue("下步去向");
				sheet.setColumnWidth(17, 2500); //设置列宽
				cell.setCellStyle(cellStyle);
				cell = row.createCell(18, CellType.STRING);
				cell.setCellValue("上传钻头下入图片");
				sheet.setColumnWidth(18, 2500); //设置列宽
				cell.setCellStyle(cellStyle);
				cell = row.createCell(19, CellType.STRING);
				cell.setCellValue("上传钻头取出图片");
				sheet.setColumnWidth(19, 2500); //设置列宽
				cell.setCellStyle(cellStyle);
				
				for(int i=0;i<list.size();i++){
					row=sheet.createRow(i+2);
					cell = row.createCell(0, CellType.STRING);
					cell.setCellValue(i+1); 
					//通过井编号查询井号
					WellInfo wellInfo=wellInfoSerivce.selectWellInfoById(list.get(i).getWellid());
			        cell = row.createCell(1, CellType.STRING);
			        cell.setCellValue(wellInfo.getWellcode()); 
			        cell = row.createCell(2, CellType.STRING);
					cell.setCellValue(list.get(i).getRjxh());
					
					cell = row.createCell(3, CellType.STRING);
					cell.setCellValue(list.get(i).getZtcc());
					
					cell = row.createCell(4, CellType.STRING);
					cell.setCellValue(list.get(i).getZtxh());
					
					cell = row.createCell(5, CellType.STRING);
					cell.setCellValue(list.get(i).getZtbh());
					
					cell = row.createCell(6, CellType.STRING);
					cell.setCellValue(list.get(i).getSccj());
					
					cell = row.createCell(7, CellType.STRING);
					cell.setCellValue(list.get(i).getQsjs());
					
					cell = row.createCell(8, CellType.STRING);
					cell.setCellValue(list.get(i).getZzsj());
					//入井时间
					cell = row.createCell(9, CellType.STRING);
					if(null!=list.get(i).getRjsj()){
						cell.setCellValue(DateUtil.DateToString(list.get(i).getRjsj(),DateUtil.YMDHMS));
					}else{
						cell.setCellValue("");
					}
					
					cell = row.createCell(10, CellType.STRING);
					if(null!=list.get(i).getQcsj()){
						cell.setCellValue(DateUtil.DateToString(list.get(i).getQcsj(),DateUtil.YMDHMS));
					}else{
						cell.setCellValue("");
					}
					
					cell = row.createCell(11, CellType.STRING);
					cell.setCellValue(list.get(i).getRjxd());
					
					cell = row.createCell(12, CellType.STRING);
					cell.setCellValue(list.get(i).getCjxd());
					
					cell = row.createCell(13, CellType.STRING);
					cell.setCellValue(list.get(i).getCw());
					
					cell = row.createCell(14, CellType.STRING);
					cell.setCellValue(list.get(i).getZyyx());
					
					cell = row.createCell(15, CellType.STRING);
					cell.setCellValue(list.get(i).getZtsy());
					
					cell = row.createCell(16, CellType.STRING);
					cell.setCellValue(list.get(i).getMsqk());
					
					cell = row.createCell(17, CellType.STRING);
					cell.setCellValue(list.get(i).getXbqx());
					
					cell = row.createCell(18, CellType.STRING);
					cell.setCellValue(list.get(i).getZtxrurl());
					
					cell = row.createCell(19, CellType.STRING);
					cell.setCellValue(list.get(i).getZtqcurl());
					
				}
			}
		}
		if("dxwell".equals(type)){
			if(null!=obj){
				List<WellDxDaily> list=(List<WellDxDaily>)obj;
				HSSFRow row = sheet.createRow(0);
			    HSSFCell cell = row.createCell(0, CellType.STRING);  
			    cell.setCellValue(name);
			    cell.setCellStyle(cellStyle);
			    row.setHeight((short) 700);// 设置行高   
			    //合并
			    CellRangeAddress region1 = new CellRangeAddress(0, 0, (short) 0, (short) 21); 
			    sheet.addMergedRegion(region1);
			    //第二行
			    row=sheet.createRow(1);
			    cell = row.createCell(0, CellType.STRING);
				cell.setCellValue("序号");
				cell.setCellStyle(cellStyle);
				cell = row.createCell(1, CellType.STRING);
				cell.setCellValue("井号");
				cell.setCellStyle(cellStyle);
				cell = row.createCell(2, CellType.STRING);
				cell.setCellValue("日期");
				cell.setCellStyle(cellStyle);
				sheet.setColumnWidth(2, 2750); //设置列宽
				cell = row.createCell(3, CellType.STRING);
				cell.setCellValue("井队");
				sheet.setColumnWidth(3, 2800); //设置列宽
				cell.setCellStyle(cellStyle);
				cell = row.createCell(4, CellType.STRING);
				cell.setCellValue("井型");
				cell.setCellStyle(cellStyle);
				cell = row.createCell(5, CellType.STRING);
				cell.setCellValue("设计井深");
				sheet.setColumnWidth(5, 2500); //设置列宽
				cell.setCellStyle(cellStyle);
				cell = row.createCell(6, CellType.STRING);
				cell.setCellValue("井深");
				cell.setCellStyle(cellStyle);
				cell = row.createCell(7, CellType.STRING);
				cell.setCellValue("造斜点");
				sheet.setColumnWidth(7, 2500); //设置列宽
				cell.setCellStyle(cellStyle);
				cell = row.createCell(8, CellType.STRING);
				cell.setCellValue("日进尺");
				sheet.setColumnWidth(8, 2500); //设置列宽
				cell.setCellStyle(cellStyle);
				cell = row.createCell(9, CellType.STRING);
				cell.setCellValue("钻压");
				cell.setCellStyle(cellStyle);
				cell = row.createCell(10, CellType.STRING);
				cell.setCellValue("排量");
				cell.setCellStyle(cellStyle);
				cell = row.createCell(11, CellType.STRING);
				cell.setCellValue("测斜点(m)");
				sheet.setColumnWidth(11, 2500); //设置列宽
				cell.setCellStyle(cellStyle);
				cell = row.createCell(12, CellType.STRING);
				cell.setCellValue("井斜(°)");
				cell.setCellStyle(cellStyle);
				cell = row.createCell(13, CellType.STRING);
				cell.setCellValue("方位(°)");
				cell.setCellStyle(cellStyle);
				cell = row.createCell(14, CellType.STRING);
				cell.setCellValue("垂深(m)");
				cell.setCellStyle(cellStyle);
				cell = row.createCell(15, CellType.STRING);
				cell.setCellValue("闭合距(m)");
				sheet.setColumnWidth(15, 2500); //设置列宽
				cell.setCellStyle(cellStyle);
				cell = row.createCell(16, CellType.STRING);
				cell.setCellValue("闭合方位(°)");
				cell.setCellStyle(cellStyle);
				cell = row.createCell(17, CellType.STRING);
				cell.setCellValue("仪器类型");
				sheet.setColumnWidth(17, 2500); //设置列宽
				cell.setCellStyle(cellStyle);
				cell = row.createCell(18, CellType.STRING);
				cell.setCellValue("工程概况");
				sheet.setColumnWidth(18, 2500); //设置列宽
				cell.setCellStyle(cellStyle);
				cell = row.createCell(19, CellType.STRING);
				cell.setCellValue("下一步方案");
				sheet.setColumnWidth(19, 3000); //设置列宽
				cell.setCellStyle(cellStyle);
				cell = row.createCell(20, CellType.STRING);
				cell.setCellValue("钻具组合");
				sheet.setColumnWidth(20, 2500); //设置列宽
				cell.setCellStyle(cellStyle);
				cell = row.createCell(21, CellType.STRING);
				cell.setCellValue("施工人员");
				sheet.setColumnWidth(21, 2500); //设置列宽
				cell.setCellStyle(cellStyle);
				for(int i=0;i<list.size();i++){
					row=sheet.createRow(i+2);
					cell = row.createCell(0, CellType.STRING);
					cell.setCellValue(i+1); 
					//通过井编号查询井号
					WellDxInfo welldxInfo=wellDxInfoSerivce.selectDxWellInfoById(list.get(i).getWellid());
			        cell = row.createCell(1, CellType.STRING);
			        cell.setCellValue(welldxInfo.getWellcode());
			        cell = row.createCell(2, CellType.STRING);
			        if(null!=DateUtil.DateToString(list.get(i).getLogdate(),DateUtil.YYYY_MM_DD)){
			        	cell.setCellValue(DateUtil.DateToString(list.get(i).getLogdate(),DateUtil.YYYY_MM_DD));
			        }else{
			        	cell.setCellValue("");
			        }
			        cell = row.createCell(3, CellType.STRING);
			        if(null!=list.get(i).getDid()){
			        	cell.setCellValue(getDeptName(list.get(i).getDid())); 
			        }else{
			        	cell.setCellValue(""); 
			        }
					cell = row.createCell(4, CellType.STRING);
					cell.setCellValue(list.get(i).getJx());
					
					cell = row.createCell(5, CellType.STRING);
					if(null!=list.get(i).getSjjs()){
						cell.setCellValue(list.get(i).getSjjs());//类型
					}else{
						cell.setCellValue("");
					}
					
					cell = row.createCell(6, CellType.STRING);
					if(null!=list.get(i).getJs()){
						cell.setCellValue(list.get(i).getJs());//类型
					}else{
						cell.setCellValue("");
					}
					
					cell = row.createCell(7, CellType.STRING);
					cell.setCellValue(list.get(i).getZxd());
					
					cell = row.createCell(8, CellType.STRING);
					if(null!=list.get(i).getRjc()){
						cell.setCellValue(""+list.get(i).getRjc());
					}else{
						cell.setCellValue("");
					}
					
					cell = row.createCell(9, CellType.STRING);
					cell.setCellValue(list.get(i).getZy());
					
					cell = row.createCell(10, CellType.STRING);
					cell.setCellValue(list.get(i).getPl());
					
					cell = row.createCell(11, CellType.STRING);
					cell.setCellValue(list.get(i).getCxd());
					
					cell = row.createCell(12, CellType.STRING);
					cell.setCellValue(list.get(i).getJxd());
					
					cell = row.createCell(13, CellType.STRING);
					cell.setCellValue(list.get(i).getFw());
					
					cell = row.createCell(14, CellType.STRING);
					cell.setCellValue(list.get(i).getCs());
					
					cell = row.createCell(15, CellType.STRING);
					cell.setCellValue(list.get(i).getBhj());
					
					cell = row.createCell(16, CellType.STRING);
					cell.setCellValue(list.get(i).getBhfw());
					
					cell = row.createCell(17, CellType.STRING);
					cell.setCellValue(list.get(i).getYqlx());
					
					cell = row.createCell(18, CellType.STRING);
					cell.setCellValue(list.get(i).getGcgk());
					
					cell = row.createCell(19, CellType.STRING);
					cell.setCellValue(list.get(i).getXybjh());
					
					cell = row.createCell(20, CellType.STRING);
					cell.setCellValue(list.get(i).getZjzh());
					
					cell = row.createCell(21, CellType.STRING);
					cell.setCellValue(list.get(i).getSgry());
					
				}
			}
		}
		if("xwlog".equals(type)){
			if(null!=obj){
				List<WellXwDaily> list=(List<WellXwDaily>) obj;
				if(null!=list){
					//创建行,第一行  
			        HSSFRow row1 = sheet.createRow(0);
			        HSSFCell cell = row1.createCell(0, CellType.STRING);  
			        cell.setCellValue(name);
			        cell.setCellStyle(cellStyle);
			        row1.setHeight((short) 700);// 设置行高   
			        //合并
			        CellRangeAddress region1 = new CellRangeAddress(0, 0, (short) 0, (short) 8); 
			        sheet.addMergedRegion(region1);
			        row1 = sheet.createRow(1); 
			        
					sheet.setColumnWidth(3, 4000); //设置列宽
					sheet.setColumnWidth(4, 4000); //设置列宽
					sheet.setColumnWidth(5, 8000); //设置列宽
					sheet.setColumnWidth(6, 8000); //设置列宽
			        
			        //创建单元格，操作第一行各列
			        cell = row1.createCell(0, CellType.STRING);  
			        cell.setCellValue("编号");
			        cell.setCellStyle(cellStyle);
			        row1.setHeight((short) 400);// 设置行高   
			        cell = row1.createCell(1, CellType.STRING);
			        cell.setCellValue("井号"); 
			        cell.setCellStyle(cellStyle);
			        cell = row1.createCell(2, CellType.STRING);
			        cell.setCellValue("日期"); 
			        cell.setCellStyle(cellStyle);
			        sheet.setColumnWidth(2, 2750); //设置列宽
			        cell = row1.createCell(3, CellType.STRING);
			        cell.setCellValue("井队");
			        sheet.setColumnWidth(3, 2800); //设置列宽
			        cell.setCellStyle(cellStyle);
			        cell = row1.createCell(4, CellType.STRING);
			        cell.setCellValue("井深(m)"); 
			        cell.setCellStyle(cellStyle);
			        cell = row1.createCell(5, CellType.STRING);
			        cell.setCellValue("进尺(m)"); 
			        cell.setCellStyle(cellStyle);
			        cell = row1.createCell(6, CellType.STRING);
			        cell.setCellValue("工程简况");
			        cell.setCellStyle(cellStyle);
			        cell = row1.createCell(7, CellType.STRING);
			        cell.setCellValue("下一步计划"); 
			        cell.setCellStyle(cellStyle);
			        cell = row1.createCell(8, CellType.STRING);
			        cell.setCellValue("汇报人"); 
			        cell.setCellStyle(cellStyle);
			        for(int i=0;i<list.size();i++){
			        	row1=sheet.createRow(i+2);
						cell = row1.createCell(0, CellType.STRING);
						cell.setCellValue(i+1); 
				        
						//通过井编号查询井号
						WellInfo wellInfo=wellInfoSerivce.selectWellInfoById(list.get(i).getWellid());
						cell = row1.createCell(1, CellType.STRING);
						cell.setCellValue(wellInfo.getWellcode()); 
						cell = row1.createCell(2, CellType.STRING);
				        if(null!=DateUtil.DateToString(list.get(i).getLogdate(),DateUtil.YYYY_MM_DD)){
				        	cell.setCellValue(DateUtil.DateToString(list.get(i).getLogdate(),DateUtil.YYYY_MM_DD));
				        }else{
				        	cell.setCellValue("");
				        }
				        cell = row1.createCell(3, CellType.STRING);
				        if(null!=list.get(i).getDid()){
				        	cell.setCellValue(getDeptName(list.get(i).getDid())); 
				        }else{
				        	cell.setCellValue(""); 
				        }
				        cell = row1.createCell(4, CellType.STRING);
				        
				        if(null!=list.get(i).getTdepth()){
				        	cell.setCellValue(list.get(i).getTdepth());
				        }else{
				        	cell.setCellValue("");
				        }
				        
				        cell = row1.createCell(5, CellType.STRING);
				        if(null!=list.get(i).getJc()){
				        	cell.setCellValue(list.get(i).getJc());
				        }else{
				        	cell.setCellValue("");
				        }
				        
				        cell = row1.createCell(6, CellType.STRING);
				        cell.setCellValue(list.get(i).getGcjk());
				        
				        cell = row1.createCell(7, CellType.STRING);
				        cell.setCellValue(list.get(i).getXybjh());
				        
				        cell = row1.createCell(8, CellType.STRING);
				        cell.setCellValue(list.get(i).getHbr());
				        
				        
			        }
				}
			}
		}
		PrintSetup   printSetUp = sheet.getPrintSetup();
		sheet.setAutobreaks(true);
		printSetUp.setFitHeight((short)1); //一页打印
		printSetUp.setPaperSize(PrintSetup.A4_PAPERSIZE);//设置
		sheet.setHorizontallyCenter(true);//设置打印页面为水平居中  
		sheet.setVerticallyCenter(true);//设置打印页面为垂直居中  
		ByteArrayOutputStream os=new ByteArrayOutputStream();
		try {
			workBook.write(os);
		} catch (Exception e) {
			e.printStackTrace();
		}
		byte[] content=os.toByteArray();
		ByteArrayInputStream is = new ByteArrayInputStream(content);
        os.close();
        workBook.close();
        return is;
	}
	/**
	 * 不同类型汇总的导出
	 * @param type
	 * @param wellId
	 * @param logDate
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value="")
	public ResponseEntity<byte[]> listexport(String type,Integer wellId,String logDate,HttpServletRequest req) throws IOException {
		ResponseEntity<byte[]> entity = null;
		Message message=new Message();
		String name="";
		Date logDate2=DateUtil.convertStringToDate(logDate, DateUtil.YYYY_MM_DD);
		String logDate3=DateUtil.DateToString(logDate2, DateUtil.YYYYMMDD);
		ByteArrayInputStream is =null;
		try {
			if("welllog".equals(type)){
				message=wellDailySumCOntroller.getAll(wellId, logDate2);
				name="钻井日报汇总"+logDate3;
			}
			if("dxlog".equals(type)){
				name="定向井日报汇总"+logDate3;
				message=wellDxDailyService.selectDxWellDaily(logDate2, wellId);
			}
			if("xwlog".equals(type)){
				name="下午日报汇总"+logDate3;
				message=WellXwDailyService.selectXwWellDaily(logDate2, wellId);
			}
			if("wdlog".equals(type)){
				name="网电日报汇总"+logDate3;
				message=wdDailyService.getWdLogInfoByLogDate(logDate2);
			}
			//钻井液处理剂及材料使用情况
			if("mudssxh".equals(type)){
				//查询井名称 
				WellInfo wellInfo=wellInfoSerivce.selectWellInfoById(wellId);
				name=wellInfo.getWellcode()+"钻井液处理剂及材料使用情况"+logDate3;
				WellMud wellMud=wellMudService.getOneDay(logDate2,wellId);
				if(wellMud!=null&&null!=wellMud.getSsxh()){
					List<SsxhListDto> dtolist=JacksonUtil.JsonToList(wellMud.getSsxh(), SsxhListDto.class);
					message.setObj(dtolist);
				}
			}
			is = makeData(type,message.getObj(),name,logDate);
			byte[] body = new byte[is.available()];
			is.read(body);
			HttpHeaders headers = new HttpHeaders();           
			headers.setContentType(MediaType.MULTIPART_FORM_DATA);
			System.out.println(req.getHeader("user-agent").toLowerCase());
			name = URLEncoder.encode(name, "UTF-8");//encode编码UTF-8 解决大多数中文乱码
//			if (req.getHeader("user-agent").toLowerCase().contains("ie")|| req.getHeader("user-agent").toLowerCase().contains("edge")) {
//		        // IE
//				headers.add("Content-Disposition", "attachment;filename="+new String(name.getBytes("utf-8"))+".xls");
//			}else{
				headers.add("Content-Disposition", "attachment;filename="+new String(name.getBytes("utf-8"),"iso8859-1")+".xls");
//			}
			HttpStatus statusCode = HttpStatus.OK;
			entity = new ResponseEntity<byte[]>(body, headers, statusCode);
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}finally {
			if(null!=is){
				is.close();
			}
		}
		return entity;
		
	}
	@SuppressWarnings("unchecked")
	private ByteArrayInputStream makeData(String type, Object obj,String name,String date) throws IOException {
		HSSFWorkbook workBook = new HSSFWorkbook();  
		HSSFSheet sheet = workBook.createSheet(name);
		HSSFCellStyle cellStyle = workBook.createCellStyle();   
		cellStyle.setAlignment(HorizontalAlignment.CENTER);// 居中  
		cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);//垂直
		HSSFFont font = workBook.createFont();  
		font.setFontName("Arial");  
		font.setFontHeightInPoints((short) 11);//设置字体大小  
		cellStyle.setFont(font);
		cellStyle.setWrapText(true);//设置自动换行  
		if("welllog".equals(type)){
			Map<String,Object> map=(Map<String, Object>) obj;
			if(null!=map.get("wellList")){
				 List<WellDaily> wellDailyList=(List<WellDaily>) map.get("wellList");
			     HSSFRow row = sheet.createRow(0);
			     HSSFCell cell = row.createCell(0, CellType.STRING);  
			     cell.setCellValue(name);
			     cell.setCellStyle(cellStyle);
			     row.setHeight((short) 700);// 设置行高   
			     //合并
			     CellRangeAddress region1 = new CellRangeAddress(0, 0, (short) 0, (short) 24); 
			     sheet.addMergedRegion(region1);
			     //第二行
			     row=sheet.createRow(1);
			     row.setHeight((short) 400);// 设置行高   
			     CellRangeAddress region2 = new CellRangeAddress(1, 2, (short) 0, (short) 0); 
			     cell = row.createCell(0, CellType.STRING);
				 cell.setCellValue("综合情况");
				 cell.setCellStyle(cellStyle);
				 sheet.setColumnWidth(0, 3500); //设置列宽
				 region2 = new CellRangeAddress(1, 2, (short) 0, (short) 0); 
				 sheet.addMergedRegion(region2);
				 
				 cell = row.createCell(1, CellType.STRING);
				 cell.setCellValue("日进尺(m)");
				 cell.setCellStyle(cellStyle);
				 region2 = new CellRangeAddress(1, 2, (short) 1, (short) 2); 
				 sheet.addMergedRegion(region2);
				 
				 cell = row.createCell(3, CellType.STRING);
				 cell.setCellValue("月累计(m)"); 
				 cell.setCellStyle(cellStyle);
				 region2 = new CellRangeAddress(1, 2, (short) 3, (short) 4); 
				 sheet.addMergedRegion(region2);
				 
				 cell = row.createCell(5, CellType.STRING);
				 cell.setCellValue("年累计(m)");
				 cell.setCellStyle(cellStyle);
				 region2 = new CellRangeAddress(1, 2, (short) 5, (short) 6); 
				 sheet.addMergedRegion(region2);
				 
			     region2 = new CellRangeAddress(1, 1, (short) 7, (short) 8); 
				 cell = row.createCell(7, CellType.STRING);
				 cell.setCellValue("开钻");  
				 cell.setCellStyle(cellStyle);
				 sheet.addMergedRegion(region2);
				 
				 region2 = new CellRangeAddress(1, 1, (short) 9, (short) 10);
				 cell = row.createCell(9, CellType.STRING);
				 cell.setCellValue("完钻");  
				 cell.setCellStyle(cellStyle);
				 sheet.addMergedRegion(region2);
				 
				 region2 = new CellRangeAddress(1, 1, (short) 11, (short) 12);
				 cell = row.createCell(11, CellType.STRING);
				 cell.setCellValue("交井"); 
				 cell.setCellStyle(cellStyle);
				 sheet.addMergedRegion(region2);
				 cell.setCellStyle(cellStyle);
				 
				 region2 = new CellRangeAddress(1, 1, (short) 13, (short) 18);
				 cell = row.createCell(13, CellType.STRING);
				 cell.setCellValue("钻井动态"); 
				 cell.setCellStyle(cellStyle);
				 sheet.addMergedRegion(region2);
				 cell.setCellStyle(cellStyle);
				 //第三行
				 row=sheet.createRow(2);
				 row.setHeight((short) 500);// 设置行高   
				 cell = row.createCell(7, CellType.STRING);
				 cell.setCellValue("数量");
				 cell.setCellStyle(cellStyle);
				 cell = row.createCell(8, CellType.STRING);
				 cell.setCellValue("井号");
				 cell.setCellStyle(cellStyle);
				 cell = row.createCell(9, CellType.STRING);
				 cell.setCellValue("数量");
				 cell.setCellStyle(cellStyle);
				 cell = row.createCell(10, CellType.STRING);
				 cell.setCellValue("井号");
				 cell.setCellStyle(cellStyle);
				 cell = row.createCell(11, CellType.STRING);
				 cell.setCellValue("数量");
				 cell.setCellStyle(cellStyle);
				 cell = row.createCell(12, CellType.STRING);
				 cell.setCellValue("井号");
				 cell.setCellStyle(cellStyle);
				 cell = row.createCell(13, CellType.STRING);
				 cell.setCellValue("正常生产");
				 cell.setCellStyle(cellStyle);
				 sheet.setColumnWidth(13, 2600); //设置列宽
				 cell = row.createCell(14, CellType.STRING);
				 cell.setCellValue("完井作业");
				 sheet.setColumnWidth(14, 2600); //设置列宽
				 cell.setCellStyle(cellStyle);
				 cell = row.createCell(15, CellType.STRING);
				 cell.setCellValue("复杂");
				 cell.setCellStyle(cellStyle);
				 cell = row.createCell(16, CellType.STRING);
				 cell.setCellValue("搬安");
				 cell.setCellStyle(cellStyle);
				 cell = row.createCell(17, CellType.STRING);
				 cell.setCellValue("待命");
				 cell.setCellStyle(cellStyle);
				 cell = row.createCell(18, CellType.STRING);
				 cell.setCellValue("试油");
				 cell.setCellStyle(cellStyle);
			     //第四行放数据
				 row=sheet.createRow(3);
				 row.setHeight((short) 500);// 设置行高   
				 cell = row.createCell(0, CellType.STRING);
				 cell.setCellValue("合计");
				 cell.setCellStyle(cellStyle);
				 
				 cell = row.createCell(1, CellType.STRING);
				 cell.setCellValue(""+map.get("SumRjc"));  
				 region2 = new CellRangeAddress(3, 3, (short) 1, (short) 2); 
				 sheet.addMergedRegion(region2);
				 cell.setCellStyle(cellStyle);
				 
				 cell = row.createCell(3, CellType.STRING);
				 cell.setCellValue(""+map.get("SumYlj"));
				 region2 = new CellRangeAddress(3, 3, (short) 3, (short) 4); 
				 sheet.addMergedRegion(region2);
				 cell.setCellStyle(cellStyle);
				 
				 cell = row.createCell(5, CellType.STRING);
				 cell.setCellValue(""+map.get("SumNlj"));
				 region2 = new CellRangeAddress(3, 3, (short) 5, (short) 6); 
				 sheet.addMergedRegion(region2);
				 cell.setCellStyle(cellStyle);
				 
				 cell = row.createCell(7, CellType.STRING);
				 cell.setCellValue(""+map.get("kzjsl"));
				 cell.setCellStyle(cellStyle);
				 cell = row.createCell(8, CellType.STRING);
				 cell.setCellValue(""+map.get("kzjjh"));
				 cell.setCellStyle(cellStyle);
				 cell = row.createCell(9, CellType.STRING);
				 cell.setCellValue(""+map.get("wzjsl"));
				 cell.setCellStyle(cellStyle);
				 cell = row.createCell(10, CellType.STRING);
				 cell.setCellValue(""+map.get("wzjjh"));
				 cell.setCellStyle(cellStyle);
				 cell = row.createCell(11, CellType.STRING);
				 cell.setCellValue(""+map.get("jjsl"));
				 cell.setCellStyle(cellStyle);
				 cell = row.createCell(12, CellType.STRING);
				 cell.setCellValue(""+map.get("jjjh"));
				 cell.setCellStyle(cellStyle);
				 //钻井动态值
				 cell = row.createCell(13, CellType.STRING);
				 cell.setCellValue(""+map.get("zcsc"));
				 cell.setCellStyle(cellStyle);
				 cell = row.createCell(14, CellType.STRING);
				 cell.setCellValue(""+map.get("wjzy"));
				 cell.setCellStyle(cellStyle);
				 cell = row.createCell(15, CellType.STRING);
				 cell.setCellValue(""+map.get("fz"));
				 cell.setCellStyle(cellStyle);
				 cell = row.createCell(16, CellType.STRING);
				 cell.setCellValue(""+map.get("banan"));
				 cell.setCellStyle(cellStyle);
				 cell = row.createCell(17, CellType.STRING);
				 cell.setCellValue(""+map.get("daiming"));
				 cell.setCellStyle(cellStyle);
				 cell = row.createCell(18, CellType.STRING);
				 cell.setCellValue(""+map.get("shiyou"));
				 cell.setCellStyle(cellStyle);
				 //第五
				 row = sheet.createRow(4);
				 row.setHeight((short) 400);
				 region2 = new CellRangeAddress(4, 5, (short) 0, (short) 0);
				 cell = row.createCell(0, CellType.STRING);
				 cell.setCellValue("井号"); 
			     cell.setCellStyle(cellStyle);
				 sheet.addMergedRegion(region2);
				 region2 = new CellRangeAddress(4, 5, (short) 1, (short) 1);
				 cell = row.createCell(1, CellType.STRING);  
			     cell.setCellValue("井队"); 
			     cell.setCellStyle(cellStyle);
				 sheet.addMergedRegion(region2);
				 sheet.setColumnWidth(1, 2800); //设置列宽
				 region2 = new CellRangeAddress(4, 5, (short) 2, (short) 2);
				 cell = row.createCell(2, CellType.STRING);  
				 cell.setCellValue("业主单位"); 
				 sheet.setColumnWidth(2, 2800); //设置列宽
				 cell.setCellStyle(cellStyle);
				 sheet.addMergedRegion(region2);
				 
				 region2 = new CellRangeAddress(4, 5, (short) 3, (short) 3);
				 cell = row.createCell(3, CellType.STRING);  
				 cell.setCellValue("井队信息"); 
				 sheet.setColumnWidth(3, 2800); //设置列宽
				 cell.setCellStyle(cellStyle);
				 sheet.addMergedRegion(region2);
				 
				 
				 region2 = new CellRangeAddress(4, 5, (short) 4, (short) 4); 
				 cell = row.createCell(4, CellType.STRING);  
			     cell.setCellValue("井别"); 
			     cell.setCellStyle(cellStyle);
				 sheet.addMergedRegion(region2);
				 region2 = new CellRangeAddress(4, 5, (short) 5, (short) 5);
				 cell = row.createCell(5, CellType.STRING);  
			     cell.setCellValue("开钻日期");
			     cell.setCellStyle(cellStyle);
			     sheet.setColumnWidth(5, 2600); //设置列宽
				 sheet.addMergedRegion(region2);
				 region2 = new CellRangeAddress(4, 4, (short) 6, (short) 8); //周期
				 cell = row.createCell(6, CellType.STRING);  
			     cell.setCellValue("周期"); 
			     cell.setCellStyle(cellStyle);
				 sheet.addMergedRegion(region2);
				 region2 = new CellRangeAddress(4, 4, (short) 9, (short) 15); //井深
				 cell = row.createCell(9, CellType.STRING);  
			     cell.setCellValue("井深"); 
			     cell.setCellStyle(cellStyle);
				 sheet.addMergedRegion(region2);
				 region2 = new CellRangeAddress(4, 4, (short) 16, (short) 19); //钻井参数
				 cell = row.createCell(16, CellType.STRING); 
				 cell.setCellStyle(cellStyle);
			     cell.setCellValue("钻井参数"); 
				 sheet.addMergedRegion(region2);
				 region2 = new CellRangeAddress(4, 4, (short) 20, (short) 22); //泥浆性能
				 cell = row.createCell(20, CellType.STRING);  
				 cell.setCellStyle(cellStyle);
			     cell.setCellValue("泥浆性能"); 
				 sheet.addMergedRegion(region2);
				 region2 = new CellRangeAddress(4, 4, (short) 23, (short) 24); //井身结构
				 cell = row.createCell(23, CellType.STRING); 
				 cell.setCellStyle(cellStyle);
			     cell.setCellValue("井身结构"); 
				 sheet.addMergedRegion(region2);
				 
				 
			     row = sheet.createRow(5);
			     //创建单元格，操作第一行各列
			     row.setHeight((short) 400);// 设置行高   
			        cell = row.createCell(6, CellType.STRING);
			        cell.setCellValue("中标(天)"); 
			        cell.setCellStyle(cellStyle);
			        cell = row.createCell(7, CellType.STRING);
			        cell.setCellValue("实际(天)"); 
			        cell.setCellStyle(cellStyle);
			        cell = row.createCell(8, CellType.STRING);
			        cell.setCellValue("试油(天)"); 
			        cell.setCellStyle(cellStyle);
			        cell = row.createCell(9, CellType.STRING);
			        cell.setCellValue("设计井深");
			        sheet.setColumnWidth(9, 2600); //设置列宽
			        cell.setCellStyle(cellStyle);
			        cell = row.createCell(10, CellType.STRING);
			        cell.setCellValue("实际井深");
			        sheet.setColumnWidth(10, 2600); //设置列宽
			        cell.setCellStyle(cellStyle);
			        cell = row.createCell(11, CellType.STRING);
			        cell.setCellValue("层位"); 
			        cell.setCellStyle(cellStyle);
			        cell = row.createCell(12, CellType.STRING);
			        cell.setCellValue("钻头尺寸");
			        sheet.setColumnWidth(12, 2600); //设置列宽
			        cell.setCellStyle(cellStyle);
			        cell = row.createCell(13, CellType.STRING);
			        cell.setCellValue("日进尺"); 
			        cell.setCellStyle(cellStyle);
			        cell = row.createCell(14, CellType.STRING);
			        cell.setCellValue("月进尺"); 
			        cell.setCellStyle(cellStyle);
			        cell = row.createCell(15, CellType.STRING);
			        cell.setCellValue("年累进尺");
			        sheet.setColumnWidth(15, 2600); //设置列宽
			        cell.setCellStyle(cellStyle);
			        cell = row.createCell(16, CellType.STRING);
			        cell.setCellValue("钻压"); 
			        cell.setCellStyle(cellStyle);
			        cell = row.createCell(17, CellType.STRING);
			        cell.setCellValue("转速"); 
			        cell.setCellStyle(cellStyle);
			        cell = row.createCell(18, CellType.STRING);
			        cell.setCellValue("排量"); 
			        cell.setCellStyle(cellStyle);
			        cell = row.createCell(19, CellType.STRING);
			        cell.setCellValue("泵压"); 
			        cell.setCellStyle(cellStyle);
			        cell = row.createCell(20, CellType.STRING);
			        cell.setCellValue("密度"); 
			        cell.setCellStyle(cellStyle);
			        cell = row.createCell(21, CellType.STRING);
			        cell.setCellValue("粘度"); 
			        cell.setCellStyle(cellStyle);
			        cell = row.createCell(22, CellType.STRING);
			        cell.setCellValue("失水"); 
			        cell.setCellStyle(cellStyle);
			        cell = row.createCell(23, CellType.STRING);
			        cell.setCellValue("工况"); 
			        cell.setCellStyle(cellStyle);
			        cell = row.createCell(24, CellType.STRING);
			        cell.setCellValue("钻具组合"); 
			        sheet.setColumnWidth(24, 2600); //设置列宽
			        cell.setCellStyle(cellStyle);
			        for (int i = 0; i < wellDailyList.size(); i++) {
			        	row=sheet.createRow(i+6);
						//通过井编号查询井号
						WellInfo wellInfo=wellInfoSerivce.selectWellInfoById(wellDailyList.get(i).getWellid());
				        cell = row.createCell(0, CellType.STRING);
				        cell.setCellValue(wellInfo.getWellcode()); 
				        cell = row.createCell(1, CellType.STRING);
				        if(null!=wellDailyList.get(i).getDid()){
				        	cell.setCellValue(getDeptName(wellDailyList.get(i).getDid())); 
				        }else{
				        	cell.setCellValue("");
				        }
				        cell = row.createCell(2, CellType.STRING);//业主单位
				        if(null!=wellInfo.getYzdw()){
				        	cell.setCellValue(wellInfo.getYzdw()); 
				        }else{
				        	cell.setCellValue("");
				        }
						cell = row.createCell(3, CellType.STRING);//井队信息
				        if(null!=wellInfo.getPtjl()){
				        	cell.setCellValue(wellInfo.getPtjl()); 
				        }else{
				        	cell.setCellValue("");
				        }
				        cell = row.createCell(4, CellType.STRING);
				        cell.setCellValue(wellDailyList.get(i).getJb()); 
				        cell = row.createCell(5, CellType.STRING);
				        if(null!=DateUtil.DateToString(wellInfo.getFirstkzsj(),DateUtil.YYYY_MM_DD)){
				        	cell.setCellValue(DateUtil.DateToString(wellInfo.getFirstkzsj(),DateUtil.YYYY_MM_DD));
				        }else{
				        	cell.setCellValue("");
				        }
				        //中标
				        cell = row.createCell(6, CellType.STRING);
				        if(null!=wellInfo.getHtzq()){
				        	cell.setCellValue(wellInfo.getHtzq()); 
				        }else{
				        	cell.setCellValue(""); 
				        }
				        cell = row.createCell(7, CellType.STRING);
				        if(wellDailyList.get(i).getLogdate()!=null&&wellInfo.getFirstkzsj()!=null){
				        	cell.setCellValue((int)DateUtil.getDaySub(wellInfo.getFirstkzsj(),wellDailyList.get(i).getLogdate())); 
				        }else{
				        	cell.setCellValue("");
				        }
				        cell = row.createCell(8, CellType.STRING);
				        if(wellDailyList.get(i).getLogdate()!=null&&wellInfo.getWjrq()!=null){
				        	cell.setCellValue((int)DateUtil.getDaySub(wellInfo.getWjrq(),wellDailyList.get(i).getLogdate())); 
				        }else{
				        	cell.setCellValue(""); 
				        }
				        
				        cell = row.createCell(9, CellType.STRING);
				        if(null!=wellInfo.getSjjs()){
				        	 cell.setCellValue(wellInfo.getSjjs()); 
				        }else{
				        	 cell.setCellValue(""); 
				        }
				        cell = row.createCell(10, CellType.STRING);
				        if(null!=wellDailyList.get(i).getTdepth()){
				        	cell.setCellValue(wellDailyList.get(i).getTdepth()); 
				        }else{
				        	cell.setCellValue("");
				        }
				        cell = row.createCell(11, CellType.STRING);
				        cell.setCellValue(wellDailyList.get(i).getJdcw()); 
				        cell = row.createCell(12, CellType.STRING);
				        cell.setCellValue(wellDailyList.get(i).getZtcc()); 
				        cell = row.createCell(13, CellType.STRING);
				        cell.setCellValue(wellDailyList.get(i).getRjc()); 
				        cell = row.createCell(14, CellType.STRING);
				        cell.setCellValue(wellDailyList.get(i).getYjc()); 
				        cell = row.createCell(15, CellType.STRING);
				        cell.setCellValue(wellDailyList.get(i).getNljjc()); 
				        
				        cell = row.createCell(16, CellType.STRING);
				        cell.setCellValue(wellDailyList.get(i).getZy()); 
				        cell = row.createCell(17, CellType.STRING);
				        cell.setCellValue(wellDailyList.get(i).getZs()); 
				        cell = row.createCell(18, CellType.STRING);
				        cell.setCellValue(wellDailyList.get(i).getPl()); 
				        cell = row.createCell(19, CellType.STRING);
				        cell.setCellValue(wellDailyList.get(i).getPy()); 
				        cell = row.createCell(20, CellType.STRING);
				        cell.setCellValue(wellDailyList.get(i).getNjmd()); 
				        cell = row.createCell(21, CellType.STRING);
				        cell.setCellValue(wellDailyList.get(i).getLdnd()); 
				        cell = row.createCell(22, CellType.STRING);
				        cell.setCellValue(wellDailyList.get(i).getSs()); 
				        cell = row.createCell(23, CellType.STRING);
				        cell.setCellValue(wellDailyList.get(i).getGk()); 
				        cell = row.createCell(24, CellType.STRING);
				        cell.setCellValue(wellDailyList.get(i).getZjzh()); 
			        }
			}
			//TODO 动力性能
			Message message=wellPowerService.listWellPowerNoPage(date, date, null);
			if(null!=message.getObj()){
				HSSFSheet sheet2 = workBook.createSheet("动力性能"+date.replace("-", ""));
				List<WellPower> list=(List<WellPower>)message.getObj();
				HSSFRow row1 = sheet2.createRow(0);
			    HSSFCell cell = row1.createCell(0, CellType.STRING);  
			    cell.setCellValue("动力性能"+date.replace("-", ""));
			    cell.setCellStyle(cellStyle);
			    row1.setHeight((short) 700);// 设置行高   
			    //合并
			    CellRangeAddress region1 = new CellRangeAddress(0, 0, (short) 0, (short) 26); 
			    sheet2.addMergedRegion(region1);
			    //第二行
			    row1=sheet2.createRow(1);
			    cell = row1.createCell(0, CellType.STRING);
				cell.setCellValue("序号");
				cell.setCellStyle(cellStyle);
				cell = row1.createCell(1, CellType.STRING);
				cell.setCellValue("井号");
				cell.setCellStyle(cellStyle);
				cell = row1.createCell(2, CellType.STRING);
				cell.setCellValue("日期");
				sheet2.setColumnWidth(2, 2750); //设置列宽
				cell.setCellStyle(cellStyle);
				cell = row1.createCell(3, CellType.STRING);
				cell.setCellValue("井队");
				cell.setCellStyle(cellStyle);
				sheet2.setColumnWidth(3, 2800); //设置列宽
				cell = row1.createCell(4, CellType.STRING);
				cell.setCellValue("动力源");
				cell.setCellStyle(cellStyle);
				cell = row1.createCell(5, CellType.STRING);
				cell.setCellValue("主要工作");
				sheet2.setColumnWidth(5, 2800); //设置列宽
				cell.setCellStyle(cellStyle);
				cell = row1.createCell(6, CellType.STRING);
				cell.setCellValue("目前进度");
				sheet2.setColumnWidth(6, 2800); //设置列宽
				cell.setCellStyle(cellStyle);
				cell = row1.createCell(7, CellType.STRING);
				cell.setCellValue("钻头外径");
				sheet2.setColumnWidth(7, 2800); //设置列宽
				cell.setCellStyle(cellStyle);
				cell = row1.createCell(8, CellType.STRING);
				cell.setCellValue("泵压");
				cell.setCellStyle(cellStyle);
				cell = row1.createCell(9, CellType.STRING);
				cell.setCellValue("排量");
				cell.setCellStyle(cellStyle);
				cell = row1.createCell(10, CellType.STRING);
				cell.setCellValue("泵冲");
				cell.setCellStyle(cellStyle);
				cell = row1.createCell(11, CellType.STRING);
				cell.setCellValue("缸径");
				cell.setCellStyle(cellStyle);
				cell = row1.createCell(12, CellType.STRING);
				cell.setCellValue("悬重");
				cell.setCellStyle(cellStyle);
				cell = row1.createCell(13, CellType.STRING);
				cell.setCellValue("绞车档位");
				sheet2.setColumnWidth(13, 2800); //设置列宽
				cell.setCellStyle(cellStyle);
				cell = row1.createCell(14, CellType.STRING);
				cell.setCellValue("绞车电机运行台数");
				sheet2.setColumnWidth(14, 2800); //设置列宽
				cell.setCellStyle(cellStyle);
				cell = row1.createCell(15, CellType.STRING);
				cell.setCellValue("转速");
				cell.setCellStyle(cellStyle);
				cell = row1.createCell(16, CellType.STRING);
				cell.setCellValue("转盘档位");
				sheet2.setColumnWidth(16, 2800); //设置列宽
				cell.setCellStyle(cellStyle);
				cell = row1.createCell(17, CellType.STRING);
				cell.setCellValue("柴油机/网电电机转速");
				sheet2.setColumnWidth(17, 2800); //设置列宽
				cell.setCellStyle(cellStyle);
				cell = row1.createCell(18, CellType.STRING);
				cell.setCellValue("柴油机运行台数");
				sheet2.setColumnWidth(18, 2800); //设置列宽
				cell.setCellStyle(cellStyle);
				cell = row1.createCell(19, CellType.STRING);
				cell.setCellValue("发电机运行台数");
				sheet2.setColumnWidth(19, 2800); //设置列宽
				cell.setCellStyle(cellStyle);
				cell = row1.createCell(20, CellType.STRING);
				cell.setCellValue("柴油消耗");
				sheet2.setColumnWidth(20, 2800); //设置列宽
				cell.setCellStyle(cellStyle);
				cell = row1.createCell(21, CellType.STRING);
				cell.setCellValue("月累计");
				cell.setCellStyle(cellStyle);
				cell = row1.createCell(22, CellType.STRING);
				cell.setCellValue("井累计");
				cell.setCellStyle(cellStyle);
				cell = row1.createCell(23, CellType.STRING);
				cell.setCellValue("日用电量");
				sheet2.setColumnWidth(23, 2800); //设置列宽
				cell.setCellStyle(cellStyle);
				cell = row1.createCell(24, CellType.STRING);
				cell.setCellValue("月累计");
				cell.setCellStyle(cellStyle);
				cell = row1.createCell(25, CellType.STRING);
				cell.setCellValue("井累计");
				cell.setCellStyle(cellStyle);
				cell = row1.createCell(26, CellType.STRING);
				cell.setCellValue("消耗情况");
				sheet2.setColumnWidth(26, 2800); //设置列宽
				cell.setCellStyle(cellStyle);
				for(int i=0;i<list.size();i++){
					row1=sheet2.createRow(i+2);
					cell = row1.createCell(0, CellType.STRING);
					cell.setCellValue(i+1); 
					//通过井编号查询井号
					WellInfo wellInfo=wellInfoSerivce.selectWellInfoById(list.get(i).getWellid());
			        cell = row1.createCell(1, CellType.STRING);
			        cell.setCellValue(wellInfo.getWellcode()); 
			        cell = row1.createCell(2, CellType.STRING);
			        if(null!=DateUtil.DateToString(list.get(i).getLogdate(),DateUtil.YYYY_MM_DD)){
			        	cell.setCellValue(DateUtil.DateToString(list.get(i).getLogdate(),DateUtil.YYYY_MM_DD));
			        }else{
			        	cell.setCellValue("");
			        }
					cell = row1.createCell(3, CellType.STRING);
					if(null!=list.get(i).getDid()){
			        	cell.setCellValue(getDeptName(list.get(i).getDid())); 
			        }else{
			        	cell.setCellValue(""); 
			        }
					cell = row1.createCell(4, CellType.STRING);
					cell.setCellValue(list.get(i).getDly());
					
					cell = row1.createCell(5, CellType.STRING);
					cell.setCellValue(list.get(i).getZygz());
					
					cell = row1.createCell(6, CellType.STRING);
					cell.setCellValue(list.get(i).getMqjd());
					
					cell = row1.createCell(7, CellType.STRING);
					cell.setCellValue(list.get(i).getZtwj());
					
					cell = row1.createCell(8, CellType.STRING);
					cell.setCellValue(list.get(i).getPy());
					
					cell = row1.createCell(9, CellType.STRING);
					cell.setCellValue(list.get(i).getPl());
					
					cell = row1.createCell(10, CellType.STRING);
					cell.setCellValue(list.get(i).getBc());
					
					cell = row1.createCell(11, CellType.STRING);
					cell.setCellValue(list.get(i).getGj());
					
					cell = row1.createCell(12, CellType.STRING);
					cell.setCellValue(list.get(i).getXz());
					
					cell = row1.createCell(13, CellType.STRING);
					cell.setCellValue(list.get(i).getJcdw());
					
					
					cell = row1.createCell(14, CellType.STRING);
					cell.setCellValue(list.get(i).getJcddjyxts());
					
					
					cell = row1.createCell(15, CellType.STRING);
					cell.setCellValue(list.get(i).getZs());
					
					cell = row1.createCell(16, CellType.STRING);
					cell.setCellValue(list.get(i).getZpdw());
					
					
					cell = row1.createCell(17, CellType.STRING);
					cell.setCellValue(list.get(i).getDjzs());
					
					cell = row1.createCell(18, CellType.STRING);
					cell.setCellValue(list.get(i).getCyjyxts());
					
					
					cell = row1.createCell(19, CellType.STRING);
					cell.setCellValue(list.get(i).getFdjyxts());
					
					cell = row1.createCell(20, CellType.STRING);
					if(null!=list.get(i).getCyxh()){
						cell.setCellValue(list.get(i).getCyxh());
					}else{
						cell.setCellValue("");
					}
					
					cell = row1.createCell(21, CellType.STRING);
					if(null!=list.get(i).getCylj()){
						cell.setCellValue(list.get(i).getCylj());
					}else{
						cell.setCellValue("");
					}
					
					cell = row1.createCell(22, CellType.STRING);
					if(null!=list.get(i).getCjlj()){
						cell.setCellValue(list.get(i).getCjlj());
					}else{
						cell.setCellValue("");
					}
					
					cell = row1.createCell(23, CellType.STRING);
					if(null!=list.get(i).getRydl()){
						cell.setCellValue(list.get(i).getRydl());
					}else{
						cell.setCellValue("");
					}
					
					cell = row1.createCell(24, CellType.STRING);
					if(null!=list.get(i).getRylj()){
						cell.setCellValue(list.get(i).getRylj());
					}else{
						cell.setCellValue("");
					}
					
					cell = row1.createCell(25, CellType.STRING);
					if(null!=list.get(i).getRjlj()){
						cell.setCellValue(list.get(i).getRjlj());
					}else{
						cell.setCellValue("");
					}
					
					cell = row1.createCell(26, CellType.STRING);
					cell.setCellValue(list.get(i).getXhqk());
					
				}
			}
			//TODO 钻井时效
			message=wellTimeService.listWellTimeNoPage(date, date, null);
			if(null!=message.getObj()){
				HSSFSheet sheet3 = workBook.createSheet("钻井时效"+date.replace("-", ""));
				List<WellTime> list=(List<WellTime>)message.getObj();
				HSSFRow row = sheet3.createRow(0);
			    HSSFCell cell = row.createCell(0, CellType.STRING);  
			    cell.setCellValue(name+"(单位:小时)");
			    cell.setCellStyle(cellStyle);
			    row.setHeight((short) 700);// 设置行高   
			    //合并
			    CellRangeAddress region1 = new CellRangeAddress(0, 0, (short) 0, (short) 31); 
			    sheet3.addMergedRegion(region1);
			    //第二行
			    row=sheet3.createRow(1);
			    cell = row.createCell(0, CellType.STRING);
				cell.setCellValue("序号");
				cell.setCellStyle(cellStyle);
				cell = row.createCell(1, CellType.STRING);
				cell.setCellValue("井号");
				cell.setCellStyle(cellStyle);
				cell = row.createCell(2, CellType.STRING);
				cell.setCellValue("日期");
				sheet3.setColumnWidth(2, 2750); //设置列宽
				cell.setCellStyle(cellStyle);
				cell = row.createCell(3, CellType.STRING);
				cell.setCellValue("纯钻时间");
				sheet3.setColumnWidth(3, 1800); //设置列宽
				cell.setCellStyle(cellStyle);
				cell = row.createCell(4, CellType.STRING);
				cell.setCellValue("起下钻时间");
				sheet3.setColumnWidth(4, 1800); //设置列宽
				cell.setCellStyle(cellStyle);
				cell = row.createCell(5, CellType.STRING);
				cell.setCellValue("接单根");
				sheet3.setColumnWidth(5, 1800); //设置列宽
				cell.setCellStyle(cellStyle);
				cell = row.createCell(6, CellType.STRING);
				cell.setCellValue("扩划眼	");
				sheet3.setColumnWidth(6, 1800); //设置列宽
				cell.setCellStyle(cellStyle);
				cell = row.createCell(7, CellType.STRING);
				cell.setCellValue("换钻头");
				sheet3.setColumnWidth(7, 1800); //设置列宽
				cell.setCellStyle(cellStyle);
				cell = row.createCell(8, CellType.STRING);
				cell.setCellValue("循环");
				sheet3.setColumnWidth(8, 1800); //设置列宽
				cell.setCellStyle(cellStyle);
				cell = row.createCell(9, CellType.STRING);
				cell.setCellValue("候凝");
				sheet3.setColumnWidth(9, 1800); //设置列宽
				cell.setCellStyle(cellStyle);
				cell = row.createCell(10, CellType.STRING);
				cell.setCellValue("测井");
				sheet3.setColumnWidth(10, 1800); //设置列宽
				cell.setCellStyle(cellStyle);
				cell = row.createCell(11, CellType.STRING);
				cell.setCellValue("甩钻具");
				sheet3.setColumnWidth(11, 1800); //设置列宽
				cell.setCellStyle(cellStyle);
				cell = row.createCell(12, CellType.STRING);
				cell.setCellValue("下套管");
				sheet3.setColumnWidth(12, 1800); //设置列宽
				cell.setCellStyle(cellStyle);
				cell = row.createCell(13, CellType.STRING);
				cell.setCellValue("固井");
				sheet3.setColumnWidth(13, 1800); //设置列宽
				cell.setCellStyle(cellStyle);
				cell = row.createCell(14, CellType.STRING);
				cell.setCellValue("试压");
				sheet3.setColumnWidth(14, 1800); //设置列宽
				cell.setCellStyle(cellStyle);
				cell = row.createCell(15, CellType.STRING);
				cell.setCellValue("辅助工作");
				sheet3.setColumnWidth(15, 1800); //设置列宽
				cell.setCellStyle(cellStyle);
				cell = row.createCell(16, CellType.STRING);
				cell.setCellValue("生产时间");
				sheet3.setColumnWidth(16, 1800); //设置列宽
				cell.setCellStyle(cellStyle);
				cell = row.createCell(17, CellType.STRING);
				cell.setCellValue("事故");
				sheet3.setColumnWidth(17, 1800); //设置列宽
				cell.setCellStyle(cellStyle);
				cell = row.createCell(18, CellType.STRING);
				cell.setCellValue("组停");
				sheet3.setColumnWidth(18, 1800); //设置列宽
				cell.setCellStyle(cellStyle);
				cell = row.createCell(19, CellType.STRING);
				cell.setCellValue("修理");
				sheet3.setColumnWidth(19, 1800); //设置列宽
				cell.setCellStyle(cellStyle);
				cell = row.createCell(20, CellType.STRING);
				cell.setCellValue("自然灾害");
				sheet3.setColumnWidth(20, 1800); //设置列宽
				cell.setCellStyle(cellStyle);
				cell = row.createCell(21, CellType.STRING);
				cell.setCellValue("复杂");
				sheet3.setColumnWidth(21, 1800); //设置列宽
				cell.setCellStyle(cellStyle);
				cell = row.createCell(22, CellType.STRING);
				cell.setCellValue("其他");
				sheet3.setColumnWidth(22, 1300); //设置列宽
				cell.setCellStyle(cellStyle);
				cell = row.createCell(23, CellType.STRING);
				cell.setCellValue("非生产时间");
				sheet3.setColumnWidth(23, 1800); //设置列宽
				cell.setCellStyle(cellStyle);
				cell = row.createCell(24, CellType.STRING);
				cell.setCellValue("中途测试");
				sheet3.setColumnWidth(24, 1800); //设置列宽
				cell.setCellStyle(cellStyle);
				cell = row.createCell(25, CellType.STRING);
				cell.setCellValue("VSP测井");
				sheet3.setColumnWidth(25, 1800); //设置列宽
				cell.setCellStyle(cellStyle);
				cell = row.createCell(26, CellType.STRING);
				cell.setCellValue("完井测试");
				sheet3.setColumnWidth(26, 1800); //设置列宽
				cell.setCellStyle(cellStyle);
				cell = row.createCell(27, CellType.STRING);
				cell.setCellValue("搬迁安装");
				sheet3.setColumnWidth(27, 1800); //设置列宽
				cell.setCellStyle(cellStyle);
				cell = row.createCell(28, CellType.STRING);
				cell.setCellValue("井眼准备");
				sheet3.setColumnWidth(28, 1800); //设置列宽
				cell.setCellStyle(cellStyle);
				cell = row.createCell(29, CellType.STRING);
				cell.setCellValue("拆甩设备");
				sheet3.setColumnWidth(29, 1800); //设置列宽
				cell.setCellStyle(cellStyle);
				cell = row.createCell(30, CellType.STRING);
				cell.setCellValue("非钻井时间");
				sheet3.setColumnWidth(30, 1800); //设置列宽
				cell.setCellStyle(cellStyle);
				cell = row.createCell(31, CellType.STRING);
				cell.setCellValue("合计");
				sheet3.setColumnWidth(31, 1800); //设置列宽
				cell.setCellStyle(cellStyle);
				for(int i=0;i<list.size();i++){
					row=sheet3.createRow(i+2);
					cell = row.createCell(0, CellType.STRING);
					cell.setCellValue(i+1); 
					//通过井编号查询井号
					WellInfo wellInfo=wellInfoSerivce.selectWellInfoById(list.get(i).getWellid());
			        cell = row.createCell(1, CellType.STRING);
			        cell.setCellValue(wellInfo.getWellcode()); 
			        cell = row.createCell(2, CellType.STRING);
			        if(null!=DateUtil.DateToString(list.get(i).getLogdate(),DateUtil.YYYY_MM_DD)){
			        	cell.setCellValue(DateUtil.DateToString(list.get(i).getLogdate(),DateUtil.YYYY_MM_DD));
			        }else{
			        	cell.setCellValue("");
			        }
			        cell = row.createCell(3, CellType.STRING);
			        if(null!=list.get(i).getCzsj()){
			        	cell.setCellValue(""+list.get(i).getCzsj());
			        }else{
			        	cell.setCellValue("");
			        }
					
					cell = row.createCell(4, CellType.STRING);
					if(null!=list.get(i).getQxzsj()){
			        	cell.setCellValue(""+list.get(i).getQxzsj());
			        }else{
			        	cell.setCellValue("");
			        }
					
					cell = row.createCell(5, CellType.STRING);
					if(null!=list.get(i).getJdgsj()){
			        	cell.setCellValue(""+list.get(i).getJdgsj());
			        }else{
			        	cell.setCellValue("");
			        }
					
					cell = row.createCell(6, CellType.STRING);
					if(null!=list.get(i).getKhy()){
			        	cell.setCellValue(""+list.get(i).getKhy());
			        }else{
			        	cell.setCellValue("");
			        }
					
					cell = row.createCell(7, CellType.STRING);
					if(null!=list.get(i).getHzt()){
						cell.setCellValue(""+list.get(i).getHzt());
					}else{
						cell.setCellValue("");
					}
					
					cell = row.createCell(8, CellType.STRING);
					if(null!=list.get(i).getXh()){
						cell.setCellValue(""+list.get(i).getXh());
					}else{
						cell.setCellValue("");
					}
					
					cell = row.createCell(9, CellType.STRING);
					if(null!=list.get(i).getHn()){
						cell.setCellValue(""+list.get(i).getHn());
					}else{
						cell.setCellValue("");
					}
				
					cell = row.createCell(10, CellType.STRING);
					if(null!=list.get(i).getCj()){
						cell.setCellValue(""+list.get(i).getCj());
					}else{
						cell.setCellValue("");
					}
					
					cell = row.createCell(11, CellType.STRING);
					if(null!=list.get(i).getSzj()){
						cell.setCellValue(""+list.get(i).getSzj());
					}else{
						cell.setCellValue("");
					}
					
					cell = row.createCell(12, CellType.STRING);
					if(null!=list.get(i).getXtg()){
						cell.setCellValue(""+list.get(i).getXtg());
					}else{
						cell.setCellValue("");
					}
					
					cell = row.createCell(13, CellType.STRING);
					if(null!=list.get(i).getGj()){
						cell.setCellValue(""+list.get(i).getGj());
					}else{
						cell.setCellValue("");
					}
					
					cell = row.createCell(14, CellType.STRING);
					if(null!=list.get(i).getSy()){
						cell.setCellValue(""+list.get(i).getSy());
					}else{
						cell.setCellValue("");
					}
					
					cell = row.createCell(15, CellType.STRING);
					if(null!=list.get(i).getFzgz()){
						cell.setCellValue(""+list.get(i).getFzgz());
					}else{
						cell.setCellValue("");
					}
					
					cell = row.createCell(16, CellType.STRING);
					if(null!=list.get(i).getScsj()){
						cell.setCellValue(""+list.get(i).getScsj());
					}else{
						cell.setCellValue("");
					}
					
					cell = row.createCell(17, CellType.STRING);
					if(null!=list.get(i).getSg()){
						cell.setCellValue(""+list.get(i).getSg());
					}else{
						cell.setCellValue("");
					}
					
					cell = row.createCell(18, CellType.STRING);
					if(null!=list.get(i).getZt()){
						cell.setCellValue(""+list.get(i).getZt());
					}else{
						cell.setCellValue("");
					}
					
					cell = row.createCell(19, CellType.STRING);
					if(null!=list.get(i).getXl()){
						cell.setCellValue(""+list.get(i).getXl());
					}else{
						cell.setCellValue("");
					}
					
					cell = row.createCell(20, CellType.STRING);
					if(null!=list.get(i).getZyzh()){
						cell.setCellValue(""+list.get(i).getZyzh());
					}else{
						cell.setCellValue("");
					}
					
					cell = row.createCell(21, CellType.STRING);
					if(null!=list.get(i).getFz()){
						cell.setCellValue(""+list.get(i).getFz());
					}else{
						cell.setCellValue("");
					}
					
					cell = row.createCell(22, CellType.STRING);
					if(null!=list.get(i).getQt()){
						cell.setCellValue(""+list.get(i).getQt());
					}else{
						cell.setCellValue("");
					}
					
					cell = row.createCell(23, CellType.STRING);
					if(null!=list.get(i).getFscsj()){
						cell.setCellValue(""+list.get(i).getFscsj());
					}else{
						cell.setCellValue("");
					}
					
					cell = row.createCell(24, CellType.STRING);
					if(null!=list.get(i).getZtcs()){
						cell.setCellValue(""+list.get(i).getZtcs());
					}else{
						cell.setCellValue("");
					}
					cell = row.createCell(25, CellType.STRING);
					if(null!=list.get(i).getVspcj()){
						cell.setCellValue(""+list.get(i).getVspcj());
					}else{
						cell.setCellValue("");
					}
					
					cell = row.createCell(26, CellType.STRING);
					if(null!=list.get(i).getVscs()){
						cell.setCellValue(""+list.get(i).getVscs());
					}else{
						cell.setCellValue("");
					}
					
					cell = row.createCell(27, CellType.STRING);
					if(null!=list.get(i).getBqaz()){
						cell.setCellValue(""+list.get(i).getBqaz());
					}else{
						cell.setCellValue("");
					}
					
					cell = row.createCell(28, CellType.STRING);
					if(null!=list.get(i).getJyzb()){
						cell.setCellValue(""+list.get(i).getJyzb());
					}else{
						cell.setCellValue("");
					}
					
					cell = row.createCell(29, CellType.STRING);
					if(null!=list.get(i).getCxsb()){
						cell.setCellValue(""+list.get(i).getCxsb());
					}else{
						cell.setCellValue("");
					}
					
					cell = row.createCell(30, CellType.STRING);
					if(null!=list.get(i).getFzjsj()){
						cell.setCellValue(""+list.get(i).getFzjsj());
					}else{
						cell.setCellValue("");
					}
					
					cell = row.createCell(31, CellType.STRING);
					if(null!=list.get(i).getHj()){
						cell.setCellValue(""+list.get(i).getHj());
					}else{
						cell.setCellValue("");
					}
				}
			}
			
	}
		//TODO 泥浆消耗
		if("mudssxh".equals(type)){
			if(null!=obj){
				List<SsxhListDto> list=(List<SsxhListDto>)obj;
				HSSFRow row = sheet.createRow(0);
			    HSSFCell cell = row.createCell(0, CellType.STRING);  
			    cell.setCellValue(name);
			    cell.setCellStyle(cellStyle);
			    row.setHeight((short) 700);// 设置行高   
			    //合并
			    CellRangeAddress region1 = new CellRangeAddress(0, 0, (short) 0, (short) 6); 
			    sheet.addMergedRegion(region1);
			    //第二行
			    row=sheet.createRow(1);
			    cell = row.createCell(0, CellType.STRING);
				cell.setCellValue("名称/代号");
				sheet.setColumnWidth(0, 5000); //设置列宽
				cell.setCellStyle(cellStyle);
				cell = row.createCell(1, CellType.STRING);
				cell.setCellValue("单价 元/吨");
				sheet.setColumnWidth(1, 3000); //设置列宽
				cell.setCellStyle(cellStyle);
				cell = row.createCell(2, CellType.STRING);
				cell.setCellValue("日进料 t");
				sheet.setColumnWidth(2, 3000); //设置列宽
				cell.setCellStyle(cellStyle);
				cell = row.createCell(3, CellType.STRING);
				cell.setCellValue("总进料 t");
				cell.setCellStyle(cellStyle);
				sheet.setColumnWidth(3, 3000); //设置列宽
				cell = row.createCell(4, CellType.STRING);
				cell.setCellValue("日消耗 t");
				cell.setCellStyle(cellStyle);
				sheet.setColumnWidth(4, 3000); //设置列宽
				cell = row.createCell(5, CellType.STRING);
				cell.setCellValue("总消耗 t");
				sheet.setColumnWidth(5, 3000); //设置列宽
				cell.setCellStyle(cellStyle);
				cell = row.createCell(6, CellType.STRING);
				cell.setCellValue("库存 t");
				sheet.setColumnWidth(6, 2800); //设置列宽
				cell.setCellStyle(cellStyle);
				double mtotal=0.00;
				double ntotal=0.00;
				for(int i=0;i<list.size();i++){
					row=sheet.createRow(i+2);
					cell = row.createCell(0, CellType.STRING);
					cell.setCellValue(list.get(i).getName()); 
			        cell = row.createCell(1, CellType.STRING);
			        cell.setCellValue(0); 
			        if(StringUtils.isNotBlank(list.get(i).getPrice())){
			        	cell.setCellValue(list.get(i).getPrice()); 
			        }
					cell = row.createCell(2, CellType.STRING);
					cell.setCellValue(0); 
				    if(StringUtils.isNotBlank(list.get(i).getDiurnalfeed())){
				    	cell.setCellValue(list.get(i).getDiurnalfeed());
				    }
					cell = row.createCell(3, CellType.STRING);
					cell.setCellValue(0); 
					if(StringUtils.isNotBlank(list.get(i).getTotalfeed())){
				    	cell.setCellValue(list.get(i).getTotalfeed());
				    }
					cell = row.createCell(4, CellType.STRING);
					cell.setCellValue(0); 
					if(StringUtils.isNotBlank(list.get(i).getDiurnalconsumption())){
						cell.setCellValue(list.get(i).getDiurnalconsumption());
						mtotal=mtotal+Double.parseDouble(list.get(i).getDiurnalconsumption())*Double.parseDouble(list.get(i).getPrice());
					}
					cell = row.createCell(5, CellType.STRING);
					cell.setCellValue(0); 
					if(StringUtils.isNotBlank(list.get(i).getTotalconsumption())){
						cell.setCellValue(list.get(i).getTotalconsumption());
						ntotal=ntotal+Double.parseDouble(list.get(i).getTotalconsumption())*Double.parseDouble(list.get(i).getPrice());
					}
					cell = row.createCell(6, CellType.STRING);
					cell.setCellValue(0); 
					if(StringUtils.isNotBlank(list.get(i).getStock())){
						cell.setCellValue(list.get(i).getStock());
					}
				}
				//统计
				row=sheet.createRow(list.size()+2);
				cell = row.createCell(0, CellType.STRING);
				cell.setCellValue("钻井液当日费用 (元)"); 
				region1 = new CellRangeAddress(list.size()+2, list.size()+2, (short) 1, (short) 2); 
				sheet.addMergedRegion(region1);
				cell = row.createCell(1, CellType.STRING);
				cell.setCellValue(mtotal); 
				cellStyle.setAlignment(HorizontalAlignment.CENTER);// 居中  
				cell.setCellStyle(cellStyle);
				region1 = new CellRangeAddress(list.size()+2, list.size()+2, (short) 3, (short) 4); 
			    sheet.addMergedRegion(region1);
			    cell = row.createCell(3, CellType.STRING);
				cell.setCellValue("钻井液总费用 (元)"); 
				region1 = new CellRangeAddress(list.size()+2,list.size()+2, (short) 5, (short) 6); 
				sheet.addMergedRegion(region1);
				cell = row.createCell(5, CellType.STRING);
				cell.setCellValue(ntotal); 
				cellStyle.setAlignment(HorizontalAlignment.CENTER);// 居中  
				cell.setCellStyle(cellStyle);
			}
		}
		if("dxlog".equals(type)){
			if(null!=obj){
				List<WellDxDaily> list=(List<WellDxDaily>) obj;
		        //创建工作表 
		        //创建行,第一行  
		        HSSFRow row1 = sheet.createRow(0);
		        HSSFCell cell = row1.createCell(0, CellType.STRING);  
		        cell.setCellValue(name);
		        cell.setCellStyle(cellStyle);
		        row1.setHeight((short) 500);// 设置行高
		        //合并
		        CellRangeAddress region1 = new CellRangeAddress(0, 0, (short) 0, (short) 21); 
		        sheet.addMergedRegion(region1);
		        row1 = sheet.createRow(1); 
		        //创建单元格，操作第一行各列
		        cell = row1.createCell(0, CellType.STRING);  
		        cell.setCellValue("编号"); 
		        cell.setCellStyle(cellStyle);
		        cell = row1.createCell(1, CellType.STRING);
		        cell.setCellValue("井号"); 
		        cell.setCellStyle(cellStyle);
		        cell = row1.createCell(2, CellType.STRING);
		        cell.setCellValue("日期"); 
		        cell.setCellStyle(cellStyle);
		        sheet.setColumnWidth(2, 2750); //设置列宽
		        cell = row1.createCell(3, CellType.STRING);
		        cell.setCellValue("井队"); 
		        sheet.setColumnWidth(3, 2800); //设置列宽
		        cell.setCellStyle(cellStyle);
		        cell = row1.createCell(4, CellType.STRING);
		        cell.setCellValue("井型"); 
		        cell.setCellStyle(cellStyle);
		        cell = row1.createCell(5, CellType.STRING);
		        cell.setCellValue("设计井深(m)"); 
		        cell.setCellStyle(cellStyle);
		        cell = row1.createCell(6, CellType.STRING);
		        cell.setCellValue("实际井深(m)"); 
		        cell.setCellStyle(cellStyle);
		        cell = row1.createCell(7, CellType.STRING);
		        cell.setCellValue("造斜点(m)"); 
		        cell.setCellStyle(cellStyle);
		        cell = row1.createCell(8, CellType.STRING);
		        cell.setCellValue("日进尺(m)"); 
		        cell.setCellStyle(cellStyle);
		        cell = row1.createCell(9, CellType.STRING);
		        cell.setCellValue("钻压(KN)"); 
		        cell.setCellStyle(cellStyle);
		        cell = row1.createCell(10, CellType.STRING);
		        cell.setCellValue("排量(l/s)"); 
		        cell.setCellStyle(cellStyle);
		        cell = row1.createCell(11, CellType.STRING);
		        cell.setCellValue("测斜点(m)"); 
		        cell.setCellStyle(cellStyle);
		        cell = row1.createCell(12, CellType.STRING);
		        cell.setCellValue("井斜(°)"); 
		        cell.setCellStyle(cellStyle);
		        cell = row1.createCell(13, CellType.STRING);
		        cell.setCellValue("方位(°)"); 
		        cell.setCellStyle(cellStyle);
		        cell = row1.createCell(14, CellType.STRING);
		        cell.setCellValue("垂深(m)"); 
		        cell.setCellStyle(cellStyle);
		        cell = row1.createCell(15, CellType.STRING);
		        cell.setCellValue("闭合距(m)"); 
		        cell.setCellStyle(cellStyle);
		        cell = row1.createCell(16, CellType.STRING);
		        cell.setCellValue("闭合方位(°)");
		        cell.setCellStyle(cellStyle);
		        cell = row1.createCell(17, CellType.STRING);
		        cell.setCellValue("仪器类型"); 
		        cell.setCellStyle(cellStyle);
		        cell = row1.createCell(18, CellType.STRING);
		        cell.setCellValue("工程概况");
		        cell.setCellStyle(cellStyle);
		        cell = row1.createCell(19, CellType.STRING);
		        cell.setCellValue("下一步方案"); 
		        cell.setCellStyle(cellStyle);
		        cell = row1.createCell(20, CellType.STRING);
		        cell.setCellValue("钻具组合"); 
		        cell.setCellStyle(cellStyle);
		        cell = row1.createCell(21, CellType.STRING);
		        cell.setCellValue("施工人员"); 
		        cell.setCellStyle(cellStyle);
		        
		        for (int i = 0; i < list.size(); i++) {
		        	row1=sheet.createRow(i+2);
					cell = row1.createCell(0, CellType.STRING);
					cell.setCellValue(i+1); 
					//通过井编号查询井号
					WellDxInfo wellDxInfo=wellDxInfoSerivce.selectDxWellInfoById(list.get(i).getWellid());
			        cell = row1.createCell(1, CellType.STRING);
			        cell.setCellValue(wellDxInfo.getWellcode()); 
			        
			        cell = row1.createCell(2, CellType.STRING);
			        if(null!=DateUtil.DateToString(list.get(i).getLogdate(),DateUtil.YYYY_MM_DD)){
			        	cell.setCellValue(DateUtil.DateToString(list.get(i).getLogdate(),DateUtil.YYYY_MM_DD));
			        }else{
			        	cell.setCellValue("");
			        }
			        cell = row1.createCell(3, CellType.STRING);
			        if(null!=list.get(i).getDid()){
			        	cell.setCellValue(getDeptName(list.get(i).getDid())); 
			        }else{
			        	cell.setCellValue(""); 
			        }
			        
			        cell = row1.createCell(4, CellType.STRING);
			        if(StringUtils.isNotBlank(wellDxInfo.getJx())){
			        	cell.setCellValue(wellDxInfo.getJx()); 
			        }else{
			        	cell.setCellValue(""); 
			        }
			        
			        cell = row1.createCell(5, CellType.STRING);
			        if(null!=wellDxInfo.getSjjs()){
			        	cell.setCellValue(wellDxInfo.getSjjs());
			        }else{
			        	cell.setCellValue("");
			        }
			        cell = row1.createCell(6, CellType.STRING);
			        if(null!=list.get(i).getJs()){
			        	cell.setCellValue(list.get(i).getJs());
			        }else{
			        	cell.setCellValue("");
			        }
			        cell = row1.createCell(7, CellType.STRING);
			        if(StringUtils.isNotBlank(wellDxInfo.getZxd())){
			        	cell.setCellValue(wellDxInfo.getZxd());
			        }else{
			        	cell.setCellValue("");
			        }
			        cell = row1.createCell(8, CellType.STRING);
			        if(null!=list.get(i).getRjc()){
			        	cell.setCellValue(list.get(i).getRjc());
			        }else{
			        	cell.setCellValue("");
			        }
			        cell = row1.createCell(9, CellType.STRING);
			        cell.setCellValue(list.get(i).getZy());
			        cell = row1.createCell(10, CellType.STRING);
			        cell.setCellValue(list.get(i).getPl());
			        cell = row1.createCell(11, CellType.STRING);
			        cell.setCellValue(list.get(i).getCxd());
			        cell = row1.createCell(12, CellType.STRING);
			        cell.setCellValue(list.get(i).getJxd());
			        cell = row1.createCell(13, CellType.STRING);
			        cell.setCellValue(list.get(i).getFw());
			        cell = row1.createCell(14, CellType.STRING);
			        cell.setCellValue(list.get(i).getCs());
			        cell = row1.createCell(15, CellType.STRING);
			        cell.setCellValue(list.get(i).getBhj());
			        cell = row1.createCell(16, CellType.STRING);
			        cell.setCellValue(list.get(i).getBhfw());
			        cell = row1.createCell(17, CellType.STRING);
			        cell.setCellValue(list.get(i).getYqlx());
			        cell = row1.createCell(18, CellType.STRING);
			        cell.setCellValue(list.get(i).getGcgk());
			        cell = row1.createCell(19, CellType.STRING);
			        cell.setCellValue(list.get(i).getXybjh());
			        cell = row1.createCell(20, CellType.STRING);
			        cell.setCellValue(list.get(i).getZjzh());
			        cell = row1.createCell(21, CellType.STRING);
			        cell.setCellValue(list.get(i).getSgry());
				}
			}
		}
		if("xwlog".equals(type)){
			if(null!=obj){
				List<WellXwDaily> list=(List<WellXwDaily>) obj;
				if(null!=list){
					//创建行,第一行  
			        HSSFRow row1 = sheet.createRow(0);
			        HSSFCell cell = row1.createCell(0, CellType.STRING);  
			        cell.setCellValue(name);
			        cell.setCellStyle(cellStyle);
			        row1.setHeight((short) 700);// 设置行高   
			        //合并
			        CellRangeAddress region1 = new CellRangeAddress(0, 0, (short) 0, (short) 8); 
			        sheet.addMergedRegion(region1);
			        row1 = sheet.createRow(1); 
			        
					sheet.setColumnWidth(3, 4000); //设置列宽
					sheet.setColumnWidth(4, 4000); //设置列宽
					sheet.setColumnWidth(5, 8000); //设置列宽
					sheet.setColumnWidth(6, 8000); //设置列宽
			        
			        //创建单元格，操作第一行各列
			        cell = row1.createCell(0, CellType.STRING);  
			        cell.setCellValue("编号");
			        cell.setCellStyle(cellStyle);
			        row1.setHeight((short) 400);// 设置行高   
			        cell = row1.createCell(1, CellType.STRING);
			        cell.setCellValue("井号"); 
			        cell.setCellStyle(cellStyle);
			        cell = row1.createCell(2, CellType.STRING);
			        cell.setCellValue("日期"); 
			        cell.setCellStyle(cellStyle);
			        sheet.setColumnWidth(2, 2750); //设置列宽
			        cell = row1.createCell(3, CellType.STRING);
			        cell.setCellValue("井队");
			        sheet.setColumnWidth(3, 2800); //设置列宽
			        cell.setCellStyle(cellStyle);
			        cell = row1.createCell(4, CellType.STRING);
			        cell.setCellValue("井深(m)"); 
			        cell.setCellStyle(cellStyle);
			        cell = row1.createCell(5, CellType.STRING);
			        cell.setCellValue("进尺(m)"); 
			        cell.setCellStyle(cellStyle);
			        cell = row1.createCell(6, CellType.STRING);
			        cell.setCellValue("工程简况");
			        cell.setCellStyle(cellStyle);
			        cell = row1.createCell(7, CellType.STRING);
			        cell.setCellValue("下一步计划"); 
			        cell.setCellStyle(cellStyle);
			        cell = row1.createCell(8, CellType.STRING);
			        cell.setCellValue("汇报人"); 
			        cell.setCellStyle(cellStyle);
			        for(int i=0;i<list.size();i++){
			        	row1=sheet.createRow(i+2);
						cell = row1.createCell(0, CellType.STRING);
						cell.setCellValue(i+1); 
				        
						//通过井编号查询井号
						WellInfo wellInfo=wellInfoSerivce.selectWellInfoById(list.get(i).getWellid());
						cell = row1.createCell(1, CellType.STRING);
						cell.setCellValue(wellInfo.getWellcode()); 
						//日期
						cell = row1.createCell(2, CellType.STRING);
				        if(null!=DateUtil.DateToString(list.get(i).getLogdate(),DateUtil.YYYY_MM_DD)){
				        	cell.setCellValue(DateUtil.DateToString(list.get(i).getLogdate(),DateUtil.YYYY_MM_DD));
				        }else{
				        	cell.setCellValue("");
				        }
				        cell = row1.createCell(3, CellType.STRING);
				        if(null!=list.get(i).getDid()){
				        	cell.setCellValue(getDeptName(list.get(i).getDid())); 
				        }else{
				        	cell.setCellValue(""); 
				        }
				        cell = row1.createCell(4, CellType.STRING);
				        
				        if(null!=list.get(i).getTdepth()){
				        	cell.setCellValue(list.get(i).getTdepth());
				        }else{
				        	cell.setCellValue("");
				        }
				        
				        cell = row1.createCell(5, CellType.STRING);
				        if(null!=list.get(i).getJc()){
				        	cell.setCellValue(list.get(i).getJc());
				        }else{
				        	cell.setCellValue("");
				        }
				        
				        cell = row1.createCell(6, CellType.STRING);
				        cell.setCellValue(list.get(i).getGcjk());
				        
				        cell = row1.createCell(7, CellType.STRING);
				        cell.setCellValue(list.get(i).getXybjh());
				        
				        cell = row1.createCell(8, CellType.STRING);
				        cell.setCellValue(list.get(i).getHbr());
				        
				        
			        }
				}
			}
		}
		if("wdlog".equals(type)){
			List<WellWdDaily> wellWdDaily=(List<WellWdDaily>) obj;
			if(null!=wellWdDaily){
				//创建行,第一行  
		        HSSFRow row1 = sheet.createRow(0);
		        HSSFCell cell = row1.createCell(0, CellType.STRING);  
		        cell.setCellValue(name);
		        cell.setCellStyle(cellStyle);
		        row1.setHeight((short) 700);// 设置行高   
		        //合并
		        CellRangeAddress region1 = new CellRangeAddress(0, 0, (short) 0, (short) 4); 
		        sheet.addMergedRegion(region1);
		        row1 = sheet.createRow(1); 
		        cell = row1.createCell(0, CellType.STRING);
		        region1 = new CellRangeAddress(1, 1, (short) 0, (short) 4); 
		        sheet.addMergedRegion(region1);
		        cell.setCellValue("汇报人：");
		        if(StringUtils.isNotBlank(wellWdDaily.get(0).getHbr())){
		        	cell.setCellValue("汇报人："+wellWdDaily.get(0).getHbr());
		        }
		        cell.setCellStyle(cellStyle);
		        
		        
		        row1 = sheet.createRow(2); 
		        cell = row1.createCell(0, CellType.STRING); 
		        cell.setCellStyle(cellStyle);
		        //分割
		        sheet.setColumnWidth(0, 5000); //设置列宽
		        HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
		        HSSFClientAnchor anchor = new HSSFClientAnchor();
		        anchor.setAnchor((short) 0, 2, 0, 0, (short) 1, 3, 0, 0);
		        HSSFSimpleShape line1 = patriarch.createSimpleShape(anchor);
		        line1.setShapeType(HSSFSimpleShape.OBJECT_TYPE_LINE);
		        line1.setLineStyle(HSSFSimpleShape.LINESTYLE_SOLID) ;
		        //在NPOI中线的宽度12700表示1pt,所以这里是0.5pt粗的线条。
		        line1.setLineWidth(6350);
		        cell.setCellStyle(cellStyle);
		        cell.setCellValue(" 名称                      数据");
		        
//		        cell = row1.createCell(1, CellType.STRING);
//		        cell.setCellValue("日期");
//		        sheet.setColumnWidth(1, 2750); //设置列宽
		        cell = row1.createCell(1, CellType.STRING);
		        cell.setCellValue("钻机数量(台)");
		        sheet.setColumnWidth(1, 5000); //设置列宽
		        cell.setCellStyle(cellStyle);
		        cell = row1.createCell(2, CellType.STRING);
		        cell.setCellValue("钻机类型");
		        sheet.setColumnWidth(2, 5000); //设置列宽
		        cell.setCellStyle(cellStyle);
		        cell = row1.createCell(3, CellType.STRING);
		        cell.setCellValue("钻机所属单位");
		        sheet.setColumnWidth(3, 5000); //设置列宽
		        cell.setCellStyle(cellStyle);
		        cell = row1.createCell(4, CellType.STRING);
		        cell.setCellValue("当日用电量(度)");
		        sheet.setColumnWidth(4, 5000); //设置列宽
		        cell.setCellStyle(cellStyle);
		        for(int i=0;i<wellWdDaily.size();i++){
		        	row1=sheet.createRow(i+3);
					cell = row1.createCell(0, CellType.STRING);
					//通过qkid查询名称
					String qkName=getQkNameById(wellWdDaily.get(i).getId());
					if(StringUtils.isNotBlank(qkName)){
						cell.setCellValue(qkName); 
					}else{
						cell.setCellValue(wellWdDaily.get(i).getQkname()); 
					}
					cell = row1.createCell(1, CellType.STRING);
					cell.setCellValue(wellWdDaily.get(i).getZjsl()); 
//					cell = row1.createCell(2, CellType.STRING);
//			        if(null!=DateUtil.DateToString(wellWdDaily.get(i).getLogdate(),DateUtil.YYYY_MM_DD)){
//			        	cell.setCellValue(DateUtil.DateToString(wellWdDaily.get(i).getLogdate(),DateUtil.YYYY_MM_DD));
//			        }else{
//			        	cell.setCellValue("");
//			        }
					cell = row1.createCell(2, CellType.STRING);
					cell.setCellValue(wellWdDaily.get(i).getZjlx()); 
					cell = row1.createCell(3, CellType.STRING);
					cell.setCellValue(wellWdDaily.get(i).getZjssdw()); 
					cell = row1.createCell(4, CellType.STRING);
					cell.setCellValue(wellWdDaily.get(i).getDrydl()); 
		        }
		        
			}
		}
		PrintSetup   printSetUp = sheet.getPrintSetup();
		sheet.setAutobreaks(true);
		printSetUp.setFitHeight((short)1); //一页打印
		printSetUp.setPaperSize(PrintSetup.A4_PAPERSIZE);//设置
		sheet.setHorizontallyCenter(true);//设置打印页面为水平居中  
		sheet.setVerticallyCenter(true);//设置打印页面为垂直居中  
		ByteArrayOutputStream os=new ByteArrayOutputStream();
		try {
			workBook.write(os);
		} catch (Exception e) {
			e.printStackTrace();
		}
		byte[] content=os.toByteArray();
		ByteArrayInputStream is = new ByteArrayInputStream(content);
        os.close();
        workBook.close();
        return is;
	}
	
	private String getQkNameById(int qkid) {
		return wdDailyService.selectQkNameByQkId(qkid);
	}
	private String getDeptName(int did){
		DepartmentDto dto=new DepartmentDto();
		dto.setId(did);
		//井队
		List<Department> result=departMentExMapper.selectDeptByDto(dto);
		if(null!=result&&result.size()>0){
			return result.get(0).getName();
		}
		return "";
	}
}