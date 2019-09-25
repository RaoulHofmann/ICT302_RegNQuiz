/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.regnquiz.model;

/**
 * Author: Matthew MacLennan
 * Date: 23/9/2019
 * Version: 1
 * Comment: Unit test for StudentAnswers
 */
public class StudentAnswerTest 
{
    public static void main(String args[])
    {
        StudentAnswer a = new StudentAnswer();
        display(a);
        
        a.setStudent(new User(1234, "a", "a", "a"));
        a.setStudentAnswer(4321);
        display(a);
        
        StudentAnswer b = new StudentAnswer(new User(5678, "b", "b", "b"), 8765);
        display(b);
        
        b.setStudent(new User(9012, "c", "c", "c"));
        b.setStudentAnswer(2109);
        display(b);
    }
    
    public static void display(StudentAnswer s)
    {
        System.out.println("Student ID: " + s.getStudent().getUserID());
        System.out.println("Answer: " + s.getStudentAnswer());
        System.out.println();
        System.out.println();
    }
}
