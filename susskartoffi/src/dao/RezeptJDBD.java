package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modell.Rezept;
import modell.User;
import modell.Zutat;

public class RezeptJDBD implements IRezept{
	
	private static final String DBLocation = "Desktop//Code+//Java//DB";	
	private static final String connString = "jdbc:derby:" + DBLocation + ";create=true";
	
	
	@Override
	public void CreateRezept(Rezept rezept) throws Exception {
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
				System.out.println(rezept.getKosten());

				//insert stmt in to Rezept table
				String insertStmt= "INSERT INTO REZEPTE (userId, rezeptId , herkunft, diaet, vorbereitungDauer, title, sichtbarkeit, beschreibung, Kosten) VALUES(" + 
						+ rezept.getUserId() + ", " 
						+ rezept.getRezeptId() + ", " 
						+ "'" + rezept.getHerkunft() + "'" + ", " 
						+ "'" + rezept.getDiaet()+ "'" + ", " 
						+ rezept.getVorbereitungDauer()+ ", "
						+ "'" + rezept.getTitle()+ "'" + ", " 
						+ "'" + rezept.istSichtbar()+ "'" + ", " 
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
							+ zutat.isLaktosefreie()+ ", "
							+ " )" ;

					stmt.executeUpdate(insertZutat);
	
				}
				
				System.out.println("test n2 ");

				//insert stmt in to schritte table
				for(Integer key : rezept.getSchritte().keySet() ) {
					String insertschritt= "INSERT INTO SCHRITTE (schrittkey, text ) VALUES(" + 
							+ key.intValue() + ", " 
							+ "'" + rezept.getSchritte().get(key) + "'" 
							+ " )" ;

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
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void delete(Rezept rezept) throws Exception {
		// TODO Auto-generated method stub
		
	}

	
	
	
	
	
	

}
