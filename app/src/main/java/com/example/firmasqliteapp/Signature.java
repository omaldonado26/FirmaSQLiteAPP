package com.example.firmasqliteapp;

public class Signature {
    private int id;
    private String description;
    private byte[] image;

    public Signature(int id, String description, byte[] image) {
        this.id = id;
        this.description = description;
        this.image = image;
    }

    public int getId() { return id; }
    public String getDescription() { return description; }
    public byte[] getImage() { return image; }
}

