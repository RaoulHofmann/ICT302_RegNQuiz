package com.mysql.demo;

import javax.persistence.*;
import java.util.Set;

@Entity // This tells Hibernate to make a table out of this class
public class Type {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer typeID;

    @Column
    private String description;

    @OneToMany(mappedBy = "type", cascade = CascadeType.ALL)
    private Set<UserType> userType;

    public int getTypeID(){
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