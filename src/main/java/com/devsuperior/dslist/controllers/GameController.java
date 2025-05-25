package com.devsuperior.dslist.controllers;

import com.devsuperior.dslist.dto.GameDTO;
import com.devsuperior.dslist.dto.GameMinDTO;
import com.devsuperior.dslist.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Indica que esta classe é um controlador REST (camada que responde às requisições HTTP)
@RequestMapping(value = "/games") // Define o caminho base para as rotas desse controlador (ex: localhost:8080/games)
public class GameController {

    @Autowired // Injeta automaticamente uma instância de GameService
    private GameService gameService;

    // Endpoint que busca os detalhes completos de um jogo, dado seu ID
    @GetMapping(value = "/{id}") // Mapeia requisições GET para o caminho /games/{id}
    public GameDTO findByid(@PathVariable Long id) {
        // Chama o serviço para buscar o jogo pelo ID
        GameDTO result = gameService.findbyId(id);
        // Retorna o DTO com os dados completos do jogo
        return result;
    }

    // Endpoint que retorna uma lista com dados mínimos de todos os jogos
    @GetMapping // Mapeia requisições GET para o caminho /games
    public List<GameMinDTO> findAll() {
        // Chama o serviço para buscar todos os jogos
        List<GameMinDTO> result = gameService.findAll();
        // Retorna a lista de DTOs com dados reduzidos (somente os necessários para listagem)
        return result;
    }
}
