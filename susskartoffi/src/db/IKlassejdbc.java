package db;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modell.Admin;
import modell.User;
import modell.Zutat;

public interface IKlassejdbc<T> {
	
	public  void InitDatenbank()throws SQLException;

	public void Create(T t) throws Exception ;
	
	public void update(int t1, String t2,String t3) throws Exception;

	public ArrayList<T> getAll(String t) throws Exception;

	public void delete(int t) throws Exception;


	
}