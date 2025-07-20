package org.example.servlet;

import java.io.IOException;

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
        int pageSize = 5;
        int pageindex = 1;
        String pageParam = request.getParameter("page");
        if (pageParam != null) {
            try { pageindex = Integer.parseInt(pageParam); } catch (Exception ignored) {}
        }
        int totalRecords = dao.countLeaveRequestsByUserId(currentUser.getUserId());
        int totalpage = (int) Math.ceil(totalRecords * 1.0 / pageSize);
        java.util.List<org.example.entity.LeaveRequests> myRequests = dao.getLeaveRequestsByUserIdPaged(currentUser.getUserId(), pageindex, pageSize);
        request.setAttribute("myRequests", myRequests);
        request.setAttribute("pageindex", pageindex);
        request.setAttribute("totalpage", totalpage);
        request.getRequestDispatcher("/my_leave_requests.jsp").forward(request, response);
    }

    @Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response, Users currentUser) throws ServletException, IOException {
        response.sendError(405, "Method Not Allowed");
    }
} 