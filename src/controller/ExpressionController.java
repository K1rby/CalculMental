package controller;

import bo.Expression;
import bo.Joueur;
import bo.Partie;
import dao.ExpressionDAO;
import dao.PartieDAO;
import model.ExpressionBean;
import model.UserBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/expression")
public class ExpressionController extends HttpServlet {

    private static final String PAGE_EXPRESSION_JSP = "/WEB-INF/jsp/expression.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ExpressionBean bean = (ExpressionBean) request.getSession().getAttribute("expressionBean");
        if (bean == null){
            bean = new ExpressionBean();
            request.getSession().setAttribute("expressionBean", bean);
        }
        int idPartie = 0;
        Partie partie = new Partie();
        partie.setScore(0);
        Joueur joueur = (Joueur)request.getSession().getAttribute("connectedUser");
        partie.setId_joueur(joueur.getId());
        PartieDAO partieDAO = new PartieDAO();
        try {
            idPartie = partieDAO.create(partie);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String libelle;
        int i = 0;
        int idExpression = 0;
        for (i = 1; i<11; i++) {
            libelle = bean.generationExpression();
            Expression expression = new Expression();
            expression.setExpression(libelle);
            expression.setId_partie(idPartie);
            ExpressionDAO expressionDAO = new ExpressionDAO();
            try {
                idExpression = expressionDAO.create(expression);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            expression.setId(idExpression);
            bean.addExpression(expression);

            bean.addResultats(bean.calculExpression());
        }
        request.getServletContext().getRequestDispatcher(PAGE_EXPRESSION_JSP).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
