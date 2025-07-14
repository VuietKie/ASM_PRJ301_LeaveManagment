package org.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.example.entity.Users;
import org.example.util.DBUtil;

public class UsersDAO {
    public List<Users> getAllUsers() {
        List<Users> list = new ArrayList<>();
        String sql = "SELECT * FROM Users";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Users user = new Users();
                user.setUserId(rs.getInt("user_id"));
                user.setUsername(rs.getString("username"));
                user.setFullName(rs.getString("full_name"));
                user.setDepartmentId(rs.getInt("department_id"));
                user.setManagerId(rs.getObject("manager_id") != null ? rs.getInt("manager_id") : null);
                user.setEmail(rs.getString("email"));
                list.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public Users getUserById(int userId) {
        String sql = "SELECT * FROM Users WHERE user_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Users user = new Users();
                    user.setUserId(rs.getInt("user_id"));
                    user.setUsername(rs.getString("username"));
                    user.setFullName(rs.getString("full_name"));
                    user.setDepartmentId(rs.getInt("department_id"));
                    user.setManagerId(rs.getObject("manager_id") != null ? rs.getInt("manager_id") : null);
                    user.setEmail(rs.getString("email"));
                    return user;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateUser(Users user) {
        String sql = "UPDATE Users SET full_name=?, department_id=?, manager_id=?, email=? WHERE user_id=?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user.getFullName());
            ps.setInt(2, user.getDepartmentId());
            if (user.getManagerId() != null) {
                ps.setInt(3, user.getManagerId());
            } else {
                ps.setNull(3, java.sql.Types.INTEGER);
            }
            ps.setString(4, user.getEmail());
            ps.setInt(5, user.getUserId());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateUserWithPassword(Users user, String hashedPassword) {
        String sql = "UPDATE Users SET full_name=?, department_id=?, manager_id=?, email=?, password=? WHERE user_id=?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user.getFullName());
            ps.setInt(2, user.getDepartmentId());
            if (user.getManagerId() != null) {
                ps.setInt(3, user.getManagerId());
            } else {
                ps.setNull(3, java.sql.Types.INTEGER);
            }
            ps.setString(4, user.getEmail());
            ps.setString(5, hashedPassword);
            ps.setInt(6, user.getUserId());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean addUser(Users user, String password) {
        String sql = "INSERT INTO Users (username, password, full_name, department_id, manager_id, email) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user.getUsername());
            ps.setString(2, password);
            ps.setString(3, user.getFullName());
            ps.setInt(4, user.getDepartmentId());
            if (user.getManagerId() != null) {
                ps.setInt(5, user.getManagerId());
            } else {
                ps.setNull(5, java.sql.Types.INTEGER);
            }
            ps.setString(6, user.getEmail());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
} 