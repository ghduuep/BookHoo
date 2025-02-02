# BookHoo - API de Usuários

A API **BookHoo** é uma aplicação construída em **Java** com o framework **Spring Boot** para gerenciamento de usuários. A aplicação permite realizar operações básicas de CRUD (Create, Read, Update, Delete) para usuários, além de validar o CPF único no momento de cadastro.

## Funcionalidades

- **Listar usuários**: Recupera todos os usuários cadastrados.
- **Buscar usuário por ID**: Recupera um usuário específico pelo seu ID.
- **Cadastrar usuário**: Cria um novo usuário com validação do CPF único.
- **Atualizar usuário**: Atualiza os dados de um usuário existente.
- **Remover usuário**: Deleta um usuário pelo seu ID.

## Tecnologias Utilizadas

- **Java 23**: Linguagem de programação principal.
- **Spring Boot**: Framework para criação da API RESTful.
- **Spring Data JPA**: Interface de persistência de dados com banco de dados relacional.
- **H2 Database**: Banco de dados em memória para facilitar os testes.
- **Jakarta EE**: Para a parte de transações e injeção de dependências.
- **Maven**: Gerenciador de dependências e build.

## Como Rodar o Projeto

### Pré-requisitos

1. **Java 23** instalado em sua máquina.
2. **Maven** instalado para gerenciar dependências e build do projeto.