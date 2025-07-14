<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    org.example.entity.Users currentUser = (org.example.entity.Users) session.getAttribute("currentUser");
    if (currentUser == null) {
        response.sendRedirect("login");
        return;
    }
    String contextPath = request.getContextPath();
    org.example.entity.LeaveRequests lr = (org.example.entity.LeaveRequests) request.getAttribute("leaveRequest");
    if (lr == null) {
        response.sendRedirect(contextPath + "/home");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Chi tiết đơn xin nghỉ</title>
</head>
<body>
    <h2>Chi tiết đơn xin nghỉ</h2>
    <table border="1" cellpadding="5" cellspacing="0">
        <tr><th>ID</th><td><%= lr.getRequestId() %></td></tr>
        <tr><th>Tiêu đề</th><td><%= lr.getTitle() %></td></tr>
        <tr><th>Người tạo</th><td><%= lr.getUserId() %></td></tr>
        <tr><th>Ngày bắt đầu</th><td><%= lr.getStartDate() %></td></tr>
        <tr><th>Ngày kết thúc</th><td><%= lr.getEndDate() %></td></tr>
        <tr><th>Lý do</th><td><%= lr.getReason() %></td></tr>
        <tr><th>Trạng thái</th><td><%= lr.getStatus() %></td></tr>
        <tr><th>Ngày tạo</th><td><%= lr.getCreatedAt() %></td></tr>
        <tr><th>Ngày cập nhật</th><td><%= lr.getUpdatedAt() %></td></tr>
        <% if (lr.getProcessedBy() != null) { %>
        <tr><th>Người xử lý</th><td><%= lr.getProcessedBy() %></td></tr>
        <tr><th>Lý do xử lý</th><td><%= lr.getProcessedReason() %></td></tr>
        <% } %>
    </table>
    <br>
    <% if ("Inprogress".equalsIgnoreCase(lr.getStatus())) { %>
    <form method="post" action="<%= contextPath %>/request/detail">
        <input type="hidden" name="id" value="<%= lr.getRequestId() %>">
        <label for="processed_reason">Lý do xử lý:</label><br>
        <textarea id="processed_reason" name="processed_reason" rows="3" cols="50" required></textarea><br><br>
        <button type="submit" name="action" value="accept">Duyệt (Accept)</button>
        <button type="submit" name="action" value="reject">Từ chối (Reject)</button>
    </form>
    <% } %>
    <br>
    <a href="<%= contextPath %>/request/view-subordinates">Quay lại danh sách đơn cấp dưới</a>
</body>
</html> 