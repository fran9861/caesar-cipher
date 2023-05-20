package com.caesar.cipher.caesarcipher.service;

import com.caesar.cipher.caesarcipher.DummyMock;
import com.caesar.cipher.caesarcipher.entities.MessageEntity;
import com.caesar.cipher.caesarcipher.openapi.model.SendResponseCaesarCipher;
import com.caesar.cipher.caesarcipher.openapi.model.SendResponseCaesarCipherList;
import com.caesar.cipher.caesarcipher.repository.MessageRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class CaesarCipherImplTest {

    @Mock
    MessageRepository messageRepository;

    @InjectMocks
    private CaesarCipherImpl caesarCipher;

    @Test
    public void testCaesarCipherText() {
        String text = "Hello, World!";
        Integer rotationFactor = 3;
        MessageEntity savedEntity = new MessageEntity();
        Mockito.when(messageRepository.save(Mockito.any(MessageEntity.class))).thenReturn(savedEntity);
        ResponseEntity<SendResponseCaesarCipher> response = caesarCipher.caesarCipherText(text, rotationFactor);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals("Khoor, Zruog!", response.getBody().getText());
    }


    @Test
    public void testCaesarCipherList() {
        Integer page = 1;
        Integer size = 10;

        Mockito.when(messageRepository.findAll(Mockito.any(Pageable.class))).thenReturn(DummyMock.repositoryResponseAllMessage(page,size, true));

        ResponseEntity<SendResponseCaesarCipherList> response = caesarCipher.caesarCipherList(page, size);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertFalse(response.getBody().getCaesarCipher().isEmpty());
    }
}
