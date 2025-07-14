package org.example.entity;

import java.util.Date;

public class Agenda {
    private int agendaId;
    private int userId;
    private Date date;
    private String status;

    public Agenda() {}
    public Agenda(int agendaId, int userId, Date date, String status) {
        this.agendaId = agendaId;
        this.userId = userId;
        this.date = date;
        this.status = status;
    }
    public int getAgendaId() { return agendaId; }
    public void setAgendaId(int agendaId) { this.agendaId = agendaId; }
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
} 