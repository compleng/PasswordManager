
import java.sql.*;
import java.io.*;
 
public class ExportPass {
 
	 private static String ip="localhost";
	 private static String database="password_manager";
	 private static String user="root";
	 private static String pass="123456";
	 public void createTxt() {
 
    try {
    	        DbConnection db = new DbConnection();
                Statement st = db.baglan.createStatement();
                  ResultSet res = st.executeQuery("SELECT * FROM  passwd");
                  
                  File f=new File("password_manager.txt");
                    FileOutputStream fp=new FileOutputStream(f);
                    String header="Format: "+ "Başlık"+", "+"Kullanıcı Adı"+ ", "+"Parola"+", " +"Notlar"+"\r\n\n";
                    fp.write(header.getBytes());
                  while (res.next()) {
                   
                   String data = res.getString("title")+", "+res.getString("user") + ", " +res.getString("pass")+ ", " + res.getString("note");
                    fp.write(data.getBytes());
                   String newline="\r\n";
                    fp.write(newline.getBytes()); 
                   // System.out.println(data);
                  }
                  fp.flush();
                    fp.close();
                     
                      db.baglan.close();
    } catch (FileNotFoundException e) {
         
        e.printStackTrace();
    } catch (SQLException e) {
         
        e.printStackTrace();
    } catch (IOException e) {
     
        e.printStackTrace();
    }
 
         
    }
   
    public static void exportDb()
    {
   
    String ss="password_manager.sql";
   
    String dumpCommand = "mysqldump " + database + " -h " + ip + " -u " + user +" -p" + pass;
    Runtime rt = Runtime.getRuntime();
    File test=new File(ss);
    PrintStream ps;
    try{
    Process child = rt.exec(dumpCommand);
    ps=new PrintStream(test);
    InputStream in = child.getInputStream();
    int ch;
    while ((ch = in.read()) != -1) {
    ps.write(ch);
    //System.out.write(ch); //to view it by console
    }

    InputStream err = child.getErrorStream();
    while ((ch = err.read()) != -1) {
    System.out.write(ch);
    }
    }catch(Exception exc) {
    exc.printStackTrace();
    }
    }

    
    
 
}