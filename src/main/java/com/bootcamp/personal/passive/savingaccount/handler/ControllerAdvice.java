package com.bootcamp.personal.passive.savingaccount.handler;

import com.bootcamp.personal.passive.savingaccount.handler.exceptions.BadRequestException;
import com.bootcamp.personal.passive.savingaccount.handler.exceptions.NotFoundException;
import com.bootcamp.personal.passive.savingaccount.util.Util;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.logging.Level;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler( value = RuntimeException.class )
    public ResponseEntity<ErrorDto> runtimeExceptionHandler(RuntimeException ex) {
        ErrorDto err = ErrorDto.builder().code("RUNTIME").message(ex.getMessage()).build();

        return new ResponseEntity<>(err, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler( value = BadRequestException.class )
    public ResponseEntity<ErrorDto> badRequestExceptionHandler(BadRequestException ex) {
        ErrorDto err = ErrorDto.builder()
                .code(ex.getCode())
                .message(ex.getMessage())
                .detail(ex.getDetail())
                .context_class(ex.getContext_class())
                .context(ex.getContext())
                .build();

        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler( value = NotFoundException.class )
    public ResponseEntity<ErrorDto> notFoundExceptionHandler(NotFoundException ex) {
        ErrorDto err = ErrorDto.builder()
                .code(ex.getCode())
                .message(ex.getMessage())
                .detail(ex.getDetail())
                .context_class(ex.getContext_class())
                .context(ex.getContext())
                .build();

        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }

}
