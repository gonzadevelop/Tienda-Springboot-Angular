package es.tfg.backend.exception;

import es.tfg.backend.model.ErrorDTO;
import jakarta.validation.ConstraintViolationException;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> handleAllExceptions(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                ErrorDTO.builder()
                        .message("Error genérico " + ex.getMessage())
                        .timestamp(java.time.LocalDateTime.now())
                        .build()
        );
    }

    @ExceptionHandler({
            UsernameAlreadyExistsException.class,
            EmailAlrreadyExistsException.class
    })
    public ResponseEntity<ErrorDTO> handleConflictExceptions(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                ErrorDTO.builder()
                        .message(ex.getMessage())
                        .timestamp(java.time.LocalDateTime.now())
                        .build()
        );
    }
}
