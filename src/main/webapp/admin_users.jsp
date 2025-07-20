<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="layout.jsp" %>
<%
    org.example.entity.Users currentUser = (org.example.entity.Users) session.getAttribute("currentUser");
    String contextPath = request.getContextPath();
    java.util.List<org.example.entity.Users> users = (java.util.List<org.example.entity.Users>) request.getAttribute("users");
%>
<div class="row justify-content-center">
    <div class="col-lg-10 col-md-12">
        <div class="d-flex justify-content-between align-items-center mb-3">
            <h2 class="mb-0">Quản lý nhân viên</h2>
            <a href="<%= contextPath %>/admin/user-add" class="btn btn-success">+ Thêm nhân viên</a>
        </div>
        <div class="table-responsive shadow-sm">
            <table class="table table-bordered table-hover align-middle bg-white">
                <thead class="table-primary">
                <tr>
                    <th scope="col">ID</th>
                    <th scope="col">Username</th>
                    <th scope="col">Họ tên</th>
                    <th scope="col">Phòng ban</th>
                    <th scope="col">Email</th>
                    <th scope="col">Chức năng</th>
                </tr>
                </thead>
                <tbody>
                <% if (users != null && !users.isEmpty()) {
                    for (org.example.entity.Users u : users) { %>
                        <tr>
                            <td><%= u.getUserId() %></td>
                            <td><%= u.getUsername() %></td>
                            <td><%= u.getFullName() %></td>
                            <td><%= u.getDepartmentId() %></td>
                            <td><%= u.getEmail() %></td>
                            <td>
                                <a href="<%= contextPath %>/admin/user-edit?id=<%= u.getUserId() %>" class="btn btn-sm btn-outline-primary">Chỉnh sửa</a>
                            </td>
                        </tr>
                <%  }
                   } else { %>
                    <tr><td colspan="6" class="text-center text-muted">Không có nhân viên nào.</td></tr>
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
<script src="../static/js/pagger.js" type="text/javascript"></script>
<link href="../static/css/pagger.css" rel="stylesheet" type="text/css"/>
<script>
    pagger_init('toppagger', ${requestScope.pageindex}, ${requestScope.totalpage}, 2);
    pagger_init('botpagger', ${requestScope.pageindex}, ${requestScope.totalpage}, 2);
</script> 