package com.devsuperior.dslist.services;

import com.devsuperior.dslist.dto.GameDTO;
import com.devsuperior.dslist.dto.GameMinDTO;
import com.devsuperior.dslist.entities.Game;
import com.devsuperior.dslist.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service // Indica que esta classe é um componente de serviço do Spring (camada de lógica de negócio)
public class GameService {

    @Autowired // Injeta automaticamente uma instância do GameRepository (injeção de dependência)
    private GameRepository gameRepository;

    @Transactional(readOnly = true)
    // Método que busca todos os jogos e retorna apenas os dados mínimos via DTO
    public List<GameMinDTO> findAll() {
        // Busca todos os jogos no banco de dados
        List<Game> result = gameRepository.findAll();

        // Converte a lista de Game para uma lista de GameMinDTO usando stream
        List<GameMinDTO> dto = result.stream().map(x -> new GameMinDTO(x)).toList();

        // Retorna a lista de DTOs
        return dto;
    }

    @Transactional(readOnly = true)
    public GameDTO findbyId(Long id){
        Game result = gameRepository.findById(id).get();
        return new GameDTO(result);
    }
}
