package com.AuditService.service;

import com.AuditService.entity.AuditLog;
import com.AuditService.repository.AuditRepository;
import com.AuditService.request.AuditRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuditService {

    @Autowired
    private AuditRepository auditRepository;


    @Autowired
    private EncryptionService encryptionService;


    public void logAudit(AuditRequest auditRequest) {
        AuditLog log = new AuditLog();
        log.setEntityId(auditRequest.getEntityId());
        log.setAction(auditRequest.getAction());
        log.setUserId(auditRequest.getUserId());
        log.setRole(auditRequest.getRole());
        log.setTimestamp(auditRequest.getTimestamp());


        try {
            // Encrypt sensitive details before saving
            String encryptedDetails = encryptionService.encrypt(auditRequest.getDetails());
            log.setDetails(encryptedDetails);
        } catch (Exception e) {
            // Handle the exception (logging, rethrowing, etc.)
            // For example, you might want to log the error
            System.err.println("Error encrypting details: " + e.getMessage());
            // Optionally rethrow as a custom runtime exception
            throw new RuntimeException("Failed to encrypt sensitive details", e);
        }
        auditRepository.save(log);
    }


    public List<AuditLog> getAuditLogsForEntity(String entityId) {
        return auditRepository.findByEntityId(entityId);
    }
}

