package com.caesar.cipher.caesarcipher.exception;

import com.caesar.cipher.caesarcipher.openapi.model.EmptyDataResponse;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import javax.persistence.PersistenceException;
import java.sql.SQLIntegrityConstraintViolationException;

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

    @ExceptionHandler({DataAccessException.class, PersistenceException.class})
    public ResponseEntity<Object> handleDataAccessException(Exception ex, WebRequest request) {
        String errorMessage = ConstantException.ERROR_DATA_BASE.getMessage();
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        String code = ConstantException.ERROR_DATA_BASE.getCode();
        if (ex instanceof SQLIntegrityConstraintViolationException) {
            errorMessage.concat(ConstantException.ERROR_DATA_BASE_RESTRICTION.getMessage());
            code = ConstantException.ERROR_DATA_BASE_RESTRICTION.getCode();
            status = HttpStatus.BAD_REQUEST;
        } else if (ex instanceof PersistenceException) {
            errorMessage.concat(ConstantException.ERROR_DATA_BASE_PERSISTENCE.getMessage()) ;
            code = ConstantException.ERROR_DATA_BASE_PERSISTENCE.getCode();
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(EmptyDataResponse
                .builder()
                .code(code)
                .message(errorMessage)
                .build(), status);
    }
}