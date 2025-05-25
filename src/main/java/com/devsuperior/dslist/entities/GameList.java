package com.devsuperior.dslist.entities;

import jakarta.persistence.*;

import java.util.Objects;

// Indica que essa classe é uma entidade JPA, ou seja, será mapeada para uma tabela no banco de dados
@Entity

// Define o nome da tabela no banco de dados como "tb_game_list"
@Table(name = "tb_game_list")
public class GameList {

    // Define o campo 'id' como chave primária da tabela
    @Id
    // Define que o valor do ID será gerado automaticamente pelo banco (auto-incremento)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Campo que armazena o nome da lista de jogos
    private String name;

    // Construtor padrão (obrigatório para o JPA funcionar)
    public GameList() {
    }

    // Construtor com argumentos, facilita a criação de objetos já com ID e nome definidos
    public GameList(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getter para acessar o nome da lista
    public String getName() {
        return name;
    }

    // Setter para modificar o nome da lista
    public void setName(String name) {
        this.name = name;
    }

    // Compara dois objetos GameList com base no ID
    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // Se for o mesmo objeto, retorna true
        if (o == null || getClass() != o.getClass()) return false; // Se for nulo ou de outra classe, retorna false
        GameList gameList = (GameList) o;
        return Objects.equals(id, gameList.id); // Compara os IDs
    }

    // Gera um código hash baseado no ID, útil em coleções como HashSet e HashMap
    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
