package com.devsuperior.dslist.services;

// Importações necessárias para funcionamento do Spring e manipulação de dados
import com.devsuperior.dslist.dto.GameDTO;
import com.devsuperior.dslist.dto.GameMinDTO;
import com.devsuperior.dslist.entities.Game;
import com.devsuperior.dslist.projections.GameMinProjection;
import com.devsuperior.dslist.repositories.GameListRepository;
import com.devsuperior.dslist.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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

    // Método para cadastrar um novo jogo
    @Transactional
    public GameDTO cadastrar(GameDTO dto) {
        Game game = new Game();
        copiarDtoParaEntidade(dto, game);
        game = gameRepository.save(game);
        return new GameDTO(game);
    }

    // Método para atualizar um jogo existente
    @Transactional
    public GameDTO atualizar(Long id, GameDTO dto) {
        Optional<Game> optional = gameRepository.findById(id);
        if (optional.isEmpty()) {
            throw new RuntimeException("Jogo com ID " + id + " não encontrado");
        }
        Game game = optional.get();
        copiarDtoParaEntidade(dto, game);
        game = gameRepository.save(game);
        return new GameDTO(game);
    }

    // Método para deletar um jogo pelo ID
    @Transactional
    public void deletar(Long id) {
        if (!gameRepository.existsById(id)) {
            throw new RuntimeException("Jogo com ID " + id + " não encontrado");
        }
        gameRepository.deleteById(id);
    }

    // Método auxiliar para copiar os dados do DTO para a entidade
    private void copiarDtoParaEntidade(GameDTO dto, Game entity) {
        entity.setTitle(dto.getTitle());
        entity.setYear(dto.getYear());
        entity.setGenre(dto.getGenre());
        entity.setPlatforms(dto.getPlatforms());
        entity.setScore(dto.getScore());
        entity.setImgUrl(dto.getImgUrl());
        entity.setShortDescription(dto.getShortDescription());
        entity.setLongDescription(dto.getLongDescription());
    }



}
