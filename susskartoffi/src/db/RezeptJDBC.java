package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import modell.Rezept;
import modell.Zutat;

public class RezeptJDBC implements IKlassejdbc<Rezept>{

	private static final String DBLocation = "C:\\Users\\anzah\\OneDrive\\Desktop\\Java code\\01-Datenbank\\susskartoffi";	
	private static final String connString = "jdbc:derby:" + DBLocation + ";create=true";


	@Override
	public void update(int t1, String t2,String t3) throws Exception {
		// TODO Auto-generated method stub

	}


	


	@Override
	public ArrayList<Rezept> getAll(String st) throws Exception {


		ArrayList<Rezept> rezepte = new ArrayList<>();
		ArrayList<Zutat> zutaten = null;
		ArrayList<String>  schritte = null;	


		try(Connection conn =  DriverManager.getConnection(connString);
				PreparedStatement stmt = conn.prepareStatement("SELECT * FROM REZEPTE");
				ResultSet rs = stmt.executeQuery()	){

			while(rs.next()) {
				schritte = new ArrayList<>();
				zutaten = new ArrayList<>();

				try (PreparedStatement stmt1 = conn.prepareStatement("SELECT * FROM SCHRITTE WHERE rezeptId=("+ rs.getInt("rezeptId")+ ")");
						ResultSet rs1 = stmt1.executeQuery()){

					while(rs1.next()) {
						schritte.add( rs1.getString("text"));
					}

				} catch (Exception e) {
					System.out.println("error in Schritte try/catch Block\n" + e.getMessage());
					e.printStackTrace();
				}
				

				try (PreparedStatement stmt2 = conn.prepareStatement("SELECT * FROM ZUTAT WHERE rezeptId=("+ rs.getInt("rezeptId")+ ")");
						ResultSet rs2 = stmt2.executeQuery()){
					System.out.println("Contents of the first result-set is sucess" + schritte.size());

					while(rs2.next()) {
						zutaten.add(new Zutat(rs2.getInt("zutatid"),rs2.getInt("rezeptId"), rs2.getString("name"), rs2.getString("unit"),
								rs2.getInt("kalorien"), rs2.getBoolean("laktosefreie"),rs2.getString("label") ));
					}	

					System.out.println("Contents of the second result-set \n " + zutaten.size());

				} catch (Exception e) {
					System.out.println("error in Zutat try/catch Block\n" + e.getMessage());
					e.printStackTrace();
				}

				rezepte.add(new Rezept(rs.getInt("userId"), rs.getInt("rezeptId"),
						rs.getString("herkunft"), rs.getString("diaet"),
						rs.getInt("vorbereitungDauer"),  rs.getString("title"),
						rs.getBoolean("sichtbarkeit"), rs.getString("beschreibung"),rs.getInt("portionen"),rs.getDouble("kosten"), zutaten, schritte ));					
			}

		}catch (SQLException e) {
			System.out.println("error getall mehtod\n" + e.getMessage());	
		}



		return rezepte;
	}




	@Override
	public void delete(int t) throws Exception {
		// TODO Auto-generated method stub

	} 







	public ArrayList<Zutat> getAlltest() throws Exception {
		Connection conn = null;
		ResultSet rs2 = null;

		String selecttStmt= "SELECT * FROM ZUTAT";
		ArrayList<Zutat> zutat = new ArrayList<>();

		conn = DriverManager.getConnection(connString);

		try(PreparedStatement stmt = conn.prepareStatement(selecttStmt)) {

			System.out.println("Connection established userJDBClass/ get Methode ");

			rs2 = stmt.executeQuery();

			while(rs2.next()) {
				zutat.add(new Zutat(rs2.getInt("zutatid"), rs2.getString("name"), rs2.getString("unit"),
						rs2.getInt("kalorien"), rs2.getBoolean("laktosefreie"),rs2.getString("label") ));
			}

		} catch (SQLException e) {
			System.out.println("error add mehtod\n" + e.getMessage());	
		}

		finally{
			try {

				if(conn!=null)
					conn.close();
					rs2.close();

			}catch(SQLException e) {
				System.out.println("error bei connection schliessen\n" + e.getMessage());
				e.printStackTrace();	
			}
		}



		return zutat;
	}









