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
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/admin/user-add")
public class AdminUserAddServlet extends BaseRBACServlet {
    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response, Users currentUser) throws ServletException, IOException {
        DepartmentsDAO departmentsDAO = new DepartmentsDAO();
        List<Departments> departments = departmentsDAO.getAllDepartments();
        request.setAttribute("departments", departments);
        request.getRequestDispatcher("/admin_user_add.jsp").forward(request, response);
    }

    @Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response, Users currentUser) throws ServletException, IOException {
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
            processGet(request, response, currentUser);
        }
    }
} 