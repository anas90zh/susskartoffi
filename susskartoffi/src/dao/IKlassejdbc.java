package dao;

import java.util.ArrayList;
import java.util.List;

import modell.User;
import modell.Zutat;

public interface IKlassejdbc<T> {

	public void Create(T t) throws Exception ;
	
	public T update(T t) throws Exception;

	public ArrayList<T> getAll() throws Exception;

	public void delete(T t) throws Exception;

	
}
