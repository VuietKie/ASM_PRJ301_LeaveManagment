<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="layout.jsp" %>
<%
    java.util.List<org.example.entity.Roles> roles = (java.util.List<org.example.entity.Roles>) request.getAttribute("roles");
    java.util.List<org.example.entity.Features> features = (java.util.List<org.example.entity.Features>) request.getAttribute("features");
    java.util.List<org.example.entity.RoleFeatures> roleFeatures = (java.util.List<org.example.entity.RoleFeatures>) request.getAttribute("roleFeatures");
    String message = (String) request.getAttribute("message");
%>
<div class="row justify-content-center">
    <div class="col-lg-10 col-md-12">
        <div class="card shadow mb-4">
            <div class="card-body">
                <h2 class="mb-4">Quản lý Permission (Role - Feature)</h2>
                <% if (message != null) { %>
                    <div class="alert alert-success alert-dismissible fade show" role="alert">
                        <%= message %>
                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                    </div>
                <% } %>
                <form class="row g-3 align-items-end mb-4" method="post" action="<%= request.getContextPath() %>/admin/permission">
                    <div class="col-md-4">
                        <label for="role_id" class="form-label">Role</label>
                        <select name="role_id" id="role_id" class="form-select" required>
                            <option value="">--Chọn Role--</option>
                            <% for (org.example.entity.Roles r : roles) { %>
                                <option value="<%= r.getRoleId() %>"><%= r.getRoleName() %></option>
                            <% } %>
                        </select>
                    </div>
                    <div class="col-md-5">
                        <label for="feature_id" class="form-label">Feature</label>
                        <select name="feature_id" id="feature_id" class="form-select" required>
                            <option value="">--Chọn Feature--</option>
                            <% for (org.example.entity.Features f : features) { %>
                                <option value="<%= f.getFeatureId() %>"><%= f.getFeatureName() %> (<%= f.getEntrypoint() %>)</option>
                            <% } %>
                        </select>
                    </div>
                    <div class="col-md-3 d-grid">
                        <button type="submit" name="action" value="add" class="btn btn-primary">Thêm quyền</button>
                    </div>
                </form>
                <div id="toppagger" class="pagger my-3"></div>
                <div class="table-responsive">
                    <table class="table table-bordered table-hover align-middle bg-white">
                        <thead class="table-primary">
                        <tr>
                            <th>Role</th>
                            <th>Feature</th>
                            <th>Entrypoint</th>
                            <th>Xoá</th>
                        </tr>
                        </thead>
                        <tbody>
                        <% for (org.example.entity.RoleFeatures rf : roleFeatures) {
                            org.example.entity.Roles role = null;
                            org.example.entity.Features feature = null;
                            for (org.example.entity.Roles r : roles) if (r.getRoleId() == rf.getRoleId()) { role = r; break; }
                            for (org.example.entity.Features f : features) if (f.getFeatureId() == rf.getFeatureId()) { feature = f; break; }
                            if (role != null && feature != null) { %>
                            <tr>
                                <td><%= role.getRoleName() %></td>
                                <td><%= feature.getFeatureName() %></td>
                                <td><%= feature.getEntrypoint() %></td>
                                <td>
                                    <form method="post" action="<%= request.getContextPath() %>/admin/permission" style="display:inline">
                                        <input type="hidden" name="role_id" value="<%= role.getRoleId() %>">
                                        <input type="hidden" name="feature_id" value="<%= feature.getFeatureId() %>">
                                        <button type="submit" name="action" value="delete" class="btn btn-sm btn-outline-danger" onclick="return confirm('Xoá quyền này?')">X</button>
                                    </form>
                                </td>
                            </tr>
                        <% }} %>
                        </tbody>
                    </table>
                </div>
                <div id="botpagger" class="pagger my-3"></div>
                <div class="mt-3">
                    <a href="<%= request.getContextPath() %>/home" class="btn btn-secondary">&larr; Quay lại trang chủ</a>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="../static/js/pagger.js" type="text/javascript"></script>
<link href="../static/css/pagger.css" rel="stylesheet" type="text/css"/>
<script>
    pagger_init('toppagger', ${pageindex}, ${totalpage}, 2);
    pagger_init('botpagger', ${pageindex}, ${totalpage}, 2);
</script> 