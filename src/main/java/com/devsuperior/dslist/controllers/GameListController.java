package com.devsuperior.dslist.controllers;

import com.devsuperior.dslist.dto.GameListDTO;
import com.devsuperior.dslist.services.GameListService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController // Define que esta classe é um controlador REST (retorna dados em formato JSON, por exemplo)
@RequestMapping(value = "/lists") // Define o caminho base dos endpoints desta classe (/lists)
public class GameListController {

    @Autowired // Injeta automaticamente o GameListService para uso aqui dentro
    private GameListService gameListService;

    // Endpoint que responde a requisições GET feitas para /lists
    @GetMapping
    public List<GameListDTO> findAll() {
        // Chama o método do serviço para buscar todas as listas de jogos
        List<GameListDTO> result = gameListService.findAll();

        // Retorna o resultado para o cliente (normalmente um array de objetos JSON)
        return result;
    }
}
