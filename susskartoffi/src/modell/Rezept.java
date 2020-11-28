package modell;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Rezept {

	private String herkunft;
	private int userId;
	private int rezeptId;
	private String title;
	private String diaet;
	private int vorbereitungDauer;
	private boolean sichtbarkeit;
	private String beschreibung;
	private ArrayList<String> schritte;
	private ArrayList<Zutat> zutaten;
	private int portionen;
	private double kosten;
	private String v;


	public Rezept(int userId, int rezeptId,String herkunft, String diaet, int vorbereitungDauer, String title, boolean sichbarkeit, String bescreibung,
			int portionen,double kosten, ArrayList<Zutat> zutaten ,ArrayList<String> schritte) {
		this.herkunft = herkunft;
		this.userId = userId;
		this.rezeptId = rezeptId;
		this.title = title;
		this.sichtbarkeit = sichbarkeit;
		this.beschreibung = bescreibung;
		this.schritte = schritte;
		this.zutaten = zutaten;
		this.portionen = portionen;
		this.kosten= kosten;
		this.diaet = diaet;
		this.vorbereitungDauer = vorbereitungDauer;

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


	public ArrayList<String> getSchritte() {
		return schritte;
	}


	public void setSchritte(ArrayList<String> schritte) {
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




	public String getV() {
		return v;
	}




	public void setV(String v) {
		this.v = v;
	}




	@Override
	public String toString() {

		//String schritteSt = schritte.stream().map(Object::toString).collect(Collectors.joining(", "));
		StringBuilder zutatenSt = new StringBuilder();
		for(Zutat z : zutaten) {
			zutatenSt.append(z);
			zutatenSt.append("\t");
			
		}


		return "Rezept [herkunft=" + herkunft + ", userId=" + userId + ", rezeptId=" + rezeptId + ", title=" + title
				+ ", diaet=" + diaet + ", vorbereitungDauer=" + vorbereitungDauer + ", sichtbarkeit=" + sichtbarkeit
				+ ", beschreibung=" + beschreibung + ", schritte=" + schritte + ", zutaten=" + zutaten + ", portionen="
				+ portionen + ", kosten=" + kosten + "]";
	}






}
