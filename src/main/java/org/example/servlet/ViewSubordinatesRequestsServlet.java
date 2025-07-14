package org.example.servlet;

import java.io.IOException;

import org.example.dao.LeaveRequestDAO;
import org.example.entity.Users;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/request/view-subordinates")
public class ViewSubordinatesRequestsServlet extends BaseRBACServlet {
    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response, Users currentUser) throws ServletException, IOException {
        LeaveRequestDAO dao = new LeaveRequestDAO();
        java.util.List<org.example.entity.LeaveRequests> subRequests = dao.getLeaveRequestsOfDepartment(currentUser.getDepartmentId(), currentUser.getUserId());
        request.setAttribute("subRequests", subRequests);
        request.getRequestDispatcher("/view_subordinates_requests.jsp").forward(request, response);
    }

    @Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response, Users currentUser) throws ServletException, IOException {
        response.sendError(405, "Method Not Allowed");
    }
} 