package com.AuditService;

import com.AuditService.request.AuditRequest;
import com.AuditService.service.AuditService;
import com.AuditService.service.SecurityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.AuditService.controller.AuditController;

@WebMvcTest(AuditController.class)
public class AuditControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private AuditController auditController;

    @Mock
    private AuditService auditService;

    @Mock
    private SecurityService securityService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLogAudit() throws Exception {
        AuditRequest auditRequest = new AuditRequest();
        auditRequest.setEntityId("123");
        auditRequest.setAction("CREATE");
        auditRequest.setUserId("user1");
        auditRequest.setRole("ADMIN");
        auditRequest.setTimestamp("2024-10-03T12:00:00Z");
        auditRequest.setDetails("Sensitive Data");

        when(securityService.validateToken("Bearer token")).thenReturn(true);

        mockMvc.perform(post("/api/audit/log")
                        .header("Authorization", "Bearer token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"entityId\":\"123\",\"action\":\"CREATE\",\"userId\":\"user1\",\"role\":\"ADMIN\",\"timestamp\":\"2024-10-03T12:00:00Z\",\"details\":\"Sensitive Data\"}"))
                .andExpect(status().isOk());
    }
}

