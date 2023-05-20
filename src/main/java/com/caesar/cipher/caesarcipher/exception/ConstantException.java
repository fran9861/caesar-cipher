package com.caesar.cipher.caesarcipher.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ConstantException {
    ERROR_DATA_BASE_PERSISTENCE_NO_CONTENT("500","Error, no existen datos" ),
    ERROR_DATA_BASE_PERSISTENCE("500","Error en la base de datos, comuniquese con el admin del sistema" ),
    ERROR_DATA_BASE_RESTRICTION("400", "Violación de restricción al guardar la entidad en la base de datos"),
    ERROR_DATA_BASE("500", "Error en base de datos: "),
    ERROR_VALIDATION("400", "Error de validación: ");
    private final String code;
    private final String Message;

}
