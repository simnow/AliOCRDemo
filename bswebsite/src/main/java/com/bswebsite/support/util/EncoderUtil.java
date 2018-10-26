package com.bswebsite.support.util;


import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Map;

public class EncoderUtil {
	private static final String ALGORITHM = "MD5";

	private static final char[] HEX_DIGITS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
			'e', 'f' };

	/**
	 * 字符串签名
	 * 
	 * @param algorithm
	 *            签名方式，如MD5,SHA1
	 * @param str
	 *            字符串参数
	 * @return String 签名串
	 */
	public static String encode(String algorithm, String str) {
		if (str == null) {
			return null;
		}
		try {
			MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
			messageDigest.update(str.getBytes());
			return getFormattedText(messageDigest.digest());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 对字符串进行MD5签名
	 * 
	 * @param str
	 * @return String
	 */
	public static String encodeByMD5(String str) {
		if (str == null) {
			return null;
		}
		try {
			MessageDigest messageDigest = MessageDigest.getInstance(ALGORITHM);
			messageDigest.update(str.getBytes("UTF-8"));
			return getFormattedText(messageDigest.digest());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 对字符串进行MD5签名,截取8到24
	 * 
	 * @param str
	 * @return String
	 */
	public static String encodeByMD5Substring(String str) {
		if (str == null) {
			return null;
		}
		try {
			MessageDigest messageDigest = MessageDigest.getInstance(ALGORITHM);
			messageDigest.update(str.getBytes());
			return getFormattedText(messageDigest.digest()).substring(8, 24).toUpperCase();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 对字符串进行SHA1签名
	 * 
	 * @param str
	 * @return String
	 */
	public static String encodeBySHA1(String str) {
		if (str == null) {
			return null;
		}
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
			messageDigest.update(str.getBytes());
			return getFormattedText(messageDigest.digest());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Takes the raw bytes from the digest and formats them correct.
	 * 
	 * @param bytes
	 *            the raw bytes from the digest.
	 * @return the formatted bytes.
	 */
	private static String getFormattedText(byte[] bytes) {
		int len = bytes.length;
		StringBuilder buf = new StringBuilder(len * 2);
		// 把密文转换成十六进制的字符串形式
		for (int j = 0; j < len; j++) {
			buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
			buf.append(HEX_DIGITS[bytes[j] & 0x0f]);
		}
		return buf.toString();
	}

	/**
	 * 
	 * @description Md5加密32位
	 * @param plainText
	 * @return String
	 */
	public static String Md5_32(String plainText) {

		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			try {
				md.update(plainText.getBytes("utf-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			byte b[] = md.digest();
			int i;
			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			return buf.toString();// 32位的加密
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 对参数加密
	 * @param signMap
	 * @param privateKey
	 * @return
	 */
	public static String createSignBykey(Map<String, String> signMap, String privateKey) {
		String sign_temp = "";
		if (signMap == null || signMap.size() == 0) {
			return "";
		}
		if (privateKey == null || privateKey.equals(""))
			return "";
		int i = 0;
		String[] keys = new String[signMap.size()];
		for (String key : signMap.keySet()) {
			keys[i] = key;
			i++;
		}
		// 参数排序A-Z
		Arrays.sort(keys);
		for (String key : keys) {
			// 空值不参与签名
			if (signMap.get(key) == null)
				continue;
			// 组装待签名字符串
			sign_temp += key + "=" + signMap.get(key) + "&";
		}
		// 生成sign=URL+私钥 转大写
		sign_temp = sign_temp + "key=" + privateKey;
		return EncoderUtil.encodeBySHA1(sign_temp).toUpperCase();
	}

	public static void main(String[] args) {
		System.err.println(Md5_32("abcd").toUpperCase());
	}
}