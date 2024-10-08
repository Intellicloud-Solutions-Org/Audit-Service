package com.AuditService;

import com.AuditService.entity.AuditLog;
import com.AuditService.repository.AuditRepository;
import com.AuditService.request.AuditRequest;
import com.AuditService.service.AuditService;
import com.AuditService.service.EncryptionService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class AuditServiceTest {


    @InjectMocks
    private AuditService auditService;

    @Mock
    private AuditRepository auditRepository;

    @Mock
    private EncryptionService encryptionService;

    public AuditServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLogAudit() throws Exception {
        // Arrange
        AuditRequest auditRequest = new AuditRequest();
        auditRequest.setEntityId("123");
        auditRequest.setAction("CREATE");
        auditRequest.setUserId("user1");
        auditRequest.setRole("ADMIN");
        auditRequest.setTimestamp("2024-10-03T12:00:00Z");
        auditRequest.setDetails("Sensitive Data");

        String encryptedDetails = "encryptedData";
        when(encryptionService.encrypt(auditRequest.getDetails())).thenReturn(encryptedDetails);

        // Act
        auditService.logAudit(auditRequest);

        // Assert
        verify(auditRepository).save(any(AuditLog.class)); // Check if save was called
    }
}

