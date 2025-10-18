package br.com.dacorweb.api.entities;

import jakarta.persistence.*;
import lombok.Data;
import br.com.dacorweb.api.entities.enums.StatusNota;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "notas")
public class Nota {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//  Cliente que possui a nota
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

//  Usuario que criou a nota
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;
    
    @Column(name = "valor_total", nullable = false, precision = 10, scale = 2)
    private BigDecimal valorTotal;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 15)
    private StatusNota status = StatusNota.ABERTA;
    
    @Column(name = "data_criacao", nullable = false, updatable = false)
    private LocalDateTime dataCriacao = LocalDateTime.now();
    
    @Column(name = "data_edicao")
    private LocalDateTime dataEdicao;
    
    @Column(name = "data_cancelamento")
    private LocalDateTime dataCancelamento;
    
    @OneToMany(mappedBy = "nota", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FotoNota> fotos = new ArrayList<>();
    
    @OneToMany(mappedBy = "nota", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PagamentoNota> pagamentos = new ArrayList<>();
}
