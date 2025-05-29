package com.devsuperior.dslist.services;

import com.devsuperior.dslist.dto.GameDTO;
import com.devsuperior.dslist.dto.GameListDTO;
import com.devsuperior.dslist.entities.Game;
import com.devsuperior.dslist.entities.GameList;
import com.devsuperior.dslist.repositories.GameListRepository;
import com.devsuperior.dslist.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

// Indica que esta classe é um componente do tipo "serviço" no Spring
// Ela contém regras de negócio e será gerenciada pelo Spring
@Service
public class GameListService {

    // Injeta automaticamente uma instância do repositório GameListRepository
    // Isso permite usar o banco de dados sem precisar instanciar o objeto manualmente
    @Autowired
    private GameListRepository gameListRepository;


    // Define que esse método será executado dentro de uma transação, mas apenas para leitura (otimiza o desempenho)
    @Transactional(readOnly = true)
    public List<GameListDTO> findAll() {
        // Busca todas as listas de jogos no banco de dados
        List<GameList> result = gameListRepository.findAll();

        // Converte cada GameList (entidade) para um GameListDTO e retorna a lista
        // Usamos DTOs para enviar apenas os dados necessários para o cliente
        return result.stream().map(GameListDTO::new).toList();
    }


}
