package org.example.servlet;

import java.io.IOException;
import java.util.List;

import org.example.dao.LeaveRequestDAO;
import org.example.entity.Users;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/request/mylr")
public class MyLeaveRequestsServlet extends BaseRBACServlet {
    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response, Users currentUser) throws ServletException, IOException {
        LeaveRequestDAO dao = new LeaveRequestDAO();
        List<org.example.entity.LeaveRequests> myRequests = dao.getLeaveRequestsByUserId(currentUser.getUserId());
        request.setAttribute("myRequests", myRequests);
        request.getRequestDispatcher("/my_leave_requests.jsp").forward(request, response);
    }

    @Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response, Users currentUser) throws ServletException, IOException {
        response.sendError(405, "Method Not Allowed");
    }
} 