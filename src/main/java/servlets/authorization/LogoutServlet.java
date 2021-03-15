package servlets.authorization;


import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final HttpSession session = req.getSession();
        System.out.println("Person: "+session.getAttribute("login")+ " Role: "+ session.getAttribute("role"));
        session.removeAttribute("password");
        session.removeAttribute("login");
        session.removeAttribute("role");
        RequestDispatcher rd = req.getRequestDispatcher("/login.jsp");
        rd.forward(req,resp);
    }

}
