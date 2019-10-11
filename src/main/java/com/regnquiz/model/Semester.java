/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.regnquiz.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;
/**
 * Author: Matthew MacLennan
 * Date: 22/9/2019
 * Version: 1
 * Comment: Contains semester data
 */
@Entity
public class Semester implements Serializable {
    @Id
    @GeneratedValue (strategy=GenerationType.AUTO)
    private Integer semesterID;
    private String description;

    @JsonBackReference
    @OneToMany(mappedBy = "semester")
    private Set<Unit> Unit;

    public Semester(){}

    public Semester(Integer id, String desc)
    {
        this.semesterID = id;
        this.description = desc;
    }
    
    public Semester(Integer id)
    {
        this.semesterID = id;
    }
    
    public Integer getSemID()
    {
        return semesterID;
    }
    
    public void setSemID(Integer id)
    {
        this.semesterID = id;
    }
    
    public String getDescription()
    {
        return description;
    }
    
    public void setDescription(String desc)
    {
        this.description = desc;
    }

    public Set<Unit> getUnit() {
        return Unit;
    }
    public void setUnit(Set<Unit> unit) {
        Unit = unit;
    }
    
    public void addUnit(Unit unit)
    {
       Unit.add(unit);
    }
}
