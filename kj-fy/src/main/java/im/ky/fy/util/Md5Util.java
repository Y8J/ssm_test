package im.ky.fy.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密
 * @author huangshipiao
 * @time   2015-8-25 上午11:15:17
 */
public class Md5Util {
	 private final static char[] hexDigits = { '0', '1', '2', '3', '4', '5',  
         '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };  
	 private static String bytesToHex(byte[] bytes) {  
	     StringBuffer sb = new StringBuffer();  
	     int t;  
	     for (int i = 0; i < 16; i++) {  
	         t = bytes[i];  
	         if (t < 0)  
	             t += 256;  
	         sb.append(hexDigits[(t >>> 4)]);  
	         sb.append(hexDigits[(t % 16)]);  
	     }  
	     return sb.toString();  
	 }  
	 public static String md5(String input) throws Exception {  
	     return code(input, 32,"utf-8");  
	 }  
	 public static String code(String input, int bit,String encoding) throws Exception {  
	     try {  
	         MessageDigest md = MessageDigest.getInstance(System.getProperty(  
	                 "MD5.algorithm", "MD5"));  
	         if (bit == 16)  
	             return bytesToHex(md.digest(input.getBytes(encoding)))  
	                     .substring(8, 24);  
	         return bytesToHex(md.digest(input.getBytes(encoding)));  
	     } catch (NoSuchAlgorithmException e) {  
	         e.printStackTrace();  
	         throw new Exception("Could not found MD5 algorithm.", e);  
	     }  
	 }  
	 public static String md5_3(String b) throws Exception{  
	     MessageDigest md = MessageDigest.getInstance(System.getProperty(  
	             "MD5.algorithm", "MD5"));  
	     byte[] a = md.digest(b.getBytes());  
	     a = md.digest(a);  
	     a = md.digest(a);  
	       
	     return bytesToHex(a);  
	 }  
}
