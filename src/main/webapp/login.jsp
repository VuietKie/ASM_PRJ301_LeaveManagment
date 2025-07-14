<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Đăng nhập - Leave Management</title>
</head>
<body>
    <h2>Đăng nhập</h2>
    <form method="post" action="login">
        <label for="username">Tên đăng nhập:</label>
        <input type="text" id="username" name="username" required><br><br>
        <label for="password">Mật khẩu:</label>
        <input type="password" id="password" name="password" required><br><br>
        <button type="submit">Đăng nhập</button>
    </form>
    <div style="color:red">
        ${error != null ? error : ''}
    </div>
</body>
</html> 