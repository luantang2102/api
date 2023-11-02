package com.luantang.pokemonreview.api.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("User")
public class UserEntity {

    @Transient
    public static final String SEQUENCE_NAME = "user_sequence";

    @Id
    private Integer id;
    private String username;
    private String password;
    private List<Role> role;

    public UserEntity() {
    }

    public UserEntity(Integer id, String username, String password, List<Role> role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRole() {
        return role;
    }

    public void setRole(List<Role> role) {
        this.role = role;
    }
}
