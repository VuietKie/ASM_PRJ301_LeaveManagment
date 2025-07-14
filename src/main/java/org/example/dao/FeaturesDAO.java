package org.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.example.entity.Features;
import org.example.util.DBUtil;

public class FeaturesDAO {
    public List<Features> getAll() {
        List<Features> list = new ArrayList<>();
        String sql = "SELECT * FROM Features";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Features f = new Features();
                f.setFeatureId(rs.getInt("feature_id"));
                f.setFeatureName(rs.getString("feature_name"));
                f.setEntrypoint(rs.getString("entrypoint"));
                list.add(f);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
} 