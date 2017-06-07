import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;

public class RandomKeyGenerator {
public  String run() {
		
		char[] chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < 20; i++) {
		    char c = chars[random.nextInt(chars.length)];
		    sb.append(c);
		}
		String output = sb.toString();
		
		String plaintext = output;
		MessageDigest m = null;
		try {
			m = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			
			e.printStackTrace();
		}
		m.reset();
		m.update(plaintext.getBytes());
		byte[] digest = m.digest();
		BigInteger bigInt = new BigInteger(1,digest);
		String hashtext = bigInt.toString(16);

		while(hashtext.length() < 32 ){
		  hashtext = "0"+hashtext;
		}
		
		String st2 = hashtext.substring(0, 20);
	    
	    DbConnection db = new DbConnection();
	    try {
	    	Statement mystmt=db.baglan.createStatement();
	    	String sql="select * from passwd where pass='"+st2+"'";
			ResultSet rs=mystmt.executeQuery(sql);
			if(rs.next())
			{
				System.out.println("There is password collision");
				System.exit(1);
				
				run();
			}
			
			
		} catch (Exception e) {
			
		}
		return st2;
	}
}
