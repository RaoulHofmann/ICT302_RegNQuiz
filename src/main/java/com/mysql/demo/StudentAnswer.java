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
 * Comment: Contains student answers to questions
 */
@Entity
public class StudentAnswer 
{
    @Id // This isn't right
    private User student;
    private Integer answer;
    
    public StudentAnswer()
    {
        student = new User();
    }
    
    public StudentAnswer(User student, Integer answer)
    {
        this.student = student;
        this.answer = answer;
    }
    
    public User getStudent()
    {
        return student;
    }
    
    public void setStudent(User student)
    {
        this.student = student;
    }
    
    public Integer getStudentAnswer()
    {
        return answer;
    }
    
    public void setStudentAnswer(Integer ans)
    {
        this.answer = ans;
    }
}
