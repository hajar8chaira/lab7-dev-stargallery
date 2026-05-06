package com.example.starsgallery.beans;

public class Star {
    private int id;
    private String name;
    private String img;
    private float rating;
    private static int compteur = 0;

    public Star(String name, String img, float rating) {
        this.id = ++compteur;
        this.name = name;
        this.img = img;
        this.rating = rating;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getImg() { return img; }
    public float getRating() { return rating; }
    public void setRating(float rating) { this.rating = rating; }
}
