package org.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.example.entity.Departments;
import org.example.util.DBUtil;

public class DepartmentsDAO {
    public List<Departments> getAllDepartments() {
        List<Departments> list = new ArrayList<>();
        String sql = "SELECT * FROM Departments";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Departments d = new Departments();
                d.setDepartmentId(rs.getInt("department_id"));
                d.setDepartmentName(rs.getString("department_name"));
                d.setIdManager(rs.getObject("id_manager") != null ? rs.getInt("id_manager") : null);
                list.add(d);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
} 