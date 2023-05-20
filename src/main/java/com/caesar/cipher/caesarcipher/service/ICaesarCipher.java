package com.caesar.cipher.caesarcipher.service;

import com.caesar.cipher.caesarcipher.openapi.model.SendResponseCaesarCipher;
import com.caesar.cipher.caesarcipher.openapi.model.SendResponseCaesarCipherList;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public interface ICaesarCipher {
   ResponseEntity<SendResponseCaesarCipher> caesarCipherText(String text, Integer rotationFactor);
   ResponseEntity<SendResponseCaesarCipherList> caesarCipherList(Integer page, Integer size);
}
