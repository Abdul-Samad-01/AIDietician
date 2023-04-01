package com.Aidietician.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "food")
public class food {
    

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String foodcatog;
    private int calories;
    private double fat;
    private double protien;
    private double carbohydrate;
    private String sugars;
    private String fiber;
    private String cholesterol;
    private String saturatedfat;
    private String calcium;
    private String potassium;

    @Column(nullable = true)
    private int servingweight;
    private String servingdesc;

    public food(){

    }
    
    public food(int id, String name, String foodCategory, int calories, double fat, double protein,
                double carbohydrate, String sugars, String fiber, String cholesterol, String saturatedFat,
                String calcium, String potassium, int servingWeight, String servingDescription) {
        this.id = id;
        this.name = name;
        this.foodcatog = foodCategory;
        this.calories = calories;
        this.fat = fat;
        this.protien = protein;
        this.carbohydrate = carbohydrate;
        this.sugars = sugars;
        this.fiber = fiber;
        this.cholesterol = cholesterol;
        this.saturatedfat = saturatedFat;
        this.calcium = calcium;
        this.potassium = potassium;
        this.servingweight = servingWeight;
        this.servingdesc = servingDescription;
    }
    
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
    
    public String getFoodcatog() {
        return foodcatog;
    }
    
    public void setFoodcatog(String foodCategory) {
        this.foodcatog = foodCategory;
    }
    
    public int getCalories() {
        return calories;
    }
    
    public void setCalories(int calories) {
        this.calories = calories;
    }
    
    public double getFat() {
        return fat;
    }
    
    public void setFat(double fat) {
        this.fat = fat;
    }
    
    public double getProtien() {
        return protien;
    }
    
    public void setProtien(double protein) {
        this.protien = protein;
    }
    
    public double getCarbohydrate() {
        return carbohydrate;
    }
    
    public void setCarbohydrate(double carbohydrate) {
        this.carbohydrate = carbohydrate;
    }
    
    public String getSugars() {
        return sugars;
    }
    
    public void setSugars(String sugars) {
        this.sugars = sugars;
    }
    
    public String getFiber() {
        return fiber;
    }
    
    public void setFiber(String fiber) {
        this.fiber = fiber;
    }
    
    public String getCholesterol() {
        return cholesterol;
    }
    
    public void setCholesterol(String cholesterol) {
        this.cholesterol = cholesterol;
    }
    
    public String getSaturatedfat() {
        return saturatedfat;
    }
    
    public void setSaturatedfat(String saturatedFat) {
        this.saturatedfat = saturatedFat;
    }
    
    public String getCalcium() {
        return calcium;
    }
    
    public void setCalcium(String calcium) {
        this.calcium = calcium;
    }
    
    public String getPotassium() {
        return potassium;
    }
    
    public void setPotassium(String potassium) {
        this.potassium = potassium;
    }
    
    public int getServingweight() {
        return servingweight;
    }
    public void setServingweight(int servingWeight) {
        this.servingweight = servingWeight;
    }

    public String getServingdesc() {
        return servingdesc;
    }public void setServingdesc(String servingDescription) {
        this.servingdesc = servingDescription;
    }

    

}
