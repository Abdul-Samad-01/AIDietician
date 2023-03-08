package com.Aidietician.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="dietReq")
public class dieticianReq {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;

    @Column(unique = true)
    private int req;
    private String description;


    public  dieticianReq(){
        super();

    }

    public dieticianReq(int id,int req,String description){
        super();
        this.id = id;
        this.req = req;
        this.description = description;
    }



    public void setId(int id) {
        this.id = id;
    }public int getId() {
        return id;
    }

    public int getReq() {
        return req;
    }public void setReq(int req) {
        this.req = req;
    }

    public String getDescription() {
        return description;
    }public void setDescription(String description) {
        this.description = description;
    }
}
