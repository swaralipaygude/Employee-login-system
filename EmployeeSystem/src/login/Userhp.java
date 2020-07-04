package login;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class Userhp extends JFrame {
	
	MySQLconn mysql = new MySQLconn();
	

	private JPanel contentPane;
	private JTextField phno;
	private JTextField email;
	private JTextField rat;
	private JPasswordField newpass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Userhp frame = new Userhp();
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
	public Userhp() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 725, 439);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Employee name :");
		lblNewLabel.setBounds(21, 69, 100, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Employee ID :");
		lblNewLabel_1.setBounds(370, 69, 89, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Email ID :");
		lblNewLabel_2.setBounds(21, 105, 61, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Phone no. :");
		lblNewLabel_3.setBounds(21, 147, 78, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Address :");
		lblNewLabel_4.setBounds(21, 195, 61, 14);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Performance");
		lblNewLabel_5.setBounds(370, 105, 89, 14);
		contentPane.add(lblNewLabel_5);
		
		phno = new JTextField();
		phno.setBounds(109, 144, 123, 20);
		contentPane.add(phno);
		phno.setColumns(10);
		
		email = new JTextField();
		email.setBounds(105, 102, 165, 20);
		contentPane.add(email);
		email.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Rating :");
		lblNewLabel_6.setBounds(378, 147, 54, 14);
		contentPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("review :");
		lblNewLabel_7.setBounds(380, 122, 67, 14);
		contentPane.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("User Homepage");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_8.setBounds(254, 11, 178, 29);
		contentPane.add(lblNewLabel_8);
		
		JTextArea addr = new JTextArea();
		addr.setBounds(104, 190, 178, 47);
		contentPane.add(addr);
		
		JLabel ename = new JLabel("Name");
		ename.setBounds(131, 69, 178, 14);
		contentPane.add(ename);
		
		JLabel eid = new JLabel("ID ");
		eid.setBounds(479, 69, 36, 14);
		contentPane.add(eid);
		
		JLabel lblNewLabel_13 = new JLabel("Enter new password :");
		lblNewLabel_13.setBounds(356, 236, 123, 14);
		contentPane.add(lblNewLabel_13);
		
		JTextArea tareapr = new JTextArea();
		tareapr.setBounds(469, 100, 165, 40);
		contentPane.add(tareapr);
		

		JLabel lbuname = new JLabel("(out of 5)");
		lbuname.setBounds(370, 160, 67, 14);
		contentPane.add(lbuname);
		
		rat = new JTextField();
		rat.setBounds(469, 157, 46, 20);
		contentPane.add(rat);
		rat.setColumns(10);
		
		
		JButton btnNewButton = new JButton("Update info");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String e = email.getText();
				String p = phno.getText();
				String a = addr.getText();
				
				try {
					Connection con = mysql.jdbc();
					
				String s = "Update emp set email='"+e+"',phone='"+p+"',address='"+a+"' where id='"+eid.getText()+"' ";
				
				PreparedStatement ps = con.prepareStatement(s);
				ps.execute();
				
				JOptionPane.showMessageDialog(null, "Info updated successfully");
								
					
				}
				
				catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton.setBounds(47, 273, 109, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Logout");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(JOptionPane.showConfirmDialog(null, "Are you sure you want to logout?","Logout",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
				{
					System.exit(0);
				}
			
			}
		});
		btnNewButton_1.setBounds(293, 366, 102, 23);
		contentPane.add(btnNewButton_1);
		
		
		
		try {
			Connection con = mysql.jdbc();
			String empname="",empid="",em="",ph="",add="",pr="",emprat="";
			String uname = EmpLogin.tfLoginUsername.getText();
			String q = "Select id,empname,email,phone,address,perfreview,rating from emp where name='"+uname+"'";
			
			PreparedStatement ps = con.prepareStatement(q);
			
			ResultSet rs = ps.executeQuery();
			
			
			while(rs.next())
			{
				empid = rs.getString(1);
				empname = rs.getString(2);
				em = rs.getString(3);
				ph = rs.getString(4);
				add = rs.getString(5);
				pr = rs.getString(6);
				emprat = rs.getString(7);
							
			}
			
			ename.setText(empname);
			eid.setText(empid);
			email.setText(em);
			phno.setText(ph);
			addr.setText(add);
			tareapr.setText(pr);
			rat.setText(emprat);
			
			
			addr.setLineWrap(true);
			tareapr.setLineWrap(true);
			tareapr.setEditable(false);
			rat.setEditable(false);
			
			
		}
		
		catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		JButton btnNewButton_2 = new JButton("Change password");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				

				try {
					Connection con = mysql.jdbc();
					int emid = Integer.parseInt(eid.getText());
					String s = "Update emp set passw='"+newpass.getText()+"' where id='"+emid+"' ";
					
					PreparedStatement pst = con.prepareStatement(s);
					pst.execute();
					JOptionPane.showMessageDialog(null, "Password changed");
					
					newpass.setText("");
					
				}
				catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnNewButton_2.setBounds(415, 273, 190, 23);
		contentPane.add(btnNewButton_2);
		
		newpass = new JPasswordField();
		newpass.setBounds(494, 233, 178, 20);
		contentPane.add(newpass);
		
		
		
	}
}
