package com.caesar.cipher.caesarcipher.service;

import com.caesar.cipher.caesarcipher.crosscutting.Constant;
import com.caesar.cipher.caesarcipher.crosscutting.ConstantPath;
import com.caesar.cipher.caesarcipher.entities.MessageEntity;
import com.caesar.cipher.caesarcipher.openapi.model.SendResponseCaesarCipher;
import com.caesar.cipher.caesarcipher.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.owasp.esapi.logging.cleaning.NewlineLogScrubber;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CaesarCipherImpl implements ICaesarCipher {

    private static  final NewlineLogScrubber logClean = new NewlineLogScrubber();
    private final MessageRepository messageRepository;

    @Override
    public SendResponseCaesarCipher CaesarCipherText(String text, Integer rotationFactor) {
        log.info(logClean.cleanMessage(String.format("Entered /".concat(ConstantPath.CAESAR_CIPHER_PATH).concat("CaesarCipherText in CaesarCipherImpl"))));
        String messageCipher = caesarCipherAlgorithm(text,rotationFactor);
        MessageEntity messageEntity = new MessageEntity();
        messageEntity.setMessage(text);
        messageEntity.setMessageCipher(messageCipher);
        messageEntity.setRotationFactor(rotationFactor);
        saveMessage(messageEntity);
        return SendResponseCaesarCipher.builder().text(messageCipher).build();
    }
    @Transactional
    public void saveMessage(MessageEntity message) {
        messageRepository.save(message);
        log.info(logClean.cleanMessage(String.format("Entered /".concat(ConstantPath.CAESAR_CIPHER_PATH).concat("saveMessage in CaesarCipherImpl - save successful"))));

    }
    private  String caesarCipherAlgorithm(String s, int k) {
        StringBuilder result = new StringBuilder();
        k = k % Constant.NUMBER_ASCII;

        for (char c : s.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                char encryptedChar = (char) ((c - base + k) % Constant.NUMBER_ASCII + base);
                result.append(encryptedChar);
            } else {
                result.append(c);
            }
        }
        return result.toString();

    }
}
