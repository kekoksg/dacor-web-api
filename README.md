# SGCC - Sistema de Gestão de Contas de Clientes

## Visão Geral

O SGCC é um sistema desenvolvido para digitalizar, automatizar e otimizar a gestão de contas de clientes, eliminando o processo manual baseado em papel e planilhas. O sistema permite o gerenciamento de usuários, clientes, notas fiscais e pagamentos de forma integrada.

## Tecnologias Utilizadas

- **Backend**: Spring Boot 3.5.6
- **Banco de Dados**: PostgreSQL
- **Java**: 17
- **Maven**: Para gerenciamento de dependências
- **JPA/Hibernate**: Para mapeamento objeto-relacional
- **Lombok**: Para redução de código boilerplate

## Estrutura do Projeto

```
src/main/java/br/com/dacorweb/api/
├── ApiApplication.java         # Classe principal da aplicação
├── config/                     # Configurações do Spring
├── controllers/                # Controladores REST
├── entities/                   # Entidades JPA
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


## Licença

Este projeto está licenciado sob a [Licença MIT](LICENSE).
