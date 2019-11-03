package dao;

import bo.Partie;
import dal.DAOFactory;
import dal.IDAO;
import dal.IPartieDAO;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PartieDAO implements IDAO<Long, Partie>, IPartieDAO<Long, Partie> {

    private static final String INSERT_QUERY = "INSERT INTO partie (score, id_joueur ) VALUES (?, ?)";
    private static final String UPDATE_QUERY = "UPDATE partie SET score = ?, id_joueur = ? WHERE id = ?";
    private static final String TOP_SCORE_QUERY = "SELECT score from partie order by score DESC limit 10";
    private static final String TOP_SCORE_JOUEUR_QUERY = "SELECT score from partie where id_joueur = ? order by score DESC limit 10";
    private static final String DELETE_QUERY = "DELETE FROM partie WHERE id= ?";
    private static final String FIND_QUERY = "SELECT * FROM partie WHERE id= ?";
    private static final String FIND_ALLQUERY = "SELECT * FROM partie";

    @Override
    public int create(Partie object) throws SQLException, IOException, ClassNotFoundException {
        int temp = 0;
        Connection connection = DAOFactory.getJDBCConnection();
        if (connection != null) {
            try (PreparedStatement ps = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {

                ps.setInt(1, object.getScore());
                ps.setInt(2, object.getId_joueur());
                ps.executeUpdate();

                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        object.setId(rs.getInt(1));
                    }
                    temp =  rs.getInt(1);
                }
            }
            if (!connection.isClosed()) {
                connection.close();
            }
        }
        return temp;
    }

    @Override
    public void update(Partie object) throws SQLException, IOException, ClassNotFoundException {

        Connection connection = DAOFactory.getJDBCConnection();
        if (connection != null) {

            try (PreparedStatement ps = connection.prepareStatement(UPDATE_QUERY)) {

                ps.setInt(1, object.getScore());
                ps.setInt(2, object.getId_joueur());
                ps.setInt(3, object.getId());
                ps.executeUpdate();
            }
        }
    }

    @Override
    public void remove(Partie object) throws SQLException, IOException, ClassNotFoundException {

    }

    @Override
    public Partie findById(Long aLong) throws SQLException, IOException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<Partie> findAll() throws SQLException, IOException, ClassNotFoundException {

        List<Partie> list = new ArrayList<>();
        Connection connection = DAOFactory.getJDBCConnection();

        if (connection != null) {

            try(PreparedStatement ps = connection.prepareStatement(FIND_ALLQUERY)) {
                try(ResultSet rs = ps.executeQuery()) {

                    while (rs.next()) {
                        Partie partie = new Partie();
                        partie.setScore(rs.getInt("score"));
                        partie.setId(rs.getInt("id"));
                        partie.setId_joueur(rs.getInt("id_joueur"));
                        list.add(partie);
                    }
                }
            }
        }
        return list;
    }

    @Override
    public List<Partie> topScore() throws SQLException {

        List<Partie> list = new ArrayList<>();
        Connection connection = DAOFactory.getJDBCConnection();

        if (connection != null) {

            try(PreparedStatement ps = connection.prepareStatement(TOP_SCORE_QUERY)) {
                try(ResultSet rs = ps.executeQuery()) {

                    while (rs.next()) {
                        Partie partie = new Partie();
                        partie.setScore(rs.getInt("score"));
                        list.add(partie);
                    }
                }
            }
        }
        if (!connection.isClosed()) {
            connection.close();
        }
        return list;
    }
    @Override
    public List<Partie> topScoreJoueur(Long id) throws SQLException {

        List<Partie> list = new ArrayList<>();
        Connection connection = DAOFactory.getJDBCConnection();

        if (connection != null) {

            try(PreparedStatement ps = connection.prepareStatement(TOP_SCORE_JOUEUR_QUERY)) {
                ps.setLong(1, id);
                try(ResultSet rs = ps.executeQuery()) {

                    while (rs.next()) {
                        Partie partie = new Partie();
                        partie.setScore(rs.getInt("score"));
                        list.add(partie);
                    }
                }
            }
        }
        if (!connection.isClosed()) {
            connection.close();
        }
        return list;
    }
}
