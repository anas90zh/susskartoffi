package modell;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Rezept {

	private String herkunft;
	private int userId;
	private int rezeptId;
	private String title;
	private String diaet;
	private int vorbereitungDauer;
	private boolean sichtbarkeit;
	private String beschreibung;
	private HashMap<Integer,String> schritte ;
	private ArrayList<Zutat> zutaten;
	private int portionen;
	private double kosten;
	
	
	public Rezept(String herkunft, int userId, String title, boolean sichbarkeit, String bescreibung,
		int portionen,ArrayList<Zutat> zutaten ,HashMap<Integer, String> schritte,double kosten) {
		this.herkunft = herkunft;
		this.userId = userId;
		this.title = title;
		this.sichtbarkeit = sichbarkeit;
		this.beschreibung = bescreibung;
		this.schritte = schritte;
		this.zutaten = zutaten;
		this.portionen = portionen;
		this.kosten= kosten;

	}




	public int getRezeptId() {
		return rezeptId;
	}


	public void setRezeptId(int rezeptId) {
		this.rezeptId = rezeptId;
	}

	public String getHerkunft() {
		return herkunft;
	}


	public void setHerkunft(String herkunft) {
		this.herkunft = herkunft;
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getDiaet() {
		return diaet;
	}


	public void setDiaet(String diaet) {
		this.diaet = diaet;
	}


	public int getVorbereitungDauer() {
		return vorbereitungDauer;
	}


	public void setVorbereitungDauer(int vorbereitungDauer) {
		this.vorbereitungDauer = vorbereitungDauer;
	}


	public boolean istSichtbar() {
		return sichtbarkeit;
	}


	public void setSichtbarkeit(boolean sichbarkeit) {
		this.sichtbarkeit = sichbarkeit;
	}


	public String getBeschreibung() {
		return beschreibung;
	}


	public void setBeschreibung(String bescreibung) {
		this.beschreibung = bescreibung;
	}


	public HashMap<Integer, String> getSchritte() {
		return schritte;
	}


	public void setSchritte(HashMap<Integer, String> schritte) {
		this.schritte = schritte;
	}



	public int getPortionen() {
		return portionen;
	}


	public void setPortionen(int portionen) {
		this.portionen = portionen;
	}


	public double getKosten() {
		return kosten;
	}


	public void setKosten(double kosten) {
		this.kosten = kosten;
	}


	public ArrayList<Zutat> getZutaten() {
		return zutaten;
	}


	public void setZutaten(ArrayList<Zutat> zutaten) {
		this.zutaten = zutaten;
	}
	
	


	
	
}
