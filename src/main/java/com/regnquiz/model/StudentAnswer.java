/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.regnquiz.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

/**
 * Author: Matthew MacLennan
 * Date: 22/9/2019
 * Version: 1
 * Comment: Contains student answers to questions
 */
@Entity
public class StudentAnswer 
{
    @EmbeddedId
    StudentAnswerKey id; // Composite key

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @MapsId("UserID")
    @JoinColumn(name = "UserID", referencedColumnName="userID")
    private User student;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @MapsId("AnswerID")
    @JoinColumn(name = "AnswerID", referencedColumnName="answerID")
    private MultipleChoice answer;
    
    public StudentAnswer()
    {
        student = new User();
        answer = new MultipleChoice();
        id = new StudentAnswerKey(student.getUserID(), answer.getMCID()); // Update key class with objects
    }
    
    public StudentAnswer(User student, MultipleChoice answer)
    {
        this.student = student;
        this.answer = answer;
        this.id = new StudentAnswerKey(this.student.getUserID(), this.answer.getMCID());
    }

    public StudentAnswer(StudentAnswerKey sta)
    {
        this.id = sta;
    }
    
    public User getStudent()
    {
        return student;
    }
    
    public void setStudent(User student)
    {
        this.student = student;
        this.id.setUserID(this.student.getUserID());
    }
    
    public MultipleChoice getStudentAnswer()
    {
        return answer;
    }
    
    public void setStudentAnswer(MultipleChoice ans)
    {
        this.answer = ans;
        this.id.setUserID(this.answer.getMCID());
    }

    public void setAnswer(MultipleChoice answer) {
        this.answer = answer;
        this.id.setUserID(this.answer.getMCID());
    }

    public void setId(StudentAnswerKey id) {
        this.id = id;
    }

    public StudentAnswerKey getId() {
        return id;
    }

}
