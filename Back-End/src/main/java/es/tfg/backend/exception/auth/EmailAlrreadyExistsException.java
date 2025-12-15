package es.tfg.backend.exception.auth;

public class EmailAlrreadyExistsException extends RuntimeException {
    public EmailAlrreadyExistsException(String email) {
        super("El email " + email + " ya está registrado.");
    }
}
