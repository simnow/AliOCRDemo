package com.partymasses.support.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期工具类
 * @author Administrator
 *
 */
public class DateUtil {

	public static final String YYYY_MM_DD = "yyyy-MM-dd";
	public static final String YYYYMMDD = "yyyyMMdd";
	public static final String HHMMSS = "HH:mm:ss";
	public static final String YMDHMS = "yyyy-MM-dd HH:mm:ss";
	
	/**
	 * 描述：获取当前日期 
	 * @return 返回当前日期 日期格式  yyyy-MM-dd HH:mm:ss
	 */
	public static Date getcurrentDateTime() {
		SimpleDateFormat sf = new SimpleDateFormat(YMDHMS);
		String currentDate = sf.format(new Date());
		try {
			Date birthDate = sf.parse(currentDate);
			return birthDate;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 描述：获取当前日期 
	 * @return 返回当前日期 日期格式  yyyy-MM-dd
	 */
	public static String currentDate() {
		return currentDate(YYYY_MM_DD);
	}
	public static String hourDate() {
		return currentDate(HHMMSS);
	}
	/**
	 * 描述：获取当前日期
	 * @param dateFormat 日期格式
	 * @return 返回当前日期
	 */
	public static String currentDate(String dateFormat) {
		try {
			SimpleDateFormat sf = new SimpleDateFormat(dateFormat);
			String currentDate = sf.format(new Date());
			return currentDate;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 转换
	 * @param date
	 * @param dateFormat
	 * @return
	 */
	public static Date convertStringToDate(String str, String dateFormat) {
		if (str != null && !"".equals(str)) {
			SimpleDateFormat sf = new SimpleDateFormat(dateFormat);
			try {
				Date birthDate = sf.parse(str);
				return birthDate;
			} catch (ParseException e) {
				e.printStackTrace();
				return null;
			}
		}
		return null;
	}
	public  static  Boolean   compareDate(Date startDate,Date endDate){
			Date now=new Date();
			if(now.after(startDate)&&now.before(endDate)){
				
				return  true ;
			}
			return false;
		
	}
	
}