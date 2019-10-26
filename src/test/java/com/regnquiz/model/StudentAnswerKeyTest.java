/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.regnquiz.model;

/**
 *
 * @author Matthew MacLennan
 */
public class StudentAnswerKeyTest 
{
    public static void main(String args[])
    {
        StudentAnswerKey a = new StudentAnswerKey();
        display(a);
        
        a.setUserID(1);
        a.setAnswerID(2);
        display(a);
        
        StudentAnswerKey b = new StudentAnswerKey(3, 4);
        display(b);
        
        b.setUserID(5);
        b.setAnswerID(6);
        display(b);
    }
    
    public static void display(StudentAnswerKey sak)
    {
        System.out.println("User ID: " + sak.getUserID());
        System.out.println("Answer ID: " + sak.getAnswerID());
        System.out.println();
        System.out.println();
    }
}
