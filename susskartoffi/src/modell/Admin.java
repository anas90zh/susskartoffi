package modell;

import java.util.ArrayList;

public class Admin extends User{
	private ArrayList<User> userListe;

	public Admin(int userId, String userName, String password, String stLisfstyle, int adminId,
			ArrayList<User> userListe) {
		super(userId, userName, password, stLisfstyle);
		
		this.userListe = userListe;
	}

	
	

	

}
