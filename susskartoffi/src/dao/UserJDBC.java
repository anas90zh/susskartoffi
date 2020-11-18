package dao;

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

public class UserJDBC implements IUser{

	private static final String DBLocation = "Desktop//Code+//Java//DB";	
	private static final String connString = "jdbc:derby:" + DBLocation + ";create=true";


	@Override
	public  void CreateUser(User user) throws Exception {
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
	public UserJDBC update(UserJDBC user) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<User> getAll() throws Exception {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String selecttStmt= "SELECT * FROM NUTZER " ;
		ArrayList<Integer> favRezept = new ArrayList<>();
		ArrayList<User> users = new ArrayList<>();
		
		
		
		try {
			conn = DriverManager.getConnection(connString);
			System.out.println("Connection established userJDBClass/ get Methode ");

			stmt = conn.prepareStatement(selecttStmt);
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
	public void delete(UserJDBC user) throws Exception {
		// TODO Auto-generated method stub

	}

}
