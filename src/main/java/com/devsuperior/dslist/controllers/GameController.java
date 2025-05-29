package com.devsuperior.dslist.controllers;

import com.devsuperior.dslist.dto.GameDTO;
import com.devsuperior.dslist.dto.GameMinDTO;
import com.devsuperior.dslist.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
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

    // Mapeia requisições HTTP POST para a URL "/games"
    @PostMapping("")
// Define que este método retorna um objeto GameDTO dentro de uma ResponseEntity
    public ResponseEntity<GameDTO> cadastrar(@RequestBody GameDTO dto) {
        // Chama o serviço para cadastrar um novo jogo com os dados recebidos no corpo da requisição (dto)
        GameDTO novo = gameService.cadastrar(dto);

        // Cria a URI do novo recurso, incluindo o ID retornado após o cadastro
        URI uri = URI.create("/games/" + novo.getId());

        // Retorna a resposta HTTP 201 (Created), com a URI no cabeçalho "Location" e o corpo com o novo DTO
        return ResponseEntity.created(uri).body(novo);
    }

    // Mapeia requisições HTTP PUT para a URL "/games/{id}" — usada para atualizar um recurso existente
    @PutMapping("/{id}")
// Define que este método retorna um GameDTO dentro de uma ResponseEntity
    public ResponseEntity<GameDTO> atualizar(@PathVariable Long id, @RequestBody GameDTO dto) {
        // Chama o serviço para atualizar o jogo com o ID especificado, usando os dados recebidos no corpo (dto)
        GameDTO atualizado = gameService.atualizar(id, dto);

        // Retorna a resposta HTTP 200 (OK) com o objeto atualizado no corpo da resposta
        return ResponseEntity.ok(atualizado);
    }


    // Mapeia requisições HTTP DELETE para a URL "/games/{id}" — usada para excluir um recurso
    @DeleteMapping("/{id}")
    // Define que este método retorna apenas o status HTTP (sem corpo), usando Void
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        // Chama o serviço para deletar o jogo com o ID fornecido
        gameService.deletar(id);

        // Retorna a resposta HTTP 204 (No Content), indicando que a operação foi bem-sucedida e não há conteúdo a retornar
        return ResponseEntity.noContent().build();
    }

}
