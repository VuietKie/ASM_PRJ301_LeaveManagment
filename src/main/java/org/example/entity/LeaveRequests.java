package org.example.entity;

import java.util.Date;

public class LeaveRequests {
    private int requestId;
    private int userId;
    private Date startDate;
    private Date endDate;
    private String reason;
    private String status;
    private Integer processedBy;
    private String processedReason;
    private Date createdAt;
    private Date updatedAt;
    private String title;

    public LeaveRequests() {}
    public LeaveRequests(int requestId, int userId, Date startDate, Date endDate, String reason, String status, Integer processedBy, String processedReason, Date createdAt, Date updatedAt, String title) {
        this.requestId = requestId;
        this.userId = userId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.reason = reason;
        this.status = status;
        this.processedBy = processedBy;
        this.processedReason = processedReason;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.title = title;
    }
    public int getRequestId() { return requestId; }
    public void setRequestId(int requestId) { this.requestId = requestId; }
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public Date getStartDate() { return startDate; }
    public void setStartDate(Date startDate) { this.startDate = startDate; }
    public Date getEndDate() { return endDate; }
    public void setEndDate(Date endDate) { this.endDate = endDate; }
    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Integer getProcessedBy() { return processedBy; }
    public void setProcessedBy(Integer processedBy) { this.processedBy = processedBy; }
    public String getProcessedReason() { return processedReason; }
    public void setProcessedReason(String processedReason) { this.processedReason = processedReason; }
    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
    public Date getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Date updatedAt) { this.updatedAt = updatedAt; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
} 