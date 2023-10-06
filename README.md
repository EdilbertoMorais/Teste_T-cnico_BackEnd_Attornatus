# Teste Técnico Backend - Attornatus

Este repositório contém a implementação do Teste Técnico Backend para a Attornatus, uma API simples para gerenciamento de Pessoas e seus Endereços, desenvolvida usando Spring Boot e H2 Database.

## Instruções do Teste

Usando Spring Boot, crie uma API simples para gerenciar Pessoas. Esta API dever permitir:

- Criar uma pessoa
- Editar uma pessoa
- Consultar uma pessoa
- Listar pessoas
- Criar endereço para pessoa
- Listar endereços da pessoa
- Definir qual endereço é o principal da pessoa

### Campos da Entidade Pessoa

- Nome
- Data de nascimento

### Campos da Entidade Endereço

- Logradouro
- CEP
- Número
- Cidade

## Requisitos

- Todas as respostas da API devem ser em formato JSON.
- Banco de dados H2.
- Testes unitários.
- Adoção de boas práticas de Clean Code.

 ## Será levado em avaliação 

- Estrutura, arquitetura e organização do projeto 

- Boas práticas de programação 

- Alcance dos objetivos propostos.


---
<h1 align="center"> Implementando o Desafio </h1>

<div align="center">
<img src="https://cdn-images-1.medium.com/max/800/0*aRegYfnbNSdbrGng.gif" align="center" height="300" width="600" />
</div>

---


### 1 - Configuração do Projeto:

- Criei um novo projeto Spring Boot usando o Spring Initializr (https://start.spring.io/) diretamente pela IDE IntelliJ com as seguintes dependências:
  - Spring Web
  - Spring Data JPA
  - H2 Database
  - Spring Boot DevTools

### 2 - Definição das Entidades

- Criei as entidades Pessoa e Endereco com os campos especificados, incluindo relacionamentos entre elas. Utilize as anotações @Entity, @Id, @GeneratedValue, etc.

```java
@Entity
public class Pessoa {
    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private LocalDate dataNascimento;

    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Endereco> enderecos = new ArrayList<>();

    @OneToOne
    @JsonManagedReference
    private Endereco enderecoPrincipal;...
}

@Entity
public class Endereco {
    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String logradouro;
    private String cep;
    private String numero;
    private String cidade;

    @ManyToOne
    @JoinColumn(name = "pessoa_id")
    @JsonBackReference
    private Pessoa pessoa; ...
}
```


### 3 - Criação dos Repositórios
- Criei interfaces de repository para as entidades Pessoa e Endereco, estendendo JpaRepository.

```java
@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
}

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
```
### 4 - Implementação dos Controllers
- Implementei os controllers REST para gerenciar operações CRUD para Pessoa e Endereco.
```java
  @RestController
@RequestMapping("/api/pessoas")
public class PessoaController {
    // Implementei os métodos para criar, editar, consultar e listar pessoas
}

@RestController
@RequestMapping("/api/enderecos")
public class EnderecoController {
    // Implementei os métodos para criar, listar e definir o endereço principal da pessoa
}
```
### 5 - Configuração do Banco de Dados H2
- Configurei o H2 Database no arquivo application.properties com as configurações de URL, usuário e senha.

```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.username=sa
spring.datasource.password=password
spring.datasource.driverClassName=org.h2.Driver
spring.jpa.hibernate.ddl-auto=update
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
```
### 6 - Implementação de Testes Unitários
- Criei testes unitários para os controllers, entidades, repository e serviços para garantir que as operações CRUD funcionem conforme o esperado.

### 7 - Respostas em JSON
- Todas as respostas da API retornam dados em formato JSON.

### 8 - Teste das Operações da API
- Testei as operações da API usando a própria IDE IntelliJ para realizar chamadas POST, GET e UPDATE.
---
> Status do Projeto: Concluido :heavy_check_mark:
