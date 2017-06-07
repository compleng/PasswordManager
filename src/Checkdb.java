
import java.sql.ResultSet;
import java.sql.Statement;

public class Checkdb {
	public void run()
	{
		
		DbConnection db = new DbConnection();
		
		try {
			
			Statement mystmt=db.baglan.createStatement();
			
			
			ResultSet master=mystmt.executeQuery("select * from masterkeys");
			
			if(!master.next())
			{
				FirstLogin f = new FirstLogin();
				f.run();
			}
			else
			{
				AskLogin a = new AskLogin();
	    		a.run();
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
