import java.util.Properties;
import java.io.IOException;
import java.util.Date;

import javax.mail.*;
import javax.mail.internet.*;
import com.sun.mail.smtp.*;


public class SendEmail {

    public  void passReminder(String user,String pass,String to) throws Exception {
    
        Properties props = System.getProperties();
        props.put("mail.smtps.host","smtp.gmail.com");
        props.put("mail.smtps.auth","true");
        Session session = Session.getInstance(props, null);
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress("ilaydagundogdu95@gmail.com"));;
        msg.setRecipients(Message.RecipientType.TO,
        InternetAddress.parse(to, false));
        msg.setSubject("Parola Hatırlatıcı ");
        String txt="Kullanıcı adınız: "+ user+"\n"+"Parolanız: "+pass;
        msg.setText(txt);
        msg.setSentDate(new Date());
        SMTPTransport t =
            (SMTPTransport)session.getTransport("smtps");
        t.connect("smtp.gmail.com", "ilaydagundogdu95@gmail.com", "********");
        t.sendMessage(msg, msg.getAllRecipients());
        System.out.println("Response: " + t.getLastServerResponse());
        t.close();
    }
    
    public void exportDb(String toAddress) throws Exception
    {	
    	ExportPass exp= new ExportPass();
    	exp.createTxt();
    	ExportPass.exportDb();
        
    	 String host = "smtp.gmail.com";
         String port = "587";
         String userName = "ilaydagundogdu95@gmail.com";
         String password = "********";
  
       
        
         String subject = "Veri Tabanı Yedeği";
         String message = "Yedekleriniz ektedir.";
  
         
         String[] attachFiles = new String[2];
         attachFiles[0] = "password_manager.sql";
         attachFiles[1] = "password_manager.txt";
         
         try {
			
        	 Properties properties = new Properties();
             properties.put("mail.smtp.host", host);
             properties.put("mail.smtp.port", port);
             properties.put("mail.smtp.auth", "true");
             properties.put("mail.smtp.starttls.enable", "true");
             properties.put("mail.user", userName);
             properties.put("mail.password", password);
      
             
             Authenticator auth = new Authenticator() {
                 public PasswordAuthentication getPasswordAuthentication() {
                     return new PasswordAuthentication(userName, password);
                 }
             };
             Session session = Session.getInstance(properties, auth);
      
             Message msg = new MimeMessage(session);
      
             msg.setFrom(new InternetAddress(userName));
             InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
             msg.setRecipients(Message.RecipientType.TO, toAddresses);
             msg.setSubject(subject);
             msg.setSentDate(new Date());
      
            
             MimeBodyPart messageBodyPart = new MimeBodyPart();
             messageBodyPart.setContent(message, "text/html");
      
            
             Multipart multipart = new MimeMultipart();
             multipart.addBodyPart(messageBodyPart);
      
           
             if (attachFiles != null && attachFiles.length > 0) {
                 for (String filePath : attachFiles) {
                     MimeBodyPart attachPart = new MimeBodyPart();
      
                     try {
                         attachPart.attachFile(filePath);
                     } catch (IOException ex) {
                         ex.printStackTrace();
                     }
      
                     multipart.addBodyPart(attachPart);
                 }
             }
      
           
             msg.setContent(multipart);
      
            
             Transport.send(msg);
             System.out.println("Email sent.");
		} catch (Exception e) {
			 System.out.println("Could not send email.");
	            e.printStackTrace();
		}
      
     
    }
}
