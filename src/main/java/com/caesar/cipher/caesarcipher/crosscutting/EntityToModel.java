package com.caesar.cipher.caesarcipher.crosscutting;

import com.caesar.cipher.caesarcipher.entities.MessageEntity;
import com.caesar.cipher.caesarcipher.openapi.model.CaesarCipher;

public class EntityToModel {

    public EntityToModel() {}

    public static CaesarCipher toModel(MessageEntity messageEntity) {
        return CaesarCipher.builder()
                .id(messageEntity.getId().intValue())
                .message(messageEntity.getMessage())
                .rotationFactor(messageEntity.getRotationFactor())
                .messageCipher(messageEntity.getMessageCipher())
                .build();
    }
}
