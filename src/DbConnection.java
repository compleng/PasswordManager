
import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {

	Connection baglan=null;
	
	public DbConnection()
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		
		    baglan=DriverManager.getConnection("jdbc:mysql://localhost:3306/password_manager?autoReconnect=true&useSSL=false","root","123456");
			
		} catch (Exception e) {
		
		}
	}
}
