package com.AuditService.controller;

import com.AuditService.entity.AuditLog;
import com.AuditService.request.AuditRequest;
import com.AuditService.service.AuditService;
import com.AuditService.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.*;


import jakarta.servlet.http.HttpServletRequest;


import java.util.List;


@RestController
@RequestMapping("/api/audit")
public class AuditController {

    @Autowired
    private AuditService auditService;

    @Autowired
    private SecurityService securityService;

    @PostMapping("/log")
    public ResponseEntity<String> logAudit(@RequestBody AuditRequest auditRequest, HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);

        // Validate token and ensure user is authorized to log audit data
        if (!securityService.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid JWT Token");
        }

        auditService.logAudit(auditRequest);
        return ResponseEntity.ok("Audit logged successfully");
    }

    @GetMapping("/logs/{entityId}")
    public ResponseEntity<List<AuditLog>> getAuditLogs(@PathVariable String entityId, HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);

        // Validate token and extract role
        String role = securityService.getRoleFromToken(token);

        // Allow only admins or auditors to access audit logs
        if (!role.equals("ADMIN") && !role.equals("AUDITOR")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }

        List<AuditLog> logs = auditService.getAuditLogsForEntity(entityId);
        return ResponseEntity.ok(logs);
    }
}


