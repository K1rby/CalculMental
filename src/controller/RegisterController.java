package controller;

import model.RegisterBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet( "/register" )
public class RegisterController extends HttpServlet {

    private static final String PAGE_REGISTER_JSP = "/WEB-INF/jsp/register.jsp";
    private static final String PAGE_LOGIN_JSP = "/login";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {

        RegisterBean bean = ( RegisterBean ) req.getAttribute( "registerBean" );
        if ( bean == null ) {
            bean = new RegisterBean();
            req.setAttribute( "registerBean", bean );
        }
        req.getServletContext().getRequestDispatcher( PAGE_REGISTER_JSP ).forward( req, resp );
    }

    @Override
    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

        RegisterBean bean = new RegisterBean();
        bean.register( request );
        request.setAttribute( "registerBean", bean );
        if (bean.getAuthResult() == null) {
            response.sendRedirect( request.getContextPath() + PAGE_LOGIN_JSP );
        }
        else {
            request.getServletContext().getRequestDispatcher( PAGE_REGISTER_JSP ).forward( request, response );
        }
    }
}
