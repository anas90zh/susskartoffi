package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modell.User;
import modell.User.Lifestyle;

public class UserJDBC implements IKlassejdbc<User>{

	private static final String DBLocation = "C:\\Users\\anzah\\OneDrive\\Desktop\\Java code\\01-Datenbank\\susskartoffi";	
	private static final String connString = "jdbc:derby:" + DBLocation + ";create=true";


	@Override
	public void Create(User user) throws Exception {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(connString);
			stmt = conn.createStatement();
			System.out.println("Connection established userJDBClass /add Mehode ");

			String selectWhere = "SELECT * FROM NUTZER WHERE  userId=" + user.getUserId();  
			rs = stmt.executeQuery(selectWhere);
			if(rs.next()) {
				System.out.println("user already exist ");
				return ;
			}
			
			
			
			String insertStmt= "INSERT INTO NUTZER (userId, userName, password, lifeStyle, erstellteRezepte) VALUES(" + 
					+ user.getUserId() + ", " 
					+ "'" + user.getUserName() + "'" + ", " 
					+ "'" + user.getPassword() + "'" + ", " 
					+ "'" + user.getLifstyle().toUpperCase()+ "'" + ", " 
					+ user.getErstellteRezepte() + " )" ;

			stmt.executeUpdate(insertStmt);

		} catch (SQLException e) {
			System.out.println("error add mehtod\n" + e.getMessage());	
		}
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

	@Override
	public User update(User user) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<User> getAll(String selectStmt) throws Exception {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		ArrayList<Integer> favRezept = new ArrayList<>();
		ArrayList<User> users = new ArrayList<>();
		
		
		
		try {
			conn = DriverManager.getConnection(connString);
			System.out.println("Connection established userJDBClass/ get Methode ");

			stmt = conn.prepareStatement(selectStmt);
			rs = stmt.executeQuery();
			while(rs.next()) {
				users.add(new User(rs.getInt("userId"),rs.getString("userName"), rs.getString("password"), favRezept ,rs.getString("lifeStyle"),rs.getInt("erstellteRezepte") ));

			}

		} catch (SQLException e) {
			System.out.println("error add mehtod\n" + e.getMessage());	
		}

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



		return users;
	}

	@Override
	public void delete(User user) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void InitDatenbank()throws SQLException{
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
