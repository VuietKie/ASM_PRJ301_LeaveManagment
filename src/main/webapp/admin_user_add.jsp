<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    org.example.entity.Users currentUser = (org.example.entity.Users) session.getAttribute("currentUser");
    if (currentUser == null) {
        response.sendRedirect("login");
        return;
    }
    String contextPath = request.getContextPath();
    java.util.List<org.example.entity.Departments> departments = (java.util.List<org.example.entity.Departments>) request.getAttribute("departments");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Thêm nhân viên mới</title>
</head>
<body>
    <h2>Thêm nhân viên mới</h2>
    <form method="post" action="<%= contextPath %>/admin/user-add">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required><br><br>
        <label for="password">Mật khẩu:</label>
        <input type="password" id="password" name="password" required><br><br>
        <label for="full_name">Họ tên:</label>
        <input type="text" id="full_name" name="full_name" required><br><br>
        <label for="department_id">Phòng ban:</label>
        <select id="department_id" name="department_id" required>
            <% for (org.example.entity.Departments d : departments) { %>
                <option value="<%= d.getDepartmentId() %>"><%= d.getDepartmentName() %></option>
            <% } %>
        </select><br><br>
        <label for="manager_id">ID Quản lý (manager_id):</label>
        <input type="number" id="manager_id" name="manager_id"><br><br>
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required><br><br>
        <button type="submit">Thêm nhân viên</button>
    </form>
    <br>
    <a href="<%= contextPath %>/admin/users">Quay lại danh sách người dùng</a>
</body>
</html> 