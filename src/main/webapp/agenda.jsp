<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="layout.jsp" %>
<%
    org.example.entity.Users currentUser = (org.example.entity.Users) session.getAttribute("currentUser");
    String contextPath = request.getContextPath();
    java.time.LocalDate from = (java.time.LocalDate) request.getAttribute("from");
    java.time.LocalDate to = (java.time.LocalDate) request.getAttribute("to");
    java.util.List<org.example.entity.Users> users = (java.util.List<org.example.entity.Users>) request.getAttribute("users");
    java.util.Map<Integer, java.util.List<org.example.entity.LeaveRequests>> userLeaveMap = (java.util.Map<Integer, java.util.List<org.example.entity.LeaveRequests>>) request.getAttribute("userLeaveMap");
    java.util.List<org.example.entity.Departments> departments = (java.util.List<org.example.entity.Departments>) request.getAttribute("departments");
    Integer selectedDepartmentId = (Integer) request.getAttribute("selectedDepartmentId");
    java.util.List<org.example.entity.Users> filteredUsers = new java.util.ArrayList<>();
    if (from != null && to != null && users != null) {
        for (org.example.entity.Users u : users) {
            boolean hasLeave = false;
            for (java.time.LocalDate d = from; !d.isAfter(to); d = d.plusDays(1)) {
                boolean isWeekend = d.getDayOfWeek().getValue() == 6 || d.getDayOfWeek().getValue() == 7;
                boolean isLeave = false;
                if (userLeaveMap != null && userLeaveMap.containsKey(u.getUserId())) {
                    for (org.example.entity.LeaveRequests lr : userLeaveMap.get(u.getUserId())) {
                        java.util.Date start = lr.getStartDate();
                        java.util.Date end = lr.getEndDate();
                        java.time.LocalDate startLocal, endLocal;
                        if (start instanceof java.sql.Date) {
                            startLocal = ((java.sql.Date) start).toLocalDate();
                        } else {
                            startLocal = start.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
                        }
                        if (end instanceof java.sql.Date) {
                            endLocal = ((java.sql.Date) end).toLocalDate();
                        } else {
                            endLocal = end.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
                        }
                        if (!d.isBefore(startLocal) && !d.isAfter(endLocal)) {
                            isLeave = true;
                            break;
                        }
                    }
                }
                if (isLeave) {
                    hasLeave = true;
                    break;
                }
            }
            if (hasLeave) filteredUsers.add(u);
        }
    }
%>
<div class="row justify-content-center">
    <div class="col-lg-12 col-md-12">
        <div class="card shadow mb-4">
            <div class="card-body">
                <h2 class="mb-4">Lịch nghỉ nhân viên</h2>
                <form method="post" action="<%= contextPath %>/agenda" class="row g-3 align-items-end mb-4">
                    <div class="col-md-3">
                        <label for="from_date" class="form-label">Từ ngày</label>
                        <input type="date" class="form-control" id="from_date" name="from_date" value="<%= from != null ? from : "" %>" required>
                    </div>
                    <div class="col-md-3">
                        <label for="to_date" class="form-label">Đến ngày</label>
                        <input type="date" class="form-control" id="to_date" name="to_date" value="<%= to != null ? to : "" %>" required>
                    </div>
                    <div class="col-md-4">
                        <label for="department_id" class="form-label">Phòng ban</label>
                        <select class="form-select" id="department_id" name="department_id">
                            <option value="">Tất cả</option>
                            <% if (departments != null) for (org.example.entity.Departments d : departments) { %>
                                <option value="<%= d.getDepartmentId() %>" <%= (selectedDepartmentId != null && selectedDepartmentId == d.getDepartmentId()) ? "selected" : "" %>><%= d.getDepartmentName() %></option>
                            <% } %>
                        </select>
                    </div>
                    <div class="col-md-2 d-grid">
                        <button type="submit" class="btn btn-primary">Xem lịch</button>
                    </div>
                </form>
                <% if (from != null && to != null && filteredUsers != null && !filteredUsers.isEmpty()) { %>
                <div class="table-responsive">
                    <table class="table table-bordered table-hover align-middle bg-white">
                        <thead class="table-primary">
                        <tr>
                            <th>Nhân viên</th>
                            <% for (java.time.LocalDate d = from; !d.isAfter(to); d = d.plusDays(1)) { %>
                                <th><%= d %></th>
                            <% } %>
                        </tr>
                        </thead>
                        <tbody>
                        <% for (org.example.entity.Users u : filteredUsers) { %>
                            <tr>
                                <td><%= u.getFullName() %></td>
                                <% for (java.time.LocalDate d = from; !d.isAfter(to); d = d.plusDays(1)) {
                                    boolean isWeekend = d.getDayOfWeek().getValue() == 6 || d.getDayOfWeek().getValue() == 7;
                                    boolean isLeave = false;
                                    if (userLeaveMap != null && userLeaveMap.containsKey(u.getUserId())) {
                                        for (org.example.entity.LeaveRequests lr : userLeaveMap.get(u.getUserId())) {
                                            java.util.Date start = lr.getStartDate();
                                            java.util.Date end = lr.getEndDate();
                                            java.time.LocalDate startLocal, endLocal;
                                            if (start instanceof java.sql.Date) {
                                                startLocal = ((java.sql.Date) start).toLocalDate();
                                            } else {
                                                startLocal = start.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
                                            }
                                            if (end instanceof java.sql.Date) {
                                                endLocal = ((java.sql.Date) end).toLocalDate();
                                            } else {
                                                endLocal = end.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
                                            }
                                            if (!d.isBefore(startLocal) && !d.isAfter(endLocal)) {
                                                isLeave = true;
                                                break;
                                            }
                                        }
                                    }
                                    String cellClass = "";
                                    String cellContent = "-";
                                    if (isWeekend) {
                                        cellClass = "table-secondary";
                                        cellContent = "Nghỉ (T7/CN)";
                                    } else if (isLeave) {
                                        cellClass = "table-danger";
                                        cellContent = "Nghỉ phép";
                                    }
                                %>
                                <td class="<%= cellClass %>"><%= cellContent %></td>
                                <% } %>
                            </tr>
                        <% } %>
                        </tbody>
                    </table>
                </div>
                <% } else if (from != null && to != null) { %>
                    <div class="alert alert-info mt-3">Không có nhân viên nào nghỉ phép trong khoảng này.</div>
                <% } %>
                <div class="mt-3">
                    <a href="<%= contextPath %>/home" class="btn btn-secondary">&larr; Quay lại trang chủ</a>
                </div>
            </div>
        </div>
    </div>
</div> 