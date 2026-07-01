package com.pokeadmin.adminteampokemon.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler( 
        BusinessException.class
    )

    
    public ResponseEntity<ErrorResponse> handleTrainerAlreadyExists(BusinessException ex) {
        ErrorResponse error = new ErrorResponse(ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(error);
    }

    public ResponseEntity<ErrorResponse> handleUnexpectedException(Exception ex) {

    ErrorResponse error =new ErrorResponse("Unexpected internal error");

    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .body(error);

}
}