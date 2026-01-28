# SGCC - Sistema de Gestão de Contas de Clientes

## Visão Geral

O SGCC é um sistema desenvolvido para digitalizar, automatizar e otimizar a gestão de contas de clientes, eliminando o processo manual baseado em papel e planilhas. O sistema permite o gerenciamento de usuários, clientes, notas fiscais e pagamentos de forma integrada, com segurança baseada em JWT (JSON Web Tokens).

## Tecnologias Utilizadas

- **Backend**: Spring Boot 3.5.6
- **Banco de Dados**: PostgreSQL
- **Java**: 17
- **Maven**: Para gerenciamento de dependências
- **JPA/Hibernate**: Para mapeamento objeto-relacional
- **Spring Security**: Para autenticação e autorização
- **JWT (JSON Web Tokens)**: Para autenticação stateless
- **Lombok**: Para redução de código boilerplate

## Configuração do Ambiente

### Pré-requisitos

- Java 17 ou superior
- Maven 3.8+
- PostgreSQL 13+

### Configuração

1. Clone o repositório:
   ```bash
   git clone [URL_DO_REPOSITÓRIO]
   cd dacor-web-api
   ```

2. Configure o banco de dados:
   - Crie um banco de dados PostgreSQL chamado `dacor_web_db`
   - As credenciais padrão estão configuradas no `application.properties`

3. Execute o projeto:
   ```bash
   ./mvnw spring-boot:run
   ```

4. Acesse a aplicação em: [http://localhost:8081](http://localhost:8081)

## Autenticação JWT

### Registro de Usuário

```http
POST /api/auth/register
Content-Type: application/json

{
  "nome": "Admin",
  "cpf": "12345678901",
  "email": "admin@example.com",
  "senha": "suaSenha123",
  "perfil": "DONO"
}
```

### Login

```http
POST /api/auth/login
Content-Type: application/json

{
  "cpf": "12345678901",
  "senha": "suaSenha123"
}
```

Resposta:
```json
{
  "token": "seu-jwt-token",
  "type": "Bearer",
  "id": 1,
  "cpf": "12345678901",
  "email": "admin@example.com",
  "nome": "Admin",
  "roles": ["ROLE_DONO"]
}
```

### Uso do Token

Para acessar rotas protegidas, inclua o token no cabeçalho:
```
Authorization: Bearer seu-jwt-token
```

## Estrutura do Projeto

```
src/main/java/br/com/dacorweb/api/
├── ApiApplication.java         # Classe principal da aplicação
├── security/                   # Configurações de segurança
│   ├── config/                # Configuração do Spring Security
│   ├── controllers/           # Controladores de autenticação
│   ├── dto/                   # Objetos de transferência de dados
│   ├── jwt/                   # Classes relacionadas ao JWT
│   └── services/              # Serviços de autenticação
├── controllers/               # Controladores REST
├── entities/                  # Entidades JPA
└── repositories/              # Repositórios JPA
│   ├── enums/                  # Enums do sistema
│   │   ├── MetodoPagamento.java
│   │   ├── PerfilUsuario.java
│   │   ├── StatusCliente.java
│   │   └── StatusNota.java
│   ├── Cliente.java
│   ├── Empresa.java
│   ├── FotoNota.java
│   ├── Nota.java
│   ├── Pagamento.java
│   ├── PagamentoNota.java
│   └── Usuario.java
├── repositories/               # Repositórios JPA
└── services/                   # Lógica de negócio
```

## Modelo de Dados

### Entidades Principais

1. **Usuário**
   - Gerencia acesso ao sistema (Dono ou Funcionário)
   - Autenticação via CPF e senha
   - Controle de permissões por perfil

2. **Cliente**
   - Cadastro de clientes com dados pessoais
   - Controle de limite de crédito
   - Status ativo/inativo

3. **Nota**
   - Registro de débitos dos clientes
   - Anexo de múltiplas fotos
   - Status (Aberta, Paga, Cancelada)

4. **Pagamento**
   - Registro de pagamentos
   - Múltiplos métodos de pagamento
   - Relacionamento com notas pagas

## Endpoints da API

### Usuários
- `GET /api/usuarios` - Lista todos os usuários
- `GET /api/usuarios/{id}` - Busca um usuário por ID
- `POST /api/usuarios` - Cria um novo usuário

### Clientes
- `GET /api/clientes` - Lista todos os clientes
- `POST /api/clientes` - Cria um novo cliente
- `GET /api/clientes/{id}` - Busca cliente por ID
- `PUT /api/clientes/{id}` - Atualiza um cliente
- `DELETE /api/clientes/{id}` - Desativa um cliente

### Notas
- `GET /api/notas` - Lista todas as notas
- `POST /api/notas` - Cria uma nova nota
- `GET /api/notas/{id}` - Busca nota por ID
- `PUT /api/notas/{id}` - Atualiza uma nota
- `DELETE /api/notas/{id}` - Cancela uma nota

### Pagamentos
- `POST /api/pagamentos` - Registra um novo pagamento
- `GET /api/pagamentos/cliente/{clienteId}` - Lista pagamentos de um cliente

## Próximos Passos

- [x] Criar entidades principais
- [ ] Implementar autenticação e autorização
- [ ] Desenvolver controladores REST
- [ ] Implementar validações de entrada
- [ ] Adicionar tratamento de erros
- [ ] Implementar testes unitários e de integração
- [ ] Configurar documentação da API (Swagger/OpenAPI)
- [ ] Implementar relatórios e estatísticas
- [ ] Adicionar suporte a upload de imagens para as notas

## Licença

Este projeto está licenciado sob a [Licença MIT](LICENSE).
