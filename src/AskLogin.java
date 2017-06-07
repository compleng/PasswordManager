
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JDialog;

public class AskLogin extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	public JFrame frame;

	/**
	 * Launch the application.
	 */

			public void run() {
				try {
					AskLogin frame = new AskLogin();
					frame.setResizable(false);
					frame.setVisible(true);
					
				
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				
			}
		
		
	

	/**
	 * Create the frame.
	 */
	public AskLogin() {
		DbConnection db = new DbConnection();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(700, 300, 500, 310);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		Icons ic=new Icons();
		
		JLabel lblLtfenKullancAd = new JLabel("Lütfen kullanıcı adı  ve giriş parolanızı giriniz");
		lblLtfenKullancAd.setBounds(105, 61, 370, 15);
		contentPane.add(lblLtfenKullancAd);
		
		JLabel label = new JLabel("Kullanıcı Adı:");
		label.setBounds(105, 120, 119, 24);
		contentPane.add(label);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(264, 123, 114, 19);
		contentPane.add(textField);
		
		JLabel label_1 = new JLabel("Giriş Parolası:");
		label_1.setBounds(105, 185, 119, 15);
		contentPane.add(label_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(264, 183, 114, 19);
		contentPane.add(passwordField);
		
		JButton btnGiriYap = new JButton(ic.okIcon);
		btnGiriYap.setBounds(220, 240, 70, 25);
		btnGiriYap.setActionCommand("Open");
		
		JLabel passforget = new JLabel("Parolamı Unuttum");
		passforget.setBounds(190,270,150,25);
		contentPane.add(passforget);
		
		passforget.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e)
			{
				SendEmail se = new SendEmail();
				try {
                    Statement mystmt=db.baglan.createStatement();
					
					ResultSet myrs=mystmt.executeQuery("select * from masterkeys");
					while(myrs.next())
					{
						String dbuser=myrs.getString("user");
						String dbpass=myrs.getString("pass");
						String dbmail=myrs.getString("mail");
					    se.passReminder(dbuser,dbpass,dbmail);}
					
					JDialog dialog = new JDialog(new Frame(), "");
					dialog.setBounds(650,350,650,50);
					dialog.setVisible(true);
					dialog.setAlwaysOnTop(true);
					String data="Lütfen sisteme kaydettiğiniz e-posta hesabınızı kontrol ediniz ";
					JTextField tf = new JTextField(data);
					tf.setHorizontalAlignment(JTextField.CENTER);
					Font font1 = new Font("SansSerif", Font.BOLD, 16);
					tf.setFont(font1);
					tf.setEditable(false);
					dialog.add(tf);
					dialog.setLocation(750, 300);
					dialog.setResizable(false);
					
				} catch (Exception e1) {
					
					e1.printStackTrace();
					JDialog dialog = new JDialog(new Frame(), "");
					dialog.setBounds(650,350,650,50);
					dialog.setVisible(true);
					dialog.setAlwaysOnTop(true);
					String data="Sistemdeki e-posta adresi geçerli bir adres değil ";
					JTextField tf = new JTextField(data);
					tf.setHorizontalAlignment(JTextField.CENTER);
					Font font1 = new Font("SansSerif", Font.BOLD, 16);
					tf.setFont(font1);
					tf.setEditable(false);
					dialog.add(tf);
					dialog.setLocation(750, 300);
					dialog.setResizable(false);
				}
			}
		});
		
		
		
		btnGiriYap.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					
					Statement mystmt=db.baglan.createStatement();
					
					ResultSet myrs=mystmt.executeQuery("select * from masterkeys");
					while(myrs.next())
					{
						String dbuser=myrs.getString("user");
						String dbpass=myrs.getString("pass");
						System.out.println(myrs.getString("user")+","+myrs.getString("pass"));
						String scuser=textField.getText();
						@SuppressWarnings("deprecation")
						
						String scpass=passwordField.getText();
						
						if(dbuser.equals(scuser)&&dbpass.equals(scpass))
						{
							PassTable.frame.setVisible(true);
							String cmd = e.getActionCommand();

					        if(cmd.equals("Open"))
					        {
					            dispose();
					       
					            
					        }
					
						}
						else
						{	
							Wrongpassdialog w = new Wrongpassdialog();
							w.run();
							String cmd = e.getActionCommand();

					        if(cmd.equals("Open"))
					        {
					            dispose();
					            
					       
					            
					        }
					 
						}
						
						
					}
					
					
					
				}
				catch (Exception e1) {
					e1.printStackTrace();
				}
				
				
				
			}
		});
		
	
		
		contentPane.add(btnGiriYap);
		
		
		
				
		
	}

}

