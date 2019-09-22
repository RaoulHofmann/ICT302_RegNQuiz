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
public class Unit 
{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer unitID;
    
    private String unitCode;
    private String unitName;
    private Semester semester;
    private Integer year;
    private User lecture;
    
    public Unit() 
    {
        semester = new Semester();
        lecture = new User();
    }
    public Unit(Integer id, String unitCode, String unitName, Semester sem, Integer year, User lect)
    {
        this.unitID = id;
        this.unitCode = unitCode;
        this.unitName = unitName;
        this.semester = sem;
        this.year = year;
        this.lecture = lect;
    }
    
    public Integer getUnitID()
    {
        return unitID;
    }
    
    public void setUnitID(Integer id)
    {
        this.unitID = id;
    }
    
    public String getUnitCode()
    {
        return unitCode;
    }
    
    public void setUnitCode(String code)
    {
        this.unitCode = code;
    }
    
    public String getUnitName()
    {
        return unitName;
    }
    
    public void setUnitName(String name)
    {
        this.unitName = name;
    }
    
    public Semester getSemester()
    {
        return semester;
    }
    
    public void setSemester(Semester sem)
    {
        this.semester = sem;
    }
    
    public Integer getYear()
    {
        return year;
    }
    
    public void setYear(Integer year)
    {
        this.year = year;
    }
    
    public User getLecture()
    {
        return lecture;
    }
    
    public void setLecture(User lect)
    {
        this.lecture = lect;
    }
}
