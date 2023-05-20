package com.caesar.cipher.caesarcipher.service;

import com.caesar.cipher.caesarcipher.crosscutting.Constant;
import com.caesar.cipher.caesarcipher.crosscutting.ConstantPath;
import com.caesar.cipher.caesarcipher.crosscutting.EntityToModel;
import com.caesar.cipher.caesarcipher.entities.MessageEntity;
import com.caesar.cipher.caesarcipher.openapi.model.CaesarCipher;
import com.caesar.cipher.caesarcipher.openapi.model.SendResponseCaesarCipher;
import com.caesar.cipher.caesarcipher.openapi.model.SendResponseCaesarCipherList;
import com.caesar.cipher.caesarcipher.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.owasp.esapi.logging.cleaning.NewlineLogScrubber;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CaesarCipherImpl implements ICaesarCipher {

    private static  final NewlineLogScrubber logClean = new NewlineLogScrubber();
    private final MessageRepository messageRepository;

    @Override
    public ResponseEntity<SendResponseCaesarCipher> caesarCipherText(String text, Integer rotationFactor) {
        log.info(logClean.cleanMessage(String.format("Entered /".concat(ConstantPath.CAESAR_CIPHER_PATH).concat("CaesarCipherText in CaesarCipherImpl"))));
        String messageCipher = caesarCipherAlgorithm(text,rotationFactor);
        MessageEntity messageEntity = new MessageEntity();
        messageEntity.setMessage(text);
        messageEntity.setMessageCipher(messageCipher);
        messageEntity.setRotationFactor(rotationFactor);
        saveMessage(messageEntity);
        return ResponseEntity.ok(SendResponseCaesarCipher.builder().text(messageCipher).build());
    }

    @Override
    public ResponseEntity<SendResponseCaesarCipherList> caesarCipherList(Integer page, Integer size) {
        page = page -1;
        Pageable pageable = PageRequest.of(Objects.nonNull(page) ? page : 0 , size, Sort.by(Constant.SORT_LIST).ascending());
        Page<MessageEntity> response = messageRepository.findAll(pageable);
        if(response.isEmpty()) {
            throw new NoResultException(Constant.MESSAGE_ERROR_NO_CONTENT_EXCEPTION);
        }

        return ResponseEntity.ok(SendResponseCaesarCipherList
                .builder()
                .caesarCipher(response.getContent().stream().map(EntityToModel::toModel).collect(Collectors.toList()))
                .totalElement(Integer.parseInt(String.valueOf(response.getTotalElements())))
                .totalPage(response.getTotalPages())
                .build());
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
