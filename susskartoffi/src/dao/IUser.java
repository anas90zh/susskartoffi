package dao;

import java.util.List;
import java.util.Optional;

import modell.User;


public interface IUser {
	
	
	public void CreateUser(User user) throws Exception ;
	
	public UserJDBC update(UserJDBC user) throws Exception;

	public List<User> getAll() throws Exception;

	public void delete(UserJDBC user) throws Exception;

	

}
