package com.luantang.pokemonreview.api.exceptions;

public class RoleNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 3;

    public RoleNotFoundException(String message) {
        super(message);
    }
}
