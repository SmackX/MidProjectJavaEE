package servlets.functional;

import dao.PostDAO;
import dao.UserDao;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class DeletePost extends HttpServlet {

    private static UserDao dao;
    private static PostDAO dao2;
    @Override
    public void init() throws ServletException {
        dao = new UserDao();
        dao.getBD();
        dao2 = new PostDAO();
        dao2.getBD();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();



        String login = (String) session.getAttribute("login");
        int userID = dao.getId(login);
        String pass = (String) session.getAttribute("password");
        User.Role role = dao.getRoleByLoginPassword( login, pass);

        if ( role.equals(User.Role.USER) || role.equals(User.Role.ADMIN) ) {
            int index = Integer.parseInt(req.getParameter("deleteNumber"));
            session.setAttribute("deleteNumber", index);
            dao2.DeleteFromDB(index);
            System.out.println(index);
            req.getRequestDispatcher("index.jsp").forward(req,resp);
        }else {
            out.println("access denied");
        }
    }
}
