package org.example.servlet;

import java.io.IOException;
import java.sql.Date;

import org.example.dao.LeaveRequestDAO;
import org.example.entity.Users;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/request/create")
public class CreateLeaveRequestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        Users currentUser = (session != null) ? (Users) session.getAttribute("currentUser") : null;
        if (currentUser == null) {
            response.sendRedirect("login");
            return;
        }
        request.getRequestDispatcher("/create_leave_request.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        Users currentUser = (session != null) ? (Users) session.getAttribute("currentUser") : null;
        if (currentUser == null) {
            response.sendRedirect("login");
            return;
        }
        String startDateStr = request.getParameter("start_date");
        String endDateStr = request.getParameter("end_date");
        String title = request.getParameter("title");
        String reason = request.getParameter("reason");
        LeaveRequestDAO dao = new LeaveRequestDAO();
        boolean success = dao.createLeaveRequest(currentUser.getUserId(), Date.valueOf(startDateStr), Date.valueOf(endDateStr), title, reason);
        if (success) {
            session.setAttribute("successMessage", "Tạo đơn xin nghỉ thành công!");
            response.sendRedirect(request.getContextPath() + "/home");
        } else {
            request.setAttribute("error", "Tạo đơn xin nghỉ thất bại. Vui lòng thử lại!");
            request.getRequestDispatcher("/create_leave_request.jsp").forward(request, response);
        }
    }
} 