	@Override
	public void Create(Rezept rezept) throws Exception {
		PreparedStatement pstmt = null;

		try (Connection conn = DriverManager.getConnection(connString)){
			System.out.println("Connection established rezeptJDBClass /CreateRezept Methode ");

			String insertschritt= "INSERT INTO SCHRITTE (rezeptId,text) VALUES(" +
					"?, " + //rezeptId
					"? " + //text
					")";
			pstmt = conn.prepareStatement(insertschritt);
			for(String schritt : rezept.getSchritte() ) {
				pstmt.setInt(1, rezept.getRezeptId());
				pstmt.setString(2,schritt );
				pstmt.executeUpdate();
			}

			System.out.println("schritte inserted");

			for(Zutat zutat: rezept.getZutaten()) {
				String insertZutat= "INSERT INTO ZUTAT (rezeptId,name, unit, kalorien, laktosefreie, label) VALUES(" + 
						"?, " + //RezeptId
						"?, " + //Name
						"?, " + //Unit
						"?, " + //Kalorien
						"?, " + //isLaktosefreie
						"? " + //Label
						")";
				pstmt = conn.prepareStatement(insertZutat);
				pstmt.setInt(1,rezept.getRezeptId());
				pstmt.setString(2,zutat.getName());
				pstmt.setString(3,zutat.getUnit());
				pstmt.setInt(4,zutat.getKalorien());
				pstmt.setBoolean(5, zutat.isLaktosefreie());
				pstmt.setString(6,zutat.getLabel());
				pstmt.executeUpdate();
			}




			//insert stmt in to Rezept table

			//						String selectWhere = "SELECT * FROM REZEPTE WHERE  rezeptId=" + rezept.getRezeptId();  
			//						rs = pstmt.executeQuery(selectWhere);
			//						if(rs.next()) {
			//							System.out.println("Rezept already exist ");
			//							return ;
			//						}

			String insertRe= "INSERT INTO REZEPTE(userId, rezeptId , herkunft, diaet, vorbereitungDauer, title, sichtbarkeit, beschreibung,portionen, Kosten) VALUES(" +
					"?, " + //UserId
					"?, " + //RezeptId
					"?, " + //Herkunft
					"?, " + //diat
					"?, " + //VorbereitungDauer
					"?, " + //Title
					"?, " + //sichtbar
					"?, " + //Beschreibung
					"?, " + //Portionen
					"? " + //Kosten
					")";
			pstmt = conn.prepareStatement(insertRe);

			pstmt.setInt(1,rezept.getUserId());
			pstmt.setInt(2,rezept.getRezeptId());
			pstmt.setString(3,rezept.getHerkunft());
			pstmt.setString(4,rezept.getDiaet());
			pstmt.setInt(5, rezept.getVorbereitungDauer());
			pstmt.setString(6, rezept.getTitle());
			pstmt.setBoolean(7, rezept.istSichtbar());
			pstmt.setString(8, rezept.getBeschreibung());
			pstmt.setInt(9, rezept.getPortionen());
			pstmt.setDouble(10, rezept.getKosten());

			pstmt.executeUpdate();

			System.out.println("rezept inserted");


		} catch (SQLException e) {
			System.out.println("error Create mehtod\n" + e.getMessage());	
		}
		finally{
			try {
				if(pstmt!=null)
					pstmt.close();
			

			}catch(SQLException e) {
				System.out.println("error bei connection schliessen\n" + e.getMessage());
				e.printStackTrace();	
			}
		}


	}




	@Override
	public void InitDatenbank() throws SQLException {
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


			String zutatTable =" CREATE TABLE ZUTAT ( zutatid INTEGER GENERATED ALWAYS AS IDENTITY(Start with 1, Increment by 1) , " + 
					" rezeptId INTEGER NOT NULL, " + 
					" name VARCHAR(100) NOT NULL, " + 
					" unit VARCHAR(5)," + 
					" kalorien INTEGER, " + 
					" laktosefreie BOOLEAN, " + 
					" label VARCHAR(10)," + 
					" PRIMARY KEY( zutatid ))";							
			stmt.executeUpdate(zutatTable);
			System.out.println("ZutatTable created 1/3");


			String rezeptTable = "CREATE TABLE REZEPTE( " + 
					" userId INTEGER NOT NULL, " + 
					" rezeptId INTEGER NOT NULL, " + 
					" herkunft VARCHAR(13), " + 
					" diaet VARCHAR(20),  " + 
					" vorbereitungDauer INTEGER, " + 
					" title VARCHAR(13), " + 
					" sichtbarkeit BOOLEAN, " + 
					" beschreibung VARCHAR(200), " + 
					" portionen INTEGER, " + 
					" kosten FLOAT, " + 
					" PRIMARY KEY( rezeptId ))";
			stmt.executeUpdate(rezeptTable);
			System.out.println("RezeptTable created 2/3");
			

			String schrittTable = "CREATE TABLE SCHRITTE ( " +
					" schrittId INTEGER GENERATED ALWAYS AS IDENTITY(Start with 1, Increment by 1)," + 
					" rezeptId INTEGER , " + 
					" text VARCHAR(200), " + 
					" PRIMARY KEY( schrittId ))";
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
























