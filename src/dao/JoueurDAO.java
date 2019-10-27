package dao;

import bo.Joueur;
import dal.DAOFactory;
import dal.IDAO;
import dal.IUserDAO;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JoueurDAO implements IDAO<Long, Joueur>, IUserDAO<Long, Joueur> {

    private static final String INSERT_QUERY = "INSERT INTO joueur (pseudo, password ) VALUES (?, ?)";
    private static final String UPDATE_QUERY = "UPDATE joueur SET pseudo = ?, password = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM agence WHERE id= ?";
    private static final String FIND_QUERY = "SELECT * FROM agence WHERE id= ?";
    private static final String FIND_ALLQUERY = "SELECT * FROM joueur";
    private static final String AUTHENT_QUERY = "SELECT * FROM joueur WHERE pseudo = ? AND password = ?";

    @Override
    public void create(Joueur object) throws SQLException, IOException, ClassNotFoundException {

        Connection connection = DAOFactory.getJDBCConnection();
        if (connection != null) {
            try (PreparedStatement ps = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {

                ps.setString(1, object.getPseudo());
                ps.setString(2, object.getPassword());
                ps.executeUpdate();

                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        object.setId(rs.getInt(1));
                    }

                }
            }
        }
    }

    @Override
    public void update(Joueur object) throws SQLException, IOException, ClassNotFoundException {

        Connection connection = DAOFactory.getJDBCConnection();
        if (connection != null) {

            try (PreparedStatement ps = connection.prepareStatement(UPDATE_QUERY)) {

                ps.setString(1, object.getPseudo());
                ps.setString(2, object.getPassword());
                ps.setInt(3, object.getId());
                ps.executeUpdate();
            }
        }
    }

    @Override
    public void remove(Joueur object) throws SQLException, IOException, ClassNotFoundException {

    }

    @Override
    public Joueur findById(Long aLong) throws SQLException, IOException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<Joueur> findAll() throws SQLException, IOException, ClassNotFoundException {

        List<Joueur> list = new ArrayList<>();
        Connection connection = DAOFactory.getJDBCConnection();

        if (connection != null) {

            try(PreparedStatement ps = connection.prepareStatement(FIND_ALLQUERY)) {
                try(ResultSet rs = ps.executeQuery()) {

                    while (rs.next()) {
                        Joueur joueur = new Joueur();
                        joueur.setPseudo(rs.getString("pseudo"));
                        joueur.setPassword(rs.getString("password"));
                        joueur.setId(rs.getInt("id"));
                        list.add(joueur);
                    }
                }
            }
        }
        return list;
    }

    @Override
    public Joueur authenticate(String pseudo, String password) throws SQLException {
        Connection connection = DAOFactory.getJDBCConnection();
        Joueur joueur = null;
        if ( null != connection ) {
            try ( PreparedStatement ps = connection
                    .prepareStatement( AUTHENT_QUERY, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE ) ) {
                ps.setString( 1, pseudo );
                ps.setString( 2, password );
                try ( ResultSet rs = ps.executeQuery() ) {
                    if ( rs.next() ) {
                        joueur = new Joueur();
                        joueur.setPseudo( rs.getString( "pseudo" ) );
                        joueur.setId(rs.getInt("id_joueur"));
                        joueur.setPassword( rs.getString( "password" ) );
                    }
                }
            }
        }
        return joueur;
    }
}
