package com.example.myrvaapplication;

public class Character {
    private String name;
    private String description;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Character(String name, String description) {
        this.name = name;
        this.description = description;
    }

}
