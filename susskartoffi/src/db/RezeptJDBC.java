package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.regex.Pattern;

import modell.Rezept;
import modell.User;
import modell.Zutat;

public class RezeptJDBC implements IKlassejdbc<Rezept>, IRezeptJDB{

	private static final String DBLocation = "C:\\Users\\anzah\\OneDrive\\Desktop\\Java code\\01-Datenbank\\susskartoffi";	
	private static final String connString = "jdbc:derby:" + DBLocation + ";create=true";

	//update in Rezepte Table
	@SuppressWarnings("resource")
	@Override
	public void update(int rezeptId, String updateStmt,String newEntry) throws Exception {
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		try (Connection conn = DriverManager.getConnection(connString);){
			System.out.println("Connection established userJDBClass/ Update Methode ");


			pstmt = conn.prepareStatement("SELECT * FROM REZEPTE WHERE rezeptId=" + rezeptId);
			rs = pstmt.executeQuery();
			if(!rs.next()){
				System.out.println("no User with the ID "+ rezeptId + " is  Found"); //data not exist
				return;
			}
			String update = "";
			switch(updateStmt) {
			case "herkunft":
				update = "UPDATE REZEPTE SET herkunft = ?" + " WHERE rezeptId = ? ";
				break;

			case "diaet":
				update =  "UPDATE REZEPTE SET diaet = ?" + " WHERE rezeptId = ? ";
				break;

			case "vorbereitungDauer":
				update =  "UPDATE REZEPTE SET vorbereitungDauer = ?" + " WHERE rezeptId = ? ";
				break;

			case "title":
				update =  "UPDATE REZEPTE SET title = ?" + " WHERE rezeptId = ? ";
				break;

			case "sichtbarkeit":
				update =  "UPDATE REZEPTE SET sichtbarkeit = ?" + " WHERE rezeptId = ? ";
				break;

			case "beschreibung":
				update =  "UPDATE REZEPTE SET beschreibung = ?" + " WHERE rezeptId = ? ";
				break;

			case "portionen":
				update =  "UPDATE REZEPTE SET portionen = ?" + " WHERE rezeptId = ? ";
				break;

			case "kosten":
				update =  "UPDATE REZEPTE SET kosten = ?" + " WHERE rezeptId = ? ";
				break;
			default :
				System.out.println("update statment doesnot exsist");
				return;
			}
			pstmt = conn.prepareStatement(update);
			if(Pattern.compile("^[1-9][0-9]{0,2}(?:,[0-9]{3}){0,3}$").matcher(newEntry).matches()) {
				System.out.println(Pattern.compile("^[1-9][0-9]{0,2}(?:,[0-9]{3}){0,3}$").matcher(newEntry).matches());
				pstmt.setInt(1, Integer.parseInt(newEntry));
			}else {pstmt.setString(1, newEntry);}
			pstmt.setInt(2, rezeptId);
			pstmt.executeUpdate();
			System.out.println("Row updated");

		} catch (SQLException e) {
			System.out.println("error Update Rezept mehtod\n" + e.getMessage());	
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


	//update in the Zutat Table
	@SuppressWarnings("resource")
	@Override
	public void updateZutat(int rezeptId, int zutatid, String updateStmt,String newEntry) throws Exception {
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		try (Connection conn = DriverManager.getConnection(connString);){
			System.out.println("Connection established userJDBClass/ Update Methode ");


			pstmt = conn.prepareStatement("SELECT * FROM ZUTAT WHERE zutatid=" + zutatid);
			rs = pstmt.executeQuery();
			if(!rs.next()){
				System.out.println("no zutat with the zutatid "+  zutatid +" is  Found"); //data not exist
				return;
			}
			String update = "";
			switch(updateStmt) {
			case "name":
				update = "UPDATE ZUTAT SET name = ?" + " WHERE rezeptId = ? " + " AND zutatid= ?";
				break;

			case "unit":
				update =  "UPDATE ZUTAT SET unit = ?" + " WHERE rezeptId = ? " + " AND zutatid= ?";
				break;

			case "kalorien":
				update =  "UPDATE ZUTAT SET kalorien = ?" + " WHERE rezeptId = ? " + " AND zutatid= ?";
				break;

			case "laktosefreie":
				update =  "UPDATE ZUTAT SET laktosefreie = ?" + " WHERE rezeptId = ? " + " AND zutatid= ?";
				break;

			case "label":
				update =  "UPDATE ZUTAT SET label = ?" + " WHERE rezeptId = ? " + " AND zutatid= ?";
				break;

			default :
				System.out.println("update statment doesnot exsist");
				return;
			}
			pstmt = conn.prepareStatement(update);
			if(Pattern.compile("^[1-9][0-9]{0,2}(?:,[0-9]{3}){0,3}$").matcher(newEntry).matches()) {
				System.out.println(Pattern.compile("^[1-9][0-9]{0,2}(?:,[0-9]{3}){0,3}$").matcher(newEntry).matches());
				pstmt.setInt(1, Integer.parseInt(newEntry));
			}else {pstmt.setString(1, newEntry);}
			pstmt.setInt(2, rezeptId);
			pstmt.setInt(3, zutatid);
			pstmt.executeUpdate();
			System.out.println("Row updated");

		} catch (SQLException e) {
			System.out.println("error Update Zutat mehtod\n" + e.getMessage());	
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

	@SuppressWarnings("resource")
	@Override
	public void updateSchritte(int rezeptId, int schrittId, String newEntry) throws Exception {
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		try (Connection conn = DriverManager.getConnection(connString);){
			System.out.println("Connection established userJDBClass/ Update Methode ");


			pstmt = conn.prepareStatement("SELECT * FROM SCHRITTE WHERE schrittId=" + schrittId);
			rs = pstmt.executeQuery();
			if(!rs.next()){
				System.out.println("no Schritt with the schrittId "+  schrittId +" is  Found"); //data not exist
				return;
			}
			String update = "UPDATE SCHRITTE SET text = ?" + " WHERE rezeptId = ? " + " AND schrittId= ?";
			pstmt = conn.prepareStatement(update);
			pstmt.setString(1, newEntry);
			pstmt.setInt(2, rezeptId);
			pstmt.setInt(3, schrittId);
			pstmt.executeUpdate();
			System.out.println("Row updated");

		} catch (SQLException e) {
			System.out.println("error Update Schritt mehtod\n" + e.getMessage());	
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


	public int getRezeptId() throws Exception{
		int rezeptid = 0;
		ResultSet rs = null;

		//in try ressourcen wird 
		//connection erstellen 
		//SQL Anweisungen erzeugen mit Statement und  durchführen 
		try (Connection conn = DriverManager.getConnection(connString)){
			System.out.println("Connection established userJDBClass/ get Methode ");

			try(PreparedStatement stmt = conn.prepareStatement("SELECT * FROM REZEPTIDTABLE ORDER BY rezeptId DESC FETCH FIRST ROW ONLY")) {
				//resultSet query duchfuhren und mit hilfe der while schlieife die User ArrayList füllen
				rs = stmt.executeQuery();
				while(rs.next()) {
					rezeptid =  rs.getInt("rezeptId");
				}
			}

		} catch (SQLException e) {
			System.out.println("error getRezeptId mehtod\n" + e.getMessage());	
		}
		//resultSet schließen
		finally{
			try {
				if(rs!=null)
					rs.close();
			}catch(SQLException e) {
				System.out.println("error getRezeptId mehtod , in finally block rs.close\n " + e.getMessage());
				e.printStackTrace();	
			}
		}

		return rezeptid;
	}

	@Override
	public ArrayList<Rezept> getAll(String selectStmt) throws Exception {
		ArrayList<Rezept> rezepte = new ArrayList<>();
		ArrayList<Zutat> zutaten = null;
		ArrayList<String>  schritte = null;	


		try(Connection conn =  DriverManager.getConnection(connString);
				PreparedStatement stmt = conn.prepareStatement(selectStmt);
				ResultSet rs = stmt.executeQuery()){

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
					while(rs2.next()) {
						zutaten.add(new Zutat(rs2.getInt("zutatid"),rs2.getInt("rezeptId"), rs2.getString("name"), rs2.getString("unit"),
								rs2.getInt("kalorien"), rs2.getBoolean("laktosefreie"),rs2.getString("label") ));
					}	

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
	public void delete(Rezept rezept) throws Exception {
		ResultSet rs = null;
		try (Connection conn = DriverManager.getConnection(connString);
				Statement stmt = conn.createStatement();
				){	

			rs = stmt.executeQuery("SELECT * FROM REZEPTE WHERE rezeptId= " + rezept.getRezeptId());
			if(!rs.next()){
				System.out.println("no REZEPTE with the rezeptid "+  rezept.getRezeptId() +" is  Found"); //data not exist
				return;
			}



			String deleteZutat ="DELETE FROM ZUTAT WHERE " + "rezeptId= " + rezept.getRezeptId();
			stmt.executeUpdate(deleteZutat);
			System.out.println("Zutat deleted");

			String deleteSchritte="DELETE FROM SCHRITTE WHERE " + "rezeptId= " + rezept.getRezeptId();
			stmt.executeUpdate(deleteSchritte);
			System.out.println("Schritte deleted");

			String deleteRe="DELETE FROM REZEPTE WHERE " + "rezeptId= " + rezept.getRezeptId();
			stmt.executeUpdate(deleteRe);
			System.out.println("reze deleted");









		} catch (SQLException e) {
			System.out.println("error Update Zutat mehtod\n" + e.getMessage());	
		}
		finally{
			try {

				if(rs!=null)
					rs.close();

			}catch(SQLException e) {
				System.out.println("error in Finally Block stmt.close \n" + e.getMessage());
				e.printStackTrace();	
			}
		}

	} 
	public void CreateRezeptId(int userid) throws Exception {
		PreparedStatement pstmt = null;
		try (Connection conn = DriverManager.getConnection(connString)){

			String insertRe= "INSERT INTO REZEPTIDTABLE (userId) VALUES(" +
					"? " + //UserId
					")";
			pstmt = conn.prepareStatement(insertRe);

			pstmt.setInt(1,userid);

			pstmt.executeUpdate();

			System.out.println("rezeptID inserted");


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
				pstmt.setInt(4,zutat.getmenge());
				pstmt.setBoolean(5, zutat.isLaktosefreie());
				pstmt.setString(6,zutat.getLabel());
				pstmt.executeUpdate();
			}


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

			try {
				rs = conn.getMetaData().getTables(null, null, "REZEPTIDTABLE" ,new String[] {"TABLE"});
				if(rs.next()) {
					stmt.executeUpdate("DROP TABLE " + "rezeptidtable");				}
				System.out.println("dropTable rezeptidtable excuted");

			} catch (SQLException e) {
				System.out.println("error in drop table block DROPrezeptidtable\n" + e.getMessage());		}

			String zutatTable =" CREATE TABLE ZUTAT ( zutatid INTEGER GENERATED ALWAYS AS IDENTITY(Start with 1, Increment by 1) , " + 
					" rezeptId INTEGER NOT NULL, " + 
					" name VARCHAR(100) NOT NULL, " + 
					" unit VARCHAR(5)," + 
					" kalorien INTEGER, " + 
					" laktosefreie BOOLEAN, " + 
					" label VARCHAR(20)," + 
					" PRIMARY KEY( zutatid ))";							
			stmt.executeUpdate(zutatTable);
			System.out.println("ZutatTable created 1/4");


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
			System.out.println("RezeptTable created 2/4");


			String schrittTable = "CREATE TABLE SCHRITTE ( " +
					" schrittId INTEGER GENERATED ALWAYS AS IDENTITY(Start with 1, Increment by 1)," + 
					" rezeptId INTEGER , " + 
					" text VARCHAR(200), " + 
					" PRIMARY KEY( schrittId ))";
			stmt.executeUpdate(schrittTable);
			System.out.println("schrittTable created 3/4");

			String rezeptIdTable = "CREATE TABLE REZEPTIDTABLE ( " +
					" rezeptId INTEGER GENERATED ALWAYS AS IDENTITY(Start with 1, Increment by 1) ," + 
					" userId INTEGER NOT NULL, " + 

					" PRIMARY KEY( rezeptId ))";
			stmt.executeUpdate(rezeptIdTable);
			System.out.println("rezeptIdTable created 4/4");

		} catch (SQLException e) {
			System.out.println("error in main tryCath  block\n posible in create tables" + e.getMessage());		}

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








