package com.softserve.edu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ApplJDBC {
	private static Connection con = null;
	//private static String username = "ssu-oms";
	//private static String password = "ssu-oms";
	//
	private static String username = "root";
	private static String password = "root";
	private static String URL = "jdbc:mysql://localhost:3306/test";
	//
	//private static String URL = "jdbc:jtds:sqlserver://ssu-sql12.training.local/ssu-oms;instance=tc;";
	//private static String URL = "jdbc:sqlserver://ssu-sql12.training.local\\tc;databasename=ssu-oms;";

	public static void main(String[] args) throws SQLException,
			ClassNotFoundException {
		System.out.println("Start...");
		//DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
		//DriverManager.registerDriver(new net.sourceforge.jtds.jdbc.Driver());
		Class.forName("net.sourceforge.jtds.jdbc.Driver");
		// Load the driver
		con = DriverManager.getConnection(URL, username, password);
		if (con != null) {
			System.out.println("Connection Successful! \n");
		} else {
			System.exit(0);
		}
		Statement st = con.createStatement();
		// Statement allows you to send inquiries database
		// ResultSet rs = st.executeQuery("select * from Users");
		//ResultSet rs = st.executeQuery("SELECT * FROM dbo.Users;");
		ResultSet rs = st.executeQuery("SELECT * FROM users;");
		//
		// ResultSet gets the result table
		int x = rs.getMetaData().getColumnCount();
		// Resultset.getMetaData () get the information
		// output file
		while (rs.next()) {
			for (int i = 1; i <= x; i++) {
				System.out.print(rs.getString(i) + "\t");
			}
			System.out.println();
		}
		System.out.println();
		if (rs != null) {
			rs.close();
		}
		if (st != null) {
			st.close();
		}
		if (con != null) {
			con.close();
		}
	}

}
