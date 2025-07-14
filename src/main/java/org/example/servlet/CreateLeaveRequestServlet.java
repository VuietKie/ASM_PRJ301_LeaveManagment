package org.example.servlet;

import java.io.IOException;
import java.sql.Date;

import org.example.dao.LeaveRequestDAO;
import org.example.entity.Users;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/request/create")
public class CreateLeaveRequestServlet extends BaseRBACServlet {
    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response, Users currentUser) throws ServletException, IOException {
        request.getRequestDispatcher("/create_leave_request.jsp").forward(request, response);
    }

    @Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response, Users currentUser) throws ServletException, IOException {
        String startDateStr = request.getParameter("start_date");
        String endDateStr = request.getParameter("end_date");
        String title = request.getParameter("title");
        String reason = request.getParameter("reason");
        LeaveRequestDAO dao = new LeaveRequestDAO();
        boolean success = dao.createLeaveRequest(currentUser.getUserId(), Date.valueOf(startDateStr), Date.valueOf(endDateStr), title, reason);
        if (success) {
            request.getSession().setAttribute("successMessage", "Tạo đơn xin nghỉ thành công!");
            response.sendRedirect(request.getContextPath() + "/home");
        } else {
            request.setAttribute("error", "Tạo đơn xin nghỉ thất bại. Vui lòng thử lại!");
            processGet(request, response, currentUser);
        }
    }
} 