package com.devsuperior.dslist.dto;

import com.devsuperior.dslist.entities.Game;
import com.devsuperior.dslist.projections.GameMinProjection;

public class GameMinDTO {

    // Atributos mínimos do jogo que serão expostos pela API ou usados em transferências de dados
    private Long id;
    private String title;
    private Integer year;
    private String imgUrl;
    private String shortDescription;

    // Construtor vazio necessário para o framework (ex: Spring) instanciar o objeto via reflexão
    public GameMinDTO() {
    }

    // Construtor que recebe uma entidade Game e copia os dados essenciais para a DTO
    public GameMinDTO(Game entity) {
        id = entity.getId();
        title = entity.getTitle();
        year = entity.getYear();
        imgUrl = entity.getImgUrl();
        shortDescription = entity.getShortDescription();
    }

    public GameMinDTO(GameMinProjection projection) {
        id = projection.getId();
        title = projection.getTitle();
        year = projection.getGameYear();
        imgUrl = projection.getImgUrl();
        shortDescription = projection.getShortDescription();
    }

    // Métodos getters que permitem acessar os valores dos atributos
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Integer getYear() {
        return year;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getShortDescription() {
        return shortDescription;
    }
}
