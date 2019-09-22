/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mysql.demo;

import javax.persistence.*;
import java.util.Set;

/**
 * Author: Matthew MacLennan
 * Date: 22/9/2019
 * Version: 1
 * Comment: Contains multiple choice data for questions
 */
@Entity
public class MultipleChoice 
{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer mcID;
    private Question question;
    private Integer answer;
    private String description;
    
    public MultipleChoice()
    {
        question = new Question();
    }
    
    public MultipleChoice(Question question, Integer ans, String description)
    {
        this.question = question;
        this.answer = ans;
        this.description = description;
    }
    
    public Integer getMCID()
    {
        return mcID;
    }
    
    public Question getQuestion()
    {
        return question;
    }
    
    public void setQuestion(Question question)
    {
        this.question = question;
    }
    
    public Integer getAnswer()
    {
        return answer;
    }
    
    public void setAnswer(Integer ans)
    {
        this.answer = ans;
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
