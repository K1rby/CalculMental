package model;

import bo.Partie;
import dal.DAOFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HomeBean implements Serializable {

    private List<Partie> partie;
    private List<Double> reponse = new ArrayList<Double>();
    private List<Double> resultat = new ArrayList<Double>();
    private String authResult;

    public HomeBean() {}

    public List<Double> getReponse() {
        return reponse;
    }

    public void setReponse(List<Double> reponse) {
        this.reponse = reponse;
    }

    public List<Double> getResultat() {
        return resultat;
    }

    public void setResultat(List<Double> resultat) {
        this.resultat = resultat;
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

    public void loadTopScore(HttpServletRequest request) {
        try {
            reponse.add(20.200);
            reponse.add(45.30);
            reponse.add(67.32);
            reponse.add(112.43);

            resultat.add(20.200);
            resultat.add(45.30);
            resultat.add(67.32);
            resultat.add(67.32);

            partie = DAOFactory.getTopScoreDAO().topScore();
            request.getSession().setAttribute("reponse", reponse);
            request.getSession().setAttribute("resultat", resultat);

        } catch ( SQLException e ) {
            authResult = "Pb de connexions à la base de données !";
        }

    }
}
