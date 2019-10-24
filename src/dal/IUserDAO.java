package dal;

import bo.Joueur;

import java.sql.SQLException;

public interface IUserDAO<ID, E> extends IDAO<ID, E> {
	
	public Joueur authenticate(String pseudo, String password) throws SQLException;
}
