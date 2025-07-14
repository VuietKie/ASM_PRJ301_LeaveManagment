<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="layout.jsp" %>
<%
    org.example.entity.Users currentUser = (org.example.entity.Users) session.getAttribute("currentUser");
    java.util.List<org.example.entity.Features> features = (java.util.List<org.example.entity.Features>) session.getAttribute("features");
    String contextPath = request.getContextPath();
    String successMessage = (String) session.getAttribute("successMessage");
    if (successMessage != null) session.removeAttribute("successMessage");
%>
<div class="container main-content">
    <div class="row justify-content-center">
        <div class="col-lg-8 col-md-10">
            <% if (successMessage != null) { %>
                <div class="alert alert-success alert-dismissible fade show" role="alert">
                    <%= successMessage %>
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>
            <% } %>
            <div class="card shadow mb-4">
                <div class="card-body">
                    <h2 class="mb-3">Xin chào, <%= currentUser.getFullName() %>!</h2>
                    <div class="mb-2"><strong>Tên đăng nhập:</strong> <%= currentUser.getUsername() %></div>
                    <div class="mb-2"><strong>Email:</strong> <%= currentUser.getEmail() %></div>
                    <div class="mb-2"><strong>Phòng ban:</strong> <%= currentUser.getDepartmentId() %></div>
                    <hr>
                    <h5 class="mb-3">Các chức năng bạn có thể sử dụng:</h5>
                    <ul class="list-group mb-3">
                        <% if (features != null && !features.isEmpty()) {
                            for (org.example.entity.Features f : features) { %>
                                <li class="list-group-item d-flex justify-content-between align-items-center">
                                    <a href="<%= contextPath + f.getEntrypoint() %>" class="text-decoration-none"><%= f.getFeatureName() %></a>
                                    <span class="badge bg-primary rounded-pill"><%= f.getEntrypoint() %></span>
                                </li>
                        <%  }
                           } else { %>
                            <li class="list-group-item">Không có chức năng nào khả dụng.</li>
                        <% } %>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
<%-- Nếu muốn include footer riêng, có thể tạo footer.jsp và include ở đây --%> 