package model;

import bo.Joueur;
import bo.Partie;
import dal.DAOFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public class HomeBean implements Serializable {

    private List<Partie> partie;
    private String authResult;

    public HomeBean() {}

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

    public void loadTopScore(HttpServletRequest request) {
        try {
            partie = DAOFactory.getTopScoreDAO().topScore();
        } catch ( SQLException e ) {
            authResult = "Identification échouée : Pb de connexions à la base de données !";
        }

    }
}
