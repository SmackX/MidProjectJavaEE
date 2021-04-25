package servlets.functional;

import dao.LikesDAO;
import dao.PostDAO;
import dao.UserDao;
import model.Likes;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Like extends HttpServlet {
    private static int postId, userId;

    private static LikesDAO dao3;
    private static PostDAO dao2;

    @Override
    public void init(ServletConfig config) throws ServletException {
        dao3 = new LikesDAO();
        dao3.getBD();
        dao2 = new PostDAO();
        dao2.getBD();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        postId = Integer.parseInt( req.getParameter("postID") );
        userId = Integer.parseInt( req.getParameter("userID") );
        if (dao3.HaveTo(userId)){
        dao3.AddToDB(dao3.sizeStore(), userId, postId);
        dao2.AddLike(postId);
        }
    }
}
