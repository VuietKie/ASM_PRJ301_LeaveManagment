<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    org.example.entity.Users currentUser = (org.example.entity.Users) session.getAttribute("currentUser");
    if (currentUser == null) {
        response.sendRedirect("login");
        return;
    }
    String contextPath = request.getContextPath();
    java.util.List<org.example.entity.LeaveRequests> myRequests = (java.util.List<org.example.entity.LeaveRequests>) request.getAttribute("myRequests");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Đơn xin nghỉ của tôi</title>
</head>
<body>
    <h2>Đơn xin nghỉ của tôi</h2>
    <table border="1" cellpadding="5" cellspacing="0">
        <tr>
            <th>ID</th>
            <th>Tiêu đề</th>
            <th>Ngày bắt đầu</th>
            <th>Ngày kết thúc</th>
            <th>Lý do</th>
            <th>Trạng thái</th>
            <th>Ngày tạo</th>
            <th>Ngày cập nhật</th>
        </tr>
        <% if (myRequests != null && !myRequests.isEmpty()) {
            for (org.example.entity.LeaveRequests lr : myRequests) { %>
                <tr>
                    <td><%= lr.getRequestId() %></td>
                    <td><%= lr.getTitle() %></td>
                    <td><%= lr.getStartDate() %></td>
                    <td><%= lr.getEndDate() %></td>
                    <td><%= lr.getReason() %></td>
                    <td><%= lr.getStatus() %></td>
                    <td><%= lr.getCreatedAt() %></td>
                    <td><%= lr.getUpdatedAt() %></td>
                </tr>
        <%  }
           } else { %>
            <tr><td colspan="8">Bạn chưa có đơn xin nghỉ nào.</td></tr>
        <% } %>
    </table>
    <br>
    <a href="<%= contextPath %>/home">Quay lại trang chủ</a>
</body>
</html> 