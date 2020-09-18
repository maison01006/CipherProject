

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class AES256 {
	private static volatile AES256 INSTANCE;
	
	final static String secretkey = "zxcvbnmasdfghjklqwertyuiop123456";
	static String IV ="";
	public static AES256 getInstance() {
		if(INSTANCE == null) {
			synchronized (AES256.class) {
				if(INSTANCE ==null)
					INSTANCE = new AES256();
			}
		}
		return INSTANCE;
	}
	private AES256() {
		IV = secretkey.substring(0,16);
	}
	public static String AES_Encode(String str) throws Exception{
		byte[] keyData = secretkey.getBytes();
		
		SecretKey secureKey = new SecretKeySpec(keyData,"AES");
		
		Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
		c.init(Cipher.ENCRYPT_MODE, secureKey,new IvParameterSpec(IV.getBytes()));
		
		byte[] encrypted = c.doFinal(str.getBytes("UTF-8"));
		String enStr = new String(Base64.encodeBase64(encrypted));
		
		return enStr;
	}
	public static String AES_Decode(String str) throws Exception{
		byte[] keyData = secretkey.getBytes();
		SecretKey secureKey = new SecretKeySpec(keyData, "AES");
		Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
		c.init(Cipher.DECRYPT_MODE, secureKey,new IvParameterSpec(IV.getBytes("UTF-8")));
		
		byte[] byteStr = Base64.decodeBase64(str.getBytes());
		
		return new String(c.doFinal(byteStr),"UTF-8");
	}
}
