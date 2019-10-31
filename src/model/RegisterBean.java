package model;

import bo.Joueur;
import dal.DAOFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;

public class RegisterBean implements Serializable {

    private static final String FORM_FIELD_LOGIN = "form-username";
    private static final String FORM_FIELD_PWD = "form-password";
    private static final String FORM_FIELD_PWD_CONFIRM = "form-password-confirm";

    private String authResult;

    public RegisterBean() { }

    public String getAuthResult() {
        return authResult;
    }

    public void setAuthResult(String authResult) {
        this.authResult = authResult;
    }

    public void register(HttpServletRequest request ) {

        String login = request.getParameter( FORM_FIELD_LOGIN );
        String password = request.getParameter( FORM_FIELD_PWD );
        String passwordConfirm = request.getParameter( FORM_FIELD_PWD_CONFIRM );

        if (password.equals(passwordConfirm)) {
            Joueur joueur = new Joueur();
            joueur.setPseudo(login);
            joueur.setPassword(password);
            try {
                try {
                    DAOFactory.JoueurDAO().create(joueur);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            } catch ( SQLException e ) {
                authResult = "Pb de connexions à la base de données !";
            }
        }
        else {
            authResult = "le mot de passe est différent du mot de passe de confirmation";
        }
    }
}
