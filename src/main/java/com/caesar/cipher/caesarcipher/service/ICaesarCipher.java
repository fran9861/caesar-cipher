package com.caesar.cipher.caesarcipher.service;

import com.caesar.cipher.caesarcipher.openapi.model.SendResponseCaesarCipher;
import org.springframework.stereotype.Service;


@Service
public interface ICaesarCipher {
    SendResponseCaesarCipher CaesarCipherText(String text, Integer rotationFactor);
}
