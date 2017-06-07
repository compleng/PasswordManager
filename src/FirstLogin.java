
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.sql.*;
import javax.swing.JButton;
import javax.swing.JDialog;
public class FirstLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField,textField2;
	private JPasswordField passwordField;
	public JFrame frame;
	
		
			public void run() {
				try {
					 FirstLogin frame = new FirstLogin();
					frame.setVisible(true);
					frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		
	
	public FirstLogin() {
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(650, 300, 800, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel lblLtfenVeritabannaUlamak = new JLabel("Lütfen giriş yapmak için bir kullanıcı adı ve giriş parolası belirleyiniz ve e-posta hesabınızı giriniz");
		lblLtfenVeritabannaUlamak.setFont(new Font("Dialog", Font.BOLD, 13));
		lblLtfenVeritabannaUlamak.setBounds(52, 51, 750, 15);
		contentPane.add(lblLtfenVeritabannaUlamak);
		
		JLabel lblKullancAd = new JLabel("Kullanıcı Adı:");
		lblKullancAd.setBounds(196, 115, 119, 24);
		contentPane.add(lblKullancAd);
		
		textField = new JTextField();
		textField.setBounds(365, 118, 140, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblMasterkey = new JLabel("Giriş Parolası:");
		lblMasterkey.setBounds(196, 177, 119, 15);
		contentPane.add(lblMasterkey);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(365, 175, 140, 19);
		contentPane.add(passwordField);
		
		JLabel mail = new JLabel("E-posta hesabı:");
		mail.setBounds(196, 239, 119, 24);
		contentPane.add(mail);
		
		textField2 = new JTextField();
		textField2.setBounds(365, 239, 140, 19);
		contentPane.add(textField2);
		textField2.setColumns(10);
		
		JButton btnKaydet = new JButton("Kaydet");
		btnKaydet.setBounds(378, 290, 117, 25);
		contentPane.add(btnKaydet);
		btnKaydet.setActionCommand("Open");
		btnKaydet.addActionListener(new ActionListener() {
			
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					DbConnection db = new DbConnection();
					Statement mystmt=db.baglan.createStatement();
					
				
					
					if(!textField.getText().isEmpty() && !passwordField.getText().isEmpty() && !textField2.getText().isEmpty()){
					String sql="INSERT INTO masterkeys values('"+textField.getText()+"','"+passwordField.getText()+"','"+textField2.getText()+"')";
					
					mystmt.executeUpdate(sql);
			
					System.out.println("Insert complete");
					
					ResultSet myrs=mystmt.executeQuery("select * from masterkeys");
					while(myrs.next())
					{
						System.out.println(myrs.getString("user")+","+myrs.getString("pass")+","+myrs.getString("mail"));
					}
					
					
					String cmd = e.getActionCommand();

			        if(cmd.equals("Open"))
			        {
			            dispose();
			            AskLogin a = new AskLogin();
			    		a.run();
			        }
					}
					else
					{
						JDialog dialog = new JDialog(new Frame(), "");
						dialog.setBounds(650,350,650,50);
						dialog.setVisible(true);
						dialog.setAlwaysOnTop(true);
						String data="Lütfen geçerli bir kullanıcı adı ve parola belirleyiniz ve e-posta hesabı giriniz ";
						JTextField tf = new JTextField(data);
						tf.setHorizontalAlignment(JTextField.CENTER);
						Font font1 = new Font("SansSerif", Font.BOLD, 16);
						tf.setFont(font1);
						tf.setEditable(false);
						dialog.add(tf);
						dialog.setLocation(750, 300);
						dialog.setResizable(false);
						

					}
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
		});
		
	
	}
	
	
}
