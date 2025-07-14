package org.example.servlet;

import java.io.IOException;
import java.util.List;

import org.example.dao.FeaturesDAO;
import org.example.dao.RoleFeaturesDAO;
import org.example.dao.RolesDAO;
import org.example.entity.Features;
import org.example.entity.RoleFeatures;
import org.example.entity.Roles;
import org.example.entity.Users;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/admin/permission")
public class PermissionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        Users currentUser = (session != null) ? (Users) session.getAttribute("currentUser") : null;
        if (currentUser == null) {
            response.sendRedirect("login");
            return;
        }
        RolesDAO rolesDAO = new RolesDAO();
        FeaturesDAO featuresDAO = new FeaturesDAO();
        RoleFeaturesDAO roleFeaturesDAO = new RoleFeaturesDAO();
        List<Roles> roles = rolesDAO.getAll();
        List<Features> features = featuresDAO.getAll();
        List<RoleFeatures> roleFeatures = roleFeaturesDAO.getAll();
        String message = null;
        if (session != null) {
            message = (String) session.getAttribute("permMessage");
            session.removeAttribute("permMessage");
        }
        request.setAttribute("roles", roles);
        request.setAttribute("features", features);
        request.setAttribute("roleFeatures", roleFeatures);
        request.setAttribute("message", message);
        request.getRequestDispatcher("/admin_permission.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        Users currentUser = (session != null) ? (Users) session.getAttribute("currentUser") : null;
        if (currentUser == null) {
            response.sendRedirect("login");
            return;
        }
        String action = request.getParameter("action");
        int roleId = Integer.parseInt(request.getParameter("role_id"));
        int featureId = Integer.parseInt(request.getParameter("feature_id"));
        RoleFeaturesDAO roleFeaturesDAO = new RoleFeaturesDAO();
        String message = null;
        boolean result = false;
        if ("add".equals(action)) {
            result = roleFeaturesDAO.add(roleId, featureId);
            message = result ? "Thêm quyền thành công!" : "Thêm quyền thất bại (có thể đã tồn tại)!";
        } else if ("delete".equals(action)) {
            result = roleFeaturesDAO.delete(roleId, featureId);
            message = result ? "Xoá quyền thành công!" : "Xoá quyền thất bại!";
        }
        session.setAttribute("permMessage", message);
        response.sendRedirect(request.getContextPath() + "/admin/permission");
    }
} 