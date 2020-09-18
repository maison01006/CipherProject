package algorithm;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;



public class SHA256{
	
	public String SHA_Encode(String str){
		MessageDigest md=null;
		try {
			md = MessageDigest.getInstance("SHA-256");
			md.update(str.getBytes());
			byte byteData[] = md.digest();
			StringBuffer sb = new StringBuffer();
			for(int i=0; i<byteData.length;i++) {
				sb.append(Integer.toString((byteData[i]&0xff)+0x100,16).substring(1));
			}
			str=sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			str=null;
		}
		return str;
	}
}