package org.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.example.entity.Users;
import org.example.util.DBUtil;
import org.mindrot.jbcrypt.BCrypt;

public class UserDAO {
    public Users getUserByUsernameAndPassword(String username, String password) {
        String sql = "SELECT * FROM Users WHERE username = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String hashedPassword = rs.getString("password");
                    if (BCrypt.checkpw(password, hashedPassword)) {
                        Users user = new Users();
                        user.setUserId(rs.getInt("user_id"));
                        user.setUsername(rs.getString("username"));
                        user.setPassword(hashedPassword);
                        user.setFullName(rs.getString("full_name"));
                        user.setDepartmentId(rs.getInt("department_id"));
                        user.setManagerId(rs.getObject("manager_id") != null ? rs.getInt("manager_id") : null);
                        user.setEmail(rs.getString("email"));
                        return user;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Users getUserByUsername(String username) {
        String sql = "SELECT * FROM Users WHERE username = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Users user = new Users();
                    user.setUserId(rs.getInt("user_id"));
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
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

    public java.util.List<org.example.entity.Features> getFeaturesByUserId(int userId) {
        java.util.List<org.example.entity.Features> features = new java.util.ArrayList<>();
        String sql = "SELECT DISTINCT f.feature_id, f.feature_name, f.entrypoint " +
                "FROM Features f " +
                "JOIN Role_Features rf ON f.feature_id = rf.feature_id " +
                "JOIN User_Roles ur ON rf.role_id = ur.role_id " +
                "WHERE ur.user_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    org.example.entity.Features f = new org.example.entity.Features();
                    f.setFeatureId(rs.getInt("feature_id"));
                    f.setFeatureName(rs.getString("feature_name"));
                    f.setEntrypoint(rs.getString("entrypoint"));
                    features.add(f);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return features;
    }
} 