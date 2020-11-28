package modell;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import modell.Zutat.Label;
import modell.Zutat.Unit;

public class ZutatFX {

	private Zutat modellObject;

	private SimpleStringProperty label;
	private SimpleStringProperty unit;
	private SimpleIntegerProperty zutatid;
	private SimpleIntegerProperty rezepid;
	private SimpleStringProperty name;
	private SimpleIntegerProperty kalorien;
	private SimpleBooleanProperty laktosefreie;


	public ZutatFX(Zutat zutat) {
		modellObject = zutat;
		label = new SimpleStringProperty(zutat.getLabel());
		unit= new SimpleStringProperty(zutat.getUnit());
		zutatid= new SimpleIntegerProperty(zutat.getZutatid());
		rezepid= new SimpleIntegerProperty(zutat.getRezeptId());
		name= new SimpleStringProperty(zutat.getName());
		kalorien= new SimpleIntegerProperty(zutat.getKalorien());
		laktosefreie= new SimpleBooleanProperty(zutat.isLaktosefreie());


	}
	public Zutat getModellObject() {
		return modellObject;
	}
	
	public String getLabel() {return label.get();}
	public SimpleStringProperty labelProperty() {return label;}
	
	public String getUnit() {return unit.get();}
	public SimpleStringProperty unitProperty() {return unit;}
	
	public int getZutatid() {return zutatid.get();}
	public SimpleIntegerProperty isbnProperty() {return zutatid;}
	
	public int getRezepid() {return rezepid.get();}
	public SimpleIntegerProperty rezepidProperty() {return rezepid;}
	
	
	public String getName() {return name.get();}
	public SimpleStringProperty nameProperty() {return name;}
	
	public int getKalorien() {return kalorien.get();}
	public SimpleIntegerProperty kalorienProperty() {return kalorien;}
	
	
	
	public boolean isLaktosefreie() {return laktosefreie.get();}
	public SimpleBooleanProperty laktosefreieProperty() {return laktosefreie;}
	
	
	
}
