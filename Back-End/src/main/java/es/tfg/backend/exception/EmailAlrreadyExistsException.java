package es.tfg.backend.exception;

public class EmailAlrreadyExistsException extends RuntimeException {
    public EmailAlrreadyExistsException(String email) {
        super("El email " + email + " ya está registrado.");
    }
}
