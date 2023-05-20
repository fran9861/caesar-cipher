package com.caesar.cipher.caesarcipher;

import com.caesar.cipher.caesarcipher.crosscutting.Constant;
import com.caesar.cipher.caesarcipher.entities.MessageEntity;
import com.caesar.cipher.caesarcipher.openapi.model.CaesarCipher;
import com.caesar.cipher.caesarcipher.openapi.model.SendRequestCaesarCipher;
import com.caesar.cipher.caesarcipher.openapi.model.SendResponseCaesarCipherList;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

public class DummyMock {
    public static SendRequestCaesarCipher sendRequestCaesarCipher() {
        return SendRequestCaesarCipher
                .builder()
                .rotationFactor(2)
                .text("hola")
                .build();
    }

    public static ResponseEntity<SendResponseCaesarCipherList> sendResponseCaesarCipherList() {
        List<CaesarCipher> caesarCiphers = new ArrayList<>();
        caesarCiphers
                .add(CaesarCipher
                        .builder()
                        .messageCipher("hola")
                        .id(1)
                        .message("hola")
                        .rotationFactor(3)
                        .build());
        return ResponseEntity.ok(SendResponseCaesarCipherList.builder().caesarCipher(caesarCiphers).build());
    }

    public static Page<MessageEntity> repositoryResponseAllMessage(Integer page, Integer size, Boolean isNull){
        MessageEntity messageEntity =  new MessageEntity();
        messageEntity.setMessage("hola");
        messageEntity.setMessageCipher("hola");
        messageEntity.setId(Long.parseLong("1"));
        messageEntity.setRotationFactor(26);

        List<MessageEntity> messageEntities = new ArrayList<>();
        if (isNull) {
            messageEntities.add(messageEntity);
        }
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by(Constant.SORT_LIST).ascending());

        Page<MessageEntity> messageEntityPage = new PageImpl<>(messageEntities, pageable, messageEntities.size());

        return  messageEntityPage;
    }
}
