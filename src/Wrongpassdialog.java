import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("unused")
public class Wrongpassdialog extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public  void run() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Wrongpassdialog frame = new Wrongpassdialog();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Wrongpassdialog() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(800, 400, 450, 100);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel label = new JLabel("Yanlış giriş yaptınız!");
		label.setVerticalAlignment(SwingConstants.TOP);
		label.setFont(new Font("Dialog", Font.BOLD, 16));
		label.setBounds(136, 25, 174, 19);
		panel.add(label);
		
		JButton btnTamam = new JButton("Tamam");
		btnTamam.setActionCommand("Tamam");
		btnTamam.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String cmd = e.getActionCommand();

		        if(cmd.equals("Tamam"))
		        {
		            dispose();
		            AskLogin a = new AskLogin();
		            a.run();
		            
		       
		            
		        }
				
			}
		});
		btnTamam.setBounds(165, 60, 117, 25);
		panel.add(btnTamam);
	}
}
