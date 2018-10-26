package com.partymasses.support.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.partymasses.modules.dao.mybatis.entity.User;
import com.partymasses.support.exception.CommonException;

public class FileParsingUtil {
	/**
	 * excel
	 */
	public static List<User> readExcel(File file, User user) throws Exception {
		String fileName = file.getName();
		String extension = fileName.lastIndexOf(".") == -1 ? "" : fileName.substring(fileName.lastIndexOf(".") + 1);
		if ("xls".equals(extension)) {
			return read2003Excel(file, user);
		} else if ("xlsx".equals(extension)) {
			return read2007Excel(file, user);
		} else {
			throw new IOException("不支持该文件类型");
		}
	}

	/**
	 * 解析 office 2003 excel
	 * 
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	private static List<User> read2003Excel(File file, User user) throws Exception {
		List<User> list = new LinkedList<User>();
		HSSFWorkbook hwb = new HSSFWorkbook(new FileInputStream(file));
		HSSFSheet sheet = hwb.getSheetAt(0);
		Object value = null;
		HSSFRow row = null;
		HSSFCell cell = null;
		int counter = 0;
		for (int i = sheet.getFirstRowNum(); counter < sheet.getPhysicalNumberOfRows(); i++) {
			row = sheet.getRow(i);
			if (row == null) {
				continue;
			} else {
				counter++;
			}
			List<Object> linked = new LinkedList<Object>();
			for (int j = row.getFirstCellNum(); j <= row.getLastCellNum(); j++) {
				cell = row.getCell(j);
				if (cell == null) {
					continue;
				}
				DecimalFormat df = new DecimalFormat("0");

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				DecimalFormat nf = new DecimalFormat("0");
				switch (cell.getCellType()) {
				case XSSFCell.CELL_TYPE_STRING:
					value = cell.getStringCellValue();
					break;
				case XSSFCell.CELL_TYPE_NUMERIC:
					if ("@".equals(cell.getCellStyle().getDataFormatString())) {
						value = df.format(cell.getNumericCellValue());
					} else if ("General".equals(cell.getCellStyle().getDataFormatString())) {
						value = nf.format(cell.getNumericCellValue());
					} else {
						value = sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue()));
					}
					break;
				case XSSFCell.CELL_TYPE_BOOLEAN:
					value = cell.getBooleanCellValue();
					break;
				case XSSFCell.CELL_TYPE_BLANK:
					value = "";
					break;
				default:
					value = cell.toString();
				}
				if (value == null || "".equals(value)) {
					continue;
				}
				linked.add(value);
			}
			if (i > 1) {
				if (linked.size() == 3) {
					SimpleDateFormat sdf = new SimpleDateFormat();
					User newUser = new User();
					for (int j = 0; j < linked.size(); j++) {
						switch (j) {
						case 0:
							newUser.setMobile(linked.get(j).toString());
							break;
						case 1:
							int unitId = Integer.parseInt(linked.get(j).toString());
							if ("superadmin".equals(user.getRoletype())) {
								newUser.setUnitid(unitId);
							} else if (user.getUnitid() == null) {
								throw new CommonException("单位ID异常");
							} else if (unitId == user.getUnitid()) {
								newUser.setUnitid(unitId);
							} else {
								throw new CommonException("Excel表格中有用户单位ID与您不符，您没有权限录入表格");
							}
							break;
						default:
							String roleType = linked.get(j).toString();
							if ("superadmin".equals(roleType)) {
								throw new CommonException("您无权授予superadmin权限");
							}else {
								newUser.setRoletype(linked.get(j).toString());
							}
							break;
						}
					}
					newUser.setPassword("QfRRxHuWnLpmQlrKjTeM2A==");
					newUser.setCreatetime(sdf.parse(DateUtil.currentDate(DateUtil.YMDHMS)));
					list.add(newUser);
				} else {
					int index = i + 1;
					throw new CommonException("Excel表格第" + index + "行信息未填写完整");
				}
			}
		}
		if (counter < 3) {
			return null;
		} else {
			return list;
		}
	}

	/**
	 * 解析Office 2007 excel
	 * 
	 * @throws ParseException
	 */
	private static List<User> read2007Excel(File file, User user) throws Exception {
		List<User> list = new LinkedList<User>();
		XSSFWorkbook xwb = new XSSFWorkbook(new FileInputStream(file));
		XSSFSheet sheet = xwb.getSheetAt(0);
		Object value = null;
		XSSFRow row = null;
		XSSFCell cell = null;
		int counter = 0;
		for (int i = sheet.getFirstRowNum(); counter < sheet.getPhysicalNumberOfRows(); i++) {
			row = sheet.getRow(i);
			if (row == null) {
				continue;
			} else {
				counter++;
			}
			List<Object> linked = new LinkedList<Object>();
			for (int j = row.getFirstCellNum(); j <= row.getLastCellNum(); j++) {
				cell = row.getCell(j);
				if (cell == null) {
					continue;
				}
				DecimalFormat df = new DecimalFormat("0");
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				DecimalFormat nf = new DecimalFormat("0");
				switch (cell.getCellType()) {
				case XSSFCell.CELL_TYPE_STRING:
					value = cell.getStringCellValue();
					break;
				case XSSFCell.CELL_TYPE_NUMERIC:
					if ("@".equals(cell.getCellStyle().getDataFormatString())) {
						value = df.format(cell.getNumericCellValue());
					} else if ("General".equals(cell.getCellStyle().getDataFormatString())) {
						value = nf.format(cell.getNumericCellValue());
					} else {
						value = sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue()));
					}
					break;
				case XSSFCell.CELL_TYPE_BOOLEAN:
					value = cell.getBooleanCellValue();
					break;
				case XSSFCell.CELL_TYPE_BLANK:
					value = "";
					break;
				default:
					value = cell.toString();
				}
				if (value == null || "".equals(value)) {
					continue;
				}
				linked.add(value);
			}
			if (i > 1) {
				if (linked.size() == 3) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					User newUser = new User();
					for (int j = 0; j < linked.size(); j++) {
						switch (j) {
						case 0:
							newUser.setMobile(linked.get(j).toString());
							break;
						case 1:
							int unitId = Integer.parseInt(linked.get(j).toString());
							if ("superadmin".equals(user.getRoletype())) {
								newUser.setUnitid(unitId);
							} else if (user.getUnitid() == null) {
								throw new CommonException("单位ID异常");
							} else if (unitId == user.getUnitid().intValue()) {
								newUser.setUnitid(unitId);
							} else {
								throw new CommonException("Excel表格中有用户单位ID与您不符，您没有权限录入表格");
							}
							break;
						default:
							String roleType = linked.get(j).toString();
							if ("superadmin".equals(roleType)) {
								throw new CommonException("您无权授予superadmin权限");
							}else {
								newUser.setRoletype(linked.get(j).toString());
							}
							break;
						}
					}
					newUser.setPassword("QfRRxHuWnLpmQlrKjTeM2A==");
					newUser.setCreatetime(sdf.parse(DateUtil.currentDate(DateUtil.YMDHMS)));
					list.add(newUser);
				} else {
					int index = i + 1;
					throw new CommonException("Excel表格第" + index + "行信息未填写完整");
				}
			}
		}
		if (counter < 3) {
			return null;
		} else {
			return list;
		}
	}
}