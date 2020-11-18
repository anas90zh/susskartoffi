package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;


public class SetupDbRezept {

	private static final String DBLocation = "Desktop//Code+//Java//DB";	
	private static final String connString = "jdbc:derby:" + DBLocation + ";create=true";


	public static void rezeptInit()throws SQLException{
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(connString);
			stmt = conn.createStatement();
			System.out.println("Connection established RezeptClass");

			try {
				rs = conn.getMetaData().getTables(null, null, "ZUTAT" ,new String[] {"TABLE"});
				if(rs.next()) {
					stmt.executeUpdate("DROP TABLE " + "ZUTAT");
					System.out.println("dropTable zutat excuted");
					}

			} catch (SQLException e) {
				System.out.println("error in drop table block zutat\n" + e.getMessage());		}

			try {
				rs = conn.getMetaData().getTables(null, null, "REZEPTE" ,new String[] {"TABLE"});
				if(rs.next()) {
					stmt.executeUpdate("DROP TABLE " + "REZEPTE");
				System.out.println("dropTable REZEPTE excuted");
				}

			}catch (SQLException e) {
				System.out.println("error in drop table block rezepte\n" + e.getMessage());}


			try {
				rs = conn.getMetaData().getTables(null, null, "SCHRITTE" ,new String[] {"TABLE"});
				if(rs.next()) {
					stmt.executeUpdate("DROP TABLE " + "SCHRITTE");				}
					System.out.println("dropTable SCHRITTE excuted");

			} catch (SQLException e) {
				System.out.println("error in drop table block schritte\n" + e.getMessage());		}


			String zutatTable =" CREATE TABLE ZUTAT ( zutatid INTEGER NOT NULL , " + 
					" unit VARCHAR(10)," + 
					" label VARCHAR(10)," + 
					" name VARCHAR(100) NOT NULL, " + 
					" kalorien DOUBLE, " + 
					" laktosefreie BOOLEAN, " + 
					" PRIMARY KEY( zutatid ))";							
			stmt.executeUpdate(zutatTable);
			System.out.println("ZutatTable created 1/3");


			String rezeptTable = "CREATE TABLE REZEPTE( " + 
					" userid INTEGER NOT NULL, " + 
					" rezeptId INTEGER NOT NULL, " + 
					" herkunft CHAR(13), " + 
					" diaet CHAR(20),  " + 
					" vorbereitungDauer INTEGER, " + 
					" title CHAR(13), " + 
					" sichtbarkeit BOOLEAN, " + 
					" beschreibung VARCHAR(200), " + 
					" Kosten DOUBLE, " + 
					" PRIMARY KEY( rezeptId ))";
			stmt.executeUpdate(rezeptTable);
			System.out.println("RezeptTable created 2/3");

			
			String schrittTable = "CREATE TABLE SCHRITTE ( " + 
					" schrittkey INTEGER NOT NULL, " + 
					" text VARCHAR(200), " + 
					" PRIMARY KEY( schrittkey ))";
			stmt.executeUpdate(schrittTable);
			System.out.println("schrittTable created 3/3");


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

