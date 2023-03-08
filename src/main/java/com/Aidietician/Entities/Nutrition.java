package com.Aidietician.Entities;

public class Nutrition {
    private String name;
    private int calories;
    private int serving_size_g;
    private int fat_total_g;
    private int fat_saturated_g;
    private int protein_g;
    private int sodium_mg;
    private int potassium_mg;
    private int cholesterol_mg;
    private int carbohydrates_total_g;
    private int fiber_g;
    private int sugar_g;


    public Nutrition(String name,int calories,int serving_size_g, int fat_total_g,int fat_saturated_g,int protein_g,int sodium_mg,int potassium_mg,int cholesterol_mg,int carbohydrates_total_g,int fiber_g,int sugar_g){
        super();
        this.name = name; 
        this.calories = calories; 
        this.serving_size_g = serving_size_g; 
        this.fat_total_g = fat_total_g; 
        this.fat_saturated_g = fat_saturated_g; 
        this.protein_g = protein_g; 
        this.sodium_mg = sodium_mg; 
        this.potassium_mg = potassium_mg; 
        this.cholesterol_mg = cholesterol_mg; 
        this.carbohydrates_total_g = carbohydrates_total_g; 
        this.fiber_g = fiber_g; 
        this.sugar_g = sugar_g;     
    
    }

    public Nutrition(){
        super();
    }

    public int getCalories() {
        return calories;
    }public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getCarbohydrates_total_g() {
        return carbohydrates_total_g;
    }public void setCarbohydrates_total_g(int carbohydrates_total_g) {
        this.carbohydrates_total_g = carbohydrates_total_g;
    }

    public int getCholesterol_mg() {
        return cholesterol_mg;
    }public void setCholesterol_mg(int cholesterol_mg) {
        this.cholesterol_mg = cholesterol_mg;
    }

    public int getFat_saturated_g() {
        return fat_saturated_g;
    }public void setFat_saturated_g(int fat_saturated_g) {
        this.fat_saturated_g = fat_saturated_g;
    }

    public int getFat_total_g() {
        return fat_total_g;
    }public void setFat_total_g(int fat_total_g) {
        this.fat_total_g = fat_total_g;
    }

    public int getFiber_g() {
        return fiber_g;
    }public void setFiber_g(int fiber_g) {
        this.fiber_g = fiber_g;
    }

    public String getName() {
        return name;
    }public void setName(String name) {
        this.name = name;
    }

    public int getPotassium_mg() {
        return potassium_mg;
    }public void setPotassium_mg(int potassium_mg) {
        this.potassium_mg = potassium_mg;
    }

    public int getProtein_g() {
        return protein_g;
    }public void setProtein_g(int protein_g) {
        this.protein_g = protein_g;
    }

    public int getServing_size_g() {
        return serving_size_g;
    }public void setServing_size_g(int serving_size_g) {
        this.serving_size_g = serving_size_g;
    }

    public int getSodium_mg() {
        return sodium_mg;
    }public void setSodium_mg(int sodium_mg) {
        this.sodium_mg = sodium_mg;
    }

    public int getSugar_g() {
        return sugar_g;
    }public void setSugar_g(int sugar_g) {
        this.sugar_g = sugar_g;
    }

    




}
