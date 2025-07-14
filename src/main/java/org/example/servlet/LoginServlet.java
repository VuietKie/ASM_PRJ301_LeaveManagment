package org.example.servlet;

import java.io.IOException;

import org.example.dao.UserDAO;
import org.example.entity.Users;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        System.out.println("DEBUG: username=" + username);
        System.out.println("DEBUG: password=" + password);

        UserDAO userDAO = new UserDAO();
        Users existingUser = userDAO.getUserByUsername(username);

        if (existingUser != null) {
            System.out.println("DEBUG: password hash in DB=" + existingUser.getPassword());
            boolean match = org.mindrot.jbcrypt.BCrypt.checkpw(password, existingUser.getPassword());
            System.out.println("DEBUG: BCrypt match=" + match);
            if (match) {
                HttpSession session = request.getSession();
                session.setAttribute("currentUser", existingUser);
                // Lấy danh sách feature theo RBAC
                java.util.List<org.example.entity.Features> features = userDAO.getFeaturesByUserId(existingUser.getUserId());
                session.setAttribute("features", features);
                // Chuyển hướng tất cả user (kể cả admin) sang /home
                response.sendRedirect("home");
                return;
            }
        }
        HttpSession session = request.getSession();
        session.setAttribute("loginError", "Invalid username or password. Please try again.");
        response.sendRedirect("login.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
} 