<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="layout.jsp" %>
<%
    org.example.entity.Users currentUser = (org.example.entity.Users) session.getAttribute("currentUser");
    String contextPath = request.getContextPath();
    org.example.entity.LeaveRequests lr = (org.example.entity.LeaveRequests) request.getAttribute("leaveRequest");
    if (lr == null) {
        response.sendRedirect(contextPath + "/home");
        return;
    }
%>
<div class="row justify-content-center">
    <div class="col-lg-7 col-md-10">
        <div class="card shadow mb-4">
            <div class="card-body">
                <h2 class="mb-4">Chi tiết đơn xin nghỉ</h2>
                <table class="table table-bordered bg-white">
                    <tr><th>ID</th><td><%= lr.getRequestId() %></td></tr>
                    <tr><th>Tiêu đề</th><td><%= lr.getTitle() %></td></tr>
                    <tr><th>Người tạo (ID)</th><td><%= lr.getUserId() %></td></tr>
                    <tr><th>Ngày bắt đầu</th><td><%= lr.getStartDate() %></td></tr>
                    <tr><th>Ngày kết thúc</th><td><%= lr.getEndDate() %></td></tr>
                    <tr><th>Lý do</th><td><%= lr.getReason() %></td></tr>
                    <tr><th>Trạng thái</th><td><span class="badge bg-<%= "Approved".equalsIgnoreCase(lr.getStatus()) ? "success" : ("Rejected".equalsIgnoreCase(lr.getStatus()) ? "danger" : "secondary") %>"><%= lr.getStatus() %></span></td></tr>
                    <tr><th>Ngày tạo</th><td><%= lr.getCreatedAt() %></td></tr>
                    <tr><th>Ngày cập nhật</th><td><%= lr.getUpdatedAt() %></td></tr>
                    <% if (lr.getProcessedBy() != null) { %>
                    <tr><th>Người xử lý (ID)</th><td><%= lr.getProcessedBy() %></td></tr>
                    <tr><th>Lý do xử lý</th><td><%= lr.getProcessedReason() %></td></tr>
                    <% } %>
                </table>
                <% if ("Inprogress".equalsIgnoreCase(lr.getStatus())) { %>
                <form method="post" action="<%= contextPath %>/request/detail" class="mt-4">
                    <input type="hidden" name="id" value="<%= lr.getRequestId() %>">
                    <div class="mb-3">
                        <label for="processed_reason" class="form-label">Lý do xử lý</label>
                        <textarea class="form-control" id="processed_reason" name="processed_reason" rows="3" required></textarea>
                    </div>
                    <div class="d-flex gap-2">
                        <button type="submit" name="action" value="accept" class="btn btn-success">Duyệt (Accept)</button>
                        <button type="submit" name="action" value="reject" class="btn btn-danger">Từ chối (Reject)</button>
                    </div>
                </form>
                <% } %>
                <div class="mt-3">
                    <a href="<%= contextPath %>/request/view-subordinates" class="btn btn-secondary">&larr; Quay lại danh sách đơn cấp dưới</a>
                </div>
            </div>
        </div>
    </div>
</div> 