package model;

import bo.Joueur;
import dal.DAOFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.sql.SQLException;

public class UserBean implements Serializable {
	
	private static final String FORM_FIELD_LOGIN = "form-username";
	private static final String FORM_FIELD_PWD = "form-password";
	public static final String ATT_SESSION_CONNECTED_USER = "connectedUser";

	private Joueur joueur;
		private String authResult;
	
	public UserBean() { }

	public Joueur getJoueur() {
		return joueur;
	}

	public void setJoueur( Joueur joueur ) {
		this.joueur = joueur;
	}

	public String getAuthResult() {
		return authResult;
	}
	
	public void setAuthResult( String authResult ) {
		this.authResult = authResult;
	}

	public boolean isConnected( HttpServletRequest request ) {
		HttpSession session = request.getSession();
		Joueur connectedUser = ( Joueur ) session.getAttribute( ATT_SESSION_CONNECTED_USER );
		return connectedUser != null;
	}
	
	public void authenticate( HttpServletRequest request ) {
		
		String login = request.getParameter( FORM_FIELD_LOGIN );
		String password = request.getParameter( FORM_FIELD_PWD );
		Joueur joueur = null;
		try {
			joueur = DAOFactory.getJoueurDAO().authenticate( login, password );
			if ( null != joueur ) {
				request.getSession().setAttribute( ATT_SESSION_CONNECTED_USER, joueur );
				authResult = "Bienvenue " + login + "!";
			} else {
				joueur = new Joueur(login, password);
				authResult = "Identification échouée, merci de recommencer...";
			}
		} catch ( SQLException e ) {
			authResult = "Identification échouée : Pb de connexions à la base de données !";
		}
	}
}
