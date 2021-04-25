package servlets.functional;

import dao.UserDao;
import model.Article;
import model.User;
import dao.PostDAO;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class CRUD extends HttpServlet {
    public String text = "";
    //ArrayList list;

    private static UserDao dao;
    private static PostDAO dao2;
    @Override
    public void init() throws ServletException {
         //list = new ArrayList();
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

        if (role.equals(User.Role.USER) || role.equals(User.Role.ADMIN)) {
            String text1 = (String) req.getParameter("text1");
            session.setAttribute("text", text1);
            dao2.AddToDB(dao2.sizeStore(), userID, text1,0);
            req.getRequestDispatcher("New_Post").forward(req,resp);
        }else {
            out.println("access denied");
        }

    }



}
