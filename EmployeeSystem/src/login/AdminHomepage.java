package login;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JTextArea;

public class AdminHomepage {
	
	
	MySQLconn mysql = new MySQLconn();
	


	static JFrame frmAdminHomePage;
	private JTextField tfEmpID;
	private JTextField tfEmpname;
	private JTextField tfUsername;
	private JTextField tfPhone;
	private JTextField tfEmail;
	
	
	
	String ename="",uname="",emailid="",eph="",eaddr="",perfreview;
	int eid;
	private JTextField remempid;
	private JTextField empid;
	private JTextField perf;
	private JTextField rating;
	

	/**
	 * Launch the application.
	 */
	public static void AdminHP() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminHomepage window = new AdminHomepage();
					window.frmAdminHomePage.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AdminHomepage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAdminHomePage = new JFrame();
		frmAdminHomePage.setTitle("Admin Home Page");
		frmAdminHomePage.setBounds(100, 100, 766, 481);
		frmAdminHomePage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAdminHomePage.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 730, 420);
		frmAdminHomePage.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btUpdaterating = new JButton("Update performance review/rating");
		btUpdaterating.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				
				
				int eid,r;
				eid = Integer.parseInt(empid.getText());
				String per = perf.getText();
				r = Integer.parseInt(rating.getText());
		
				String q = "Update emp set perfreview='"+per+"',rating='"+r+"'  where id='"+eid+"'";
				
				Connection con;
				try {
					
					con = mysql.jdbc();
					
					PreparedStatement ps = con.prepareStatement(q);
					ps.executeUpdate();
					
					JOptionPane.showMessageDialog(null, "Updated successfully");
					
					empid.setText("");
					perf.setText("");
					rating.setText("");
				} 
				
				catch (Exception e1) {
					// TODO Auto-generated catch block
					
					JOptionPane.showMessageDialog(null,e1.getMessage());
					
					
				}
				
				
			}
		});
		btUpdaterating.setBounds(427, 298, 233, 39);
		panel.add(btUpdaterating);
		
		JButton btRemoveemp = new JButton("Remove employee");
		btRemoveemp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int remid;
				remid = Integer.parseInt(remempid.getText());
				String q = "Delete from emp where id='"+remid+"'";
				
				try {
					
					Connection con = mysql.jdbc();
					
					Statement st = con.createStatement();
					st.executeUpdate(q);
					
					JOptionPane.showMessageDialog(null, "Employee removed");
					remempid.setText("");
					
				}
				
				catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btRemoveemp.setBounds(493, 105, 160, 31);
		panel.add(btRemoveemp);
		
		JButton btEmplist = new JButton("View Employee List");
		btEmplist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Emplist list = new Emplist();
				list.setVisible(true);
			}
		});
		btEmplist.setBounds(535, 11, 185, 31);
		panel.add(btEmplist);
		
		JTextArea tareaAddress = new JTextArea();
		tareaAddress.setBounds(67, 235, 228, 52);
		panel.add(tareaAddress);
		
		JButton btAddemp = new JButton("Add new employee");
		btAddemp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
																
				
				try {
										
					Connection con = mysql.jdbc();
					
																			
					
						eid = Integer.parseInt(tfEmpID.getText());
					
					
					if(tfEmpname.getText()==null) 
					{
						JOptionPane.showMessageDialog(null, "Please enter employee name");
					}
					
					else 
					{
						ename = tfEmpname.getText();
					}
					
					if(tfUsername.getText()==null) 
					{
						JOptionPane.showMessageDialog(null, "Please enter username");
					}
					
					else 
					{
						uname = tfUsername.getText();
					}
					
					eph = tfPhone.getText();
					emailid = tfEmail.getText();
					eaddr = tareaAddress.getText();
					String s = "Insert into emp(id,name,passw,empname,email,phone,address)"+" values(?,?,?,?,?,?,?) ";
					
					PreparedStatement stmt = con.prepareStatement(s);
					//ResultSet rs = stmt.executeQuery(s);
					
					stmt.setInt(1, eid);
					stmt.setString(2, uname);
					stmt.setString(3, "upass10");
					stmt.setString(4, ename);
					stmt.setString(5, emailid);
					stmt.setString(6, eph);
					stmt.setString(7, eaddr);
					
					stmt.execute();
					
					
					JOptionPane.showMessageDialog(null, "New employee added succesfully");
					tfEmpID.setText("");
					tfEmpname.setText("");
					tfUsername.setText("");
					tfPhone.setText("");
					tfEmail.setText("");
					tareaAddress.setText("");

				
				}
				
				catch (Exception e1) {
					// TODO Auto-generated catch block
				/*	if(tfEmpID.getText()==null) 
					{
						JOptionPane.showMessageDialog(null, "Please enter employee ID");
					} */

					
					JOptionPane.showMessageDialog(null,e1);
					
				}


				
				
			}
		});
		btAddemp.setBounds(38, 302, 144, 31);
		panel.add(btAddemp);
		
		JButton btAdminlogout = new JButton("Logout");
		btAdminlogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmAdminHomePage = new JFrame("Logout");
				if(JOptionPane.showConfirmDialog(frmAdminHomePage, "Are you sure you want to logout?","Logout",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
				{
					//EmpLogin.frmLogin.setVisible(true);
					//frmAdminHomePage.setVisible(false);
					System.exit(0);
				}
			}
		});
		btAdminlogout.setBounds(311, 389, 113, 31);
		panel.add(btAdminlogout);
		
		JLabel lblNewLabel = new JLabel("ADMIN");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(311, 0, 113, 31);
		panel.add(lblNewLabel);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(264, 33, 0, 224);
		panel.add(separator);
		
		JLabel lblNewLabel_1 = new JLabel("Employee ID :");
		lblNewLabel_1.setBounds(10, 77, 100, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Employee name :");
		lblNewLabel_2.setBounds(10, 102, 106, 20);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Username :");
		lblNewLabel_3.setBounds(10, 133, 91, 14);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setBounds(10, 166, 74, 14);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Email ID :");
		lblNewLabel_5.setBounds(10, 198, 74, 14);
		panel.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Phone no. :");
		lblNewLabel_6.setBounds(10, 164, 85, 14);
		panel.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Address :");
		lblNewLabel_7.setBounds(10, 240, 74, 14);
		panel.add(lblNewLabel_7);
		
		tfEmpID = new JTextField();
		tfEmpID.setBounds(121, 74, 56, 20);
		panel.add(tfEmpID);
		tfEmpID.setColumns(10);
		
		tfEmpname = new JTextField();
		tfEmpname.setBounds(121, 102, 160, 20);
		panel.add(tfEmpname);
		tfEmpname.setColumns(10);
		
		tfUsername = new JTextField(); 
		tfUsername.setBounds(121, 133, 143, 20);
		panel.add(tfUsername);
		tfUsername.setColumns(10);
		
		tfPhone = new JTextField();
		tfPhone.setBounds(121, 160, 113, 20);
		panel.add(tfPhone);
		tfPhone.setColumns(10);
		
		tfEmail = new JTextField();
		tfEmail.setBounds(94, 195, 170, 20);
		panel.add(tfEmail);
		tfEmail.setColumns(10);
		
		
		
		JLabel lblNewLabel_8 = new JLabel("Fill new employee details :");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_8.setBounds(24, 33, 170, 20);
		panel.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("Give Emp ID :");
		lblNewLabel_9.setBounds(493, 80, 86, 14);
		panel.add(lblNewLabel_9);
		
		remempid = new JTextField();
		remempid.setBounds(598, 77, 62, 20);
		panel.add(remempid);
		remempid.setColumns(10);
		
		JLabel lblNewLabel_10 = new JLabel("Emp ID :");
		lblNewLabel_10.setBounds(390, 185, 46, 14);
		panel.add(lblNewLabel_10);
		
		JLabel lblNewLabel_11 = new JLabel("Performance ");
		lblNewLabel_11.setBounds(390, 213, 78, 14);
		panel.add(lblNewLabel_11);
		
		JLabel lblNewLabel_12 = new JLabel(" Rating :");
		lblNewLabel_12.setBounds(390, 253, 46, 14);
		panel.add(lblNewLabel_12);
		
		empid = new JTextField();
		empid.setBounds(478, 182, 62, 20);
		panel.add(empid);
		empid.setColumns(10);
		
		perf = new JTextField();
		perf.setBounds(478, 213, 170, 25);
		panel.add(perf);
		perf.setColumns(10);
		
		rating = new JTextField();
		rating.setBounds(478, 250, 62, 20);
		panel.add(rating);
		rating.setColumns(10);
		
		JLabel lblNewLabel_13 = new JLabel("review :");
		lblNewLabel_13.setBounds(400, 228, 46, 14);
		panel.add(lblNewLabel_13);
	}
}
