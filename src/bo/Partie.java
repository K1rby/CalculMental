package bo;

import java.io.Serializable;

public class Partie implements Serializable {

    private int id;
    private int score;
    private int id_joueur;

    public Partie() {}

    public Partie(int id, int score, int id_joueur) {
        this.id = id;
        this.score = score;
        this.id_joueur = id_joueur;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getId_joueur() {
        return id_joueur;
    }

    public void setId_joueur(int id_joueur) {
        this.id_joueur = id_joueur;
    }
}
