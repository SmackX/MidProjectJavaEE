package servlets.functional;

import dao.UserDao;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class CRUD extends HttpServlet {
    public String text = "";
    ArrayList list;

    private static UserDao dao;
    @Override
    public void init() throws ServletException {
         list = new ArrayList();
         dao = new UserDao();
         dao.getBD();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();


        String login = (String) session.getAttribute("login");
        String pass = (String) session.getAttribute("password");
        User.Role role = dao.getRoleByLoginPassword( login, pass);
        System.out.println("Role this user: "+role + " Login: " + login + " pass:" + pass);
        if (role.equals(User.Role.USER) || role.equals(User.Role.ADMIN)) {
            String text1 = (String) req.getParameter("text1");
//            String text2 = (String) req.getParameter("text2");
//            String text3 = (String) req.getParameter("text3");
//            list.add(text2);
//            list.add(text3);
            session.setAttribute("text", text1);

            out.println("<html><body>");
            for (int i = 0; i < list.size(); i++) {
                out.println("<h1>" + i + ": " + list.get(i) + "</h1>");
            }
            out.println("</body></html>");
        }else {
            out.println("access denied");
        }
        req.getRequestDispatcher("index.jsp").forward(req,resp);
    }
    public void Push(){

        System.out.println("Good!");    }
}
