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
        // Lọc filteredUsers như cũ
        java.util.List<org.example.entity.Users> filteredUsers = new java.util.ArrayList<>();
        for (org.example.entity.Users u : users) {
            boolean hasLeave = false;
            for (LocalDate d = from; !d.isAfter(to); d = d.plusDays(1)) {
                boolean isLeave = false;
                if (userLeaveMap != null && userLeaveMap.containsKey(u.getUserId())) {
                    for (LeaveRequests lr : userLeaveMap.get(u.getUserId())) {
                        java.util.Date start = lr.getStartDate();
                        java.util.Date end = lr.getEndDate();
                        LocalDate startLocal, endLocal;
                        if (start instanceof java.sql.Date) {
                            startLocal = ((java.sql.Date) start).toLocalDate();
                        } else {
                            startLocal = start.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
                        }
                        if (end instanceof java.sql.Date) {
                            endLocal = ((java.sql.Date) end).toLocalDate();
                        } else {
                            endLocal = end.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
                        }
                        if (!d.isBefore(startLocal) && !d.isAfter(endLocal)) {
                            isLeave = true;
                            break;
                        }
                    }
                }
                if (isLeave) {
                    hasLeave = true;
                    break;
                }
            }
            if (hasLeave) filteredUsers.add(u);
        }
        // Phân trang filteredUsers
        int pageSize = 5;
        int pageindex = 1;
        String pageParam = request.getParameter("page");
        if (pageParam != null) {
            try { pageindex = Integer.parseInt(pageParam); } catch (Exception ignored) {}
        }
        int totalRecords = filteredUsers.size();
        int totalpage = (int) Math.ceil(totalRecords * 1.0 / pageSize);
        int fromIdx = (pageindex - 1) * pageSize;
        int toIdx = Math.min(fromIdx + pageSize, totalRecords);
        java.util.List<org.example.entity.Users> filteredUsersPage = new java.util.ArrayList<>();
        if (fromIdx < toIdx) filteredUsersPage = filteredUsers.subList(fromIdx, toIdx);
        request.setAttribute("users", users); // giữ nguyên cho các logic khác
        request.setAttribute("filteredUsers", filteredUsersPage);
        request.setAttribute("from", from);
        request.setAttribute("to", to);
        request.setAttribute("userLeaveMap", userLeaveMap);
        request.setAttribute("pageindex", pageindex);
        request.setAttribute("totalpage", totalpage);
        request.getRequestDispatcher("/agenda.jsp").forward(request, response);
    }
} 