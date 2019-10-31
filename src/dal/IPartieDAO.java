package dal;

import bo.Partie;

import java.sql.SQLException;
import java.util.List;

public interface IPartieDAO<ID, E> extends IDAO<ID, E> {

    public List<Partie> topScore() throws SQLException;
    public List<Partie> topScoreJoueur(ID id) throws SQLException;
}
