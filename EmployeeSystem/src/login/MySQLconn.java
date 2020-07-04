package login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLconn {

	public Connection jdbc() throws Exception {
		
		String dbUrl = "jdbc:mysql://localhost:3306/employeedb" ;
   		String user = "root";
   		String passw = "Swaralip567!";
		Connection conn = DriverManager.getConnection(dbUrl,user,passw);
		return conn;
	}


	
}
