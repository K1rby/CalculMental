package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/logout")
public class DisconnectController extends HttpServlet {

    private static final String PAGE_LOGIN_JSP = "/WEB-INF/jsp/login.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if(session != null)
            session.invalidate();
        req.getRequestDispatcher(PAGE_LOGIN_JSP).forward(req,resp);
    }

}
