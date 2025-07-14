package org.example.servlet;

import java.io.IOException;
import java.util.List;

import org.example.dao.LeaveRequestDAO;
import org.example.entity.Users;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/request/mylr")
public class MyLeaveRequestsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        Users currentUser = (session != null) ? (Users) session.getAttribute("currentUser") : null;
        if (currentUser == null) {
            response.sendRedirect("login");
            return;
        }
        LeaveRequestDAO dao = new LeaveRequestDAO();
        List<org.example.entity.LeaveRequests> myRequests = dao.getLeaveRequestsByUserId(currentUser.getUserId());
        request.setAttribute("myRequests", myRequests);
        request.getRequestDispatcher("/my_leave_requests.jsp").forward(request, response);
    }
} 