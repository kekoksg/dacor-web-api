# DACOR Web API - Sistema de Gestão de Contas de Clientes

## Visão Geral

A DACOR Web API é um sistema desenvolvido para digitalizar, automatizar e otimizar a gestão de contas de clientes, eliminando o processo manual baseado em papel e planilhas. O sistema permite o gerenciamento de usuários, clientes, notas fiscais e pagamentos de forma integrada com autenticação JWT segura.

## Tecnologias Utilizadas

- **Backend**: Spring Boot 3.5.6
- **Banco de Dados**: PostgreSQL
- **Java**: 17
- **Maven**: Para gerenciamento de dependências
- **JPA/Hibernate**: Para mapeamento objeto-relacional
- **Spring Security**: Para autenticação e autorização
- **JWT (JSON Web Token)**: Para tokens de autenticação
- **Hibernate Validator**: Para validação de dados

## Estrutura do Projeto

```
src/main/java/br/com/dacorweb/api/
├── ApiApplication.java
├── entities/
│   ├── enums/
│   │   ├── Cargo.java
│   │   ├── MetodoPagamento.java
│   │   ├── PerfilUsuario.java
│   │   ├── StatusCliente.java
│   │   ├── StatusNota.java
│   │   └── TipoPessoa.java
│   ├── Cliente.java
│   ├── FotoNota.java
│   ├── Nota.java
│   ├── Pagamento.java
│   ├── PagamentoNota.java
│   └── Usuario.java
├── repositories/
│   ├── ClienteRepository.java
│   ├── NotaRepository.java
│   ├── PagamentoRepository.java
│   ├── FotoNotaRepository.java
│   ├── PagamentoNotaRepository.java
│   └── UsuarioRepository.java
├── controllers/
│   ├── AuthController.java
│   ├── ClienteController.java
│   ├── NotaController.java
│   ├── PagamentoController.java
│   ├── UsuarioController.java
│   └── RelatorioController.java
├── services/
│   ├── AuthService.java
│   ├── ClienteService.java
│   ├── NotaService.java
│   ├── PagamentoService.java
│   ├── UsuarioService.java
│   ├── RelatorioService.java
│   └── EmailService.java
├── dto/
│   ├── auth/
│   │   ├── JwtResponse.java
│   │   ├── LoginRequest.java
│   │   └── RegisterRequest.java
│   ├── cliente/
│   │   ├── ClienteRequest.java
│   │   ├── ClienteResponse.java
│   │   └── ClienteUpdateRequest.java
│   ├── nota/
│   │   ├── NotaRequest.java
│   │   ├── NotaResponse.java
│   │   └── NotaUpdateRequest.java
│   ├── pagamento/
│   │   ├── PagamentoRequest.java
│   │   ├── PagamentoResponse.java
│   │   └── PagamentoAplicacaoRequest.java
│   └── usuario/
│       ├── UsuarioRequest.java
│       ├── UsuarioResponse.java
│       └── UsuarioUpdateRequest.java
├── security/
│   ├── jwt/
│   │   ├── JwtAuthFilter.java
│   │   └── JwtUtils.java
│   ├── SecurityConfig.java
│   ├── UserDetailsImpl.java
│   └── UserDetailsServiceImpl.java
├── exceptions/
│   ├── GlobalExceptionHandler.java
│   ├── ResourceNotFoundException.java
│   ├── ValidationException.java
│   └── UnauthorizedException.java
├── utils/
│   ├── DateUtils.java
│   ├── ValidationUtils.java
│   └── FileUtils.java
└── config/
    ├── SwaggerConfig.java
    ├── CorsConfig.java
    └── DatabaseConfig.java
```

## Modelo de Dados

### Entidades Principais

- **Usuário**: Gerencia acesso ao sistema com autenticação JWT
- **Cliente**: Cadastro completo com dados pessoais e controle de crédito
- **Nota**: Registro de débitos com anexo de fotos e controle de status
- **Pagamento**: Registro de pagamentos com múltiplos métodos
- **FotoNota**: Anexo de imagens às notas
- **PagamentoNota**: Relacionamento entre pagamentos e notas

### Enums do Sistema

