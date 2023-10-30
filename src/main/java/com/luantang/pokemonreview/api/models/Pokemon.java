package com.luantang.pokemonreview.api.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document("Pokemon")
public class Pokemon {

    @Transient
    public static final String SEQUENCE_NAME = "pokemon_sequence";

    @Id
    private int id;
    private String name;
    private String type;

    private List<Review> reviewList = new ArrayList<>();

    public Pokemon() {
    }

    public Pokemon(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public Pokemon(int id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Review> getReviewList() {
        return reviewList;
    }

    public void setReviewList(List<Review> reviewList) {
        this.reviewList = reviewList;
    }
}
