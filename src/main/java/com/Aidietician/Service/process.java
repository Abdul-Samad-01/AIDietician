package com.Aidietician.Service;

import org.springframework.stereotype.Service;

import com.Aidietician.Entities.userReq;

@Service
public class process {
    private double calorie;
    private double bmi;
    private String message;

    public void processData(userReq det){
        this.setbmi(det.getWeight()/Math.pow((det.getHeight()/100),2));
        if(det.getGender()==2){
            this.setCalorie(655.1 + (9.563*det.getWeight()) + (1.850*det.getHeight()) - (4.676*det.getAge()));
        }else{
            this.setCalorie(66.47 + (13.75*det.getWeight()) + (5.003*det.getHeight()) - (6.755*det.getAge()));
        }
        setMessage("Your Bmi is "+this.getbmi()+" and required calories are "+this.getCalorie());
        


        
    }

    public double getbmi() {
        return bmi;
    }public void setbmi(double bmi) {
        double scale = Math.pow(10, 3);
        bmi=Math.round(bmi * scale) / scale;
        this.bmi = bmi;
    }

    public double getCalorie() {
        return calorie;
    }public void setCalorie(double calorie) {
        this.calorie = calorie;
    }

    public String getMessage() {
        return message;
    }public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return getMessage();
    }
}
