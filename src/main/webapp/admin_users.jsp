<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    org.example.entity.Users currentUser = (org.example.entity.Users) session.getAttribute("currentUser");
    if (currentUser == null) {
        response.sendRedirect("login");
        return;
    }
    String contextPath = request.getContextPath();
    java.util.List<org.example.entity.Users> users = (java.util.List<org.example.entity.Users>) request.getAttribute("users");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Quản lý người dùng</title>
</head>
<body>
    <h2>Quản lý người dùng</h2>
    <a href="<%= contextPath %>/admin/user-add">Thêm nhân viên</a>
    <br><br>
    <table border="1" cellpadding="5" cellspacing="0">
        <tr>
            <th>ID</th>
            <th>Username</th>
            <th>Họ tên</th>
            <th>Phòng ban</th>
            <th>Email</th>
            <th>Chức năng</th>
        </tr>
        <% if (users != null && !users.isEmpty()) {
            for (org.example.entity.Users u : users) { %>
                <tr>
                    <td><%= u.getUserId() %></td>
                    <td><%= u.getUsername() %></td>
                    <td><%= u.getFullName() %></td>
                    <td><%= u.getDepartmentId() %></td>
                    <td><%= u.getEmail() %></td>
                    <td><a href="<%= contextPath %>/admin/user-edit?id=<%= u.getUserId() %>">Chỉnh sửa</a></td>
                </tr>
        <%  }
           } else { %>
            <tr><td colspan="6">Không có người dùng nào.</td></tr>
        <% } %>
    </table>
    <br>
    <a href="<%= contextPath %>/home">Quay lại trang chủ</a>
</body>
</html> 