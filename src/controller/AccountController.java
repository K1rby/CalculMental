package controller;

import model.AccountBean;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet( urlPatterns = {"/account", "/account/score", "/account/details"} )
public class AccountController extends HttpServlet {

    private static final String PAGE_ACCOUNT_JSP = "/WEB-INF/jsp/accountScore.jsp";
    private static final String PAGE_HOME_JSP = "/home";
    private static final String PAGE_ACCOUNT2_JSP = "/WEB-INF/jsp/accountDetail.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {

        String path = req.getServletPath();
        String realPath = path.substring( path.lastIndexOf( "/" )+ 1 );
        AccountBean bean = ( AccountBean ) req.getAttribute( "accountBean" );
        if ( bean == null ) {
            bean = new AccountBean();
            req.setAttribute( "accountBean", bean );
        }
        switch ( realPath ) {
            case "score" :
                try {
                    bean.loadTopScoreJoueur( req );
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                req.getServletContext().getRequestDispatcher( PAGE_ACCOUNT_JSP ).forward( req, resp );
                break;

            case "details":
            default:
                //bean.loadContactsList( request );
                req.getServletContext().getRequestDispatcher( PAGE_ACCOUNT2_JSP ).forward( req, resp );
        }
    }

    @Override
    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

        AccountBean bean = new AccountBean();
        try {
            bean.updateJoueur( request );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute( "accountBean", bean );
        if (bean.getAuthResult() == null) {
            response.sendRedirect( request.getContextPath() + PAGE_HOME_JSP );
        }
        else {
            request.getServletContext().getRequestDispatcher( PAGE_ACCOUNT2_JSP ).forward( request, response );
        }
    }
}
