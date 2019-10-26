/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.regnquiz.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.Set;
import javax.persistence.*;
import java.util.HashSet;

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
    private Integer answerID;
    private String description; // The description of the answer

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "QuestionID", referencedColumnName="questionID")
    private Question question; // Question that this answer is linked to

    @JsonBackReference
    @OneToMany(mappedBy = "answer")
    private Set<StudentAnswer> studentAnswer; // List of students that have used this answer to answer a question (used for database purposes)
    
    public MultipleChoice()
    {
        question = new Question();
        studentAnswer = new HashSet<>();
    }
    
    public MultipleChoice(Question question, String description)
    {
        this.question = question;
        this.description = description;
        studentAnswer = new HashSet<>();
    }

    public void setAnswerID(Integer id)
    {
        answerID = id;
    }
    
    public Integer getAnswerID() {
        return answerID;
    }

    public Integer getMCID()
    {
        return answerID;
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
    
    public Set<StudentAnswer> getStudentAnswers()
    {
        return studentAnswer;
    }
    
    public void setStudentAnswers(Set<StudentAnswer> sa)
    {
        this.studentAnswer = sa;
    }
    
    public void addStudentAnswer(StudentAnswer sa)
    {
        studentAnswer.add(sa);
    }
}
