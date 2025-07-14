<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    org.example.entity.Users currentUser = (org.example.entity.Users) session.getAttribute("currentUser");
    if (currentUser == null) {
        response.sendRedirect("login");
        return;
    }
    String contextPath = request.getContextPath();
    java.time.LocalDate from = (java.time.LocalDate) request.getAttribute("from");
    java.time.LocalDate to = (java.time.LocalDate) request.getAttribute("to");
    java.util.List<org.example.entity.Users> users = (java.util.List<org.example.entity.Users>) request.getAttribute("users");
    java.util.Map<Integer, java.util.List<org.example.entity.LeaveRequests>> userLeaveMap = (java.util.Map<Integer, java.util.List<org.example.entity.LeaveRequests>>) request.getAttribute("userLeaveMap");
    java.util.List<org.example.entity.Departments> departments = (java.util.List<org.example.entity.Departments>) request.getAttribute("departments");
    Integer selectedDepartmentId = (Integer) request.getAttribute("selectedDepartmentId");

    // Lọc danh sách users có ít nhất một ngày nghỉ trong khoảng
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
<!DOCTYPE html>
<html>
<head>
    <title>Lịch nghỉ nhân viên</title>
    <style>th,td { text-align: center; }</style>
</head>
<body>
    <h2>Lịch nghỉ nhân viên</h2>
    <form method="post" action="<%= contextPath %>/agenda">
        <label for="from_date">Từ ngày:</label>
        <input type="date" id="from_date" name="from_date" value="<%= from != null ? from : "" %>" required>
        <label for="to_date">Đến ngày:</label>
        <input type="date" id="to_date" name="to_date" value="<%= to != null ? to : "" %>" required>
        <label for="department_id">Phòng ban:</label>
        <select id="department_id" name="department_id">
            <option value="">Tất cả</option>
            <% if (departments != null) for (org.example.entity.Departments d : departments) { %>
                <option value="<%= d.getDepartmentId() %>" <%= (selectedDepartmentId != null && selectedDepartmentId == d.getDepartmentId()) ? "selected" : "" %>><%= d.getDepartmentName() %></option>
            <% } %>
        </select>
        <button type="submit">Xem lịch</button>
    </form>
    <br>
    <% if (from != null && to != null && filteredUsers != null) { %>
    <table border="1" cellpadding="5" cellspacing="0">
        <tr>
            <th>Nhân viên</th>
            <% for (java.time.LocalDate d = from; !d.isAfter(to); d = d.plusDays(1)) { %>
                <th><%= d %> (<%= d.getDayOfWeek() %>)</th>
            <% } %>
        </tr>
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
                    String bgColor = "#fff";
                    String cellContent = "-";
                    if (isWeekend) {
                        bgColor = "#eee";
                        cellContent = "Nghỉ (T7/CN)";
                    } else if (isLeave) {
                        bgColor = "#f99";
                        cellContent = "Nghỉ phép";
                    }
                    out.print("<td style='background-color: " + bgColor + ";'>" + cellContent + "</td>");
                } %>
            </tr>
        <% } %>
    </table>
    <% } %>
    <br>
    <a href="<%= contextPath %>/home">Quay lại trang chủ</a>
</body>
</html> 