<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="layout.jsp" %>
<%
    org.example.entity.Users currentUser = (org.example.entity.Users) session.getAttribute("currentUser");
    String contextPath = request.getContextPath();
    String error = (String) request.getAttribute("error");
    String success = (String) session.getAttribute("successMessage");
    if (success != null) session.removeAttribute("successMessage");
%>
<div class="row justify-content-center">
    <div class="col-lg-6 col-md-8">
        <div class="card shadow mb-4">
            <div class="card-body">
                <h2 class="mb-4">Tạo đơn xin nghỉ</h2>
                <% if (error != null) { %>
                    <div class="alert alert-danger"><%= error %></div>
                <% } %>
                <% if (success != null) { %>
                    <div class="alert alert-success alert-dismissible fade show" role="alert">
                        <%= success %>
                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                    </div>
                <% } %>
                <form method="post" action="<%= contextPath %>/request/create">
                    <div class="mb-3">
                        <label for="start_date" class="form-label">Ngày bắt đầu</label>
                        <input type="date" class="form-control" id="start_date" name="start_date" required>
                    </div>
                    <div class="mb-3">
                        <label for="end_date" class="form-label">Ngày kết thúc</label>
                        <input type="date" class="form-control" id="end_date" name="end_date" required>
                    </div>
                    <div class="mb-3">
                        <label for="title" class="form-label">Tiêu đề</label>
                        <input type="text" class="form-control" id="title" name="title" required>
                    </div>
                    <div class="mb-3">
                        <label for="reason" class="form-label">Lý do</label>
                        <textarea class="form-control" id="reason" name="reason" rows="4" required></textarea>
                    </div>
                    <div class="d-flex justify-content-between">
                        <a href="<%= contextPath %>/home" class="btn btn-secondary">&larr; Quay lại</a>
                        <button type="submit" class="btn btn-primary">Gửi đơn</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div> 