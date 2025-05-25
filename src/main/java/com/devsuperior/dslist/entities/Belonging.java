package com.devsuperior.dslist.entities;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Objects;

// Define que essa classe é uma entidade JPA, ou seja, ela será mapeada para uma tabela no banco de dados.
@Entity
// Especifica o nome da tabela no banco de dados: "tb_belonging"
@Table(name = "tb_belonging")
public class Belonging {

    // Define que o campo 'id' é uma chave primária composta, utilizando a classe BelongingPK
    @EmbeddedId
    private BelongingPK id = new BelongingPK();

    // Campo que representa a posição de um jogo dentro de uma lista
    private Integer position;

    // Construtor padrão (obrigatório para JPA funcionar corretamente)
    public Belonging() {
    }

    // Construtor com argumentos, facilitando a criação de um objeto Belonging com os dados principais
    public Belonging(Game game, GameList list, Integer position) {
        // Define os objetos game e list dentro da chave composta
        id.setGame(game);
        id.setList(list);
        // Define a posição do jogo na lista
        this.position = position;
    }

    // Método getter para acessar o id (chave composta)
    public BelongingPK getId() {
        return id;
    }

    // Método setter para modificar o id
    public void setId(BelongingPK id) {
        this.id = id;
    }

    // Getter para acessar a posição
    public Integer getPosition() {
        return position;
    }

    // Setter para modificar a posição
    public void setPosition(Integer position) {
        this.position = position;
    }

    // Método que compara dois objetos Belonging para saber se são iguais, baseado no ID
    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // Se for o mesmo objeto na memória, retorna true
        if (o == null || getClass() != o.getClass()) return false; // Se o outro objeto for nulo ou de outra classe, retorna false
        Belonging belonging = (Belonging) o;
        return Objects.equals(id, belonging.id); // Compara os IDs para saber se são iguais
    }

    // Gera um código hash baseado no ID, usado em estruturas como HashSet ou HashMap
    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
