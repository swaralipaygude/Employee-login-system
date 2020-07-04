package login;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Window;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;


public class EmpLogin {
	
	
	public static String username="";

	private JFrame frmLogin;
	static JTextField tfLoginUsername;
	private JPasswordField pfLoginPassword;
	public JComboBox comboBoxLogin;
	
	MySQLconn mysql = new MySQLconn();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmpLogin window = new EmpLogin();
					window.frmLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public EmpLogin() {
		initialize();
	}
	
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLogin = new JFrame();
		frmLogin.setTitle("Login Page");
		frmLogin.setBounds(100, 100, 450, 334);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogin.getContentPane().setLayout(null);
		
		tfLoginUsername = new JTextField();
		tfLoginUsername.setBounds(192, 111, 171, 26);
		frmLogin.getContentPane().add(tfLoginUsername);
		tfLoginUsername.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblUsername.setBounds(46, 109, 99, 26);
		frmLogin.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPassword.setBounds(46, 171, 99, 26);
		frmLogin.getContentPane().add(lblPassword);
		
		pfLoginPassword = new JPasswordField();
		pfLoginPassword.setBounds(192, 174, 171, 25);
		frmLogin.getContentPane().add(pfLoginPassword);
		
		JComboBox comboBoxLogin = new JComboBox();
		comboBoxLogin.setModel(new DefaultComboBoxModel(new String[] {"admin", "user"}));
		comboBoxLogin.setBounds(113, 60, 99, 20);
		frmLogin.getContentPane().add(comboBoxLogin);
		
		
		
		JButton btLogin = new JButton("Login");
		btLogin.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				
			    String password="";
				
				String empname="",email="",empid="",phone="",addr="",pr="",rat="";
												
				
				username = tfLoginUsername.getText();
				password = pfLoginPassword.getText();
				
				if(comboBoxLogin.getSelectedItem()=="admin") 
				{
					
					if(username.contains("admin") && password.contains("testpass10")) 
					{						
						AdminHomepage adhomepg = new AdminHomepage();
						adhomepg.AdminHP();
						
						frmLogin.setVisible(false);
						pfLoginPassword.setText(null);
						
						//JOptionPane.showMessageDialog(null, "Logged in as admin","Login Successful",JOptionPane.INFORMATION_MESSAGE);
					}
									

					else
					{
						JOptionPane.showMessageDialog(null, "Invalid Login","Login error",JOptionPane.ERROR_MESSAGE);
						//tfLoginUsername.setText(null);
						//pfLoginPassword.setText(null);
					}
					
					
				}	
				
				else if(comboBoxLogin.getSelectedItem()=="user") 
				{
					try 
					{
						
						Connection conn = mysql.jdbc();
						PreparedStatement st = conn.prepareStatement("Select name,passw from emp where name='"+username+"' and passw='"+password+"'");
					//	st.setString(1,username);
					//	st.setString(2,password);
						ResultSet rs = st.executeQuery();
						if(rs.next()) 
						{
							Userhp user = new Userhp();
							user.setVisible(true);
							frmLogin.setVisible(false);
							
				/*			UserHomepage homepg = new UserHomepage();
							homepg.UserHP();		 */
							
																					
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Invalid Login","Login error",JOptionPane.ERROR_MESSAGE);

						}
						
						
					}
					
					catch(Exception ex) {
						ex.printStackTrace();
						
					}
					
					
				}	
							
				
			}

		
		});
		btLogin.setBounds(46, 241, 89, 26);
		frmLogin.getContentPane().add(btLogin);
		
		JButton btReset = new JButton("Reset");
		btReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pfLoginPassword.setText(null);
				tfLoginUsername.setText(null);
				
			}
		});
		btReset.setBounds(177, 241, 89, 26);
		frmLogin.getContentPane().add(btReset);
		
		JButton btExit = new JButton("Exit");
		btExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmLogin = new JFrame("Exit");
				if(JOptionPane.showConfirmDialog(frmLogin,"Do you want to exit?","Login Page",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
				{
					System.exit(0);
				}
			}
		});
		btExit.setBounds(307, 241, 89, 26);
		frmLogin.getContentPane().add(btExit);
		
		JLabel lblLoginTitle = new JLabel("User Login");
		lblLoginTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblLoginTitle.setBounds(160, 11, 130, 26);
		frmLogin.getContentPane().add(lblLoginTitle);
		
		
	}
}
