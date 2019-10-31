package model;

import bo.Joueur;
import bo.Partie;
import dal.DAOFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.*;

public class ResultBean implements Serializable {

    private LinkedHashMap<Double, Double> resultReponse = new LinkedHashMap<>();
    private Partie partie = new Partie();
    private List<Double> reponse = new ArrayList<>();
    private List<Double> resultat = new ArrayList<>();

    public ResultBean() { }

    public Partie getPartie() {
        return partie;
    }

    public void setPartie(Partie partie) {
        this.partie = partie;
    }

    public LinkedHashMap<Double, Double> getResultReponse() {
        return resultReponse;
    }

    public void setResultReponse(LinkedHashMap<Double, Double> resultReponse) {
        this.resultReponse = resultReponse;
    }

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

    public void recupResultat(HttpServletRequest request) throws SQLException, IOException, ClassNotFoundException {

        reponse = (List<Double>) request.getSession().getAttribute("reponse");
        resultat = (List<Double>) request.getSession().getAttribute("resultat");

        for (int i = 0; i < reponse.size(); i++) {
            resultReponse.put(reponse.get(i), resultat.get(i));
        }

        int score = 0;
        Iterator iterator = resultReponse.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry mapentry = (Map.Entry) iterator.next();

            if (mapentry.getKey().equals(mapentry.getValue())) {
                score = score + 4000;
            }
        }

        Joueur joueur = (Joueur) request.getSession().getAttribute("connectedUser");
        partie.setId_joueur(joueur.getId());
        partie.setScore(score);
        DAOFactory.PartieDAO().create(partie);
        request.setAttribute("resultReponse", resultReponse);
        request.setAttribute("score", score);
    }
}
