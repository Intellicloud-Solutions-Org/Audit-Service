package com.AuditService.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


import java.util.Objects;

@Entity
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String entityId;
    private String action;
    private String userId;
    private String role;
    private String timestamp;
    private String details;  // Sensitive details encrypted


// Getters and setters
// toString, hashCode, equals methods


    // Getters and Setters
    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getEntityId() {
        return entityId;
    }


    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }


    public String getAction() {
        return action;
    }


    public void setAction(String action) {
        this.action = action;
    }


    public String getUserId() {
        return userId;
    }


    public void setUserId(String userId) {
        this.userId = userId;
    }


    public String getRole() {
        return role;
    }


    public void setRole(String role) {
        this.role = role;
    }


    public String getTimestamp() {
        return timestamp;
    }


    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }


    public String getDetails() {
        return details;
    }


    public void setDetails(String details) {
        this.details = details;
    }


    // toString method
    @Override
    public String toString() {
        return "AuditLog{" +
                "id=" + id +
                ", entityId='" + entityId + '\'' +
                ", action='" + action + '\'' +
                ", userId='" + userId + '\'' +
                ", role='" + role + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", details='" + details + '\'' +
                '}';
    }


    // hashCode method
    @Override
    public int hashCode() {
        return Objects.hash(id, entityId, action, userId, role, timestamp, details);
    }


    // equals method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AuditLog)) return false;
        AuditLog auditLog = (AuditLog) o;
        return Objects.equals(id, auditLog.id) &&
                Objects.equals(entityId, auditLog.entityId) &&
                Objects.equals(action, auditLog.action) &&
                Objects.equals(userId, auditLog.userId) &&
                Objects.equals(role, auditLog.role) &&
                Objects.equals(timestamp, auditLog.timestamp) &&
                Objects.equals(details, auditLog.details);
    }
}
