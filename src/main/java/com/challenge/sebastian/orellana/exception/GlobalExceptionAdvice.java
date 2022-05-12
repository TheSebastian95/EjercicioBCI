package com.challenge.sebastian.orellana.exception;

import com.challenge.sebastian.orellana.dto.ErrorDTO;
import com.challenge.sebastian.orellana.exception.BackendException;
import com.challenge.sebastian.orellana.exception.InvalidArgumentException;
import com.challenge.sebastian.orellana.exception.ResourceAlreadyExistException;
import com.challenge.sebastian.orellana.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
@Slf4j
public class GlobalExceptionAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllExceptions(Exception exception) {
        logError(exception);
        ErrorDTO error = ErrorDTO.builder().mensaje(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()).build();
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> resourceNotFoundException(ResourceNotFoundException ex) {
        logError(ex);
        ErrorDTO detail = ErrorDTO.builder()
                .mensaje(ex.getMessage())
                .build();
        return new ResponseEntity<>(detail, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceAlreadyExistException.class)
    public ResponseEntity<Object> resourceAlreadyExistException(ResourceAlreadyExistException ex) {
        logError(ex);
        ErrorDTO detail = ErrorDTO.builder()
                .mensaje(ex.getMessage())
                .build();
        return new ResponseEntity<>(detail, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(BackendException.class)
    public ResponseEntity<Object> backendException(BackendException ex) {
        logError(ex);
        ErrorDTO detail = ErrorDTO.builder()
                .mensaje(ex.getMessage())
                .build();
        return new ResponseEntity<>(detail, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(InvalidArgumentException.class)
    public ResponseEntity<Object> invalidArgumentException(InvalidArgumentException ex) {
        logError(ex);
        ErrorDTO detail = ErrorDTO.builder()
                .mensaje(ex.getMessage())
                .build();
        return new ResponseEntity<>(detail, HttpStatus.BAD_REQUEST);
    }

    private void logError(Exception exception) {
        log.error("[ExceptionController] Exception caught", exception);
    }
}
