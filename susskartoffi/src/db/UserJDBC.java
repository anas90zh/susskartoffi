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
		ResultSet rs = null;
		PreparedStatement pstmt = null;

		//in try ressourcen
		//connection erstellen 
		//SQL Anweisungen erzeugen mit Statement und  durchführen 
		try (Connection conn = DriverManager.getConnection(connString)){
			System.out.println("Connection established userJDBClass /add Mehode ");
			try (Statement stmt = conn.createStatement() ){
				//checken ob die User schon exsistiern 
				String selectWhere = "SELECT * FROM USERS WHERE  userId=" + user.getUserId();
				rs = stmt.executeQuery(selectWhere);
				if(rs.next()) {
					System.out.println("user already exist ");
					return ;
				}
				// insert neue User in die USERS Tablle 
				String insertStmt= "INSERT INTO USERS (userName, name, password, lifeStyle) VALUES(" + 
						"?, " + //Username
						"?, " + //Name
						"?, " + //password
						"? " + //lifstyle
						")";
					
				pstmt = conn.prepareStatement(insertStmt);
				pstmt.setString(1,user.getUserName());
				pstmt.setString(2,user.getName());
				pstmt.setString(3, user.getPassword());
				pstmt.setString(4, user.getLifstyle());
				pstmt.executeUpdate();
			} 

		} catch (SQLException e) {
			System.out.println("error Create mehtod\n" + e.getMessage());	
		}

		finally {
			try {
				if(pstmt!=null)
					pstmt.close();
				if(rs!=null)
				rs.close();
			} catch (SQLException e2) {
				System.out.println("error Create mehtod in finally Block rs.close \n" + e2.getMessage());
			}
		}


	}

	@SuppressWarnings({ "null", "resource" })
	@Override
	public void update(int userId, String updateStmt, String newEntry) throws Exception {
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		try (Connection conn = DriverManager.getConnection(connString);){
			System.out.println("Connection established userJDBClass/ Update Methode ");
			pstmt = conn.prepareStatement("SELECT * FROM USERS WHERE userId=" + userId);
			rs = pstmt.executeQuery();
			if(!rs.next()){
				System.out.println("no User with the ID "+ userId + " is  Found"); //data not exist
				return;
			}

			String update = "";
			switch(updateStmt) {
			case "updatePassword":
				update = "UPDATE USERS SET password = ?" + " WHERE userId = ? ";
				break;

			case "updateUserName":
				update =  "UPDATE USERS SET userName = ?" + " WHERE userId = ? ";
				break;

			case "updateLifeStyle":
				update =  "UPDATE USERS SET lifeStyle = ?" + " WHERE userId = ? ";
				break;
			}
			pstmt = conn.prepareStatement(update);
			pstmt.setString(1, newEntry);
			pstmt.setInt(2, userId);
			pstmt.executeUpdate();
			System.out.println("Row updated");

		} catch (SQLException e) {
			System.out.println("error UpdateUser mehtod\n" + e.getMessage());	
		}
		finally{
			try {
				if(pstmt!=null)
					pstmt.close();
				if(rs!=null)
					rs.close();

			}catch(SQLException e) {
				System.out.println("error in Finally Block stmt.close \n" + e.getMessage());
				e.printStackTrace();	
			}
		}


	}

	@Override
	public ArrayList<User> getAll(String selectStmt) throws Exception {
		ResultSet rs = null;
		ArrayList<Integer> favRezept = new ArrayList<>();
		ArrayList<User> users =null;

		//in try ressourcen wird 
		//connection erstellen 
		//SQL Anweisungen erzeugen mit Statement und  durchführen 
		try (Connection conn = DriverManager.getConnection(connString)){
			System.out.println("Connection established userJDBClass/ get Methode ");
			try(PreparedStatement stmt = conn.prepareStatement(selectStmt)) {
				//resultSet query duchfuhren und mit hilfe der while schlieife die User ArrayList füllen
				rs = stmt.executeQuery();
				users = new ArrayList<>();
				while(rs.next()) {
					users.add(new User(rs.getInt("userId"),rs.getString("userName"),rs.getString("name"), rs.getString("password"), favRezept ,rs.getString("lifeStyle"),rs.getInt("erstellteRezepte") ));
				}
			}

		} catch (SQLException e) {
			System.out.println("error getAllUser mehtod\n" + e.getMessage());	
		}
		//resultSet schließen
		finally{
			try {
				if(rs!=null)
					rs.close();
			}catch(SQLException e) {
				System.out.println("error getAllUser mehtod , in finally block rs.close\n " + e.getMessage());
				e.printStackTrace();	
			}
		}

		return users;
	}

	@Override
	public void delete(int userId) throws Exception {
		ResultSet rs = null;           
		
		try (Connection conn = DriverManager.getConnection(connString);
				Statement stmt = conn.createStatement()){			
			System.out.println("Connection established UserClass Delete Mehode\n ");

			//check ob die Table ist schon exisitiert, wenn schon exsistiert dann loschen 
			rs= stmt.executeQuery("SELECT * FROM USERS WHERE userId=" + userId);
			if(!rs.next()){
				System.out.println("no User with the ID "+ userId + " is  Found"); //data not exist
				return;
			}


			String deleteUser =	"DELETE FROM USERS WHERE " + "userId= " + userId;
			stmt.executeUpdate(deleteUser);
			System.out.println("User deleted");

		} catch (SQLException e) {
			System.out.println("error in main tryCath block\n DeletUserMehod" + e.getMessage());
		}

		finally{
			try {
				if(rs!=null)
					rs.close();

			}catch(SQLException e) {
				System.out.println("error in Finally Bock rs.close USerDelete\n" + e.getMessage());
				e.printStackTrace();	
			}
		}

	}

	@Override
	public void InitDatenbank()throws SQLException{
		ResultSet rs = null;

		//in try ressourcen
		//connection erstellen 
		//SQL Anweisungen erzeugen mit Statement und  durchführen 
		try (Connection conn = DriverManager.getConnection(connString);
				Statement stmt = conn.createStatement()){			
			System.out.println("Connection established UserClass InitDB Mehode\n ");
			//check ob die Table ist schon exisitiert, wenn schon exsistiert dann loschen 
			try {				
				rs = conn.getMetaData().getTables(null, null, "USERS" ,new String[] {"TABLE"});
				if(rs.next()) {
					stmt.executeUpdate("DROP TABLE " + "USERS");
					System.out.println("dropTable Nuzer excuted");
				}

			} catch (SQLException e) {
				System.out.println("error in drop table block USERS\n" + e.getMessage());	
			}
			//check ob die Table ist schon exisitiert, wenn schon exsistiert dann loschen 
			try {				
				rs = conn.getMetaData().getTables(null, null, "FAVREZEPTE" ,new String[] {"TABLE"});
				if(rs.next()) {
					stmt.executeUpdate("DROP TABLE " + "FAVREZEPTE");
					System.out.println("dropTable FAVREZEPTE excuted");
				}

			} catch (SQLException e) {
				System.out.println("error in drop table block favrezepte\n" + e.getMessage());	
			}

			// User Table erstellen 
			String userTable = "CREATE TABLE USERS ( userId INTEGER GENERATED ALWAYS AS IDENTITY(Start with 1, Increment by 1), " + 
					" userName VARCHAR(20) NOT NULL UNIQUE,  " + 
					" name VARCHAR(20) NOT NULL,  " + 
					" password VARCHAR(100)," + 
					" lifeStyle VARCHAR(10),  " + 
					" erstellteRezepte INTEGER," + 
					" PRIMARY KEY( userId ))";
			stmt.executeUpdate(userTable);
			System.out.println("UserTable created 1/2");

			// FAVREZEPTE Table erstellen 
			String favRezepteTable = "CREATE TABLE FAVREZEPTE ( favReID INTEGER NOT NULL, rezeptId INTEGER NOT NULL," +
					" PRIMARY KEY( favReID ))";
			stmt.executeUpdate(favRezepteTable);
			System.out.println("UserTable created 2/2");

		} catch (SQLException e) {
			System.out.println("error in main tryCath block\n" + e.getMessage());		}

		finally{
			try {
				if(rs!=null)
					rs.close();

			}catch(SQLException e) {
				System.out.println("error in Finally Blick rs.close \n" + e.getMessage());
				e.printStackTrace();	
			}
		}

	}


}
