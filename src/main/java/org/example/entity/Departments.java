package org.example.entity;

public class Departments {
    private int departmentId;
    private String departmentName;
    private Integer idManager;

    public Departments() {}
    public Departments(int departmentId, String departmentName, Integer idManager) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.idManager = idManager;
    }
    public int getDepartmentId() { return departmentId; }
    public void setDepartmentId(int departmentId) { this.departmentId = departmentId; }
    public String getDepartmentName() { return departmentName; }
    public void setDepartmentName(String departmentName) { this.departmentName = departmentName; }
    public Integer getIdManager() { return idManager; }
    public void setIdManager(Integer idManager) { this.idManager = idManager; }
} 