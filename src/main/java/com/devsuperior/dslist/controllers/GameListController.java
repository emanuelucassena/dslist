package com.devsuperior.dslist.controllers;

// Importa os DTOs usados como resposta da API
import com.devsuperior.dslist.dto.GameListDTO;
import com.devsuperior.dslist.dto.GameMinDTO;

// Importa os serviços responsáveis pela lógica de negócio
import com.devsuperior.dslist.services.GameListService;
import com.devsuperior.dslist.services.GameService;

// Importações do Spring
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController // Define que esta classe é um controlador REST (responde requisições HTTP e retorna dados, geralmente em JSON)
@RequestMapping(value = "/lists") // Define a rota base para todos os endpoints da classe (ex: /lists)
public class GameListController {

    @Autowired // Injeta automaticamente a instância de GameListService
    private GameListService gameListService;

    @Autowired // Injeta automaticamente a instância de GameService
    private GameService gameService;

    // Endpoint que retorna todas as listas de jogos
    // Exemplo: GET http://localhost:8080/lists
    @GetMapping
    public List<GameListDTO> findAll() {
        // Busca todas as listas de jogos usando o serviço
        List<GameListDTO> result = gameListService.findAll();
        return result; // Retorna a lista como resposta
    }

    // Endpoint que retorna os jogos pertencentes a uma lista específica
    // Exemplo: GET http://localhost:8080/lists/1/games
    @GetMapping(value = "/{listId}/games") // {listId} é uma variável de caminho
    public List<GameMinDTO> findByList(@PathVariable Long listId) {
        // Busca os jogos da lista com o ID fornecido
        List<GameMinDTO> result = gameService.findBylist(listId);
        return result; // Retorna os jogos como resposta
    }
}
