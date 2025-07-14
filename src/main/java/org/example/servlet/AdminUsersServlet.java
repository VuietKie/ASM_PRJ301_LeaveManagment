package org.example.servlet;

import java.io.IOException;
import java.util.List;

import org.example.dao.UsersDAO;
import org.example.entity.Users;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/admin/users")
public class AdminUsersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        Users currentUser = (session != null) ? (Users) session.getAttribute("currentUser") : null;
        if (currentUser == null) {
            response.sendRedirect("login");
            return;
        }
        UsersDAO usersDAO = new UsersDAO();
        List<Users> users = usersDAO.getAllUsers();
        request.setAttribute("users", users);
        request.getRequestDispatcher("/admin_users.jsp").forward(request, response);
    }
} 