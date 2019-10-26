package com.regnquiz.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Set;

@Entity // This tells Hibernate to make a table out of this class
public class Type {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer typeID;

    private String description;

    @JsonBackReference
    @OneToMany(mappedBy = "type")
    private Set<UserType> userType;

    public Type(){
    }

    public Type(int id, String desc){
        this.typeID = id;
        this.description = desc;
    }

    public Integer getTypeID(){
        return this.typeID;
    }

    public void setTypeID(int id){
        this.typeID = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}