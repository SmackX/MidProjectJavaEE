package Logic;

import dao.PostDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Size extends HttpServlet {
    int size = 0;
    PostDAO dao;

    @Override
    public void init() throws ServletException {
        this.dao = new PostDAO();
        dao.getBD();
        this.size = dao.sizeStore();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        req.getSession().setAttribute("size", getSize());
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

}
