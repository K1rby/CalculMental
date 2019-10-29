package model;

import bo.Partie;
import dal.DAOFactory;

import javax.servlet.http.HttpServletRequest;
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
            authResult = "Pb de connexions à la base de données !";
        }

    }
}
