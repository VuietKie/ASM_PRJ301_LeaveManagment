<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    org.example.entity.Users currentUser = (org.example.entity.Users) session.getAttribute("currentUser");
    if (currentUser == null) {
        response.sendRedirect("login");
        return;
    }
    String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
    <title>Tạo đơn xin nghỉ</title>
</head>
<body>
    <h2>Tạo đơn xin nghỉ</h2>
    <form method="post" action="<%= contextPath %>/request/create">
        <label for="start_date">Ngày bắt đầu:</label>
        <input type="date" id="start_date" name="start_date" required><br><br>
        <label for="end_date">Ngày kết thúc:</label>
        <input type="date" id="end_date" name="end_date" required><br><br>
        <label for="title">Tiêu đề:</label>
        <input type="text" id="title" name="title" required><br><br>
        <label for="reason">Lý do:</label><br>
        <textarea id="reason" name="reason" rows="4" cols="50" required></textarea><br><br>
        <button type="submit">Gửi đơn</button>
    </form>
    <a href="<%= contextPath %>/home">Quay lại trang chủ</a>
</body>
</html> 