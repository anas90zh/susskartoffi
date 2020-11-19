package dao;

import java.util.ArrayList;

import db.UserJDBC;
import modell.User;

public class AdminDAO {

	public ArrayList<User> getUser(){
		ArrayList<User> user = null;
		UserJDBC userjdbc = new UserJDBC();
		try {
			user = userjdbc.getAll("SELECT * FROM NUTZER" );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return user;

	}
}
