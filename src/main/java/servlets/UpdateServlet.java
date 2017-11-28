package servlets;

import dbservice.dao.DaoImpl;
import dbservice.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateServlet extends HttpServlet {
    DaoImpl dao = new DaoImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        int id = Integer.parseInt(req.getParameter("id"));
        User user = null;
        try {
            user = dao.getUserById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        req.setAttribute("user", user);
        
        req.getRequestDispatcher("WEB-INF/view/update.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        int id = Integer.valueOf(req.getParameter("id"));
        int age = Integer.valueOf(req.getParameter("age"));
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        try {
            dao.updateUser(id, name, surname, age);
        } catch (Exception e) {
            e.printStackTrace();
        }
        resp.sendRedirect("/");
    }
}
