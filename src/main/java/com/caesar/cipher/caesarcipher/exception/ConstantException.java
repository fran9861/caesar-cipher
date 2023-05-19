package com.caesar.cipher.caesarcipher.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ConstantException {
    ERROR_DATA_BASE_PERSISTENCE("500","Error de persistencia al guardar la entidad en la base de datos" ),
    ERROR_DATA_BASE_RESTRICTION("400", "Violación de restricción al guardar la entidad en la base de datos"),
    ERROR_DATA_BASE("500", "Error en base de datos: "),
    ERROR_VALIDATION("400", "Error de validación: ");
    private final String code;
    private final String Message;

}
