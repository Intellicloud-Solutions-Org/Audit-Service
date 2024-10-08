package com.AuditService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import com.AuditService.service.SecurityService;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class SecurityServiceTest {

    @InjectMocks
    private SecurityService securityService;

    private final String secretKey = "B374A26A71490437AA024E4FADD5B497FDFF1A8EA6FF12F6FB65AF2720B59CCF"; // Make sure this matches your application's secret

    @Test
    void testValidateToken() {
        String token = Jwts.builder()
                .setSubject("user1")
                .claim("role", "ADMIN")
                .signWith(SignatureAlgorithm.HS256, secretKey.getBytes())
                .compact();

        assertTrue(securityService.validateToken(token));
    }

    @Test
    void testGetRoleFromToken() {
        String token = Jwts.builder()
                .setSubject("user1")
                .claim("role", "ADMIN")
                .signWith(SignatureAlgorithm.HS256, secretKey.getBytes())
                .compact();

        assertEquals("ADMIN", securityService.getRoleFromToken(token));
    }
}
