<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    java.util.List<org.example.entity.Roles> roles = (java.util.List<org.example.entity.Roles>) request.getAttribute("roles");
    java.util.List<org.example.entity.Features> features = (java.util.List<org.example.entity.Features>) request.getAttribute("features");
    java.util.List<org.example.entity.RoleFeatures> roleFeatures = (java.util.List<org.example.entity.RoleFeatures>) request.getAttribute("roleFeatures");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Quản lý Permission</title>
    <style>
        table, th, td { border: 1px solid #ccc; border-collapse: collapse; padding: 6px; }
        th { background: #eee; }
        .delete-btn { color: red; cursor: pointer; text-decoration: underline; border: none; background: none; }
        .perm-message {
            color: green;
            font-weight: bold;
            margin-bottom: 10px;
            background: #eaffea;
            border: 1px solid #b2e6b2;
            padding: 8px 20px;
            border-radius: 5px;
            position: fixed;
            top: 30px;
            left: 50%;
            transform: translateX(-50%) scale(0.8);
            opacity: 0;
            z-index: 9999;
            transition: opacity 0.4s, transform 0.4s;
        }
        .perm-message.show {
            opacity: 1;
            transform: translateX(-50%) scale(1);
        }
    </style>
</head>
<body>
<h2>Quản lý Permission (Role - Feature)</h2>
<% if (request.getAttribute("message") != null) { %>
    <div id="permMsg" class="perm-message"><%= request.getAttribute("message") %></div>
    <script>
        window.addEventListener('DOMContentLoaded', function() {
            var msg = document.getElementById('permMsg');
            if (msg) {
                setTimeout(function() { msg.classList.add('show'); }, 100);
                setTimeout(function() { msg.classList.remove('show'); }, 2500);
            }
        });
    </script>
<% } %>

<!-- Form thêm quyền mới -->
<form method="post" action="<%= request.getContextPath() %>/admin/permission">
    <label for="role_id">Role:</label>
    <select name="role_id" id="role_id" required>
        <option value="">--Chọn Role--</option>
        <% for (org.example.entity.Roles r : roles) { %>
            <option value="<%= r.getRoleId() %>"><%= r.getRoleName() %></option>
        <% } %>
    </select>
    <label for="feature_id">Feature:</label>
    <select name="feature_id" id="feature_id" required>
        <option value="">--Chọn Feature--</option>
        <% for (org.example.entity.Features f : features) { %>
            <option value="<%= f.getFeatureId() %>"><%= f.getFeatureName() %> (<%= f.getEntrypoint() %>)</option>
        <% } %>
    </select>
    <button type="submit" name="action" value="add">Thêm quyền</button>
</form>
<br>

<!-- Bảng quyền hiện tại -->
<table>
    <thead>
    <tr>
        <th>Role</th>
        <th>Feature</th>
        <th>Entrypoint</th>
        <th>Xoá</th>
    </tr>
    </thead>
    <tbody>
    <% for (org.example.entity.RoleFeatures rf : roleFeatures) {
        org.example.entity.Roles role = null;
        org.example.entity.Features feature = null;
        for (org.example.entity.Roles r : roles) if (r.getRoleId() == rf.getRoleId()) { role = r; break; }
        for (org.example.entity.Features f : features) if (f.getFeatureId() == rf.getFeatureId()) { feature = f; break; }
        if (role != null && feature != null) { %>
        <tr>
            <td><%= role.getRoleName() %></td>
            <td><%= feature.getFeatureName() %></td>
            <td><%= feature.getEntrypoint() %></td>
            <td>
                <form method="post" action="<%= request.getContextPath() %>/admin/permission" style="display:inline">
                    <input type="hidden" name="role_id" value="<%= role.getRoleId() %>">
                    <input type="hidden" name="feature_id" value="<%= feature.getFeatureId() %>">
                    <button type="submit" name="action" value="delete" class="delete-btn" onclick="return confirm('Xoá quyền này?')">X</button>
                </form>
            </td>
        </tr>
    <% }} %>
    </tbody>
</table>
<br>
<a href="<%= request.getContextPath() %>/home">Quay lại trang chủ</a>
</body>
</html> 