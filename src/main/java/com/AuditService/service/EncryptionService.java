package com.AuditService.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

@Service
public class EncryptionService {

    @Value("${encryption.algorithm}")
    private String algorithm;  // e.g., "AES"

    @Value("${encryption.secretKey}")
    private String secretKey;  // Must be 16/24/32 bytes // Must be 16/24/32 bytes


public String encrypt(String data) throws Exception {
    SecretKey key = new SecretKeySpec(secretKey.getBytes(), algorithm);
    Cipher cipher = Cipher.getInstance(algorithm);
    cipher.init(Cipher.ENCRYPT_MODE, key);
    byte[] encryptedBytes = cipher.doFinal(data.getBytes());
    return new String(java.util.Base64.getEncoder().encode(encryptedBytes));
}


public String decrypt(String encryptedData) throws Exception {
    SecretKey key = new SecretKeySpec(secretKey.getBytes(), algorithm);
    Cipher cipher = Cipher.getInstance(algorithm);
    cipher.init(Cipher.DECRYPT_MODE, key);
    byte[] decryptedBytes = cipher.doFinal(java.util.Base64.getDecoder().decode(encryptedData));
    return new String(decryptedBytes);
    }
}




