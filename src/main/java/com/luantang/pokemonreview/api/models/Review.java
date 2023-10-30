package com.luantang.pokemonreview.api.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;


@Document("Review")
public class Review {

    @Transient
    public static final String SEQUENCE_NAME = "review_sequence";

    @Id
    private int id;
    private String title;
    private String content;
    private int stars;

    private Pokemon pokemon;

    public Review() {
    }

    public Review(String title, String content, int stars) {
        this.title = title;
        this.content = content;
        this.stars = stars;
    }

    public Review(int id, String title, String content, int stars) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.stars = stars;
    }

    public Review(int id, String title, String content, int stars, Pokemon pokemon) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.stars = stars;
        this.pokemon = pokemon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public Pokemon getPokemon() {
        return pokemon;
    }

    public void setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
    }
}
