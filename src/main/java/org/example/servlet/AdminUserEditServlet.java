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

@WebServlet("/admin/user-edit")
public class AdminUserEditServlet extends BaseRBACServlet {
    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response, Users currentUser) throws ServletException, IOException {
        String idStr = request.getParameter("id");
        if (idStr == null) {
            response.sendRedirect("/admin/users");
            return;
        }
        int userId = Integer.parseInt(idStr);
        UsersDAO usersDAO = new UsersDAO();
        Users user = usersDAO.getUserById(userId);
        DepartmentsDAO departmentsDAO = new DepartmentsDAO();
        List<Departments> departments = departmentsDAO.getAllDepartments();
        request.setAttribute("userEdit", user);
        request.setAttribute("departments", departments);
        request.getRequestDispatcher("/admin_user_edit.jsp").forward(request, response);
    }

    @Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response, Users currentUser) throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("user_id"));
        String fullName = request.getParameter("full_name");
        int departmentId = Integer.parseInt(request.getParameter("department_id"));
        String email = request.getParameter("email");
        String managerIdStr = request.getParameter("manager_id");
        Integer managerId = (managerIdStr != null && !managerIdStr.isEmpty()) ? Integer.parseInt(managerIdStr) : null;
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirm_password");
        Users user = new Users();
        user.setUserId(userId);
        user.setFullName(fullName);
        user.setDepartmentId(departmentId);
        user.setEmail(email);
        user.setManagerId(managerId);
        UsersDAO usersDAO = new UsersDAO();
        boolean success;
        if (password != null && !password.isEmpty()) {
            if (!password.equals(confirmPassword)) {
                request.setAttribute("error", "Mật khẩu xác nhận không khớp!");
                processGet(request, response, currentUser);
                return;
            }
            String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(12));
            success = usersDAO.updateUserWithPassword(user, hashedPassword);
        } else {
            success = usersDAO.updateUser(user);
        }
        if (success) {
            response.sendRedirect(request.getContextPath() + "/admin/users");
        } else {
            request.setAttribute("error", "Cập nhật thất bại!");
            processGet(request, response, currentUser);
        }
    }
} 