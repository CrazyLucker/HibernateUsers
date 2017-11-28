package servlets;

import dbservice.dao.Dao;
import dbservice.dao.DaoImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteServlet extends HttpServlet {
    Dao dao = new DaoImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        int id = Integer.valueOf(req.getParameter("id"));
        try {
            dao.deleteUser(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        resp.sendRedirect("/");
    }
}
