package com.jemmyee.framework.utils;

import java.security.MessageDigest;


/**
 * @Description:密码加密解密处理
 * @author:jemmyee@gmail.com
 * @date:2010-10-20
 * @version:v1.0
 */
public class EncryptUtils {


	/**
	* @Description:将传入的字符串加密成32位MD5密文
	* @author:jemmyee@gmail.com
	* @date:2010-10-20
	*/
	public final static String getMD5String(String chars){
		char hexDigits[] = {
		'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
		'e', 'f'};
		try {
		byte[] strTemp = chars.getBytes();
		MessageDigest mdTemp = MessageDigest.getInstance("MD5");
		mdTemp.update(strTemp);
		byte[] md = mdTemp.digest();
		int j = md.length;
		char str[] = new char[j * 2];
		int k = 0;
		for (int i = 0; i < j; i++) {
		byte byte0 = md[i];
		str[k++] = hexDigits[byte0 >>>4 & 0xf];
		str[k++] = hexDigits[byte0 & 0xf];
		}
		return new String(str);
		}
		catch (Exception e){
		return null;
		}

	 }
	
	 public static void main(String[] args) {
		System.out.println(getMD5String("111111"));
	}

}
