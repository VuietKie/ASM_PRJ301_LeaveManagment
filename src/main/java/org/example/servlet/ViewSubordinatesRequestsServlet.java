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
        int pageSize = 5;
        int pageindex = 1;
        String pageParam = request.getParameter("page");
        if (pageParam != null) {
            try { pageindex = Integer.parseInt(pageParam); } catch (Exception ignored) {}
        }
        int totalRecords = dao.countLeaveRequestsOfDepartment(currentUser.getDepartmentId(), currentUser.getUserId());
        int totalpage = (int) Math.ceil(totalRecords * 1.0 / pageSize);
        java.util.List<org.example.entity.LeaveRequests> subRequests = dao.getLeaveRequestsOfDepartmentPaged(currentUser.getDepartmentId(), currentUser.getUserId(), pageindex, pageSize);
        request.setAttribute("subRequests", subRequests);
        request.setAttribute("pageindex", pageindex);
        request.setAttribute("totalpage", totalpage);
        request.getRequestDispatcher("/view_subordinates_requests.jsp").forward(request, response);
    }

    @Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response, Users currentUser) throws ServletException, IOException {
        response.sendError(405, "Method Not Allowed");
    }
} 