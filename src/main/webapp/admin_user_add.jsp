<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="layout.jsp" %>
<%
    org.example.entity.Users currentUser = (org.example.entity.Users) session.getAttribute("currentUser");
    String contextPath = request.getContextPath();
    java.util.List<org.example.entity.Departments> departments = (java.util.List<org.example.entity.Departments>) request.getAttribute("departments");
    String error = (String) request.getAttribute("error");
%>
<div class="row justify-content-center">
    <div class="col-lg-6 col-md-8">
        <div class="card shadow mb-4">
            <div class="card-body">
                <h2 class="mb-4">Thêm nhân viên mới</h2>
                <% if (error != null) { %>
                    <div class="alert alert-danger"><%= error %></div>
                <% } %>
                <form method="post" action="<%= contextPath %>/admin/user-add">
                    <div class="mb-3">
                        <label for="username" class="form-label">Username</label>
                        <input type="text" class="form-control" id="username" name="username" required>
                    </div>
                    <div class="mb-3">
                        <label for="password" class="form-label">Mật khẩu</label>
                        <input type="password" class="form-control" id="password" name="password" required>
                    </div>
                    <div class="mb-3">
                        <label for="full_name" class="form-label">Họ tên</label>
                        <input type="text" class="form-control" id="full_name" name="full_name" required>
                    </div>
                    <div class="mb-3">
                        <label for="department_id" class="form-label">Phòng ban</label>
                        <select class="form-select" id="department_id" name="department_id" required>
                            <% for (org.example.entity.Departments d : departments) { %>
                                <option value="<%= d.getDepartmentId() %>"><%= d.getDepartmentName() %></option>
                            <% } %>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label for="manager_id" class="form-label">ID Quản lý (manager_id)</label>
                        <input type="number" class="form-control" id="manager_id" name="manager_id">
                    </div>
                    <div class="mb-3">
                        <label for="email" class="form-label">Email</label>
                        <input type="email" class="form-control" id="email" name="email" required>
                    </div>
                    <div class="d-flex justify-content-between">
                        <a href="<%= contextPath %>/admin/users" class="btn btn-secondary">&larr; Quay lại</a>
                        <button type="submit" class="btn btn-primary">Thêm nhân viên</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div> 