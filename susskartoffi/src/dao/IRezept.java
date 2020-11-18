package dao;

import java.util.List;

import modell.Rezept;


public interface IRezept {
	public void CreateRezept(Rezept rezept) throws Exception ;

	public Rezept update(Rezept rezept) throws Exception;

	public List<Rezept> getAll() throws Exception;

	public void delete(Rezept rezept) throws Exception;




}
