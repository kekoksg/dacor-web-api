package br.com.dacorweb.api.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String cpf;
    private String email;
    private String nome;
    private List<String> roles;
}
