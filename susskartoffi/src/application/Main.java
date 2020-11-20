package application;

import java.util.ArrayList;
import java.util.HashMap;


import dao.AdminDAO;
import dao.UserDAO;
import db.UserJDBC;
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

//
//
//			RezeptJDBC rezeptJDBD = new RezeptJDBC();
//
//			
//			Rezept re = new Rezept(2,2,"thai2","diat2",525,"blavlatitle2",true,"sehr lecker222",4,20.0,arra,hm);
//			rezeptJDBD.Create(re);
//	
//			ArrayList<Zutat> ae = rezeptJDBD.getAlltest();
//			ae.stream().forEach(System.out::println);
//
//			ArrayList<Rezept> ae1 = rezeptJDBD.getAll();
//			ae1.stream().forEach(System.out::println);

		
						UserJDBC userJDBC = new UserJDBC();			
			
//						userJDBC.InitDatenbank();
//		
//						User us = new User( 1, "anna", "ortner","VEGETARIA");
//						userJDBC.Create(us);
						
						AdminDAO dao = new AdminDAO();
//						dao.deleteUser();
						ArrayList<User> a = dao.getUser();
						a.stream().forEach(System.out::println);
						
						UserDAO jd = new UserDAO();
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
