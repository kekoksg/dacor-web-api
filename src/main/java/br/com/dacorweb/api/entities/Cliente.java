package br.com.dacorweb.api.entities;

import br.com.dacorweb.api.entities.enums.TipoPessoa;
import jakarta.persistence.*;
import lombok.Data;
import br.com.dacorweb.api.entities.enums.StatusCliente;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "clientes")
public class Cliente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoPessoa tipoPessoa;

    @Column(name = "cpf_cnpj", nullable = false, unique = true, length = 14)
    private String cpfCnpj;
    
    private String contato;
    
    private String endereco;
    
    @Column(name = "apelido_interno")
    private String apelidoInterno;
    
    @Column(name = "limite_credito", nullable = false, precision = 10, scale = 2)
    private BigDecimal limiteCredito = BigDecimal.ZERO;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private StatusCliente status = StatusCliente.ATIVO;
    
    @Column(name = "data_criacao", nullable = false, updatable = false)
    private LocalDateTime dataCriacao = LocalDateTime.now();
}
