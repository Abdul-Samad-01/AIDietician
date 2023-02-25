package com.Aidietician.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "faq")
public class Faq {

    @Id
    @GeneratedValue(strategy  = GenerationType.TABLE)
    private int id;
    private String ques;
    private String ans;

    public Faq() {
		super();
		// TODO Auto-generated constructor stub
	}

    public Faq(int id,String ques,String ans) {
		super();
        this.ques = ques;
        this.ans = ans;
        this.id = id;

	}
  

    public int getId() {
        return id;
    }public void setId(int id) {
        this.id = id;
    }

    public String getAns() {
        return ans;
    }public void setAns(String ans) {
        this.ans = ans;
    }

    public String getQues() {
        return ques;
    }public void setQues(String ques) {
        this.ques = ques;
    }
}
