package controller;

import model.HomeBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet( "/home" )
public class HomeController extends HttpServlet {

    private static final String PAGE_HOME_JSP = "/WEB-INF/jsp/home.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {

        HomeBean bean = new HomeBean();
        req.setAttribute( "homeBean", bean );
        bean.loadTopScore( req );
        req.getServletContext().getRequestDispatcher( PAGE_HOME_JSP ).forward( req, resp );
    }
}
