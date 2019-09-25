/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.regnquiz.classes;

import com.regnquiz.classes.MultipleChoice;
import com.regnquiz.classes.Question;

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
        
        a.setQuestion(new Question("test1", 1234, 4321));
        a.setAnswer(5678);
        a.setDescription("test2");
        display(a);
        
        MultipleChoice b = new MultipleChoice(new Question("test3", 8765, 9012), 2109, "test4");
        display(b);
        
        b.setQuestion(new Question("test5", 3456, 6543));
        b.setAnswer(7890);
        b.setDescription("test6");
        display(b);
    }
    
    public static void display(MultipleChoice m)
    {
        System.out.println("MC ID: " + m.getMCID());
        System.out.println("Question ID: " + m.getQuestion().getDescription());
        System.out.println("Answer: " + m.getAnswer());
        System.out.println("Description: " + m.getDescription());
        System.out.println();
        System.out.println();
    }
}
