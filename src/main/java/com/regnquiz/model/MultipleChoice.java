/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.regnquiz.model;

import javax.persistence.*;

/**
 * Author: Matthew MacLennan
 * Date: 22/9/2019
 * Version: 1.1
 * Comment: Contains multiple choice data for questions
 * Changes: Removed Answer from this class
 */
@Entity
public class MultipleChoice 
{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer mcID;
    private String description;
    
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @MapsId("QuestionID")
    @JoinColumn(name = "QuestionID", referencedColumnName="questionID")
    private Question question;
    
    public MultipleChoice()
    {
        question = new Question();
    }
    
    public MultipleChoice(Question question, String description)
    {
        this.question = question;
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
    
    public String getDescription()
    {
        return description;
    }
    
    public void setDescription(String desc)
    {
        this.description = desc;
    }
    
}