package cn.caecc.erp.support.util;

import java.text.DecimalFormat;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCell;

public class ExcelUtil {
	
	@SuppressWarnings("deprecation")
	public static  String getCellValueByType(HSSFCell hssfCell){
			DecimalFormat df = new DecimalFormat("#");  
			String ceString="";
			switch(hssfCell.getCellType()){
				case HSSFCell.CELL_TYPE_STRING:
					ceString = hssfCell.getRichStringCellValue().getString().trim();
					break;
				case HSSFCell.CELL_TYPE_NUMERIC:
					ceString = df.format(hssfCell.getNumericCellValue()).toString();
					break;
				case HSSFCell.CELL_TYPE_BOOLEAN:
					ceString = String.valueOf(hssfCell.getBooleanCellValue()).trim();
					break;
				case HSSFCell.CELL_TYPE_FORMULA:
					ceString =hssfCell.getCellFormula().trim();
				default:
					ceString = ""; 
			}
			return ceString;	
		
	}
	@SuppressWarnings("deprecation")
	public static  String getCellValueByType(XSSFCell xssfCell){
			DecimalFormat df = new DecimalFormat("#");  
			String ceString="";
			switch(xssfCell.getCellType()){
				case HSSFCell.CELL_TYPE_STRING:
					ceString = xssfCell.getRichStringCellValue().getString().trim();
					break;
				case HSSFCell.CELL_TYPE_NUMERIC:
					ceString = df.format(xssfCell.getNumericCellValue()).toString();
					break;
				case HSSFCell.CELL_TYPE_BOOLEAN:
					ceString = String.valueOf(xssfCell.getBooleanCellValue()).trim();
					break;
				case HSSFCell.CELL_TYPE_FORMULA:
					ceString =xssfCell.getCellFormula().trim();
				default:
					ceString = ""; 
			}
			return ceString;	
		
	}
}
