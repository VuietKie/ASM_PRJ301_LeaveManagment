<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="layout.jsp" %>
<%
    org.example.entity.Users currentUser = (org.example.entity.Users) session.getAttribute("currentUser");
    String contextPath = request.getContextPath();
    java.util.List<org.example.entity.LeaveRequests> subRequests = (java.util.List<org.example.entity.LeaveRequests>) request.getAttribute("subRequests");
%>
<div class="row justify-content-center">
    <div class="col-lg-10 col-md-12">
        <div class="card shadow mb-4">
            <div class="card-body">
                <h2 class="mb-4">Đơn xin nghỉ của cấp dưới</h2>
                <div class="table-responsive">
                    <table class="table table-bordered table-hover align-middle bg-white">
                        <thead class="table-primary">
                        <tr>
                            <th>ID</th>
                            <th>Tiêu đề</th>
                            <th>Người tạo (ID)</th>
                            <th>Ngày bắt đầu</th>
                            <th>Ngày kết thúc</th>
                            <th>Trạng thái</th>
                            <th>Ngày tạo</th>
                            <th>Chi tiết</th>
                        </tr>
                        </thead>
                        <tbody>
                        <% if (subRequests != null && !subRequests.isEmpty()) {
                            for (org.example.entity.LeaveRequests lr : subRequests) { %>
                                <tr>
                                    <td><%= lr.getRequestId() %></td>
                                    <td><%= lr.getTitle() %></td>
                                    <td><%= lr.getUserId() %></td>
                                    <td><%= lr.getStartDate() %></td>
                                    <td><%= lr.getEndDate() %></td>
                                    <td><span class="badge bg-<%= "Approved".equalsIgnoreCase(lr.getStatus()) ? "success" : ("Rejected".equalsIgnoreCase(lr.getStatus()) ? "danger" : "secondary") %>"><%= lr.getStatus() %></span></td>
                                    <td><%= lr.getCreatedAt() %></td>
                                    <td><a href="<%= contextPath %>/request/detail?id=<%= lr.getRequestId() %>" class="btn btn-sm btn-outline-info">Xem chi tiết</a></td>
                                </tr>
                        <%  }
                           } else { %>
                            <tr><td colspan="8" class="text-center text-muted">Không có đơn xin nghỉ nào của cấp dưới.</td></tr>
                        <% } %>
                        </tbody>
                    </table>
                </div>
                <div class="mt-3">
                    <a href="<%= contextPath %>/home" class="btn btn-secondary">&larr; Quay lại trang chủ</a>
                </div>
            </div>
        </div>
    </div>
</div> 