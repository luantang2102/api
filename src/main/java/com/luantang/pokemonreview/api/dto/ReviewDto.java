package com.luantang.pokemonreview.api.dto;

public class ReviewDto {
    private int id;
    private String title;
    private String content;
    private int stars;

    public ReviewDto(int id, String title, String content, int stars) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.stars = stars;
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
}
