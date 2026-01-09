# Elifoot - API de Gerenciamento de Futebol

## 📋 Descrição do Projeto

**Elifoot** é uma API REST desenvolvida em Spring Boot que fornece funcionalidades para gerenciamento de clubes de futebol, estádios, jogadores e usuários. O projeto utiliza autenticação JWT e controle de acesso baseado em escopos para garantir a segurança das operações.

## 🛠️ Tecnologias Utilizadas

### Backend
- **Spring Boot 3.3.6** - Framework principal para desenvolvimento da API REST
- **Java 17** - Linguagem de programação
- **Spring Security** - Autenticação e autorização
- **Spring OAuth2 Resource Server** - Validação de tokens JWT
- **Spring Data JPA** - Acesso e manipulação de dados
- **Spring Validation** - Validação de dados de entrada

### Banco de Dados
- **PostgreSQL** - Sistema gerenciador de banco de dados relacional
- **Flyway** - Versionamento e migração de banco de dados
- **Hibernate** - ORM para mapeamento objeto-relacional

### Ferramentas e Bibliotecas
- **MapStruct 1.6.3** - Mapeamento automático entre DTOs e entidades
- **Lombok** - Redução de código boilerplate (getters, setters, construtores)
- **Maven** - Gerenciador de dependências e build

### Testes
- **Spring Boot Test** - Framework para testes unitários e de integração
- **TestContainers** - Containers Docker para testes de integração
- **JUnit 5** - Framework de testes

### Segurança
- **JWT (JSON Web Token)** - Autenticação baseada em tokens
- **Criptografia RSA** - Assinatura digital de tokens (chaves pública e privada)

## 🏗️ Arquitetura do Projeto

O projeto segue uma arquitetura em camadas bem definida:

```
src/main/java/dev/felix/elifoot/
├── Controller/          # Camada de apresentação (endpoints REST)
│   ├── Request/         # DTOs para requisições
│   └── Response/        # DTOs para respostas
├── Service/             # Camada de negócio
├── Entity/              # Entidades JPA (modelos de dados)
├── Repository/          # Camada de acesso a dados (Data Access Object)
├── Mapper/              # Mapeamento entre entidades e DTOs (MapStruct)
├── Config/              # Configurações (CORS, Segurança, JWT)
├── Enum/                # Enumerações (ex: Position)
└── Exception/           # Exceções customizadas
```

## 📊 Modelo de Dados

### Entidades Principais

#### **Club (Clube)**
- `id` - Identificador único (chave primária)
- `name` - Nome do clube
- `founded` - Data de fundação
- `urlImg` - URL da imagem/logo
- `createdAt` - Data de criação no sistema
- `active` - Status do clube
- `stadium` - Relacionamento um-para-um com Stadium
- `players` - Relacionamento um-para-muitos com Player

#### **Stadium (Estádio)**
- `id` - Identificador único
- `name` - Nome do estádio
- `city` - Cidade onde fica localizado
- `capacity` - Capacidade de público
- `urlImg` - URL da imagem
- `club` - Relacionamento inverso com Club

#### **Player (Jogador)**
- `id` - Identificador único
- `name` - Nome do jogador
- `position` - Posição em campo (enum: GOLEIRO, ZAGUEIRO, LATERAL, MEIO-CAMPO, ATACANTE)
- `shirtNumber` - Número da camisa
- `urlImg` - URL da foto do jogador
- `club` - Relacionamento com Club

#### **User (Usuário)**
- `id` - Identificador único
- `username` - Nome de usuário único
- `password` - Senha criptografada
- `scopes` - Escopos de acesso (permissões)

#### **Scope (Escopo/Permissão)**
- `id` - Identificador único
- `name` - Nome do escopo
- Exemplos: `club:read`, `club:write`, `admin:all`

## 🔐 Segurança e Autenticação

### JWT (JSON Web Token)
- Tokens assinados com chave privada RSA (arquivo: `authz.pem`)
- Validação com chave pública (arquivo: `authz.pub`)
- Acesso baseado em escopos armazenados no token

### Autorização por Escopos
- `club:read` - Permissão para ler dados de clubes
- `club:write` - Permissão para criar/editar clubes
- `player:read` - Permissão para ler dados de jogadores
- `player:write` - Permissão para criar/editar jogadores
- `admin:all` - Acesso total como administrador

## 🔌 Endpoints da API

### **Clubes** (`/clubs`)
- `GET /clubs` - Listar todos os clubes (paginado)
- `GET /clubs/{id}` - Obter detalhes de um clube
- `POST /clubs` - Criar novo clube
- `GET /clubs/{id}/players` - Listar jogadores de um clube

