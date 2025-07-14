package org.example.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.dao.LeaveRequestDAO;
import org.example.entity.LeaveRequests;
import org.example.entity.Users;

import java.io.IOException;

@WebServlet("/request/detail")
public class LeaveRequestDetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        Users currentUser = (session != null) ? (Users) session.getAttribute("currentUser") : null;
        if (currentUser == null) {
            response.sendRedirect("login");
            return;
        }
        String idStr = request.getParameter("id");
        if (idStr == null) {
            response.sendRedirect("/home");
            return;
        }
        int requestId = Integer.parseInt(idStr);
        LeaveRequestDAO dao = new LeaveRequestDAO();
        LeaveRequests lr = dao.getLeaveRequestById(requestId);
        if (lr == null) {
            response.sendRedirect("/home");
            return;
        }
        request.setAttribute("leaveRequest", lr);
        request.getRequestDispatcher("/leave_request_detail.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        Users currentUser = (session != null) ? (Users) session.getAttribute("currentUser") : null;
        if (currentUser == null) {
            response.sendRedirect("login");
            return;
        }
        String idStr = request.getParameter("id");
        String action = request.getParameter("action");
        String processedReason = request.getParameter("processed_reason");
        if (idStr == null || action == null) {
            response.sendRedirect("/home");
            return;
        }
        int requestId = Integer.parseInt(idStr);
        LeaveRequestDAO dao = new LeaveRequestDAO();
        boolean success = false;
        if ("accept".equalsIgnoreCase(action)) {
            success = dao.processLeaveRequest(requestId, "Approved", currentUser.getUserId(), processedReason);
        } else if ("reject".equalsIgnoreCase(action)) {
            success = dao.processLeaveRequest(requestId, "Rejected", currentUser.getUserId(), processedReason);
        }
        if (success) {
            response.sendRedirect(request.getContextPath() + "/request/view-subordinates");
        } else {
            request.setAttribute("error", "Xử lý đơn thất bại. Vui lòng thử lại!");
            doGet(request, response);
        }
    }
} 