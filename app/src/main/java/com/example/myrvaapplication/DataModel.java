package com.example.myrvaapplication;

public class DataModel {
    String name;
    String description;
    int id_;
    byte[] image;

    public DataModel(String name, String description, int id_, byte[] image) {
        this.name = name;
        this.description = description;
        this.id_ = id_;
        this.image = image;
    }


    public String getName() {
        return name;
    }


    public String getDescription() {
        return description;
    }

    public byte[] getImage() {
        return image;
    }

    public int getId() {
        return id_;
    }
}
