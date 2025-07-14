<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    org.example.entity.Users currentUser = (org.example.entity.Users) session.getAttribute("currentUser");
    if (currentUser == null) {
        response.sendRedirect("login");
        return;
    }
    java.util.List<org.example.entity.Features> features = (java.util.List<org.example.entity.Features>) session.getAttribute("features");
    String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
    <title>Trang chủ - Leave Management</title>
</head>
<body>
    <% String successMessage = (String) session.getAttribute("successMessage");
       if (successMessage != null) { %>
        <div style="color: green; font-weight: bold;"> <%= successMessage %> </div>
        <% session.removeAttribute("successMessage"); %>
    <% } %>
    <h2>Xin chào, <%= currentUser.getFullName() %>!</h2>
    <p>Tên đăng nhập: <%= currentUser.getUsername() %></p>
    <p>Email: <%= currentUser.getEmail() %></p>
    <p>Phòng ban: <%= currentUser.getDepartmentId() %></p>
    <h3>Các chức năng bạn có thể sử dụng:</h3>
    <ul>
        <% if (features != null) {
            for (org.example.entity.Features f : features) { %>
                <li><a href="<%= contextPath + f.getEntrypoint() %>"><%= f.getFeatureName() %></a></li>
        <%  }
           } else { %>
            <li>Không có chức năng nào khả dụng.</li>
        <% } %>
    </ul>
    <a href="logout.jsp">Đăng xuất</a>
</body>
</html> 