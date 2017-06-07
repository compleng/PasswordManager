import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class PassTable extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static JFrame frame = new JFrame();
	static JLabel Lbaslik,Lkullanici,Lparola,Lnotlar;
	static JPanel pane= new JPanel();
	static JTextField Tfbaslik,Tfkullanici,Tfparola,Tfnotlar;
	static Object[][] veriTabaniSonucu;
	static Object[] colon={"Başlık","Kullanıcı Adı","Parola","Notlar"};
	static DefaultTableModel dTabloModeli= new DefaultTableModel(veriTabaniSonucu,colon)
			{
		/**
				 * 
				 */
				private static final long serialVersionUID = 1L;
		@SuppressWarnings("unused")
		public Class<?> getColonClass(int colon)
		{
			Class<?> returnValue;
			if(colon>=0 && (colon<getColumnCount()))
			{
				returnValue=getValueAt(0, colon).getClass();
			}
			else
			{
				returnValue=Object.class;
			}
			return returnValue;
		}
		 public boolean isCellEditable(int row, int column)
		 {
		     return false;
		 }
		 
			};
			
	static JTable tablo=new JTable(dTabloModeli);
public  static void main(String [] args) {
	DbConnection db = new DbConnection();
	ExportPass ex = new ExportPass();
	Checkdb c = new Checkdb();
	c.run();
	//FirstLogin f = new FirstLogin();
	//f.run();
	
	frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
	try {
		
		
		Statement sqlDurum=db.baglan.createStatement();
		String veriSec="select title,user,pass,note from passwd";
		ResultSet satir= sqlDurum.executeQuery(veriSec);
		
		Object[] rastSatir;
		while (satir.next()) {
			rastSatir= new Object[]{satir.getString(1),satir.getString(2),satir.getString(3),satir.getString(4)};
			dTabloModeli.addRow(rastSatir);
		}
	} catch (Exception e) {
	}
	tablo.setFont(new Font("Serif",Font.BOLD,14));
	tablo.setRowHeight(tablo.getRowHeight()+16);
	tablo.setAutoCreateRowSorter(true);
	tablo.setRowSelectionAllowed(true);
	tablo.getTableHeader().setFont( new Font( "SansSerif" , Font.BOLD, 15 ));
	
	JScrollPane scrollPane= new JScrollPane(tablo);
	frame.add(scrollPane,BorderLayout.CENTER);
	
	JButton yeni = new JButton("Yeni");
	yeni.setBounds(264, 250, 117, 25);
	
	yeni.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			AddData y = new AddData();
			y.run();
			
		}
	});
	
	
	JButton sil = new JButton("Sil");
	sil.setBounds(264, 250, 117, 25);
	
	sil.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				
				//System.out.println(data);
				
				Statement mystmt=db.baglan.createStatement();
				
				String data=tablo.getValueAt(tablo.getSelectedRow(),0).toString();
				String sql="delete from passwd where title ='"+data+"'";
			
				mystmt.executeUpdate(sql);
		
				System.out.println("Delete completed..");
				
				
			} catch (Exception e2) {
				JDialog dialog = new JDialog(new Frame());
				dialog.setSize(500,50);
				dialog.setResizable(false);
				String data="Silmek için bir satır seçmelisiniz";
				JTextField tf = new JTextField(data);
				tf.setHorizontalAlignment(JTextField.CENTER);
				Font font1 = new Font("SansSerif", Font.BOLD, 16);
				tf.setFont(font1);
				tf.setEditable(false);
				dialog.add(tf);
				dialog.setLocation(800, 500);
				dialog.setVisible(true);
			}
			
			dTabloModeli.removeRow(tablo.getSelectedRow());
			
			
			
			
		}
	});
	JButton kopyala = new JButton("Kopyala");
	kopyala.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {try {
			
			JDialog dialog = new JDialog(new Frame(), "Kopyalanacak metin");
			dialog.setSize(500,50);
			dialog.setResizable(false);
			String data=tablo.getValueAt(tablo.getSelectedRow(),tablo.getSelectedColumn()).toString();
			System.out.println(data);
			JTextField tf = new JTextField(data);
			tf.setHorizontalAlignment(JTextField.CENTER);
			Font font1 = new Font("SansSerif", Font.BOLD, 16);
			tf.setFont(font1);
			tf.setEditable(false);
			dialog.add(tf);
			dialog.setLocation(800, 500);
			dialog.setVisible(true);
			
		} catch (Exception e2) {
			
			JDialog dialog2 = new JDialog(new Frame());
			dialog2.setSize(500,50);
			dialog2.setResizable(false);
			String data="Kopyalamak için bir metin seçmelisiniz";
			JTextField tf = new JTextField(data);
			tf.setHorizontalAlignment(JTextField.CENTER);
			Font font1 = new Font("SansSerif", Font.BOLD, 16);
			tf.setFont(font1);
			tf.setEditable(false);
			dialog2.add(tf);
			dialog2.setLocation(800, 500);
			dialog2.setVisible(true);
		}
			
			
		}
	});

	frame.revalidate();
	frame.repaint();
	
	
	pane.add(yeni);
	pane.add(sil);
	pane.add(kopyala);
	frame.add(pane,BorderLayout.SOUTH);
	frame.setBounds(400, 300, 1200,500 );
	frame.setVisible(false);
	frame.setResizable(false);
	JMenuBar menuBar;
	JMenu menu,menu2, submenu2;
	JMenuItem menuItem3,menuItem4,menuItem5;
	//Create the menu bar.
	menuBar = new JMenuBar();

	//Build the first menu.
	menu = new JMenu("Dosya");
	menu.setMnemonic(KeyEvent.VK_A);
	menu.getAccessibleContext().setAccessibleDescription(
	        "The only menu in this program that has menu items");
	menuBar.add(menu);


	//a submenu
	


	submenu2 = new JMenu("Dışa Aktar");
	submenu2.setMnemonic(KeyEvent.VK_S);

	menuItem3 = new JMenuItem("TXT Dosyası");
	submenu2.add(menuItem3);

	menuItem4 = new JMenuItem("SQL Dosyası");
	submenu2.add(menuItem4);
	
	menuItem5 = new JMenuItem("E-posta Adresi");
	submenu2.add(menuItem5);
	
	
	menu.add(submenu2);

	//Build second menu in the menu bar.
	menu2 = new JMenu("Yardım");
	menu2.setMnemonic(KeyEvent.VK_N);
	menu2.getAccessibleContext().setAccessibleDescription(
	        "This menu does nothing");
	menuBar.add(menu2);

	
	frame.setJMenuBar(menuBar);
	menuItem3.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			ex.createTxt();
			JDialog dialog = new JDialog(new Frame());
			dialog.setSize(600,50);
			dialog.setResizable(false);
			String data="Parola veritabanı password_manager.txt dosyasına kaydedildi";
			JTextField tf = new JTextField(data);
			tf.setHorizontalAlignment(JTextField.CENTER);
			Font font1 = new Font("SansSerif", Font.BOLD, 16);
			tf.setFont(font1);
			tf.setEditable(false);
			dialog.add(tf);
			dialog.setLocation(800, 500);
			dialog.setVisible(true);
			
		}
	});
	
	menuItem4.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			ExportPass.exportDb();
			JDialog dialog = new JDialog(new Frame());
			dialog.setSize(600,50);
			dialog.setResizable(false);
			String data="Parola veritabanı password_manager.sql dosyasına kaydedildi";
			JTextField tf = new JTextField(data);
			tf.setHorizontalAlignment(JTextField.CENTER);
			Font font1 = new Font("SansSerif", Font.BOLD, 16);
			tf.setFont(font1);
			tf.setEditable(false);
			dialog.add(tf);
			dialog.setLocation(800, 500);
			dialog.setVisible(true);
			
		}
	});
	
	menuItem5.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			SendEmail se = new SendEmail();
			try {
                Statement mystmt=db.baglan.createStatement();
				
				ResultSet myrs=mystmt.executeQuery("select * from masterkeys");
				while(myrs.next())
				{
					String dbmail=myrs.getString("mail");
					System.out.println(dbmail);
					se.exportDb(dbmail);
					
				   }
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
				
			} catch (Exception e2) {
				// TODO: handle exception
			}
			
			
			
		}
	});
		
		
	
}
}
