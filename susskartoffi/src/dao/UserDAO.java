package dao;
import db.RezeptJDBC;
import db.UserJDBC;
import modell.Rezept;
import modell.User;

import java.sql.SQLException;
import java.util.ArrayList;


public class UserDAO {

	//Method for User DataBase
	public void createUser(User user){
		try {
			UserJDBC userjdbc = new UserJDBC();
			userjdbc.Create(user);
		} catch (Exception e) {
			System.out.println("error getall mehtod\n" + e.getMessage());	
			e.printStackTrace();
		}
	}

	public ArrayList<User> getUser(String username){
		ArrayList<User> user = null;
		UserJDBC userjdbc = new UserJDBC();
		try {

			user = userjdbc.getAll("SELECT * FROM USERS WHERE userName=(" +"'" + username + "'" + ")");
		} catch (Exception e) {
			System.out.println("error getall mehtod\n" + e.getMessage());	
			e.printStackTrace();
		}


		return user;

	}


	public void updatePassword(int userId,String newPassword) {
		UserJDBC userjdbc = new UserJDBC();

		try {
			userjdbc.update(userId,"updatePassword",newPassword);

		} catch (Exception e) {
			System.out.println("error getall mehtod\n" + e.getMessage());	
			e.printStackTrace();
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


	public ArrayList<Rezept> getRezept(String rezeptTitle) {
		ArrayList<Rezept> rezepts = new ArrayList<>();
		RezeptJDBC rezeptjdbc = new RezeptJDBC();
		try {
			rezepts = rezeptjdbc.getAll("SELECT * FROM REZEPTE WHERE title= " + "'" + rezeptTitle + "'");
		}catch (Exception e) {
			System.out.println("error getall mehtod\n" + e.getMessage());	
		}
		return rezepts;

	}

	public ArrayList<Rezept> getAllRezepte() {
		ArrayList<Rezept> rezepts = new ArrayList<>();

		RezeptJDBC rezeptjdbc = new RezeptJDBC();
		try{
			rezepts = rezeptjdbc.getAll("SELECT * FROM REZEPTE ");
		}catch (Exception e) {
			System.out.println("error getall mehtod\n" + e.getMessage());	
		}
		return rezepts;

	}




	public void CreateRezept(Rezept rezept,int userid){

		try {
			RezeptJDBC rezeptJDBD = new RezeptJDBC();
			rezeptJDBD.CreateRezeptId(userid);
			rezept.setRezeptId(rezeptJDBD.getRezeptId());
			rezeptJDBD.Create(rezept);
			System.out.println("Rezept inseted");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}



	public void UpdateRezept(Rezept rezept,int userid){
	
	
	}
	
	
	
	
	public void deleteRezept(Rezept  rezept){
		
		try {
			RezeptJDBC rezeptJDBD = new RezeptJDBC();
			rezeptJDBD.delete(rezept);
			
			System.out.println("Rezept deleted");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	
	
	
	
	
	
	
	
	
	

}
