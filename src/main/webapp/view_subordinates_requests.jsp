<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    org.example.entity.Users currentUser = (org.example.entity.Users) session.getAttribute("currentUser");
    if (currentUser == null) {
        response.sendRedirect("login");
        return;
    }
    String contextPath = request.getContextPath();
    java.util.List<org.example.entity.LeaveRequests> subRequests = (java.util.List<org.example.entity.LeaveRequests>) request.getAttribute("subRequests");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Đơn xin nghỉ của cấp dưới</title>
</head>
<body>
    <h2>Đơn xin nghỉ của cấp dưới</h2>
    <table border="1" cellpadding="5" cellspacing="0">
        <tr>
            <th>ID</th>
            <th>Tiêu đề</th>
            <th>Người tạo</th>
            <th>Ngày bắt đầu</th>
            <th>Ngày kết thúc</th>
            <th>Trạng thái</th>
            <th>Ngày tạo</th>
            <th>Chi tiết</th>
        </tr>
        <% if (subRequests != null && !subRequests.isEmpty()) {
            for (org.example.entity.LeaveRequests lr : subRequests) { %>
                <tr>
                    <td><%= lr.getRequestId() %></td>
                    <td><%= lr.getTitle() %></td>
                    <td><%= lr.getUserId() %></td>
                    <td><%= lr.getStartDate() %></td>
                    <td><%= lr.getEndDate() %></td>
                    <td><%= lr.getStatus() %></td>
                    <td><%= lr.getCreatedAt() %></td>
                    <td><a href="<%= contextPath %>/request/detail?id=<%= lr.getRequestId() %>">Xem chi tiết</a></td>
                </tr>
        <%  }
           } else { %>
            <tr><td colspan="8">Không có đơn xin nghỉ nào của cấp dưới.</td></tr>
        <% } %>
    </table>
    <br>
    <a href="<%= contextPath %>/home">Quay lại trang chủ</a>
</body>
</html> 