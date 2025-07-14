package org.example.entity;

import java.util.Date;

public class AccessScope {
    private int accessId;
    private int accessorId;
    private Integer targetUserId;
    private Integer targetDepartmentId;
    private String permissionType;
    private Date expiryDate;

    public AccessScope() {}
    public AccessScope(int accessId, int accessorId, Integer targetUserId, Integer targetDepartmentId, String permissionType, Date expiryDate) {
        this.accessId = accessId;
        this.accessorId = accessorId;
        this.targetUserId = targetUserId;
        this.targetDepartmentId = targetDepartmentId;
        this.permissionType = permissionType;
        this.expiryDate = expiryDate;
    }
    public int getAccessId() { return accessId; }
    public void setAccessId(int accessId) { this.accessId = accessId; }
    public int getAccessorId() { return accessorId; }
    public void setAccessorId(int accessorId) { this.accessorId = accessorId; }
    public Integer getTargetUserId() { return targetUserId; }
    public void setTargetUserId(Integer targetUserId) { this.targetUserId = targetUserId; }
    public Integer getTargetDepartmentId() { return targetDepartmentId; }
    public void setTargetDepartmentId(Integer targetDepartmentId) { this.targetDepartmentId = targetDepartmentId; }
    public String getPermissionType() { return permissionType; }
    public void setPermissionType(String permissionType) { this.permissionType = permissionType; }
    public Date getExpiryDate() { return expiryDate; }
    public void setExpiryDate(Date expiryDate) { this.expiryDate = expiryDate; }
} 