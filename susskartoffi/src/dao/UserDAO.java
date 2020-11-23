package dao;
import db.RezeptJDBC;
import db.UserJDBC;
import modell.Rezept;
import modell.User;

import java.sql.SQLException;
import java.util.ArrayList;


public class UserDAO {

	//Method for User DataBase
	public ArrayList<User> getUser(int userId){
		ArrayList<User> user = null;
		UserJDBC userjdbc = new UserJDBC();
		try {
			user = userjdbc.getAll("SELECT * FROM USERS WHERE userId=(" + userId + ")");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return user;

	}

	
	public void updateUser() {
		UserJDBC userjdbc = new UserJDBC();

		try {
			userjdbc.update(1,"updatePassword", "VEGAN");
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
	}
	
	
	
	//Method for Rezept DataBase
	public void updateRezept(int rezeptId, String updateStmt,String newEntry) {
		RezeptJDBC rezeptJDBC = new RezeptJDBC();
		try {
			rezeptJDBC.update(rezeptId, updateStmt, newEntry);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	public ArrayList<Rezept> getRezept(int rezeptId) {
		ArrayList<Rezept> rezepts = new ArrayList<>();
		RezeptJDBC rezeptjdbc = new RezeptJDBC();
		try {
			rezepts = rezeptjdbc.getAll("SELECT * FROM REZEPTE WHERE rezeptId= " + rezeptId);
		}catch (Exception e) {
			System.out.println("error getall mehtod\n" + e.getMessage());	
		}
		return rezepts;
		
	}

	public ArrayList<Rezept> getAllRezepte() {
		ArrayList<Rezept> rezepts = new ArrayList<>();

		RezeptJDBC rezeptjdbc = new RezeptJDBC();
		try {
			rezepts = rezeptjdbc.getAll("SELECT * FROM REZEPTE ");
		}catch (Exception e) {
			System.out.println("error getall mehtod\n" + e.getMessage());	
		}
		return rezepts;
		
	}

	
}
