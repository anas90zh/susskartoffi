package application;

import java.util.ArrayList;
import java.util.HashMap;


import dao.AdminDAO;
import dao.UserDAO;
import db.RezeptJDBC;
import db.UserJDBC;
import modell.Rezept;
import modell.User;
import modell.Zutat;

public class Main {

	private static ArrayList<Integer> ar = new ArrayList<>();
	private static ArrayList<Zutat> arra = new ArrayList<>();

	public static void main(String[] args) {
		ar.add(2);
		arra.add(new Zutat(1,"spinat", "g", 100, true, "PLANTBASED"));
		ArrayList<String>  hm = new ArrayList<>();
		hm.add("test");
		hm.add("schritt1");
		hm.add("schritt2");

		

		
		try {

			UserDAO userD = new UserDAO();	
			RezeptJDBC rezeptJDBD = new RezeptJDBC();
//			rezeptJDBD.InitDatenbank();
//			
//			Rezept re = new Rezept(1,1,"thai2","diat2",525,"blavlatitle2",true,"sehr lecker222",4,20.0,arra,hm);
//			rezeptJDBD.Create(re);
//			rezeptJDBD.updateZutat(2,1, "laktosefreie", "false");
			rezeptJDBD.updateSchritte(2, 1, "bla bla");

			ArrayList<Rezept> ae1 = userD.getAllRezepte();
			ae1.stream().forEach(System.out::println);

//			
//			ArrayList<Zutat> ae =rezeptJDBD.getAlltest();
//			ae.stream().forEach(System.out::println);

		
			
//						userJDBC.InitDatenbank();
//		
//						User us = new User( 1, "anna", "ortner","VEGETARIA");
//						userJDBC.Create(us);
						
//						AdminDAO dao = new AdminDAO();
//						dao.deleteUser();
//						ArrayList<User> a = dao.getUser();
//						a.stream().forEach(System.out::println);
						
//						UserDAO jd = new UserDAO();
//						jd.updateUser();
//						a.stream().forEach(System.out::println);

//						UserDAO udao = new UserDAO();
//						ArrayList<User> aa =udao.getUser(1);
//						aa.stream().forEach(System.out::println);





		} catch ( Exception e) {
			System.out.println(e);
			e.printStackTrace();
			return;
		}



	}


}
