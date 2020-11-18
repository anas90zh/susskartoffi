package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modell.Rezept;
import modell.User;
import modell.Zutat;

public class RezeptJDBD implements IKlassejdbc<Rezept>{
	
	private static final String DBLocation = "C:\\Users\\anzah\\OneDrive\\Desktop\\Java code\\01-Datenbank\\susskartoffi";	
	private static final String connString = "jdbc:derby:" + DBLocation + ";create=true";
	
	
	@Override
	public void Create(Rezept rezept) throws Exception {
			Connection conn = null;
			Statement stmt = null;
			ResultSet rs = null;
			
			
			try {
				conn = DriverManager.getConnection(connString);
				stmt = conn.createStatement();
				System.out.println("Connection established rezeptJDBClass /CreateRezept Methode ");

				String selectWhere = "SELECT * FROM REZEPTE WHERE  rezeptId=" + rezept.getRezeptId();  
				rs = stmt.executeQuery(selectWhere);
				if(rs.next()) {
					System.out.println("Rezept already exist ");
					return ;
				}

				//insert stmt in to Rezept table
				String insertStmt= "INSERT INTO REZEPTE (userId, rezeptId , herkunft, diaet, vorbereitungDauer, title, sichtbarkeit, beschreibung, Kosten) VALUES(" + 
						+ rezept.getUserId() + ", " 
						+ rezept.getRezeptId() + ", " 
						+ "'" + rezept.getHerkunft() + "'" + ", " 
						+ "'" + rezept.getDiaet()+ "'" + ", " 
						+ rezept.getVorbereitungDauer()+ ", "
						+ "'" + rezept.getTitle()+ "'" + ", " 
						+ rezept.istSichtbar() + ", " 
						+ "'" + rezept.getBeschreibung()+ "'" + ", " 
						+ rezept.getKosten() + " )" ;

				stmt.executeUpdate(insertStmt);

				
				//insert stmt in to Zutat table
				for(Zutat zutat : rezept.getZutaten() ) {

					String insertZutat= "INSERT INTO ZUTAT (zutatid, unit , label, name, kalorien, laktosefreie) VALUES(" + 
							+ zutat.getZutatid()+ ", " 
							+ "'" + zutat.getUnit() + "'" + ", " 
							+ "'" + zutat.getLabel() + "'" + ", " 
							+ "'" + zutat.getName()+ "'" + ", " 
							+ zutat.getKalorien()+ ", "
							+ zutat.isLaktosefreie()+ " )" ;

					stmt.executeUpdate(insertZutat);

				}
				

				//insert stmt in to schritte table
				for(Integer key : rezept.getSchritte().keySet() ) {
					String insertschritt= "INSERT INTO SCHRITTE (schrittkey, text ) VALUES(" + 
							+ key.intValue() + ", " 
							+ "'" + rezept.getSchritte().get(key) + "' )";

					stmt.executeUpdate(insertschritt);
	
				}
				
				
			} catch (SQLException e) {
				System.out.println("error Create mehtod\n" + e.getMessage());	
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
	public Rezept update(Rezept rezept) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Rezept> getAll() throws Exception {
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String selecttStmt= "SELECT * FROM NUTZER " ;
			ArrayList<Integer> favRezept = new ArrayList<>();
			ArrayList<Rezept> users = new ArrayList<>();
			
			
			
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
	public void delete(Rezept rezept) throws Exception {
		// TODO Auto-generated method stub
		
	}

	
	
	
	
	
	

}
