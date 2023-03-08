package com.Aidietician.Entities;




import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="userRequest")
public class userRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int sId;
    private double height;
    private double weight;
    private String goal;
    private int age;
    private String gender;
    private String  activity;

    @ManyToOne
    private User user;

    
    public userRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

   

    public userRequest(int sId,double height,double weight, String goal, int age,String gender,String  activity,User user){
        super();
        this.sId =sId;
        this.goal=goal;
        this.height = height;
        this.weight = weight;
        this.gender = gender;
        this.activity = activity;
        this.user = user;
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

    public String getGoal() {
        return goal;
    }public void setGoal(String goal) {
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

    public String getGender() {
        return gender;
    }public void setGender(String gender) {
        this.gender = gender;
    }

    public String getActivity() {
        return activity;
    }public void setActivity(String activity) {
        this.activity = activity;
    }

    @Override
    public String toString() {
        return getGoal()+" "+ getHeight()+" "+ getWeight()+" "+getAge()+" "+getGender()+" "+getActivity();
    }

}


