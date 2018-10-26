package com.partymasses.support.util;

import java.security.SecureRandom;
import org.apache.axis.encoding.Base64;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class AesUtil {

	private final static Logger logger = LoggerFactory.getLogger(AesUtil.class);
	public static String AES = "AES";

	public static String CIPHER_ALGORITHM = "AES";

	public static String KEY = "union";

	/**
	 * 解密
	 * 
	 * @param content
	 *            待解密内容
	 * @param password
	 *            secureKey
	 * @return
	 */
	public static String decrypt(String content) {
		try {
			if (StringUtils.isEmpty(content) || StringUtils.isEmpty(KEY)) {
				return null;
			}

			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
			secureRandom.setSeed(KEY.getBytes());
			kgen.init(128, secureRandom);
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance("AES");// 创建密码器
			cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
			byte[] base64Dec = Base64.decode(content);
			byte[] result = cipher.doFinal(base64Dec);
			return new String(result);
		} catch (Exception e) {
			logger.info("解密错误,错误信息是:{}", e);
		}
		return null;
	}

	public static String encrypt(String content) {
		try {
			if (StringUtils.isEmpty(content)) {
				return null;
			}

			KeyGenerator kgen = KeyGenerator.getInstance(AES);
			SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
			secureRandom.setSeed(KEY.getBytes());
			kgen.init(128, secureRandom);
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, AES);
			Cipher cipher = Cipher.getInstance(AES);// 创建密码器
			byte[] byteContent = content.getBytes("utf-8");
			cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
			byte[] result = cipher.doFinal(byteContent);
			return encodeBASE64(result); // 加密
		} catch (Exception e) {
			logger.error("加密错误.", e);
		}
		return null;
	}

	public static String encodeBASE64(byte[] content) throws Exception {
		if (content == null || content.length == 0)
			return null;
		try {
			return Base64.encode(content);
		} catch (Exception e) {
			logger.info("Base64 encode error.", e);
			return null;
		}
	}
	
	 public static void main(String[] args)throws Exception{  
	        String message = "123456";  //QfRRxHuWnLpmQlrKjTeM2A==
	        String entryptedMsg = encrypt(message); 
	        System.out.println(entryptedMsg);
//	        System.out.println("encrypted message is below :");  
//	        System.out.println(entryptedMsg);  
	          
	        String decryptedMsg = decrypt(entryptedMsg);  
	        System.out.println("decrypted message is below :");  
         System.out.println(decryptedMsg);  
	    }  
}
