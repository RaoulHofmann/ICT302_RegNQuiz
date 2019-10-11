/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.regnquiz.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Author: Matthew MacLennan
 * Date: 24/9/2019
 * Version: 1
 * Comment: Key for StudentAnswers class
 */
public class StudentAnswerKey implements Serializable
{
    @Column(name = "UserID")
    private Integer UserID;
    
    @Column(name = "answerID")
    private Integer answerID;
    
    public StudentAnswerKey()
    {
        
    }
    
    public StudentAnswerKey(Integer uid, Integer aid)
    {
        this.UserID = uid;
        this.answerID = aid;
    }
    
    public Integer getUserID()
    {
        return UserID;
    }
    
    public void setUserID(Integer id)
    {
        this.UserID = id;
    }
    
    public Integer getAnswerID()
    {
        return answerID;
    }
    
    public void setAnswerID(Integer id)
    {
        this.answerID = id;
    }
}
