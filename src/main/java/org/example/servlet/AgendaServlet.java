package org.example.servlet;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.example.dao.LeaveRequestDAO;
import org.example.entity.LeaveRequests;
import org.example.entity.Users;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/agenda")
public class AgendaServlet extends BaseRBACServlet {
    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response, Users currentUser) throws ServletException, IOException {
        request.getRequestDispatcher("/agenda.jsp").forward(request, response);
    }

    @Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response, Users currentUser) throws ServletException, IOException {
        String fromStr = request.getParameter("from_date");
        String toStr = request.getParameter("to_date");
        if (fromStr == null || toStr == null) {
            request.setAttribute("error", "Vui lòng chọn khoảng thời gian!");
            request.getRequestDispatcher("/agenda.jsp").forward(request, response);
            return;
        }
        LocalDate from = LocalDate.parse(fromStr);
        LocalDate to = LocalDate.parse(toStr);
        org.example.dao.DepartmentsDAO departmentsDAO = new org.example.dao.DepartmentsDAO();
        java.util.List<org.example.entity.Departments> departments = departmentsDAO.getAllDepartments();
        String departmentIdStr = request.getParameter("department_id");
        Integer departmentId = null;
        if (departmentIdStr != null && !departmentIdStr.isEmpty()) {
            try { departmentId = Integer.parseInt(departmentIdStr); } catch (Exception ignored) {}
        }
        java.util.List<org.example.entity.Users> users;
        org.example.dao.UsersDAO usersDAO = new org.example.dao.UsersDAO();
        if (departmentId != null) {
            users = new java.util.ArrayList<>();
            for (org.example.entity.Users u : usersDAO.getAllUsers()) {
                if (u.getDepartmentId() == departmentId) users.add(u);
            }
        } else {
            users = usersDAO.getAllUsers();
        }
        request.setAttribute("departments", departments);
        request.setAttribute("selectedDepartmentId", departmentId);

        LeaveRequestDAO lrDAO = new LeaveRequestDAO();
        List<LeaveRequests> leaveRequests = lrDAO.getLeaveRequestsInRange(Date.valueOf(from), Date.valueOf(to));
        Map<Integer, List<LeaveRequests>> userLeaveMap = new HashMap<>();
        for (LeaveRequests lr : leaveRequests) {
            userLeaveMap.computeIfAbsent(lr.getUserId(), k -> new ArrayList<>()).add(lr);
        }
        request.setAttribute("users", users);
        request.setAttribute("from", from);
        request.setAttribute("to", to);
        request.setAttribute("userLeaveMap", userLeaveMap);
        request.getRequestDispatcher("/agenda.jsp").forward(request, response);
    }
} 