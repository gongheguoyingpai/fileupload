package com.ustb.zhang.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;

public class CryptUtil {
    
	private static final char hexDigits[] = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
	
	public static byte[]  CommonEncrypt(String algorithmName, String str) throws NoSuchAlgorithmException {
		byte[] bStr = str.getBytes();
		MessageDigest algo = MessageDigest.getInstance(algorithmName);
		algo.update(bStr);
		return algo.digest(); 
	}
	
	private static String byteToString(byte[] encryptBytes) {
		int len = encryptBytes.length;
		StringBuilder buf = new StringBuilder(len * 2);
		for (int j = 0; j < len; j++) {          
			buf.append(hexDigits[(encryptBytes[j] >> 4) & 0x0f]);
		    buf.append(hexDigits[encryptBytes[j] & 0x0f]);
		 }
		 return buf.toString();
	}
	
	public static String sha1Encrypt(String str) throws NoSuchAlgorithmException {
		if (str == null) {
			return null;
		}
		byte[] sha1 = CommonEncrypt(ConstantUtil.Encrypt.SHA1, str);
		return byteToString(sha1);
	}
	
	public static String md5Encrypt(String str) throws NoSuchAlgorithmException {
		if (str == null) {
			return null;
		}
		byte[] md5 = CommonEncrypt(ConstantUtil.Encrypt.MD5, str);
		return byteToString(md5);
	}
	
	public static String md5WithBase64(String str) throws NoSuchAlgorithmException {
		return (new BASE64Encoder()).encode(CommonEncrypt(ConstantUtil.Encrypt.MD5, str));
	}
}
