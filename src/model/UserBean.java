package model;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

public class UserBean implements Serializable {
	
	private static final String FORM_FIELD_LOGIN = "form-username";
	private static final String FORM_FIELD_PWD = "form-password";
		private static final String ATT_SESSION_IS_CONNECTED = "isConnected";
	
	private String login;
	private String password;
	private String authResult;
	
	public UserBean() {
		login = "";
		password = "";
		authResult = "";
	}
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin( String login ) {
		this.login = login;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword( String password ) {
		this.password = password;
	}
	
	public String getAuthResult() {
		return authResult;
	}
	
	public void setAuthResult( String authResult ) {
		this.authResult = authResult;
	}
	
	public boolean isConnected( HttpServletRequest request ) {
		Boolean isConnected = ( Boolean ) request.getSession().getAttribute( ATT_SESSION_IS_CONNECTED );
		return isConnected != null ? isConnected :false;
	}
	
	public void authenticate( HttpServletRequest request ) {
		
		login = request.getParameter( FORM_FIELD_LOGIN );
		password = request.getParameter( FORM_FIELD_PWD );
		if ( "sega".equals( login ) && "ssy".equals( password ) ) {
			request.getSession().setAttribute( ATT_SESSION_IS_CONNECTED, true );
			authResult = "Bienvenue " + login + "!";
		} else {
			authResult = "Identification échouée, merci de recommencer...";
		}
	}
}
