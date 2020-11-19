package modell;

public class Zutat {
	public enum Label {VEGAN, VEGETARISCH, PLANTBASED};
	public enum Unit {G , ML};
	
	private Label label;
	private Unit unit;
	private int zutatid;
	private int rezeptId;
	private String name;
	private int kalorien;
	private boolean laktosefreie;
	
	
	public Zutat(int zutatid,int rezeptId ,String name, String unit, int kalorien, boolean laktosefreie, String label){
		super();
		this.label= Label.valueOf(label.toUpperCase());
		this.unit = Unit.valueOf(unit.toUpperCase());
		this.name = name;
		this.zutatid= zutatid;
		this.rezeptId=rezeptId;
		this.kalorien = kalorien;
		this.laktosefreie = laktosefreie;
	}
	public Zutat(int zutatid, String name, String unit, int kalorien, boolean laktosefreie, String label){
		super();
		this.label= Label.valueOf(label.toUpperCase());
		this.unit = Unit.valueOf(unit.toUpperCase());
		this.name = name;
		this.zutatid= zutatid;
		this.kalorien = kalorien;
		this.laktosefreie = laktosefreie;
	}


	public int getZutatid() {
		return zutatid;
	}


	public void setZutatid(int zutatid) {
		this.zutatid = zutatid;
	}


	public int getRezeptId() {
		return rezeptId;
	}


	public void setRezeptId(int rezeptId) {
		this.rezeptId = rezeptId;
	}


	public String getLabel() {
		return label.name();
		
	}


	public void setLabel(String label) {
		this.label = Label.valueOf(label.toUpperCase());
	}


	public String getUnit() {
		return unit.name();
	}


	public void setUnit(String unit) {
		this.unit = Unit.valueOf(unit.toUpperCase());
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getKalorien() {
		return kalorien;
	}


	public void setKalorien(int kalorien) {
		this.kalorien = kalorien;
	}


	public boolean isLaktosefreie() {
		return laktosefreie;
	}


	public void setLaktosefreie(boolean laktosefreie) {
		this.laktosefreie = laktosefreie;
	}


	@Override
	public String toString() {
		return "Zutat [label=" + label + ", unit=" + unit + ", zutatid=" + zutatid + ", rezeptId=" + rezeptId
				+ ", name=" + name + ", kalorien=" + kalorien + ", laktosefreie=" + laktosefreie + "]";
	}





	
	
	
	
	
}
