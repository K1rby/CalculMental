package model;

import bo.Joueur;
import dal.DAOFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.Stack;

public class UserBean implements Serializable {
	
	private static final String FORM_FIELD_LOGIN = "form-username";
	private static final String FORM_FIELD_PWD = "form-password";
	public static final String ATT_SESSION_CONNECTED_USER = "connectedUser";

	private Joueur joueur;
	private String authResult;
	
	private String currentExpression;

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

	public String getCurrentExpression() {
		return currentExpression;
	}

	public void setCurrentExpression(String currentExpression) {
		this.currentExpression = currentExpression;
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
				request.getSession().setAttribute("login", login);
				authResult = "Bienvenue " + login + "!";
			} else {
				joueur = new Joueur(login, password);
				authResult = "Identification échouée, merci de recommencer...";
			}
		} catch ( SQLException e ) {
			authResult = "Identification échouée : Pb de connexions à la base de données !";
		}
	}

	public String generationExpression(){
		String expression = "";
		String[] op1arg = {"rac", "inv"};
		String[] op2arg = {"+", "-", "*", "/"};
		int count = 0;
		int i;
		int choice;
		int flag = (int)(Math.random() * 2);
		Double add;
		for (i = 0; i < 5; i++) {
			if (count == 0) {
				add = (double) Math.round((Math.random() * 100) * 100d) / 100d;
				count++;
				expression = expression + add.toString() + " ";
			} else if (count == 1) {
				if (flag == 0) {
					choice = (int)(Math.random() * 2);
					expression = expression + op1arg[choice] + " ";
					if (i < 4) {
						flag = 1;
					}
				} else {
					add = (double) Math.round((Math.random() * 100) * 100d) / 100d;
					count++;
					expression = expression + add.toString() + " ";
					flag = 0;
				}
			} else {
				choice = (int)(Math.random() * 4);
				expression = expression + op2arg[choice] + " ";
				count--;
			}
		}
		expression = expression.substring(0, expression.length() -1);
		setCurrentExpression(expression);
		return expression;
	}

	public Double calculExpression() {
		String expression = getCurrentExpression();
		String []array = expression.split(" ", 6);
		Stack<Double> stack = new Stack<Double>();
		Double resultat;

		Double intA = 0.0;
		Double intB = 0.0;
		Double c;
		for (String a : array) {
			switch (a){
				case "rac":
					intA = stack.peek();
					stack.pop();
					c = Math.sqrt(intA);
					stack.push(c);
					break;
				case "inv":
					intA = stack.peek();
					stack.pop();
					intA *= -1;
					stack.push(intA);
					break;
				case "+":
					intA = stack.peek();
					stack.pop();
					intB = stack.peek();
					stack.pop();
					c = intA + intB;
					stack.push(c);
					break;
				case "-":
					intA = stack.peek();
					stack.pop();
					intB = stack.peek();
					stack.pop();
					c = intA - intB;
					stack.push(c);
					break;
				case "*":
					intA = stack.peek();
					stack.pop();
					intB = stack.peek();
					stack.pop();
					c = intA * intB;
					stack.push(c);
					break;
				case "/":
					intA = stack.peek();
					stack.pop();
					intB = stack.peek();
					stack.pop();
					c = intA / intB;
					stack.push(c);
					break;
				default:
					stack.push(Double.parseDouble(a));
					break;
			}
		}
		resultat = stack.peek();
		return resultat;
	}
}
