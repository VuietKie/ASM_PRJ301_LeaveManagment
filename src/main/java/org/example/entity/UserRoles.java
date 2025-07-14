package org.example.entity;

import java.util.Date;

public class UserRoles {
    private int userRoleId;
    private int userId;
    private int roleId;
    private int assignedBy;
    private Date assignedAt;

    public UserRoles() {}
    public UserRoles(int userRoleId, int userId, int roleId, int assignedBy, Date assignedAt) {
        this.userRoleId = userRoleId;
        this.userId = userId;
        this.roleId = roleId;
        this.assignedBy = assignedBy;
        this.assignedAt = assignedAt;
    }
    public int getUserRoleId() { return userRoleId; }
    public void setUserRoleId(int userRoleId) { this.userRoleId = userRoleId; }
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public int getRoleId() { return roleId; }
    public void setRoleId(int roleId) { this.roleId = roleId; }
    public int getAssignedBy() { return assignedBy; }
    public void setAssignedBy(int assignedBy) { this.assignedBy = assignedBy; }
    public Date getAssignedAt() { return assignedAt; }
    public void setAssignedAt(Date assignedAt) { this.assignedAt = assignedAt; }
} 