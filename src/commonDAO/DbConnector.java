package commonDAO;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DbConnector {
	//디비연결 메서드 
		protected Connection getConnection() throws Exception {
			Connection con = null;
			Context init = new InitialContext();
			DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/rocketpunch");
			con = ds.getConnection();
			return con;
		}
}
