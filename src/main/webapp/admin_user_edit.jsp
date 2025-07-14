<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    org.example.entity.Users currentUser = (org.example.entity.Users) session.getAttribute("currentUser");
    if (currentUser == null) {
        response.sendRedirect("login");
        return;
    }
    String contextPath = request.getContextPath();
    org.example.entity.Users userEdit = (org.example.entity.Users) request.getAttribute("userEdit");
    java.util.List<org.example.entity.Departments> departments = (java.util.List<org.example.entity.Departments>) request.getAttribute("departments");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Chỉnh sửa thông tin nhân viên</title>
</head>
<body>
    <h2>Chỉnh sửa thông tin nhân viên</h2>
    <form method="post" action="<%= contextPath %>/admin/user-edit">
        <input type="hidden" name="user_id" value="<%= userEdit.getUserId() %>">
        <label>Username: <b><%= userEdit.getUsername() %></b></label><br><br>
        <label for="full_name">Họ tên:</label>
        <input type="text" id="full_name" name="full_name" value="<%= userEdit.getFullName() %>" required><br><br>
        <label for="department_id">Phòng ban:</label>
        <select id="department_id" name="department_id" required>
            <% for (org.example.entity.Departments d : departments) { %>
                <option value="<%= d.getDepartmentId() %>" <%= (userEdit.getDepartmentId() == d.getDepartmentId()) ? "selected" : "" %>><%= d.getDepartmentName() %></option>
            <% } %>
        </select><br><br>
        <label for="manager_id">ID Quản lý (manager_id):</label>
        <input type="number" id="manager_id" name="manager_id" value="<%= userEdit.getManagerId() != null ? userEdit.getManagerId() : "" %>"><br><br>
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" value="<%= userEdit.getEmail() %>" required><br><br>
        <!-- Thêm trường đổi mật khẩu -->
        <label for="password">Mật khẩu mới (bỏ trống nếu không đổi):</label>
        <input type="password" id="password" name="password"><br><br>
        <label for="confirm_password">Xác nhận mật khẩu mới:</label>
        <input type="password" id="confirm_password" name="confirm_password"><br><br>
        <button type="submit">Lưu thay đổi</button>
    </form>
    <br>
    <a href="<%= contextPath %>/admin/users">Quay lại danh sách người dùng</a>
</body>
</html> 