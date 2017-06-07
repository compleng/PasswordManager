
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Frame;

public class AddData extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField Tfbaslik;
	private JTextField Tfkullanici;
	private JTextField Tfparola;
	private JTextField Tftekrar;
	private JTextArea Tfnotlar;
	JLabel derece;
	

	/**
	 * Launch the application.
	 */
	public void run() {
		AddData dialog = new AddData();
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setResizable(false);
		dialog.setVisible(true);
	}

	/**
	 * Create the dialog.
	 */
	public AddData() {
		setBounds(700, 400, 450, 400);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		RandomKeyGenerator r = new RandomKeyGenerator();
		String txt=r.run();
		Icons ic=new Icons();
		
		JLabel lblBalk = new JLabel("Başlık:");
		lblBalk.setBounds(39, 20, 70, 15);
		contentPanel.add(lblBalk);
		
		JLabel lblKullancAd = new JLabel("Kullanıcı Adı:");
		lblKullancAd.setBounds(39, 50, 99, 15);
		contentPanel.add(lblKullancAd);
		
		JLabel lblNewLabel = new JLabel("Parola:");
		lblNewLabel.setBounds(39, 80, 70, 15);
		contentPanel.add(lblNewLabel);
		
		JLabel lblTekrar = new JLabel("Tekrar:");
		lblTekrar.setBounds(39, 110, 70, 15);
		contentPanel.add(lblTekrar);
		
		JLabel lblNotlar = new JLabel("Notlar:");
		lblNotlar.setBounds(39, 230, 70, 15);
		contentPanel.add(lblNotlar);
		
		JLabel lblkalite = new JLabel("Parola Kalitesi:");
		lblkalite.setBounds(39, 150, 120, 15);
		contentPanel.add(lblkalite);
		
		Tfbaslik = new JTextField();
		Tfbaslik.setBounds(216, 20, 180, 19);
		contentPanel.add(Tfbaslik);
		Tfbaslik.setColumns(10);
		
		Tfkullanici = new JTextField();
		Tfkullanici.setColumns(10);
		Tfkullanici.setBounds(216, 50, 180, 19);
		contentPanel.add(Tfkullanici);
		
		
		Tfparola = new JTextField(txt);
		Tfparola.setColumns(10);
		Tfparola.setBounds(216, 80, 180, 19);
		contentPanel.add(Tfparola);
		
		derece = new JLabel("İyi");
		derece.setFont(new Font("Dialog", Font.BOLD, 16));
		derece.setBounds(216, 150, 180, 26);
		derece.setBackground(Color.GREEN);
		derece.setHorizontalAlignment(SwingConstants.CENTER);
		derece.setOpaque(true);
		contentPanel.add(derece);
		
		JLabel lblControl = new JLabel("");
		lblControl.setBounds(140, 195, 200, 15);
		contentPanel.add(lblControl);
		
		Tftekrar = new JTextField(txt);
		Tftekrar.setColumns(10);
		Tftekrar.setBounds(216, 110, 180, 19);
		contentPanel.add(Tftekrar);
		
		Tfparola.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				
				if(Tfparola.getText().length()<=8)
				{
					derece.setBackground(Color.RED);
					derece.setText("Kötü");
				}
				else if(Tfparola.getText().length()>8 && Tfparola.getText().length()<=15)
				{
					derece.setBackground(Color.YELLOW);
					derece.setText("Orta");
				}
				else if(Tfparola.getText().length()>15)
				{
					derece.setBackground(Color.GREEN);
					derece.setText("İyi");
				}
				
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				
				if(Tfparola.getText().length()<=8)
				{
					derece.setBackground(Color.RED);
					derece.setText("Kötü");
				}
				else if(Tfparola.getText().length()>8 && Tfparola.getText().length()<=15)
				{
					derece.setBackground(Color.YELLOW);
					derece.setText("Orta");
				}
				else if(Tfparola.getText().length()>15)
				{
					derece.setBackground(Color.GREEN);
					derece.setText("İyi");
				}
				
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
			
				if(Tfparola.getText().length()<=8)
				{
					derece.setBackground(Color.RED);
					derece.setText("Kötü");
				}
				else if(Tfparola.getText().length()>8 && Tfparola.getText().length()<=15)
				{
					derece.setBackground(Color.YELLOW);
					derece.setText("Orta");
				}
				else if(Tfparola.getText().length()>15)
				{
					derece.setBackground(Color.GREEN);
					derece.setText("İyi");
				}
				
			}
			
		});
		Tftekrar.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				if(!Tfparola.getText().equals(Tftekrar.getText()))
				{
					lblControl.setText("Parolalar uyuşmuyor!");
				}
				else
					lblControl.setText("");
				
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				if(!Tfparola.getText().equals(Tftekrar.getText()))
				{
					lblControl.setText("Parolalar uyuşmuyor!");
				}
				else
					lblControl.setText("");
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				if(!Tfparola.getText().equals(Tftekrar.getText()))
				{
					lblControl.setText("Parolalar uyuşmuyor!");
				}
				else
					lblControl.setText("");
			}
		});
		
		
			 
			
		
		
		
		Tfnotlar = new JTextArea();
		Tfnotlar.setColumns(10);
		Tfnotlar.setBounds(216, 230, 180, 80);
		
		
		contentPanel.add(Tfnotlar);
		
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton(ic.okIcon);
				okButton.setActionCommand("OK");
				okButton.addActionListener(new ActionListener() {
					
					@SuppressWarnings("static-access")
					@Override
					public void actionPerformed(ActionEvent e) {
						if(Tfparola.getText().equals(Tftekrar.getText())){
						PassTable t = new PassTable();
						String sTitle="",sUser="",sPass="",sNote="";
						sTitle=Tfbaslik.getText();
						sUser=Tfkullanici.getText();
						sPass=Tfparola.getText();
						sNote=Tfnotlar.getText();
						if(sTitle.isEmpty())
						{
							sTitle="----------";
						}
						if(sUser.isEmpty())
						{
							sUser="----------";
						}
						if(sPass.isEmpty())
						{
							sPass="----------";
						}
						if(sNote.isEmpty())
						{
							sNote="----------";
						}
						Object[] ekle={sTitle,sUser,sPass,sNote};
						t.dTabloModeli.addRow(ekle);
						try {
							DbConnection db = new DbConnection();
							Statement mystmt=db.baglan.createStatement();
							
							
							String sql="insert into passwd"+"(title,user,pass,note)"+"values ('"+sTitle+"','"+sUser+"','"+sPass+"','"+sNote+"')";
							mystmt.executeUpdate(sql);
							System.out.println("Insert completed");
						
							
						} catch (Exception e1) {
							e1.printStackTrace();
						}}
						
						else{
							System.out.println("parolalar uyuşmadığı için kayıt yapılamadı");
							JDialog dialog2 = new JDialog(new Frame());
							dialog2.setSize(500,50);
							dialog2.setResizable(false);
							String data="Parolalar uyuşmadığı için kayıt yapılamadı";
							JTextField tf = new JTextField(data);
							tf.setHorizontalAlignment(JTextField.CENTER);
							Font font1 = new Font("SansSerif", Font.BOLD, 16);
							tf.setFont(font1);
							tf.setEditable(false);
							dialog2.add(tf);
							dialog2.setLocation(800, 500);
							dialog2.setAlwaysOnTop(true);
							dialog2.setVisible(true);}

						String cmd = e.getActionCommand();

				        if(cmd.equals("OK"))
				        {
				            dispose();
				            
				        }
						
					}
				});
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton(ic.cancelIcon);
				cancelButton.setActionCommand("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						String cmd = e.getActionCommand();

				        if(cmd.equals("Cancel"))
				        {
				            dispose();
				            
				        }
						
					}
				});
				buttonPane.add(cancelButton);
			}
		}
	}
}
