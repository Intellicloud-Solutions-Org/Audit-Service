package com.AuditService.repository;

import com.AuditService.entity.AuditLog;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface AuditRepository extends JpaRepository<AuditLog, Long> {
    List<AuditLog> findByEntityId(String entityId);
}

