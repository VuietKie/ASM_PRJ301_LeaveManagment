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
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/admin/permission")
public class PermissionServlet extends BaseRBACServlet {
    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response, Users currentUser) throws ServletException, IOException {
        RolesDAO rolesDAO = new RolesDAO();
        FeaturesDAO featuresDAO = new FeaturesDAO();
        RoleFeaturesDAO roleFeaturesDAO = new RoleFeaturesDAO();
        List<Roles> roles = rolesDAO.getAll();
        List<Features> features = featuresDAO.getAll();
        List<RoleFeatures> roleFeatures = roleFeaturesDAO.getAll();
        String message = null;
        if (request.getSession(false) != null) {
            message = (String) request.getSession(false).getAttribute("permMessage");
            request.getSession(false).removeAttribute("permMessage");
        }
        request.setAttribute("roles", roles);
        request.setAttribute("features", features);
        request.setAttribute("roleFeatures", roleFeatures);
        request.setAttribute("message", message);
        request.getRequestDispatcher("/admin_permission.jsp").forward(request, response);
    }

    @Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response, Users currentUser) throws ServletException, IOException {
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
        request.getSession().setAttribute("permMessage", message);
        response.sendRedirect(request.getContextPath() + "/admin/permission");
    }
} 