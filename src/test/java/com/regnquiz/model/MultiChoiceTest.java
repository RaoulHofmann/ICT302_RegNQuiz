/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.regnquiz.model;

import com.regnquiz.model.MultipleChoice;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: Matthew MacLennan
 * Date: 23/9/2019
 * Version: 1
 * Comment: Unit test for MultipleChoice
 */
public class MultiChoiceTest 
{
    public static void main(String args[])
    {
        MultipleChoice a = new MultipleChoice();
        display(a);
        
        Set<StudentAnswer> sa = new HashSet<>();
        sa.add(new StudentAnswer());
        
        a.setStudentAnswers(sa);
        
        a.setQuestion(new Question("test1", 1234, 4321));
        a.setDescription("test2");
        display(a);
        
        MultipleChoice b = new MultipleChoice(new Question("test3", 8765, 9012), "test4");
        display(b);
        
        b.setStudentAnswers(sa);
        b.setQuestion(new Question("test5", 3456, 6543));
        b.setDescription("test6");
        display(b);
        
        b.addStudentAnswer(new StudentAnswer());
        display(b);
    }
    
    public static void display(MultipleChoice m)
    {     
        System.out.println("MC ID: " + m.getMCID());
        System.out.println("Question ID: " + m.getQuestion().getDescription());
        System.out.println("Description: " + m.getDescription());
        System.out.println("Size of StudentAnswer Set: " + m.getStudentAnswers().size());
        System.out.println();
        System.out.println();
    }
}
