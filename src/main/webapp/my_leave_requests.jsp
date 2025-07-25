<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="layout.jsp" %>
<%
    org.example.entity.Users currentUser = (org.example.entity.Users) session.getAttribute("currentUser");
    String contextPath = request.getContextPath();
    java.util.List<org.example.entity.LeaveRequests> myRequests = (java.util.List<org.example.entity.LeaveRequests>) request.getAttribute("myRequests");
%>
<div class="row justify-content-center">
    <div class="col-lg-10 col-md-12">
        <div class="card shadow mb-4">
            <div class="card-body">
                <h2 class="mb-4">Đơn xin nghỉ của tôi</h2>
                <div class="table-responsive">
                    <table class="table table-bordered table-hover align-middle bg-white">
                        <thead class="table-primary">
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
                        </thead>
                        <tbody>
                        <% if (myRequests != null && !myRequests.isEmpty()) {
                            for (org.example.entity.LeaveRequests lr : myRequests) { %>
                                <tr>
                                    <td><%= lr.getRequestId() %></td>
                                    <td><%= lr.getTitle() %></td>
                                    <td><%= lr.getStartDate() %></td>
                                    <td><%= lr.getEndDate() %></td>
                                    <td><%= lr.getReason() %></td>
                                    <td><span class="badge bg-<%= "Approved".equalsIgnoreCase(lr.getStatus()) ? "success" : ("Rejected".equalsIgnoreCase(lr.getStatus()) ? "danger" : "secondary") %>"><%= lr.getStatus() %></span></td>
                                    <td><%= lr.getCreatedAt() %></td>
                                    <td><%= lr.getUpdatedAt() %></td>
                                </tr>
                        <%  }
                           } else { %>
                            <tr><td colspan="8" class="text-center text-muted">Bạn chưa có đơn xin nghỉ nào.</td></tr>
                        <% } %>
                        </tbody>
                    </table>
                </div>
                <div id="toppagger" class="pagger my-3"></div>
                <div id="botpagger" class="pagger my-3"></div>
                <div class="mt-3">
                    <a href="<%= contextPath %>/home" class="btn btn-secondary">&larr; Quay lại trang chủ</a>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="../static/js/pagger.js" type="text/javascript"></script>
<link href="../static/css/pagger.css" rel="stylesheet" type="text/css"/>
<script>
    pagger_init('toppagger', ${requestScope.pageindex}, ${requestScope.totalpage}, 2);
    pagger_init('botpagger', ${requestScope.pageindex}, ${requestScope.totalpage}, 2);
</script> 