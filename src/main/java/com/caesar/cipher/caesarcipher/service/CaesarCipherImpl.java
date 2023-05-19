package com.caesar.cipher.caesarcipher.service;

import com.caesar.cipher.caesarcipher.crosscutting.ConstantPath;
import com.caesar.cipher.caesarcipher.openapi.model.SendResponseCaesarCipher;
import lombok.extern.slf4j.Slf4j;
import org.owasp.esapi.logging.cleaning.NewlineLogScrubber;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CaesarCipherImpl implements ICaesarCipher {

    private static  final NewlineLogScrubber logClean = new NewlineLogScrubber();

    @Override
    public SendResponseCaesarCipher CaesarCipherText(String text, Integer rotationFactor) {
        log.info(logClean.cleanMessage(String.format("Entered /".concat(ConstantPath.CAESAR_CIPHER_PATH).concat("CaesarCipherText in CaesarCipherImpl"))));
        return SendResponseCaesarCipher.builder().text(caesarCipherAlgorithm(text,rotationFactor)).build();
    }

    private  String caesarCipherAlgorithm(String s, int k) {
        StringBuilder result = new StringBuilder();
        k = k % 26;

        for (char c : s.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                char encryptedChar = (char) ((c - base + k) % 26 + base);
                result.append(encryptedChar);
            } else {
                result.append(c); // Mantenemos los caracteres no alfabéticos sin cifrar
            }
        }
        return result.toString();

    }
}
