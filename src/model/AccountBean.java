package model;

import bo.Joueur;
import bo.Partie;
import dal.DAOFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import static model.UserBean.ATT_SESSION_CONNECTED_USER;

public class AccountBean implements Serializable {

    private static final String FORM_FIELD_LOGIN = "form-username";
    private static final String FORM_FIELD_PWD = "form-password";
    private static final String FORM_FIELD_PWD_CONFIRM = "form-password-confirm";

    private List partie;
    private String authResult;
    private Joueur joueur;

    public AccountBean() {
    }

    public Joueur getJoueur() {
        return joueur;
    }

    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
    }

    public String getAuthResult() {
        return authResult;
    }

    public void setAuthResult(String authResult) {
        this.authResult = authResult;
    }

    public List<Partie> getPartie() {
        return partie;
    }

    public void setPartie(List<Partie> partie) {
        this.partie = partie;
    }

    public void loadTopScoreJoueur(HttpServletRequest request) throws SQLException {

        joueur = (Joueur) request.getSession().getAttribute("connectedUser");
        partie = DAOFactory.getTopScoreDAO().topScoreJoueur((long) joueur.getId());
    }
    public void updateJoueur(HttpServletRequest request) throws SQLException {

        String login = request.getParameter( FORM_FIELD_LOGIN );
        String password = request.getParameter( FORM_FIELD_PWD );
        String passwordConfirm = request.getParameter( FORM_FIELD_PWD_CONFIRM );
        joueur = (Joueur) request.getSession().getAttribute("connectedUser");

        if (password.equals(passwordConfirm)) {
            Joueur joueurUpdate = new Joueur();
            joueurUpdate.setPseudo(login);
            joueurUpdate.setId(joueur.getId());
            joueurUpdate.setPassword(password);
            request.getSession().setAttribute("login", login);
            request.getSession().setAttribute( ATT_SESSION_CONNECTED_USER, joueurUpdate );
            try {
                try {
                    DAOFactory.JoueurDAO().update(joueurUpdate);
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
