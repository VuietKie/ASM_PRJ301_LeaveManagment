package org.example.entity;

public class Users {
    private int userId;
    private String username;
    private String password;
    private String fullName;
    private int departmentId;
    private Integer managerId;
    private String email;

    public Users() {}
    public Users(int userId, String username, String password, String fullName, int departmentId, Integer managerId, String email) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.departmentId = departmentId;
        this.managerId = managerId;
        this.email = email;
    }
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public int getDepartmentId() { return departmentId; }
    public void setDepartmentId(int departmentId) { this.departmentId = departmentId; }
    public Integer getManagerId() { return managerId; }
    public void setManagerId(Integer managerId) { this.managerId = managerId; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
} 