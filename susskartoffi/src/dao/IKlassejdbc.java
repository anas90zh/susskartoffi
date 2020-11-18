package dao;

import java.util.List;

import modell.User;

public interface IKlassejdbc<T> {

	public void Create(T t) throws Exception ;
	
	public T update(T t) throws Exception;

	public List<T> getAll() throws Exception;

	public void delete(T t) throws Exception;

	
}
