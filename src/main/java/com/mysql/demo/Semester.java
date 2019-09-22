/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mysql.demo;

import javax.persistence.*;
import java.util.Set;
/**
 *
 * @author Matthew MacLennan
 */
@Entity
public class Semester {
    @Id
    @GeneratedValue (strategy=GenerationType.AUTO)
    private Integer semesterID;
    
    private String description;
    
    public Semester(){}
    public Semester(Integer id, String desc)
    {
        this.semesterID = id;
        this.description = desc;
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
}