- **PerfilUsuario**: DONO, FUNCIONARIO
- **TipoPessoa**: FISICA, JURIDICA  
- **StatusCliente**: ATIVO, INATIVO
- **StatusNota**: ABERTA, PAGA, CANCELADA
- **MetodoPagamento**: DINHEIRO, PIX, CARTAO, TRANSFERENCIA
- **Cargo**: Cargos dos funcionários

## Endpoints da API

### Autenticação
- `POST /api/auth/login` - Realiza login e retorna token JWT
- `POST /api/auth/register` - Registra novo usuário
- `POST /api/auth/refresh` - Atualiza token JWT
- `POST /api/auth/logout` - Realiza logout do usuário

### Usuários
- `GET /api/usuarios` - Lista todos os usuários (paginado)
- `GET /api/usuarios/{id}` - Busca um usuário por ID
- `POST /api/usuarios` - Cria um novo usuário
- `PUT /api/usuarios/{id}` - Atualiza um usuário
- `DELETE /api/usuarios/{id}` - Desativa um usuário
- `GET /api/usuarios/perfil/{perfil}` - Lista usuários por perfil
- `PUT /api/usuarios/{id}/status` - Altera status do usuário

### Clientes
- `GET /api/clientes` - Lista todos os clientes (paginado)
- `GET /api/clientes/{id}` - Busca cliente por ID
- `POST /api/clientes` - Cria um novo cliente
- `PUT /api/clientes/{id}` - Atualiza um cliente
- `DELETE /api/clientes/{id}` - Desativa um cliente
- `GET /api/clientes/busca` - Busca clientes por nome ou apelido
- `GET /api/clientes/status/{status}` - Lista clientes por status
- `GET /api/clientes/limite/{valor}` - Lista clientes com limite >= valor
- `GET /api/clientes/{id}/notas` - Lista notas do cliente
- `GET /api/clientes/{id}/pagamentos` - Lista pagamentos do cliente
- `GET /api/clientes/{id}/saldo` - Consulta saldo devedor do cliente

### Notas
- `GET /api/notas` - Lista todas as notas (paginado)
- `GET /api/notas/{id}` - Busca nota por ID
- `POST /api/notas` - Cria uma nova nota
- `PUT /api/notas/{id}` - Atualiza uma nota
- `DELETE /api/notas/{id}` - Cancela uma nota
- `GET /api/notas/cliente/{clienteId}` - Lista notas de um cliente
- `GET /api/notas/status/{status}` - Lista notas por status
- `POST /api/notas/{id}/fotos` - Adiciona foto à nota
- `DELETE /api/notas/{notaId}/fotos/{fotoId}` - Remove foto da nota
- `GET /api/notas/abertas` - Lista todas as notas abertas
- `GET /api/notas/vencidas` - Lista notas vencidas

### Pagamentos
- `GET /api/pagamentos` - Lista todos os pagamentos (paginado)
- `GET /api/pagamentos/{id}` - Busca pagamento por ID
- `POST /api/pagamentos` - Registra um novo pagamento
- `PUT /api/pagamentos/{id}` - Atualiza um pagamento
- `DELETE /api/pagamentos/{id}` - Cancela um pagamento
- `GET /api/pagamentos/cliente/{clienteId}` - Lista pagamentos de um cliente
- `GET /api/pagamentos/data/{data}` - Lista pagamentos por data
- `GET /api/pagamentos/metodo/{metodo}` - Lista pagamentos por método
- `POST /api/pagamentos/{id}/aplicar` - Aplica pagamento a notas
- `GET /api/pagamentos/{id}/notas` - Lista notas vinculadas ao pagamento

### Relatórios
- `GET /api/relatorios/clientes/ativos` - Relatório de clientes ativos
- `GET /api/relatorios/notas/abertas` - Relatório de notas em aberto
- `GET /api/relatorios/pagamentos/periodo` - Relatório de pagamentos por período
- `GET /api/relatorios/clientes/saldo` - Relatório de saldos devedores
- `GET /api/relatorios/fluxo-caixa` - Relatório de fluxo de caixa
- `GET /api/relatorios/inadimplencia` - Relatório de inadimplência

### Upload de Arquivos
- `POST /api/upload/fotos` - Upload de fotos para notas
- `GET /api/files/fotos/{filename}` - Download de foto
- `DELETE /api/files/fotos/{filename}` - Exclui arquivo de foto

## Licença

Este projeto está licenciado sob a [Licença MIT](LICENSE).
