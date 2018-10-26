package com.bswebsite.controller;

import java.beans.IntrospectionException;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bswebsite.modules.dao.mybatis.entity.Bx;
import com.bswebsite.modules.dao.mybatis.entity.News;
import com.bswebsite.modules.service.BxService;
import com.bswebsite.support.dto.ExcelBean;
import com.bswebsite.support.message.Message;
import com.bswebsite.support.util.ExcelUtil;

@Controller
@RequestMapping(value="/bx")
public class BxController {

	@Autowired
	private BxService bxService;
	/**
	 * 提交
	 */
	@RequestMapping(value="/dosubmit",method = RequestMethod.GET)
	@ResponseBody
	public Message dosubmit(String pername,String ktname,String date,String shiyou,String neirong,
        	String source,String hetonghao,String money,String ketihao,String sourcemethod,
        	String buzhumoney,String tomoney,String backmoney,String beizhu){
		Message message=new Message();
		Bx bx=new Bx();
		bx.setBeizhu(beizhu);
		bx.setDate(date);
		bx.setHetonghao(hetonghao);
		bx.setKetihao(ketihao);
		bx.setKtname(ktname);
		bx.setMoney(money);
		bx.setNeirong(neirong);
		bx.setPername(pername);
		bx.setShiyou(shiyou);
		bx.setSource(source);
		bx.setBackmoney(backmoney);
		bx.setTomoney(tomoney);
		bx.setSourcemethod(sourcemethod);
		bx.setBuzhumoney(buzhumoney);
		
		if(bxService.insert(bx)){
			return message;
		}else{
			message.setSuccess(false);
			return message;	
		}
		
	}
	/**
	 * 查询
	 */
	@RequestMapping(value="/SelectAll")
	@ResponseBody
	public Message SelectAll(){
		Message message=new Message();
		message.setObj(bxService.selectAll());
		return message;
	}
	/**
	 * 删除
	 */
	@RequestMapping(value="/delete")
	@ResponseBody
	public Message delete(int id){
		Message message=new Message();
		if(0==id){
			message.setSuccess(bxService.deleteAll());
		}else{
			message.setSuccess(bxService.deleteById(new Integer(id)));
		}
		return message;
	}
	/**
	 * 删除
	 */
	@RequestMapping(value="/getExcel")
	@ResponseBody
	public Message getExcel(HttpServletRequest req){
		Message message=new Message();
		//查询数据后存储到Excel里面
	    List<Bx> BxList = bxService.selectAll();
	    String filename = "bx.csv";
	    List<Object> exportDataList = new ArrayList();
	    Map<String, String> map = new HashMap();
	    
	    map.put("11", "报销人");
	    map.put("12", "课题名称");
	    map.put("13", "日期");
	    map.put("14", "事由");
	    map.put("1", "开支内容");
	    map.put("2", "经费渠道");
	    map.put("3", "开支金额");
	    map.put("4", "合同号");
	    map.put("5", "课题号");
	    map.put("6", "还借款方式");
	    map.put("7", "还借款金额");
	    map.put("8", "应退还");
	    map.put("9", "应补助");
	    map.put("10", "备注");
	    if (BxList.size() > 0)
	    {
	      Map<String, String> row = new HashMap();
	      for (Bx g : BxList)
	      {
	        row = new LinkedHashMap();
	        row.put("11", g.getPername().toString());
	        row.put("12", g.getKtname().toString());
	        row.put("13", g.getDate().toString());
	        row.put("14", g.getShiyou().toString());
	        row.put("1", g.getNeirong().toString());
	        row.put("2", g.getSource().toString());
	        row.put("3", g.getMoney().toString());
	        row.put("4", g.getHetonghao().toString());
	        row.put("5", g.getKetihao().toString());
	        row.put("6", g.getSourcemethod().toString());
	        row.put("7", g.getTomoney().toString());
	        row.put("8", g.getBackmoney().toString());
	        row.put("9", g.getBuzhumoney().toString());
	        row.put("10", g.getBeizhu().toString());
	        exportDataList.add(row);
	      }
	    }
	    
	    String filePath = "/resources/down/";
	    String outPutPath = req.getRealPath(filePath);
	    createCSVFile(exportDataList, map, outPutPath, filename);
	    message.setObj(filePath + filename);
	    return message;
	  }
	  public static File createCSVFile(List exportData, Map<String, String> rowMapper, String filePath, String fileName)
	  {
	    File csvFile = null;
	    BufferedWriter csvFileOutputStream = null;
	    try
	    {
	      csvFile = new File(filePath + fileName);
	      File parent = csvFile.getParentFile();
	      if ((parent != null) && (!parent.exists())) {
	        parent.mkdirs();
	      }
	      csvFile.createNewFile();
	      
	      csvFileOutputStream = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(csvFile), "GBK"), 1024);
	      for (Iterator propertyIterator = rowMapper.entrySet().iterator(); propertyIterator.hasNext();)
	      {
	        Map.Entry propertyEntry = (Map.Entry)propertyIterator.next();
	        csvFileOutputStream.write((String)propertyEntry.getValue() != null ? new String(((String)propertyEntry.getValue()).getBytes("UTF-8"), "UTF-8") : "");
	        if (propertyIterator.hasNext()) {
	          csvFileOutputStream.write(",");
	        }
	      }
	      csvFileOutputStream.write("\r\n");
	      for (Iterator iterator = exportData.iterator(); iterator.hasNext();)
	      {
	        Object row = iterator.next();
	        for (Iterator propertyIterator = rowMapper.entrySet().iterator(); propertyIterator.hasNext();)
	        {
	          Map.Entry propertyEntry = (Map.Entry)propertyIterator.next();
	          csvFileOutputStream.write(BeanUtils.getProperty(row, (String)propertyEntry.getKey() != null ? (String)propertyEntry.getKey() : ""));
	          if (propertyIterator.hasNext()) {
	            csvFileOutputStream.write(",");
	          }
	        }
	        if (iterator.hasNext()) {
	          csvFileOutputStream.write("\r\n");
	        }
	      }
	      csvFileOutputStream.flush();
	    }catch (Exception e){
	    	e.printStackTrace();
	    }
	    finally
	    {
	      try
	      {
	        csvFileOutputStream.close();
	      }
	      catch (IOException e)
	      {
	        e.printStackTrace();
	      }
	    }
	    return csvFile;
	  }
	@RequestMapping(value="/export")
	@ResponseBody
	public void testExport(HttpServletRequest request, HttpServletResponse response) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, ClassNotFoundException, IntrospectionException, ParseException{
		 String name = request.getParameter("name");  
		response.reset(); //清除buffer缓存  
        Map<String,Object> map=new HashMap<String,Object>();  
        // 指定下载的文件名  
        response.setHeader("Content-Disposition", "attachment;filename="+"测试"+".xlsx");  
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");  
        response.setHeader("Pragma", "no-cache");  
        response.setHeader("Cache-Control", "no-cache");  
        response.setDateHeader("Expires", 0); 
		XSSFWorkbook workbook=null;  
        //TODO 导出Excel对象  
        workbook = testDate();  
        OutputStream output;  
        try {  
            output = response.getOutputStream();  
            BufferedOutputStream bufferedOutPut = new BufferedOutputStream(output);  
            bufferedOutPut.flush();  
            workbook.write(bufferedOutPut);  
            bufferedOutPut.close();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
	}
	private XSSFWorkbook testDate() throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, ClassNotFoundException, IntrospectionException, ParseException {
		//根据条件查询数据，把数据装载到一个list中  
	    List<News> list = new ArrayList<>();
	    News e=new News();
	    News e1=new News();
	    News e2=new News();
	    News e3=new News();
	    News e4=new News();
	    e.setId(3);
	    e.setNewsabstract("厂名3");
	    e.setNewsauthor("工号3");
	    e.setNewscontent("工号3");
	    e.setNewsimg("姓名3");
	    e.setNewstitle("性别3");
	    e.setNewstype("开户名3");
	    e.setPublishtime("银行卡号3");
	    e.setUpdateuser(3);
	    e.setCreateuser(3);
	    e2.setId(2);
	    e2.setNewsabstract("厂名2");
	    e2.setNewsauthor("工号2");
	    e2.setNewscontent("工号2");
	    e2.setNewsimg("姓名2");
	    e2.setNewstitle("性别2");
	    e2.setNewstype("开户名1");
	    e2.setPublishtime("银行卡号2");
	    e2.setUpdateuser(2);
	    e2.setCreateuser(2);
	    
	    e1.setId(1);
	    e1.setNewsabstract("厂名1");
	    e1.setNewsauthor("工号1");
	    e1.setNewscontent("工号1");
	    e1.setNewsimg("姓名1");
	    e1.setNewstitle("性别");
	    e1.setNewstype("开户名1");
	    e1.setPublishtime("2018-03-02");
	    e1.setUpdateuser(1);
	    e1.setCreateuser(1);
	    
	    list.add(e);
	    list.add(e1);
	    list.add(e2);
	    
	    
	      
	    List<ExcelBean> excel=new ArrayList<>();  
	    Map<Integer,List<ExcelBean>> map=new LinkedHashMap<>();  
	    XSSFWorkbook xssfWorkbook=null;  
	    //设置标题栏  
//	    excel.add();
	    excel.add(new ExcelBean("序号","id",0));  
	    excel.add(new ExcelBean("厂名","newsabstract",0));  
	    excel.add(new ExcelBean("工号","newsauthor",0));  
	    excel.add(new ExcelBean("姓名","newscontent",0));  
	    excel.add(new ExcelBean("性别","newstitle",0));  
	    excel.add(new ExcelBean("开户名","newstype",0));  
	    excel.add(new ExcelBean("银行卡号","publishtime",0));  
	    excel.add(new ExcelBean("开户行","updateuser",0));  
	    excel.add(new ExcelBean("金额","createuser",0));  
	    excel.add(new ExcelBean("备注","newsimg",0));  
	    map.put(0, excel);  
	    String sheetName = "测试";  
	    //调用ExcelUtil的方法  
	    xssfWorkbook = ExcelUtil.createExcelFile(News.class, list, map, sheetName);  
	    return xssfWorkbook;  
	}

}
