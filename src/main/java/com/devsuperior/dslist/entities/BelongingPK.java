package com.devsuperior.dslist.entities;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.util.Objects;

// Indica que esta classe é uma parte "embutida" (composite key) de outra entidade JPA
@Embeddable
public class BelongingPK {

    // Muitos objetos BelongingPK podem estar ligados ao mesmo Game
    @ManyToOne
    // Define o nome da coluna no banco de dados que irá armazenar o ID do jogo
    @JoinColumn(name = "game_id")
    private Game game;

    // Muitos objetos BelongingPK podem estar ligados à mesma lista de jogos
    @ManyToOne
    // Define o nome da coluna no banco de dados que irá armazenar o ID da lista
    @JoinColumn(name = "list_id")
    private GameList list;

    // Construtor padrão necessário para JPA funcionar corretamente
    public BelongingPK() {
    }

    // Construtor com argumentos, facilita a criação da chave composta com os dados principais
    public BelongingPK(Game game, GameList list) {
        this.game = game;
        this.list = list;
    }

    // Getter para acessar o objeto Game
    public Game getGame() {
        return game;
    }

    // Setter para definir o objeto Game
    public void setGame(Game game) {
        this.game = game;
    }

    // Getter para acessar o objeto GameList
    public GameList getList() {
        return list;
    }

    // Setter para definir o objeto GameList
    public void setList(GameList list) {
        this.list = list;
    }

    // Compara se dois objetos BelongingPK são iguais, ou seja, se têm o mesmo game e a mesma lista
    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // Se forem o mesmo objeto, retorna true
        if (o == null || getClass() != o.getClass()) return false; // Se o outro for nulo ou de classe diferente, retorna false
        BelongingPK that = (BelongingPK) o;
        // Compara os campos game e list
        return Objects.equals(game, that.game) && Objects.equals(list, that.list);
    }

    // Gera um código hash baseado nos campos game e list, útil para estruturas como HashSet ou HashMap
    @Override
    public int hashCode() {
        return Objects.hash(game, list);
    }
}
