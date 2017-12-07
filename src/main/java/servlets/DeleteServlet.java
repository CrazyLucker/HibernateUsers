package servlets;

import dbservice.dao.DaoFactory;
import dbservice.dao.UserDao;
import dbservice.dao.UserDaoHibernateImpl;
import dbservice.dao.UserDaoJDBCImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {
    UserDao userDao = DaoFactory.getRealisation();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        int id = Integer.valueOf(req.getParameter("id"));
        userDao.deleteUser(id);
        resp.sendRedirect("/");
    }
}
