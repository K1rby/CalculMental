package bo;

import java.io.Serializable;

public class Joueur implements Serializable {

    private int id;
    private String pseudo;
    private String password;

    public Joueur() {}

    public Joueur(String pseudo, String password) {
        this.pseudo = pseudo;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
