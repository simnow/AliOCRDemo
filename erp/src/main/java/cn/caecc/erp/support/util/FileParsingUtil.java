package cn.caecc.erp.support.util;

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

import cn.caecc.erp.modules.dao.mybatis.entity.User;
import cn.caecc.erp.support.exception.CommonException;

public class FileParsingUtil {
	/**
	 * excel
	 */
	public static List<User> readExcel(File file, Integer userId) throws Exception {
		String fileName = file.getName();
		String extension = fileName.lastIndexOf(".") == -1 ? "" : fileName.substring(fileName.lastIndexOf(".") + 1);
		if ("xls".equals(extension)) {
			return read2003Excel(file, userId);
		} else if ("xlsx".equals(extension)) {
			return read2007Excel(file, userId);
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
	private static List<User> read2003Excel(File file, Integer userId) throws Exception {
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
			for (int j = 0; j <= 10; j++) {
				cell = row.getCell(j);
				if (cell == null) {
					linked.add(cell);
					continue;
				}
				DecimalFormat df = new DecimalFormat("0");
				SimpleDateFormat sdf = new SimpleDateFormat(DateUtil.YMDHMS);
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
//				if (value == null || "".equals(value)) {
//					continue;
//				}
				linked.add(value);
			}
			if (i > 1) {
				boolean pass = true;
				for(int t = 0;t < 3;t ++){
					String string = linked.get(t).toString();
					if (string == null || "".equals(string)) {
						pass = false;
					}
				}
				if (pass) {
					SimpleDateFormat sdf = new SimpleDateFormat(DateUtil.YYYY_MM_DD);
					User newUser = new User();
					for (int j = 0; j < linked.size(); j++) {
						switch (j) {
						case 0:
							newUser.setLoginname(linked.get(j).toString());
							break;
						case 1:
							newUser.setName(linked.get(j).toString());
							break;
						case 2:
							String string = linked.get(j).toString();
							int sex = 1;
							if ("女".equals(string)) {
								sex = 2;
							}
							newUser.setSex(sex);
							break;
						case 3:
							Object tel = linked.get(j);
							if (tel != null && !"".equals(tel)) {
								newUser.setTelephone(linked.get(j).toString());
							}
							break;
						case 4:
							Object email = linked.get(j);
							if (email != null && !"".equals(email)) {
								newUser.setEmail(linked.get(j).toString());
							}
							break;
						case 5:
							Object did = linked.get(j);
							if (did != null && !"".equals(did)) {
								newUser.setDid(Integer.parseInt(linked.get(j).toString()));
							}
							break;
						case 6:
							Object jobno = linked.get(j);
							if (jobno != null && !"".equals(jobno)) {
								newUser.setJobno(linked.get(j).toString());
							}
							break;
						case 7:
							Object idno = linked.get(j);
							if (idno != null && !"".equals(idno)) {
								newUser.setIdno(linked.get(j).toString());
							}
							break;
						case 8:
							Object positionid = linked.get(j);
							if (positionid != null && !"".equals(positionid)) {
								newUser.setPositionid(Integer.parseInt(linked.get(j).toString()));
							}
							break;
						case 9:
							Object employtime = linked.get(j);
							if (employtime != null && !"".equals(employtime)) {
								newUser.setEmploytime(sdf.parse(linked.get(j).toString()));
							}
							break;
						default:
							Object poid = linked.get(j);
							if (poid != null && !"".equals(poid)) {
								String str = poid.toString();
								int poidValue = 1;
								if ("团员".equals(str)) {
									poidValue = 2;
								}
								if ("群众".equals(str)) {
									poidValue = 3;
								}
								newUser.setPoid(poidValue);
							}
							break;
						}
					}
					newUser.setPassword("QfRRxHuWnLpmQlrKjTeM2A==");
					newUser.setCreatetime(DateUtil.getcurrentDateTime());
					newUser.setCreateuserid(userId);
					newUser.setUpdateuserid(userId);
					newUser.setUpdatetime(DateUtil.getcurrentDateTime());
					list.add(newUser);
				} else {
					int index = i + 1;
					hwb.close();
					throw new CommonException("Excel表格第" + index + "行必填项信息未填写完整");
				}
			}
		}
		hwb.close();
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
	private static List<User> read2007Excel(File file, Integer userId) throws Exception {
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
			for (int j = 0; j <= 10; j++) {
				cell = row.getCell(j);
				if (cell == null) {
					linked.add(cell);
					continue;
				}
				DecimalFormat df = new DecimalFormat("0");
				SimpleDateFormat sdf = new SimpleDateFormat(DateUtil.YMDHMS);
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
//				if (value == null || "".equals(value)) {
//					continue;
//				}
				linked.add(value);
			}
			if (i > 1) {
				boolean pass = true;
				for(int t = 0;t < 3;t ++){
					String string = linked.get(t).toString();
					if (string == null || "".equals(string)) {
						pass = false;
					}
				}
				if (pass) {
					SimpleDateFormat sdf = new SimpleDateFormat(DateUtil.YYYY_MM_DD);
					User newUser = new User();
					for (int j = 0; j < linked.size(); j++) {
						switch (j) {
						case 0:
							newUser.setLoginname(linked.get(j).toString());
							break;
						case 1:
							newUser.setName(linked.get(j).toString());
							break;
						case 2:
							String string = linked.get(j).toString();
							int sex = 1;
							if ("女".equals(string)) {
								sex = 2;
							}
							newUser.setSex(sex);
							break;
						case 3:
							Object tel = linked.get(j);
							if (tel != null && !"".equals(tel)) {
								newUser.setTelephone(linked.get(j).toString());
							}
							break;
						case 4:
							Object email = linked.get(j);
							if (email != null && !"".equals(email)) {
								newUser.setEmail(linked.get(j).toString());
							}
							break;
						case 5:
							Object did = linked.get(j);
							if (did != null && !"".equals(did)) {
								newUser.setDid(Integer.parseInt(linked.get(j).toString()));
							}
							break;
						case 6:
							Object jobno = linked.get(j);
							if (jobno != null && !"".equals(jobno)) {
								newUser.setJobno(linked.get(j).toString());
							}
							break;
						case 7:
							Object idno = linked.get(j);
							if (idno != null && !"".equals(idno)) {
								newUser.setIdno(linked.get(j).toString());
							}
							break;
						case 8:
							Object positionid = linked.get(j);
							if (positionid != null && !"".equals(positionid)) {
								newUser.setPositionid(Integer.parseInt(linked.get(j).toString()));
							}
							break;
						case 9:
							Object employtime = linked.get(j);
							if (employtime != null && !"".equals(employtime)) {
								newUser.setEmploytime(sdf.parse(linked.get(j).toString()));
							}
							break;
						default:
							Object poid = linked.get(j);
							if (poid != null && !"".equals(poid)) {
								String str = poid.toString();
								int poidValue = 1;
								if ("团员".equals(str)) {
									poidValue = 2;
								}
								if ("群众".equals(str)) {
									poidValue = 3;
								}
								newUser.setPoid(poidValue);
							}
							break;
						}
					}
					newUser.setPassword("QfRRxHuWnLpmQlrKjTeM2A==");
					newUser.setCreatetime(DateUtil.getcurrentDateTime());
					newUser.setCreateuserid(userId);
					newUser.setUpdateuserid(userId);
					newUser.setUpdatetime(DateUtil.getcurrentDateTime());
					list.add(newUser);
				} else {
					int index = i + 1;
					xwb.close();
					throw new CommonException("Excel表格第" + index + "行必填项信息未填写完整");
				}
			}
		}
		xwb.close();
		if (counter < 3) {
			return null;
		} else {
			return list;
		}
	}
}