package br.com.dacorweb.api.security.services;

import br.com.dacorweb.api.entities.Usuario;
import br.com.dacorweb.api.entities.enums.PerfilUsuario;
import br.com.dacorweb.api.repositories.UsuarioRepository;
import br.com.dacorweb.api.security.UserDetailsImpl;
import br.com.dacorweb.api.security.dto.JwtResponse;
import br.com.dacorweb.api.security.dto.LoginRequest;
import br.com.dacorweb.api.security.dto.RegisterRequest;
import br.com.dacorweb.api.security.jwt.JwtUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder encoder;
    private final JwtUtils jwtUtils;

    public AuthService(AuthenticationManager authenticationManager,
                      UsuarioRepository usuarioRepository,
                      PasswordEncoder encoder,
                      JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.usuarioRepository = usuarioRepository;
        this.encoder = encoder;
        this.jwtUtils = jwtUtils;
    }

    public JwtResponse authenticateUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequest.getCpf(), loginRequest.getSenha())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();        
        List<String> roles = userDetails.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.toList());

        return new JwtResponse(
            jwt,
            "Bearer",
            userDetails.getId(),
            userDetails.getUsername(),
            userDetails.getEmail(),
            userDetails.getNome(),
            roles
        );
    }

    public void registerUser(RegisterRequest registerRequest) {
        if (usuarioRepository.existsByCpf(registerRequest.getCpf())) {
            throw new RuntimeException("Erro: CPF já está em uso!");
        }

        if (usuarioRepository.existsByEmail(registerRequest.getEmail())) {
            throw new RuntimeException("Erro: Email já está em uso!");
        }

        // Criar novo usuário
        Usuario usuario = new Usuario();
        usuario.setNome(registerRequest.getNome());
        usuario.setCpf(registerRequest.getCpf());
        usuario.setEmail(registerRequest.getEmail());
        usuario.setHashSenha(encoder.encode(registerRequest.getSenha()));
        
        // Definir perfil como DONO para o primeiro cadastro
        // Você pode modificar isso conforme necessário
        // TODO
        usuario.setPerfil(registerRequest.getPerfil() != null ? 
            registerRequest.getPerfil() : PerfilUsuario.DONO);

        usuarioRepository.save(usuario);
    }
}
