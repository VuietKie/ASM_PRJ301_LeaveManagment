<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Đăng nhập - Leave Management System</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Bootstrap 5 CDN -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background: #f8fafc;
            min-height: 100vh;
        }
        .login-container {
            min-height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
        }
        .login-card {
            padding: 2.5rem 2rem;
            border-radius: 1rem;
            box-shadow: 0 0.5rem 1.5rem rgba(0,0,0,0.08);
            background: #fff;
        }
        .logo {
            font-size: 2rem;
            font-weight: bold;
            color: #0d6efd;
            margin-bottom: 1rem;
            letter-spacing: 1px;
        }
    </style>
</head>
<body>
<div class="login-container">
    <div class="login-card col-12 col-sm-8 col-md-5 col-lg-4">
        <div class="text-center mb-4">
            <div class="logo">Leave Management</div>
            <div class="text-muted">Đăng nhập hệ thống</div>
        </div>
        <c:if test="${not empty error}">
            <div class="alert alert-danger alert-dismissible fade show" role="alert">
                ${error}
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
        </c:if>
        <form action="login" method="post" autocomplete="off">
            <div class="mb-3">
                <label for="username" class="form-label">Tên đăng nhập</label>
                <input type="text" class="form-control" id="username" name="username" required autofocus
                       value="${param.username}">
            </div>
            <div class="mb-3">
                <label for="password" class="form-label">Mật khẩu</label>
                <input type="password" class="form-control" id="password" name="password" required>
            </div>
            <button class="btn btn-primary w-100" type="submit">Đăng nhập</button>
        </form>
        <div class="mt-4 text-center text-muted" style="font-size: 0.95em;">
            &copy; 2024 Leave Management System
        </div>
    </div>
</div>
<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html> 