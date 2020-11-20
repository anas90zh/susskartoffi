package dao;
import db.UserJDBC;
import modell.User;

import java.util.ArrayList;


public class UserDAO {

	
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
	
	
}
