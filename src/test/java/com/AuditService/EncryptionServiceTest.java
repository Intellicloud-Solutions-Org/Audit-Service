package com.AuditService;


import com.AuditService.service.EncryptionService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
public class EncryptionServiceTest {

    @InjectMocks
    private EncryptionService encryptionService;

    @Test
    void testEncryptionDecryption() throws Exception {
        String originalData = "Sensitive Data";
        String encryptedData = encryptionService.encrypt(originalData);
        String decryptedData = encryptionService.decrypt(encryptedData);

        assertEquals(originalData, decryptedData); // Check if decrypted data matches original
    }
}
