

public class Main {

	public static void main(String[] args) {
		String name = "¿À½ÂÁø";
		AES256 a = AES256.getInstance();
		
		String enName;
		try {
			enName = a.AES_Encode(name);
			String desName = a.AES_Decode(enName);
			System.out.println(enName+" : "+desName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}

}
