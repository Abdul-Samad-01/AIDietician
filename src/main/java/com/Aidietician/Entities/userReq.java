package com.Aidietician.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="userReq")
public class userReq {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int sId;
    private double height;
    private double weight;
    private int goal;
    private int age;
    private int gender;
    private int  activity;

    @ManyToOne
    private User user;

    
    public userReq() {
		super();
		// TODO Auto-generated constructor stub
	}

   

    public userReq(int sId,double height,double weight, int goal, int age,int gender,int  activity){
        super();
        this.sId =sId;
        this.goal=goal;
        this.height = height;
        this.weight = weight;
        this.gender = gender;
        this.activity = activity;
    }

    public User getUser() {
        return user;
    }public void setUser(User user) {
        this.user = user;
    }
    
    public int getsId() {
        return sId;
    }public void setsId(int sId) {
        this.sId = sId;
    }


    
    public void setAge(int age) {
        this.age = age;
    }public int getAge() {
        return age;
    }

    public int getGoal() {
        return goal;
    }public void setGoal(int goal) {
        this.goal = goal;
    }
    
    public double getHeight() {
        return height;
    }public void setHeight(double height) {
        this.height = height;
    }
    
    
    public double getWeight() {
        return weight;
    }public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getGender() {
        return gender;
    }public void setGender(int gender) {
        this.gender = gender;
    }

    public int getActivity() {
        return activity;
    }public void setActivity(int activity) {
        this.activity = activity;
    }

    @Override
    public String toString() {
        return getGoal()+" "+ getHeight()+" "+ getWeight()+" "+getAge()+" "+getGender()+" "+getActivity();
    }

}
