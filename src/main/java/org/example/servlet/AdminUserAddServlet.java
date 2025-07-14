package org.example.servlet;

import java.io.IOException;
import java.util.List;

import org.example.dao.DepartmentsDAO;
import org.example.dao.UsersDAO;
import org.example.entity.Departments;
import org.example.entity.Users;
import org.mindrot.jbcrypt.BCrypt;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/admin/user-add")
public class AdminUserAddServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        Users currentUser = (session != null) ? (Users) session.getAttribute("currentUser") : null;
        if (currentUser == null) {
            response.sendRedirect("login");
            return;
        }
        DepartmentsDAO departmentsDAO = new DepartmentsDAO();
        List<Departments> departments = departmentsDAO.getAllDepartments();
        request.setAttribute("departments", departments);
        request.getRequestDispatcher("/admin_user_add.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        Users currentUser = (session != null) ? (Users) session.getAttribute("currentUser") : null;
        if (currentUser == null) {
            response.sendRedirect("login");
            return;
        }
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String fullName = request.getParameter("full_name");
        int departmentId = Integer.parseInt(request.getParameter("department_id"));
        String email = request.getParameter("email");
        String managerIdStr = request.getParameter("manager_id");
        Integer managerId = (managerIdStr != null && !managerIdStr.isEmpty()) ? Integer.parseInt(managerIdStr) : null;
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(12));
        Users user = new Users();
        user.setUsername(username);
        user.setFullName(fullName);
        user.setDepartmentId(departmentId);
        user.setEmail(email);
        user.setManagerId(managerId);
        UsersDAO usersDAO = new UsersDAO();
        boolean success = usersDAO.addUser(user, hashedPassword);
        if (success) {
            response.sendRedirect(request.getContextPath() + "/admin/users");
        } else {
            request.setAttribute("error", "Thêm nhân viên thất bại!");
            doGet(request, response);
        }
    }
} 