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


@WebServlet("/update")
public class UpdateServlet extends HttpServlet {
    UserDao userDao = DaoFactory.getRealisation();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        int id = Integer.parseInt(req.getParameter("id"));
        User user = userDao.getUserById(id);
        req.setAttribute("user", user);
        req.getRequestDispatcher("WEB-INF/view/update.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        int id = Integer.valueOf(req.getParameter("id"));
        User user = userDao.getUserById(id);
        user.setName(req.getParameter("name"));
        user.setSurname(req.getParameter("surname"));
        user.setAge(Integer.valueOf(req.getParameter("age")));
        userDao.updateUser(user);
        resp.sendRedirect("/");
    }
}
