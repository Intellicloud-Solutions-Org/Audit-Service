package com.AuditService.request;

public class AuditRequest {

    private String entityId;
    private String action;
    private String userId;
    private String role;
    private String timestamp;
    private String details; // Sensitive details to be encrypted


    // Getter for entityId
    public String getEntityId() {
        return entityId;
    }


    // Setter for entityId
    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }


    // Getter for action
    public String getAction() {
        return action;
    }


    // Setter for action
    public void setAction(String action) {
        this.action = action;
    }


    // Getter for userId
    public String getUserId() {
        return userId;
    }


    // Setter for userId
    public void setUserId(String userId) {
        this.userId = userId;
    }


    // Getter for role
    public String getRole() {
        return role;
    }


    // Setter for role
    public void setRole(String role) {
        this.role = role;
    }


    // Getter for timestamp
    public String getTimestamp() {
        return timestamp;
    }


    // Setter for timestamp
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }


    // Getter for details
    public String getDetails() {
        return details;
    }


    // Setter for details
    public void setDetails(String details) {
        this.details = details;
    }
}
