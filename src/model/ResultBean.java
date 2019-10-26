package model;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.*;

public class ResultBean implements Serializable {

    private HashMap<Double, Double> resultReponse = new HashMap<>();
    private List<Double> reponse = new ArrayList<>();
    private List<Double> resultat = new ArrayList<>();

    public ResultBean() {
    }

    public HashMap<Double, Double> getResultReponse() {
        return resultReponse;
    }

    public void setResultReponse(HashMap<Double, Double> resultReponse) {
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

    public void recupResultat(HttpServletRequest request) {

        reponse = (List<Double>) request.getSession().getAttribute("reponse");
        resultat = (List<Double>) request.getSession().getAttribute("resultat");

        for (int i = 0; i < reponse.size(); i++) {
            resultReponse.put(reponse.get(i), resultat.get(i));
        }

        request.setAttribute("resultReponse", resultReponse);
    }
}
