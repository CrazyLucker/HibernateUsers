package servlets;

import dbservice.dao.DaoImpl;
import dbservice.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class StartPageServlet extends HttpServlet {
    private static String index = "/WEB-INF/view/index.jsp";
    DaoImpl dao = new DaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        ArrayList<User> users = null;
        try {
            users = dao.getAllUsers();
        } catch (Exception e) {
            e.printStackTrace();
        }
        req.setAttribute("users", users);
        req.getRequestDispatcher(index).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        int age = Integer.parseInt(req.getParameter("age"));

        try {
            dao.addUser(new User(name, surname,age));
        } catch (Exception e) {
            e.printStackTrace();
        }

        doGet(req, resp);
    }
}
