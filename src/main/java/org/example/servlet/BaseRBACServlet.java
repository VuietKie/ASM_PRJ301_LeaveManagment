package org.example.servlet;

import java.io.IOException;
import java.util.List;

import org.example.entity.Features;
import org.example.entity.Users;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public abstract class BaseRBACServlet extends HttpServlet {
    private boolean isGrantedAccessControl(HttpServletRequest req, Users user) {
        String currentEntrypoint = req.getServletPath();
        @SuppressWarnings("unchecked")
        List<Features> features = (List<Features>) req.getSession().getAttribute("features");
        if (features == null) return false;
        for (Features f : features) {
            if (f.getEntrypoint().equals(currentEntrypoint)) {
                return true;
            }
        }
        return false;
    }

    protected abstract void processGet(HttpServletRequest req, HttpServletResponse resp, Users user) throws ServletException, IOException;
    protected abstract void processPost(HttpServletRequest req, HttpServletResponse resp, Users user) throws ServletException, IOException;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        Users user = (session != null) ? (Users) session.getAttribute("currentUser") : null;
        if (user == null) {
            resp.sendRedirect("login");
            return;
        }
        if (isGrantedAccessControl(req, user)) {
            processGet(req, resp, user);
        } else {
            resp.sendError(403, "Bạn không có quyền truy cập chức năng này!");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        Users user = (session != null) ? (Users) session.getAttribute("currentUser") : null;
        if (user == null) {
            resp.sendRedirect("login");
            return;
        }
        if (isGrantedAccessControl(req, user)) {
            processPost(req, resp, user);
        } else {
            resp.sendError(403, "Bạn không có quyền truy cập chức năng này!");
        }
    }
} 