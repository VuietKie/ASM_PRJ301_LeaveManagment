<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Leave Management System</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body { background: #f8f9fa; }
        .navbar-brand { font-weight: bold; }
        .footer { background: #222; color: #fff; text-align: center; padding: 12px 0; margin-top: 40px; }
        .container { margin-top: 32px; }
        .sidebar { min-width: 200px; }
        .main-content { min-height: 400px; }
    </style>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
    <div class="container-fluid">
        <a class="navbar-brand" href="<%= request.getContextPath() %>/home">Leave Management</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item"><a class="nav-link" href="<%= request.getContextPath() %>/home">Trang chủ</a></li>
                <li class="nav-item"><a class="nav-link" href="<%= request.getContextPath() %>/request/mylr">Đơn nghỉ của tôi</a></li>
                <li class="nav-item"><a class="nav-link" href="<%= request.getContextPath() %>/request/create">Tạo đơn nghỉ</a></li>
                <li class="nav-item"><a class="nav-link" href="<%= request.getContextPath() %>/agenda">Lịch nghỉ</a></li>
                <li class="nav-item"><a class="nav-link" href="<%= request.getContextPath() %>/admin/users">Quản lý nhân viên</a></li>
                <li class="nav-item"><a class="nav-link" href="<%= request.getContextPath() %>/admin/permission">Phân quyền</a></li>
                <li class="nav-item"><a class="nav-link" href="<%= request.getContextPath() %>/logout">Đăng xuất</a></li>
            </ul>
        </div>
    </div>
</nav>
<!-- Nội dung trang sẽ được include sau khi include layout.jsp -->
<footer class="footer">
    &copy; 2024 Leave Management System. All rights reserved.
</footer>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html> 