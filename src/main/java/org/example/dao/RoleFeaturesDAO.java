package org.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.example.entity.RoleFeatures;
import org.example.util.DBUtil;

public class RoleFeaturesDAO {
    public List<RoleFeatures> getAll() {
        List<RoleFeatures> list = new ArrayList<>();
        String sql = "SELECT * FROM Role_Features";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                RoleFeatures rf = new RoleFeatures();
                rf.setRoleId(rs.getInt("role_id"));
                rf.setFeatureId(rs.getInt("feature_id"));
                list.add(rf);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean add(int roleId, int featureId) {
        String sql = "INSERT INTO Role_Features (role_id, feature_id) VALUES (?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, roleId);
            ps.setInt(2, featureId);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(int roleId, int featureId) {
        String sql = "DELETE FROM Role_Features WHERE role_id = ? AND feature_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, roleId);
            ps.setInt(2, featureId);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public int countAll() {
        String sql = "SELECT COUNT(*) FROM Role_Features";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<RoleFeatures> getByPage(int page, int pageSize) {
        List<RoleFeatures> list = new ArrayList<>();
        String sql = "SELECT * FROM Role_Features ORDER BY role_id, feature_id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            int offset = (page - 1) * pageSize;
            ps.setInt(1, offset);
            ps.setInt(2, pageSize);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    RoleFeatures rf = new RoleFeatures();
                    rf.setRoleId(rs.getInt("role_id"));
                    rf.setFeatureId(rs.getInt("feature_id"));
                    list.add(rf);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
} 