package db;


// hier sind extra Methoden zu implementierten nur f�r die RezepteJDBC klasse
public interface IRezeptJDB {
	public void updateZutat(int rezeptId, int zutatid, String updateStmt,String newEntry) throws Exception ;
	public void updateSchritte(int schrittId, int rezeptId, String newEntry) throws Exception ;

}
