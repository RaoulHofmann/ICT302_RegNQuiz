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
 * Comment: Unit test for Question
 */
public class QuestionTest 
{
    public static void main(String args[])
    {
        Question a = new Question();
        display(a);
        
        a.setDescription("test1");
        a.setTime(1234);
        a.setAnswer(4321);
        display(a);
        
        Question b = new Question("test2", 5678, 8765);
        display(b);
        
        b.setDescription("test3");
        b.setTime(9012);
        b.setAnswer(2109);
        display(b);
    }
    
    public static void display(Question q)
    {
        System.out.println("Question ID: " + q.getQID());
        System.out.println("Description: " + q.getDescription());
        System.out.println("Time: " + q.getTime());
        System.out.println("Answer: " + q.getAnswer());
        System.out.println();
        System.out.println();
    }
}
