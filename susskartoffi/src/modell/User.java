package modell;

import java.util.ArrayList;

public class User {
	
	public enum Lifestyle {VEGETARIA, VEGAN, MEATLOVER, ICHESSEALLES};
	
	private Lifestyle lifestyle;
	private int userId;
	private String userName;
	private String password;
	private ArrayList<Integer> favRecepts;
	private int erstellteRezepte;
	
	public User( int userId, String userName, String password, String stLisfstyle) {
		super();
		this.lifestyle = Lifestyle.valueOf(stLisfstyle);
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.erstellteRezepte =0;
	}
	
	
	
	public User( int userId, String userName, String password, ArrayList<Integer> favRecepts, String stLisfstyle, int erstellteRezepte) {
		super();
		this.lifestyle = Lifestyle.valueOf(stLisfstyle);
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.favRecepts = favRecepts;
		this.erstellteRezepte = erstellteRezepte;
	}
	
	public String getLifstyle() {
		return lifestyle.name();
		
	}
	
	public void setLifstyle(String stLisfstyle) {
		this.lifestyle = Lifestyle.valueOf(stLisfstyle);
	}
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public ArrayList<Integer> getFavRecepts() {
		return favRecepts;
	}
	public void setFavRecepts(ArrayList<Integer> favRecepts) {
		this.favRecepts = favRecepts;
	}
	public int getErstellteRezepte() {
		return erstellteRezepte;
	}
	public void setErstellteRezepte(int erstellteRezepte) {
		this.erstellteRezepte = erstellteRezepte;
	}

	@Override
	public String toString() {
		return "User [lifestyle=" + lifestyle.name() + ", userId=" + userId + ", userName=" + userName + ", password="
				+ password + ", favRecepts=" + favRecepts + ", erstellteRezepte=" + erstellteRezepte + "]";
	}
	
	

}
