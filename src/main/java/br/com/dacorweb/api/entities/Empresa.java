package br.com.dacorweb.api.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "empresas")
public class Empresa {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String nomeFantasia;
    
    @Column(nullable = false, unique = true, length = 18)
    private String cnpj;
    
    private String razaoSocial;
    private String telefone;
    private String email;
    
    @OneToMany(mappedBy = "empresa")
    private List<Usuario> usuarios = new ArrayList<>();
    
    @Column(nullable = false)
    private boolean ativo = true;
}
