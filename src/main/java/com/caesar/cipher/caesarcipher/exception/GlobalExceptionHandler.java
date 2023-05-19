package com.caesar.cipher.caesarcipher.exception;

import com.caesar.cipher.caesarcipher.openapi.model.EmptyDataResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<EmptyDataResponse> handleValidationException(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();

        String errorMessage = ConstantException.ERROR_VALIDATION.getMessage()
                .concat(bindingResult.getFieldErrors()
                        .stream()
                        .map(error -> error.getField() + ": " + error.getDefaultMessage())
                        .reduce("", (msg1, msg2) -> msg1 + ", " + msg2));

        return new ResponseEntity<>(EmptyDataResponse
                .builder()
                .code(ConstantException.ERROR_VALIDATION.getCode())
                .message(errorMessage)
                .build(), HttpStatus.BAD_REQUEST);
    }
}