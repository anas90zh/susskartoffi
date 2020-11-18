package application;

import java.util.ArrayList;
import java.util.HashMap;

import dao.RezeptJDBD;
import dao.UserJDBC;
import db.SetupDbRezept;
import db.SetupDbUser;
import modell.Rezept;
import modell.User;
import modell.Zutat;

public class Main {

	private static ArrayList<Integer> ar = new ArrayList<>();
	private static ArrayList<Zutat> arra = new ArrayList<>();
	private static HashMap<Integer, String> hm = new HashMap<>();

	public static void main(String[] args) {
		ar.add(2);
		arra.add(new Zutat(1,"spinat", "g", 100.0, true, "PLANTBASED"));
		hm.put(1, "test1");
		try {
			SetupDbRezept.rezeptInit();
			
//			Rezept re = new Rezept("thai", 1, "title1", true, "beschreibung", 4, arra, hm ,10);
//			RezeptJDBD rezeptJDBD = new RezeptJDBD();
//			rezeptJDBD.CreateRezept(re);

			//SetupDbUser.userInit();

			//			User us = new User( 12, "anas", "asad","VEGETARIA");
			//			UserJDBC userJDBC = new UserJDBC();
			//			userJDBC.CreateUser(us);
			//			ArrayList<User> a = userJDBC.getAll();
			//			a.stream().forEach(System.out::println);
			//			




		} catch ( Exception e) {
			System.out.println(e);
			e.printStackTrace();
			return;
		}



	}


}
