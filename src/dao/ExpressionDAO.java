package dao;

import bo.Expression;
import dal.DAOFactory;
import dal.IDAO;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExpressionDAO implements IDAO<Long, Expression> {

    private static final String INSERT_QUERY = "INSERT INTO expression (expression, id_partie ) VALUES (?, ?)";
    private static final String UPDATE_QUERY = "UPDATE expression SET expression = ?, id_partie = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM agence WHERE id= ?";
    private static final String FIND_QUERY = "SELECT * FROM agence WHERE id= ?";
    private static final String FIND_ALLQUERY = "SELECT * FROM expression";

    @Override
    public int create(Expression object) throws SQLException, IOException, ClassNotFoundException {
        int temp = 0;
        Connection connection = DAOFactory.getJDBCConnection();
        if (connection != null) {
            try (PreparedStatement ps = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {

                ps.setString(1, object.getExpression());
                ps.setInt(2, object.getId_partie());
                ps.executeUpdate();

                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        object.setId(rs.getInt(1));
                    }
                    temp = rs.getInt(1);
                }
            }
            if (!connection.isClosed()) {
                connection.close();
            }
        }
        return temp;
    }

    @Override
    public void update(Expression object) throws SQLException, IOException, ClassNotFoundException {

        Connection connection = DAOFactory.getJDBCConnection();
        if (connection != null) {

            try (PreparedStatement ps = connection.prepareStatement(UPDATE_QUERY)) {

                ps.setString(1, object.getExpression());
                ps.setInt(2, object.getId_partie());
                ps.setInt(3, object.getId());
                ps.executeUpdate();
            }
        }
    }

    @Override
    public void remove(Expression object) throws SQLException, IOException, ClassNotFoundException {

    }

    @Override
    public Expression findById(Long aLong) throws SQLException, IOException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<Expression> findAll() throws SQLException, IOException, ClassNotFoundException {

        List<Expression> list = new ArrayList<>();
        Connection connection = DAOFactory.getJDBCConnection();

        if (connection != null) {

            try(PreparedStatement ps = connection.prepareStatement(FIND_ALLQUERY)) {
                try(ResultSet rs = ps.executeQuery()) {

                    while (rs.next()) {
                        Expression expression = new Expression();
                        expression.setExpression(rs.getString("expression"));
                        expression.setId(rs.getInt("id"));
                        expression.setId_partie(rs.getInt("id_partie"));
                        list.add(expression);
                    }
                }
            }
        }
        return list;
    }
}
