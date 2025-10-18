package br.com.dacorweb.api.entities;

import jakarta.persistence.*;
import lombok.Data;
import br.com.dacorweb.api.entities.enums.PerfilUsuario;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "usuarios")
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String nome;
    
    @Column(nullable = false, unique = true, length = 11)
    private String cpf;
    
    @Column(nullable = false, unique = true)
    private String email;
    
    @Column(name = "hash_senha", nullable = false)
    private String hashSenha;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private PerfilUsuario perfil;
    
    @Column(name = "data_criacao", nullable = false, updatable = false)
    private LocalDateTime dataCriacao = LocalDateTime.now();
    
    @Column(nullable = false)
    private boolean ativo = true;
}