**Requer autenticação:** Sim  
**Escopos necessários:** `club:read` ou `admin:all` (leitura), `club:write` ou `admin:all` (escrita)

### **Jogadores** (`/players`)
- `GET /players` - Listar todos os jogadores (paginado)
- `GET /players/{id}` - Obter detalhes de um jogador
- `POST /players` - Criar novo jogador

**Requer autenticação:** Sim  
**Escopos necessários:** `player:read` ou `admin:all` (leitura), `player:write` ou `admin:all` (escrita)

### **Autenticação** (`/login`)
- `POST /login` - Realizar login e obter token JWT

**Requer autenticação:** Não

### **Estádios** (`/stadiums`)
- `GET /stadiums` - Listar todos os estádios
- `GET /stadiums/{id}` - Obter detalhes de um estádio
- `POST /stadiums` - Criar novo estádio
- `DELETE /stadiums/{id}` - Deletar um estádio

## 💾 Banco de Dados

### Versionamento com Flyway
As migrações SQL estão localizadas em `src/main/resources/db/migration/`:

- **V1__create_table.sql** - Criação das tabelas principais
- **V2__create_user_scop.sql** - Criação de tabelas de usuário e escopo
- **V3__create_scopes.sql** - População de dados de escopos

### Configuração
```yaml
datasource:
  url: jdbc:postgresql://localhost:5432/elifoot
  username: postgres
  password: postgres

flyway:
  enabled: true
  baseline-on-migrate: true
```

## 🚀 Como Executar o Projeto

### Pré-requisitos
- Java 17+
- Maven 3.8+
- PostgreSQL 12+
- Docker (opcional, para usar TestContainers)

### Passos para executar

1. **Clone o repositório**
```bash
git clone <url-do-repositorio>
cd elifoot
```

2. **Configure o banco de dados**
```bash
# Crie um banco de dados PostgreSQL
createdb elifoot
```

3. **Configure as variáveis de ambiente** (em `application.yml`)
```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/elifoot
    username: postgres
    password: postgres
```

4. **Execute o projeto**
```bash
# Com Maven
mvn spring-boot:run

# Ou compile e execute
mvn clean install
java -jar target/elifoot-0.0.1-SNAPSHOT.jar
```

5. **A API estará disponível em**
```
http://localhost:8080
```

## 🧪 Testes

Execute os testes com:

```bash
# Todos os testes
mvn test

# Teste específico
mvn test -Dtest=NomeDaClasseTest
```

Testes disponíveis:
- `StadiumMapperTest` - Testes de mapeamento de Estádios
- `ClubMapperTest` - Testes de mapeamento de Clubes
- Testes de Controllers e Services

## 📝 Exemplo de Uso

### 1. Fazer Login
```bash
POST /login
Content-Type: application/json

{
  "username": "admin",
  "password": "senha123"
}

Response:
{
  "token": "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9...",
  "type": "Bearer"
}
```

### 2. Criar um Clube
```bash
POST /clubs
Authorization: Bearer <token>
Content-Type: application/json

{
  "name": "Flamengo",
  "founded": "1895-11-17",
  "urlImg": "https://..."
}
```

### 3. Listar Clubes
```bash
GET /clubs?page=0&size=10
Authorization: Bearer <token>
```

### 4. Criar um Estádio
```bash
POST /stadiums
Authorization: Bearer <token>
Content-Type: application/json

{
  "name": "Estádio do Maracanã",
  "city": "Rio de Janeiro",
  "capacity": 78936,
  "urlImg": "https://..."
}
```

## 🛡️ Tratamento de Erros

A API implementa tratamento global de exceções com respostas padronizadas:

```json
{
  "timestamp": "2024-01-08T10:30:00",
  "status": 404,
  "message": "Recurso não encontrado",
  "errors": ["Club não encontrado com id: 1"]
}
```

Exceções tratadas:
- `ResourceNotFoundException` - Recurso não encontrado (HTTP 404)
- `ResourceAlreadyExists` - Recurso já existe (HTTP 409)
- Erros de validação (HTTP 400)
- Erros de autenticação/autorização (HTTP 401/403)

## 📈 Próximas Melhorias

- [ ] Implementar refresh token
- [ ] Adicionar mais operações CRUD completas
- [ ] Implementar cache com Redis
- [ ] Adicionar documentação Swagger/OpenAPI
- [ ] Melhorar testes de integração
- [ ] Implementar auditorias de operações

## 👨‍💼 Autores

Desenvolvido por **Felix**

## 📄 Licença

Este projeto está sob a licença MIT.

---

**Última atualização:** Janeiro de 2026

