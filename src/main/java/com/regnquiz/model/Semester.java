/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.regnquiz.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Set;
import java.util.HashSet;
/**
 * Author: Matthew MacLennan
 * Date: 22/9/2019
 * Version: 1
 * Comment: Contains semester data
 */
@Entity
public class Semester {
    @Id
    @GeneratedValue (strategy=GenerationType.IDENTITY)
    private Integer semesterID;
    
    private String description; // Description of the semester for exmaple "S1" or "S2" ect.

    @JsonBackReference
    @OneToMany(mappedBy = "semester")
    private Set<Unit> Unit;

    public Semester()
    {
        Unit = new HashSet<>();
    }
    
    public Semester(Integer id, String desc)
    {
        this.semesterID = id;
        this.description = desc;
        
        Unit = new HashSet<>();
    }
    
    public Semester(String desc)
    {
        this.description = desc;
        Unit = new HashSet<>();
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
    public void setUnit(Set<Unit> u) {
        Unit = u;
    }
    
    public void addUnit(Unit unit)
    {
       Unit.add(unit);
    }

    public Integer getSemesterID() {
        return semesterID;
    }
}
