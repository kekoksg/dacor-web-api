package br.com.dacorweb.api.security.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {
    @NotBlank
    private String cpf;

    @NotBlank
    private String senha;
}
