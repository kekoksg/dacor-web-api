package br.com.dacorweb.api.security.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import br.com.dacorweb.api.entities.enums.PerfilUsuario;

@Data
public class RegisterRequest {
    @NotBlank
    private String nome;

    @NotBlank
    @Size(min = 11, max = 11, message = "CPF deve conter 11 dígitos")
    private String cpf;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 6, max = 40)
    private String senha;

    @NotBlank
    @NotNull
    private PerfilUsuario perfil;
}
