package org.example.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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

    public java.util.List<org.example.entity.LeaveRequests> getLeaveRequestsByUserId(int userId) {
        java.util.List<org.example.entity.LeaveRequests> list = new java.util.ArrayList<>();
        String sql = "SELECT * FROM Leave_Requests WHERE user_id = ? ORDER BY created_at DESC";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    org.example.entity.LeaveRequests lr = new org.example.entity.LeaveRequests();
                    lr.setRequestId(rs.getInt("request_id"));
                    lr.setUserId(rs.getInt("user_id"));
                    lr.setStartDate(rs.getDate("start_date"));
                    lr.setEndDate(rs.getDate("end_date"));
                    lr.setReason(rs.getString("reason"));
                    lr.setStatus(rs.getString("status"));
                    lr.setProcessedBy(rs.getObject("processed_by") != null ? rs.getInt("processed_by") : null);
                    lr.setProcessedReason(rs.getString("processed_reason"));
                    lr.setCreatedAt(rs.getTimestamp("created_at"));
                    lr.setUpdatedAt(rs.getTimestamp("updated_at"));
                    lr.setTitle(rs.getString("title"));
                    list.add(lr);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public java.util.List<org.example.entity.LeaveRequests> getLeaveRequestsOfSubordinates(int managerId) {
        java.util.List<org.example.entity.LeaveRequests> list = new java.util.ArrayList<>();
        String sql = "SELECT lr.* FROM Leave_Requests lr " +
                "JOIN Users u ON lr.user_id = u.user_id " +
                "WHERE u.manager_id = ? ORDER BY lr.created_at DESC";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, managerId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    org.example.entity.LeaveRequests lr = new org.example.entity.LeaveRequests();
                    lr.setRequestId(rs.getInt("request_id"));
                    lr.setUserId(rs.getInt("user_id"));
                    lr.setStartDate(rs.getDate("start_date"));
                    lr.setEndDate(rs.getDate("end_date"));
                    lr.setReason(rs.getString("reason"));
                    lr.setStatus(rs.getString("status"));
                    lr.setProcessedBy(rs.getObject("processed_by") != null ? rs.getInt("processed_by") : null);
                    lr.setProcessedReason(rs.getString("processed_reason"));
                    lr.setCreatedAt(rs.getTimestamp("created_at"));
                    lr.setUpdatedAt(rs.getTimestamp("updated_at"));
                    lr.setTitle(rs.getString("title"));
                    list.add(lr);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public org.example.entity.LeaveRequests getLeaveRequestById(int requestId) {
        String sql = "SELECT * FROM Leave_Requests WHERE request_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, requestId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    org.example.entity.LeaveRequests lr = new org.example.entity.LeaveRequests();
                    lr.setRequestId(rs.getInt("request_id"));
                    lr.setUserId(rs.getInt("user_id"));
                    lr.setStartDate(rs.getDate("start_date"));
                    lr.setEndDate(rs.getDate("end_date"));
                    lr.setReason(rs.getString("reason"));
                    lr.setStatus(rs.getString("status"));
                    lr.setProcessedBy(rs.getObject("processed_by") != null ? rs.getInt("processed_by") : null);
                    lr.setProcessedReason(rs.getString("processed_reason"));
                    lr.setCreatedAt(rs.getTimestamp("created_at"));
                    lr.setUpdatedAt(rs.getTimestamp("updated_at"));
                    lr.setTitle(rs.getString("title"));
                    return lr;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean processLeaveRequest(int requestId, String status, int processedBy, String processedReason) {
        String sql = "UPDATE Leave_Requests SET status = ?, processed_by = ?, processed_reason = ?, updated_at = GETDATE() WHERE request_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, status);
            ps.setInt(2, processedBy);
            ps.setString(3, processedReason);
            ps.setInt(4, requestId);
            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public java.util.List<org.example.entity.LeaveRequests> getLeaveRequestsOfDepartment(int departmentId, int excludeUserId) {
        java.util.List<org.example.entity.LeaveRequests> list = new java.util.ArrayList<>();
        String sql = "SELECT lr.* FROM Leave_Requests lr " +
                "JOIN Users u ON lr.user_id = u.user_id " +
                "WHERE u.department_id = ? AND lr.user_id <> ? ORDER BY lr.created_at DESC";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, departmentId);
            ps.setInt(2, excludeUserId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    org.example.entity.LeaveRequests lr = new org.example.entity.LeaveRequests();
                    lr.setRequestId(rs.getInt("request_id"));
                    lr.setUserId(rs.getInt("user_id"));
                    lr.setStartDate(rs.getDate("start_date"));
                    lr.setEndDate(rs.getDate("end_date"));
                    lr.setReason(rs.getString("reason"));
                    lr.setStatus(rs.getString("status"));
                    lr.setProcessedBy(rs.getObject("processed_by") != null ? rs.getInt("processed_by") : null);
                    lr.setProcessedReason(rs.getString("processed_reason"));
                    lr.setCreatedAt(rs.getTimestamp("created_at"));
                    lr.setUpdatedAt(rs.getTimestamp("updated_at"));
                    lr.setTitle(rs.getString("title"));
                    list.add(lr);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
} 