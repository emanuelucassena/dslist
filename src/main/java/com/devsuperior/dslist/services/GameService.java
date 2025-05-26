package com.devsuperior.dslist.services;

// Importações necessárias para funcionamento do Spring e manipulação de dados
import com.devsuperior.dslist.dto.GameDTO;
import com.devsuperior.dslist.dto.GameMinDTO;
import com.devsuperior.dslist.entities.Game;
import com.devsuperior.dslist.projections.GameMinProjection;
import com.devsuperior.dslist.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service // Indica que esta classe é um componente de serviço do Spring (camada de lógica de negócio)
public class GameService {

    @Autowired // Injeta automaticamente uma instância do GameRepository (injeção de dependência)
    private GameRepository gameRepository;

    @Transactional(readOnly = true) // Indica que a transação será apenas de leitura (otimiza desempenho)
    public List<GameMinDTO> findAll() {
        // Busca todos os jogos no banco de dados (lista de entidades Game)
        List<Game> result = gameRepository.findAll();

        // Converte a lista de Game para uma lista de GameMinDTO (com dados mínimos, como título e capa)
        List<GameMinDTO> dto = result.stream().map(x -> new GameMinDTO(x)).toList();

        // Retorna a lista de DTOs para o controller
        return dto;
    }

    @Transactional(readOnly = true) // Transação de leitura para garantir integridade dos dados
    public GameDTO findbyId(Long id) {
        // Busca um jogo pelo ID no banco (findById retorna um Optional, usamos get() para acessar o conteúdo)
        Game result = gameRepository.findById(id).get();

        // Converte o Game para GameDTO (com dados completos, ex: descrição, gênero, ano, etc.)
        return new GameDTO(result);
    }


    @Transactional(readOnly = true)
    // Indica que essa transação é apenas de leitura. Isso melhora o desempenho,
    // pois o banco de dados sabe que nenhuma informação será alterada.

    public List<GameMinDTO> findBylist(Long listId) {
        // Chama o método do repositório para buscar os jogos pertencentes a uma lista específica.
        // O resultado é uma lista de projeções com dados específicos dos jogos.
        List<GameMinProjection> result = gameRepository.searchByList(listId);

        // Converte a lista de projeções (GameMinProjection) em uma lista de DTOs (GameMinDTO),
        // usando stream para percorrer os dados e aplicar o construtor do DTO.
        return result.stream().map(GameMinDTO::new).toList();
    }


}
