package servlets.functional;

import dao.LikesDAO;
import dao.PostDAO;
import dao.UserDao;
import model.User;
import model.Article;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
public class New_Post extends HttpServlet {
    private static String text = "";

    public static String getText() {
        return text;
    }

    public void setText(String text1) {
        text = text1;
    }

    private static int id=0;

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        New_Post.id = id;
    }

    private static String Name = "";

    public static int getSumRatings() {
        return sumRatings;
    }

    public static void setSumRatings(int sumRatings) {
        New_Post.sumRatings = sumRatings;
    }

    private static int sumRatings = 0;

    private static UserDao dao;
    private static PostDAO dao2;
    private static LikesDAO dao3;


    public static String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    @Override
    public void init() throws ServletException {
        dao = new UserDao();
        dao.getBD();

        dao2 = new PostDAO();
        dao2.getBD();

        dao3 = new LikesDAO();
        dao3.getBD();
        setSumRatings(dao3.sizeStore());
    }
    private static int index=0;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();


        model.Article article = dao2.getData(index);
        setName(dao.getName(article.getUserId()));
        setText(article.getText());
        setId(article.getId());
        System.out.println(" LOL: " + getName() +" "+ getText());
        req.getRequestDispatcher("Theme1.jsp").forward(req,resp);


    }

}
