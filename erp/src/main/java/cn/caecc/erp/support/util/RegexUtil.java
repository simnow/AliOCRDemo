/**
 * 
 */
package cn.caecc.erp.support.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author weizhen
 *
 */
public class RegexUtil {

	/**
	 * 
	 */
	public RegexUtil() {
		// TODO Auto-generated constructor stub

	}

	public static boolean checkPassword(String password) {
		boolean ret = false;
		Pattern pattern = Pattern.compile(
				"^(?:(?=.*[0-9].*)(?=.*[A-Za-z].*)(?=.*[,\\.#%'\\+\\*\\-:;^_`].*))[,\\.#%'\\+\\*\\-:;^_`0-9A-Za-z]{8,20}$");
		Matcher matcher = pattern.matcher(password);
		if (matcher.matches()) {
			ret = true;
		}
		return ret;
	}
	
	
	public static boolean checkEmail(String email) {
		boolean ret = false;
		Pattern pattern = Pattern.compile(
				"^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$");
		Matcher matcher = pattern.matcher(email);
		if (matcher.matches()) {
			ret = true;
		}
		return ret;
	}
	
	
	public static boolean checkTelphone(String telphone) {
		boolean ret = false;
		Pattern pattern = Pattern.compile(
				"^1\\d{10}$");
		Matcher matcher = pattern.matcher(telphone);
		if (matcher.matches()) {
			ret = true;
		}
		return ret;
	}
	
	
	public static boolean checkIdNo(String idNo) {
		boolean ret = false;
		Pattern pattern = Pattern.compile(
				"^((\\d{18})|([0-9x]{18})|([0-9X]{18}))$");
		Matcher matcher = pattern.matcher(idNo);
		if (matcher.matches()) {
			ret = true;
		}
		return ret;
	}
	
	public static boolean checkLandline(String landline) {
		boolean ret = false;
		Pattern pattern = Pattern.compile(
				"^(\\d{3,4}|\\d{3,4}-)?\\d{7,8}$");
		Matcher matcher = pattern.matcher(landline);
		if (matcher.matches()) {
			ret = true;
		}
		return ret;
	}
}
