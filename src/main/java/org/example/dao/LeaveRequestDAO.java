package org.example.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

import org.example.util.DBUtil;

public class LeaveRequestDAO {
    public boolean createLeaveRequest(int userId, Date startDate, Date endDate, String title, String reason) {
        String sql = "INSERT INTO Leave_Requests (user_id, start_date, end_date, title, reason, status, created_at, updated_at) " +
                "VALUES (?, ?, ?, ?, ?, 'Inprogress', GETDATE(), GETDATE())";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.setDate(2, startDate);
            ps.setDate(3, endDate);
            ps.setString(4, title);
            ps.setString(5, reason);
            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
} 