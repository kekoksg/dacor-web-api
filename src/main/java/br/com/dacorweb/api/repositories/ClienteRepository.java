package br.com.dacorweb.api.repositories;

import br.com.dacorweb.api.entities.Cliente;
import br.com.dacorweb.api.entities.enums.StatusCliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    
    // Busca um cliente por CPF/CNPJ
    Optional<Cliente> findByCpfCnpj(String cpfCnpj);
    
    // Verifica se existe um cliente com o CPF/CNPJ informado
    boolean existsByCpfCnpj(String cpfCnpj);
    
    // Busca clientes por status
    List<Cliente> findByStatus(StatusCliente status);
    
    // Busca clientes ativos
    List<Cliente> findByStatusOrderByNome(StatusCliente status);
    
    // Busca clientes que possuem limite de crédito maior ou igual ao valor informado
    List<Cliente> findByLimiteCreditoGreaterThanEqual(BigDecimal valor);
    
    // Busca clientes por nome (case insensitive e contendo o termo)
    List<Cliente> findByNomeContainingIgnoreCase(String nome);
    
    // Busca clientes por apelido (case insensitive e contendo o termo)
    List<Cliente> findByApelidoInternoContainingIgnoreCase(String apelido);
    
    // Busca clientes por parte do nome ou apelido
    @Query("SELECT c FROM Cliente c WHERE " +
           "LOWER(c.nome) LIKE LOWER(CONCAT('%', :termo, '%')) OR " +
           "LOWER(c.apelidoInterno) LIKE LOWER(CONCAT('%', :termo, '%'))")
    List<Cliente> buscarPorNomeOuApelido(@Param("termo") String termo);
}
