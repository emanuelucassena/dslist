# 🎮 DSList API

Este projeto é uma API REST construída com **Spring Boot**, que disponibiliza uma lista de jogos organizados por listas personalizadas. Ele permite a consulta de todos os jogos cadastrados, assim como os jogos de uma lista específica. Ideal para quem quer construir ou consumir um backend simples de catálogo de games.

---

## 🚀 Tecnologias Utilizadas

- Java 17
- Spring Boot 3
- Spring Data JPA
- Hibernate
- Banco de dados H2 (em memória)
- Maven
- JPA Projections e DTOs

---

## 📁 Estrutura do Projeto

A seguir estão os principais pacotes e classes explicadas para facilitar o entendimento do projeto:

### `entities/`

- **Game**: Representa a entidade de um jogo.
- **GameList**: Representa uma lista de jogos, contendo apenas `id` e `name`.
- **Belonging**: Entidade de associação entre `Game` e `GameList`, indicando a qual lista um jogo pertence e sua posição.
- **BelongingPK**: Classe auxiliar (chave composta) usada por `Belonging`, contendo as referências para `Game` e `GameList`.

### `dto/`

- **GameDTO**: Contém os dados completos de um jogo, usado ao buscar um jogo por ID.
- **GameMinDTO**: Contém dados resumidos de um jogo (id, título, etc.), usado em listagens.
- **GameListDTO**: Contém apenas o id e o nome da lista de jogos.

### `repositories/`

- **GameRepository**: Interface que acessa a tabela de jogos e possui um método customizado `searchByList(Long listId)` para buscar jogos por lista.
- **GameListRepository**: Interface que acessa a tabela de listas de jogos.

### `services/`

- **GameService**:
  - `findAll()`: Retorna todos os jogos em formato `GameMinDTO`.
  - `findById(id)`: Retorna os dados completos de um jogo por ID.
  - `findBylist(listId)`: Retorna os jogos pertencentes a uma lista específica.
- **GameListService**:
  - `findAll()`: Retorna todas as listas de jogos.

### `controllers/`

- **GameController**:
  - `GET /games`: Lista todos os jogos.
  - `GET /games/{id}`: Retorna os dados completos de um jogo.
- **GameListController**:
  - `GET /lists`: Lista todas as listas de jogos.
  - `GET /lists/{listId}/games`: Lista todos os jogos de uma lista específica.

---

## 🔗 Endpoints da API

### 🎮 Jogos

| Método | Rota           | Descrição                          |
|--------|----------------|-------------------------------------|
| GET    | `/games`       | Retorna todos os jogos (resumidos) |
| GET    | `/games/{id}`  | Retorna detalhes de um jogo        |

### 🗂️ Listas de Jogos

| Método | Rota                      | Descrição                               |
|--------|---------------------------|------------------------------------------|
| GET    | `/lists`                  | Retorna todas as listas de jogos         |
| GET    | `/lists/{listId}/games`   | Retorna os jogos de uma lista específica |

---

## 🤝 Créditos

Este projeto foi baseado no curso **Semana Spring React** da [DevSuperior](https://devsuperior.com.br), que forneceu a estrutura, orientação e conteúdo para o desenvolvimento desta aplicação.


## 👤 Autor

**Emanuel Lucas Telles Bastos Sena**  
Estudante de Engenharia de Computação na UEFS  
Técnico em Desenvolvimento de Sistemas - SENAI

