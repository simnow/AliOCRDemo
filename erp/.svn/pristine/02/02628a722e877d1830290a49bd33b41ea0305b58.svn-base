package cn.caecc.erp.modules.service.serviceImpl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.caecc.erp.modules.dao.ex.dto.MaterialGoodsRelationshipActivitiApplyDto;
import cn.caecc.erp.modules.dao.ex.mapper.MaterialGoodsRelationshipActivitiApplyExMapper;
import cn.caecc.erp.modules.dao.mybatis.entity.Goods;
import cn.caecc.erp.modules.dao.mybatis.entity.MaterialGoodsRelationshipActivitiApply;
import cn.caecc.erp.modules.dao.mybatis.entity.MaterialGoodsRelationshipActivitiApplyExample;
import cn.caecc.erp.modules.dao.mybatis.entity.MaterialGoodsRelationshipActivitiApplyExample.Criteria;
import cn.caecc.erp.modules.dao.mybatis.mapper.GoodsMapper;
import cn.caecc.erp.modules.dao.mybatis.mapper.MaterialGoodsRelationshipActivitiApplyMapper;
import cn.caecc.erp.modules.service.MaterialGoodsRelationshipActivitiApplyService;
import cn.caecc.erp.support.exception.CommonException;
import cn.caecc.erp.support.util.ExcelUtil;
@Service
public class MaterialGoodsRelationshipActivitiApplyServiceImpl
		implements MaterialGoodsRelationshipActivitiApplyService {
	
	@Autowired
	private MaterialGoodsRelationshipActivitiApplyMapper materialGoodsRelationshipActivitiApplyMapper;
	@Autowired
	private MaterialGoodsRelationshipActivitiApplyExMapper materialGoodsRelationshipActivitiApplyExMapper;
	@Autowired
	private GoodsMapper goodsMapper;
	@Override
	public List<MaterialGoodsRelationshipActivitiApply> create(List<MaterialGoodsRelationshipActivitiApply> list) {
		if (list != null && list.size() > 0) {
			int batchAdd = materialGoodsRelationshipActivitiApplyExMapper.batchAdd(list);
			if (batchAdd != list.size()) {
				throw new CommonException("创建失败");
			}
		}else {
			throw new CommonException("参数异常");
		}
		return list;
	}

	@Override
	public MaterialGoodsRelationshipActivitiApplyDto findDetail(int id) {
		return materialGoodsRelationshipActivitiApplyExMapper.findDetail(id);
	}

	@Override
	public List<MaterialGoodsRelationshipActivitiApply> batchUpdate(List<MaterialGoodsRelationshipActivitiApply> list) {
		if (list != null && list.size() > 0) {
			List<Integer> idList = new ArrayList<>();
			List<MaterialGoodsRelationshipActivitiApply> batchFind = null;
			for (MaterialGoodsRelationshipActivitiApply mgr : list) {
				idList.add(mgr.getId());
			}
			if (idList != null && idList.size() == list.size()) {
				batchFind = this.batchFind(idList);
			}else {
				throw new CommonException("数据异常");
			}
			if (batchFind != null && batchFind.size() == list.size()) {
				int result = materialGoodsRelationshipActivitiApplyExMapper.batchUpdate(list);
				if (result != list.size()) {
					throw new CommonException("更新失败");
				}
			}else {
				throw new CommonException("数据异常");
			}
		}else {
			throw new CommonException("参数异常");
		}
		return list;
	}

	@Override
	public int deleteByMid(int id) {
		MaterialGoodsRelationshipActivitiApplyExample example = new MaterialGoodsRelationshipActivitiApplyExample();
		Criteria criteria = example.createCriteria();
		criteria.andMidEqualTo(id);
		int result = materialGoodsRelationshipActivitiApplyMapper.deleteByExample(example);
		if (result <= 0) {
			throw new CommonException("删除失败");
		}
		return result;
	}

	@Override
	public List<MaterialGoodsRelationshipActivitiApply> batchFind(List<Integer> list) {
		return materialGoodsRelationshipActivitiApplyExMapper.batchFind(list);
	}

	@Override
	public List<MaterialGoodsRelationshipActivitiApply> exportExcel(InputStream inputStream,
			String fileName) throws Exception {
		// TODO Auto-generated method stub
		String extension = fileName.lastIndexOf(".") == -1 ? "" : fileName.substring(fileName.lastIndexOf(".") + 1);
		if ("xls".equals(extension)) {
			return exportBeforeExcel(inputStream);
		} else if ("xlsx".equals(extension)) {
			return exportLatestExcel(inputStream);
		} else {
			throw  new CommonException("文件格式不匹配");
		}
	}
	@SuppressWarnings("deprecation")
	public List<MaterialGoodsRelationshipActivitiApply> exportLatestExcel(InputStream in) throws Exception{
		
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook(in);
		XSSFSheet xssfSheet= xssfWorkbook.getSheetAt(0);
		XSSFRow xssfRow = null;
		XSSFCell xssfCell = null;
		String code="";
		//表头集合
		List<String> headerList=new ArrayList<String>();
		//循环获取行
		List<MaterialGoodsRelationshipActivitiApply> dataList=new ArrayList<MaterialGoodsRelationshipActivitiApply>();
		//获取数据库存在物品列表
		List<Goods> existGoodsList=goodsMapper.selectByExample(null);
		//设置读取列数为表头列数
		int rowNum=0;
		//读取EXCEL
		for (int i =xssfSheet.getFirstRowNum(); i < xssfSheet.getLastRowNum()+1; i++) {
					xssfRow=xssfSheet.getRow(i);
					//i判断行是否null 为null进入下一行
					if(xssfRow==null){
						continue;
					}
					//保存表头信息
					if(i==xssfSheet.getFirstRowNum()){
						//设置列数
						rowNum=xssfRow.getLastCellNum();
						for (int n = xssfRow.getFirstCellNum(); n < rowNum; n++) {
							xssfCell=xssfRow.getCell(n);
							//如果表头信息为null返回异常
							if (xssfCell==null) {
								xssfWorkbook.close();
								throw new CommonException("excel表头信息需要填写");
							}
							headerList.add(xssfCell.getStringCellValue());
						}
							
						}
					//循环获取内容信息
					else{
					//创建接受信息对象
					MaterialGoodsRelationshipActivitiApply mgRelationshipActivitiApply=new MaterialGoodsRelationshipActivitiApply();
					for (int j = xssfRow.getFirstCellNum(); j <rowNum; j++) {
						xssfCell=xssfRow.getCell(j);
						String headerName=headerList.get(j);
						if(xssfCell==null||xssfCell.getCellType()==XSSFCell.CELL_TYPE_BLANK){
							//申请原因和备注可不填
							if(headerName.equals("申请原因")||headerName.equals("备注")){
								 continue;
							}
							//其余选项必须填
							else{
							xssfWorkbook.close();
							throw new CommonException("excel第"+(i+1)+"记录"+headerName+"是必填项");
							}
						}
						switch(headerName){
						case "编码":
							code=xssfCell.getStringCellValue();
							if(!isExistGoods(code, existGoodsList)){
								xssfWorkbook.close();
								throw new CommonException("excel第"+(i+1)+"条记录物品编码不存在，请进行核对");
							}
							else if(!isExistContractGoods(code,dataList)){
								xssfWorkbook.close();
								throw new CommonException("excel存在多条编码为"+code+"记录，每天记录只能存在一条");
							}
							else{
								mgRelationshipActivitiApply.setGcode(code);
								break;
							}		
						case "数量":
							if(xssfCell.getCellType()!=XSSFCell.CELL_TYPE_NUMERIC){
								xssfWorkbook.close();
								throw new CommonException("excel第"+(i+1)+"条记录单价格式不符，必须为数值形式");
							}
							mgRelationshipActivitiApply.setApplycount((int)xssfCell.getNumericCellValue());
							break;
						case "申请原因":
							mgRelationshipActivitiApply.setReason(xssfCell.getStringCellValue());
							break;
						case "备注":
							mgRelationshipActivitiApply.setRemarks(xssfCell.getStringCellValue());
							break;
						default:
							xssfWorkbook.close();
							throw new CommonException("Excel表头不匹配，应为编码，数量，申请原因，备注");
						}		
					}
					dataList.add(mgRelationshipActivitiApply);
					}
					
				}
				xssfWorkbook.close();
				return  dataList;
	}
	@SuppressWarnings("deprecation")
	public  List<MaterialGoodsRelationshipActivitiApply> exportBeforeExcel(InputStream in) throws Exception{
		HSSFWorkbook hssfWorkbook=new HSSFWorkbook(in);
		HSSFSheet hssfSheet=hssfWorkbook.getSheetAt(0);
		HSSFRow hssfRow=null;
		HSSFCell hssfCell=null;
		String code="";
		//表头集合
		List<String> headerList=new ArrayList<String>();
		//循环获取行
		List<MaterialGoodsRelationshipActivitiApply> dataList=new ArrayList<MaterialGoodsRelationshipActivitiApply>();
		//获取数据库存在物品列表
		List<Goods> existGoodsList=goodsMapper.selectByExample(null);
		//设置读取列数为表头列数
		int rowNum=0;
		//读取EXCEL
		for (int i =hssfSheet.getFirstRowNum(); i < hssfSheet.getLastRowNum()+1; i++) {
					hssfRow=hssfSheet.getRow(i);
					//i判断行是否null 为null进入下一行
					if(hssfRow==null){
						continue;
					}
					//保存表头信息
					if(i==hssfSheet.getFirstRowNum()){
						//设置列数
						rowNum=hssfRow.getLastCellNum();
						for (int n = hssfRow.getFirstCellNum(); n < rowNum; n++) {
							hssfCell=hssfRow.getCell(n);
							//如果表头信息为null返回异常
							if (hssfCell==null) {
								hssfWorkbook.close();
								throw new CommonException("excel表头信息需要填写");
							}
							headerList.add(hssfCell.getStringCellValue());
						}
							
						}
					//循环获取内容信息
					else{
					MaterialGoodsRelationshipActivitiApply mgRelationshipActivitiApply=new MaterialGoodsRelationshipActivitiApply();
					for (int j = hssfRow.getFirstCellNum(); j <rowNum; j++) {
						hssfCell=hssfRow.getCell(j);
						String headerName=headerList.get(j);
						if(hssfCell==null||hssfCell.getCellType()==XSSFCell.CELL_TYPE_BLANK){
							//申请原因和备注可不填
							if(headerName.equals("申请原因")||headerName.equals("备注")){
								 continue;
							}
							//其余选项必须填
							else{
							hssfWorkbook.close();
							throw new CommonException("excel第"+(i+1)+"记录"+headerName+"是必填项");
							}
						}
						//判断是哪个值
						switch(headerName){
						case "编码":
							code=ExcelUtil.getCellValueByType(hssfCell);
							if(!isExistGoods(code, existGoodsList)){
								hssfWorkbook.close();
								throw new CommonException("excel第"+(i+1)+"条记录物品编码不存在，请进行核对");
							}
							else if(!isExistContractGoods(code,dataList)){
								hssfWorkbook.close();
								throw new CommonException("excel存在多条编码为"+code+"记录，每天记录只能存在一条");
							}
							else{
								mgRelationshipActivitiApply.setGcode(code);
								break;
							}		
						case "数量":
							if(hssfCell.getCellType()!=XSSFCell.CELL_TYPE_NUMERIC){
								hssfWorkbook.close();
								throw new CommonException("excel第"+(i+1)+"条记录单价格式不符，必须为数值形式");
							}
							mgRelationshipActivitiApply.setApplycount((int)hssfCell.getNumericCellValue());
							break;
						case "申请原因":
							mgRelationshipActivitiApply.setReason(ExcelUtil.getCellValueByType(hssfCell));
							break;
						case "备注":
							mgRelationshipActivitiApply.setRemarks(ExcelUtil.getCellValueByType(hssfCell));
							break;
						default:
							hssfWorkbook.close();
							throw new CommonException("Excel表头不匹配，应为编码，数量，单价");
						}		
					}
					dataList.add(mgRelationshipActivitiApply);
					}
					
				}
				hssfWorkbook.close();
				return  dataList;
	}
	public boolean  isExistGoods(String code,List<Goods> goodsList){
			if(goodsList!=null&&goodsList.size()>0){
				for (int i = 0; i < goodsList.size(); i++) {
					
					if(code.equals(goodsList.get(i).getCode())){
						return true;
					}
				}
			}
			return false;
		
	}
	public boolean  isExistContractGoods(String code,List<MaterialGoodsRelationshipActivitiApply> cgList){
		if(cgList!=null&&cgList.size()>0){
			for (int i = 0; i < cgList.size(); i++) {
				
				if(code.equals(cgList.get(i).getGcode())){
					return false;
				}
			}
		}
		return true;
	
}	

	
}
