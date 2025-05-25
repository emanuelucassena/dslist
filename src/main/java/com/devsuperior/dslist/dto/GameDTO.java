package com.devsuperior.dslist.dto;

import com.devsuperior.dslist.entities.Game;
import org.springframework.beans.BeanUtils;

public class GameDTO {

    // Atributos que representam os dados que queremos expor no frontend
    private Long id;
    private String title;
    private Integer year;
    private String genre;
    private String platforms;
    private double score;
    private String imgUrl;
    private String shortDescription;
    private String longDescription;

    // Construtor vazio (obrigatório para frameworks como o Spring e ferramentas de serialização JSON)
    public GameDTO() {
    }

    // Construtor que recebe um objeto Game (entidade) e copia seus dados para o DTO
    public GameDTO(Game entity){
        // Copia os atributos com os mesmos nomes e tipos do objeto "entity" para "this" (o DTO)
        BeanUtils.copyProperties(entity, this);
    }

    // Métodos getters e setters para permitir acessar e modificar os dados (seguindo o padrão JavaBeans)

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPlatforms() {
        return platforms;
    }

    public void setPlatforms(String platforms) {
        this.platforms = platforms;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }
}
