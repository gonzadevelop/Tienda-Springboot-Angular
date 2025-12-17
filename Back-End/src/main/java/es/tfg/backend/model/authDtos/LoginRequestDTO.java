package es.tfg.backend.model.authDtos;

import lombok.Data;

@Data
public class LoginRequestDTO {
    private String email;
    private String password;
}
