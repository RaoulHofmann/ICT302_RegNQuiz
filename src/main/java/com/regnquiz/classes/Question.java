/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.regnquiz.classes;

import javax.persistence.*;
import java.util.Set;
/**
 * Author: Matthew MacLennan
 * Date: 22/9/2019
 * Version: 1
 * Comment: Contains question data
 */
@Entity
public class Question 
{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer questionID;
    private String description;
    private Integer time;
    private Integer answer;
       
    public Question() {}
    public Question(String desc, Integer time, Integer answer)
    {
        this.description = desc;
        this.time = time;
        this.answer = answer;
    }
    
    public Integer getQID()
    {
        return questionID;
    }
    
    public String getDescription()
    {
        return description;
    }
    
    public void setDescription(String desc)
    {
        this.description = desc;
    }
    
    public Integer getTime()
    {
        return time;
    }
    
    public void setTime(Integer time)
    {
        this.time = time;
    }
    
    public Integer getAnswer()
    {
        return answer;
    }
    
    public void setAnswer(Integer answer)
    {
        this.answer = answer;
    }
}
