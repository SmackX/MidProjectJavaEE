package servlets.filters;


import dao.UserDao;
import model.User;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import static java.util.Objects.nonNull;

public class AuthFilter implements Filter {
    private static UserDao dao;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        dao = new UserDao();
        dao.getBD();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        final HttpServletRequest req = (HttpServletRequest) request;
        final HttpServletResponse res = (HttpServletResponse) response;

        String login = req.getParameter("login");
        String password = req.getParameter("password");

        //req.getRequestDispatcher("/admin_menu.jsp").forward(req, res);
        System.out.println(login + "  " + password+ "                        here!" );

        HttpSession session = req.getSession();
        if (nonNull(session) &&
                nonNull(session.getAttribute("login")) &&
                nonNull(session.getAttribute("password"))) {

            final User.Role role = (User.Role) session.getAttribute("role");
            moveToMenu(req, res, role);


        } else if (dao.userIsExist(login, password)) {

            final User.Role role = dao.getRoleByLoginPassword(login, password);

            req.getSession().setAttribute("password", password);
            req.getSession().setAttribute("login", login);
            req.getSession().setAttribute("role", role);

            moveToMenu(req, res, role);

        } else {
            moveToMenu(req, res, model.User.Role.UNKNOWN);
        }
    }


    private void moveToMenu(final HttpServletRequest req, final HttpServletResponse res, final User.Role role) throws ServletException, IOException {


        if (role.equals(model.User.Role.ADMIN)) {
            System.out.println("ADMIN BOX");
            //req.getRequestDispatcher("/admin_menu.jsp").forward(req, res);
            RequestDispatcher rd = req.getRequestDispatcher("/admin_menu.jsp");
            rd.forward(req,res);

        } else if (role.equals(model.User.Role.USER)) {

            req.getRequestDispatcher("/user_menu.jsp").forward(req, res);

        } else {
            req.getRequestDispatcher("/login.jsp").forward(req, res);
        }
    }



    @Override
    public void destroy() {

    }
}
