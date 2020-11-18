package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SetupDbUser {

	private static final String DBLocation = "C:\\Users\\anzah\\OneDrive\\Desktop\\Java code\\01-Datenbank\\susskartoffi";	
	private static final String connString = "jdbc:derby:" + DBLocation + ";create=true";


	public static void userInit()throws SQLException{
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(connString);
			stmt = conn.createStatement();
			System.out.println("Connection established UserClass ");



			try {				
				rs = conn.getMetaData().getTables(null, null, "NUTZER" ,new String[] {"TABLE"});
				if(rs.next()) {
					stmt.executeUpdate("DROP TABLE " + "NUTZER");
					System.out.println("dropTable Nuzer excuted");
				}

			} catch (SQLException e) {
				System.out.println("error in drop table block nutzer\n" + e.getMessage());	
			}
			
			try {				
				rs = conn.getMetaData().getTables(null, null, "FAVREZEPTE" ,new String[] {"TABLE"});
				if(rs.next()) {
					stmt.executeUpdate("DROP TABLE " + "FAVREZEPTE");
					System.out.println("dropTable FAVREZEPTE excuted");
				}

			} catch (SQLException e) {
				System.out.println("error in drop table block favrezepte\n" + e.getMessage());	
			}


			String nutzerTable = "CREATE TABLE NUTZER ( userId INTEGER NOT NULL, " + 
					" userName VARCHAR(20) NOT NULL,  " + 
					" password VARCHAR(100)," + 
					" lifeStyle VARCHAR(10),  " + 
					" erstellteRezepte INTEGER," + 
					" PRIMARY KEY( userId ))";
			stmt.executeUpdate(nutzerTable);
			System.out.println("UserTable created 1/2");

			String favRezepteTable = "CREATE TABLE FAVREZEPTE ( favReID INTEGER NOT NULL, rezeptId INTEGER NOT NULL," +
					" PRIMARY KEY( favReID ))";
			stmt.executeUpdate(favRezepteTable);
			System.out.println("UserTable created 2/2");




		} catch (SQLException e) {
			System.out.println("error in main tryCath block\n" + e.getMessage());		}

		finally{
			try {
				if(stmt!=null)
					stmt.close();
				if(conn!=null)
					conn.close();

			}catch(SQLException e) {
				System.out.println("error bei connection schliessen\n" + e.getMessage());
				e.printStackTrace();	
			}
		}




	}

}
