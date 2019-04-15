package com.example.samsung.playersreme;

import android.graphics.drawable.Drawable;

public class Player {
    private String name;
    private String positionName;
    private int jerseyNumber;
    private String height;
    private int weight;
    private int age;
    private Drawable image;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public int getJerseyNumber() {
        return jerseyNumber;
    }

    public void setJerseyNumber(int jerseyNumber) {
        this.jerseyNumber = jerseyNumber;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }

    public Player() {
        this.name = "";
        this.positionName = "";
        this.jerseyNumber = 0;
        this.height = "";
        this.weight = 0;
        this.age = 0;
        this.image = null;
    }

    public Player(String name, String positionName, int jerseyNumber, String height, int weight, int age, String image) {
        this.name = name;
        this.positionName = positionName;
        this.jerseyNumber = jerseyNumber;
        this.height = height;
        this.weight = weight;
        this.age = age;
        this.image = Image.fromUrl(image);
    }
}
