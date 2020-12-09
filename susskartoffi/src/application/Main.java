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
	private static ArrayList<Zutat> zu = new ArrayList<>();
	private static ArrayList<String> scg = new ArrayList<>();

	public static void main(String[] args) {

		zu.add(new Zutat("name", "g", 4, true, "VEGAN"));
		scg.add("asd");

		
		try {

			UserDAO userD = new UserDAO();	
//			RezeptJDBC rezeptJDBD = new RezeptJDBC();
//			rezeptJDBD.InitDatenbank();
//			rezeptJDBD.CreateRezeptId(1);
//			rezeptJDBD.getRezeptId();
			
//			Rezept re = new Rezept(1, "herkunft", "diaet", 5, "title", true, "bescreibung", 5, 50, zu, scg);
//			userD.CreateRezept(re,1);
			
			
			
//			rezeptJDBD.updateZutat(2,1, "laktosefreie", "false");
//			rezeptJDBD.updateSchritte(2, 1, "bla bla");

//			ArrayList<Rezept> ae1 = userD.getAllRezepte();
//			ae1.stream().forEach(System.out::println);

//			
//			ArrayList<Zutat> ae =rezeptJDBD.getAlltest();
//			ae.stream().forEach(System.out::println);

		
//						UserJDBC userJDBC = new UserJDBC();
//						userJDBC.InitDatenbank();
						User us = new User( "asd", "dasdasddd ortner","asdasdasdasd","VEGETARIA");
////						userJDBC.Create(us);
//						UserDAO userDAO = new UserDAO();
//						userDAO.createUser(us);
						
						AdminDAO dao = new AdminDAO();
//						dao.deleteUser();
 						ArrayList<User> a = dao.getUser();
						a.stream().forEach(System.out::println);
						
//						UserDAO jd = new UserDAO();
//						jd.updateUser();
//						a.stream().forEach(System.out::println);

//						UserDAO udao = new UserDAO();
//						ArrayList<User> aa =udao.getUser("anas900");
//						System.out.println(aa.isEmpty());
//						aa.stream().forEach(System.out::println);
//




		} catch ( Exception e) {
			System.out.println(e);
			e.printStackTrace();
			return;
		}



	}


}
