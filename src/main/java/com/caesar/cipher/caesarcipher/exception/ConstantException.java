package com.caesar.cipher.caesarcipher.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ConstantException {

    ERROR_VALIDATION("400", "Error de validación: ");
    private final String code;
    private final String Message;

}
