package com.Aidietician.Entities;

public class fooddemo {
    private int id;
    private String name;
    private String category;
    private double price;
    private boolean isVegetarian;
    private boolean isGlutenFree;
    private String description;
    private int calories;
    private String allergens;
    private String imagePath;

    // Constructors
    public fooddemo() {
    }

    public fooddemo(int id, String name, String category, double price, boolean isVegetarian, boolean isGlutenFree, String description, int calories, String allergens, String imagePath) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.isVegetarian = isVegetarian;
        this.isGlutenFree = isGlutenFree;
        this.description = description;
        this.calories = calories;
        this.allergens = allergens;
        this.imagePath = imagePath;
    }

    // Getters and Setters
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isVegetarian() {
        return isVegetarian;
    }

    public void setVegetarian(boolean vegetarian) {
        isVegetarian = vegetarian;
    }

    public boolean isGlutenFree() {
        return isGlutenFree;
    }

    public void setGlutenFree(boolean glutenFree) {
        isGlutenFree = glutenFree;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public String getAllergens() {
        return allergens;
    }

    public void setAllergens(String allergens) {
        this.allergens = allergens;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public String toString() {
        return "FoodItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                ", isVegetarian=" + isVegetarian +
                ", isGlutenFree=" + isGlutenFree +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                ", allergens='" + allergens + '\'' +
                ", imagePath='" + imagePath + '\'' +
                '}';
    }
}

