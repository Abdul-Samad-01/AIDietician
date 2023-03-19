package com.Aidietician.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "dieticianDiet")
public class dieticianDiet {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;
    private int userId;
    private String breakfast;
    private String lunch;
    private String dinner;
    private String snack;
    private String description;
    private int dieticianId;

    public dieticianDiet(){
        super();
    }

    public dieticianDiet(int id,int userId,int dieticianId,String breakfast,String lunch,String dinner,String description,String snack){
        super();
        this.id = id;
        this.userId = userId;
        this.breakfast=breakfast;
        this.lunch=lunch;
        this.snack = snack;
        this.description = description;
        this.dieticianId = dieticianId;
    }

    public int getId() {
        return id;
    }public void setId(int id) {
        this.id = id;
    }
    public int getUserId() {
        return userId;
    }public void setUserId(int userId) {
        this.userId = userId;
    }
    public int getDieticianId() {
        return dieticianId;
    }public void setDieticianId(int dieticianId) {
        this.dieticianId = dieticianId;
    }

    public String getBreakfast() {
        return breakfast;
    }public void setBreakfast(String breakfast) {
        this.breakfast = breakfast;
    }

    public String getDescription() {
        return description;
    }public void setDescription(String description) {
        this.description = description;
    }
    public String getDinner() {
        return dinner;
    }public void setDinner(String dinner) {
        this.dinner = dinner;
    }
    public String getLunch() {
        return lunch;
    }public void setLunch(String lunch) {
        this.lunch = lunch;
    }
    public String getSnack() {
        return snack;
    }public void setSnack(String snack) {
        this.snack = snack;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString();
    }
}
