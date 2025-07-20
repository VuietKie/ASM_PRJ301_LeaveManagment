package org.example.servlet;

import java.io.IOException;
import java.util.List;

import org.example.dao.UsersDAO;
import org.example.entity.Users;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/admin/users")
public class AdminUsersServlet extends BaseRBACServlet {
    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response, Users currentUser) throws ServletException, IOException {
        UsersDAO usersDAO = new UsersDAO();
        int pageSize = 5;
        int pageindex = 1;
        String pageParam = request.getParameter("page");
        if (pageParam != null) {
            try { pageindex = Integer.parseInt(pageParam); } catch (Exception ignored) {}
        }
        int totalRecords = usersDAO.countAllUsers();
        int totalpage = (int) Math.ceil(totalRecords * 1.0 / pageSize);
        List<Users> users = usersDAO.getUsersByPage(pageindex, pageSize);
        request.setAttribute("users", users);
        request.setAttribute("pageindex", pageindex);
        request.setAttribute("totalpage", totalpage);
        request.getRequestDispatcher("/admin_users.jsp").forward(request, response);
    }

    @Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response, Users currentUser) throws ServletException, IOException {
        response.sendError(405, "Method Not Allowed");
    }
} 