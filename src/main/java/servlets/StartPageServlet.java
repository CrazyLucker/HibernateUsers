package servlets;

import dbservice.dao.DaoFactory;
import dbservice.dao.UserDao;
import dbservice.dao.UserDaoHibernateImpl;
import dbservice.dao.UserDaoJDBCImpl;
import dbservice.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/")
public class StartPageServlet extends HttpServlet {
    private static String index = "/WEB-INF/view/startpage.jsp";
    UserDao userDao = DaoFactory.getRealisation();
    ArrayList<User> users;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        users = userDao.getAllUsers();
        req.setAttribute("users", users);
        req.getRequestDispatcher(index).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        int age = Integer.parseInt(req.getParameter("age"));
        userDao.addUser(new User(name, surname, age));
        doGet(req, resp);
    }
}
