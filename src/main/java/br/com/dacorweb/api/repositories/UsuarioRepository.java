package br.com.dacorweb.api.repositories;

import br.com.dacorweb.api.entities.Usuario;
import br.com.dacorweb.api.entities.enums.PerfilUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    // Busca um usuário por CPF
    Optional<Usuario> findByCpf(String cpf);
    
    // Busca um usuário por email
    Optional<Usuario> findByEmail(String email);
    
    // Verifica se existe um usuário com o CPF informado
    boolean existsByCpf(String cpf);
    
    // Verifica se existe um usuário com o email informado
    boolean existsByEmail(String email);
    
    // Busca usuários por perfil
    List<Usuario> findByPerfil(PerfilUsuario perfil);
    
    // Busca usuários ativos
    List<Usuario> findByAtivoTrue();

}
