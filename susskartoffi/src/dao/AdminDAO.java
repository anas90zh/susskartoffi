package dao;

import java.util.ArrayList;

import db.UserJDBC;
import modell.User;

public class AdminDAO extends UserDAO{

	public ArrayList<User> getUser(){
		ArrayList<User> user = null;
		UserJDBC userjdbc = new UserJDBC();
		try {
			user = userjdbc.getAll("SELECT * FROM USERS" );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return user;
	}
	
	
	public void updateUser() {
		UserJDBC userjdbc = new UserJDBC();

		try {
			userjdbc.update(1,"updateLifeStyle", "VEGAN");
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
	}


	public void deleteUser() {
		UserJDBC userjdbc = new UserJDBC();

		try {
			userjdbc.delete(1);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
	}


}
