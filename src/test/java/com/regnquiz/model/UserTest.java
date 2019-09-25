/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.regnquiz.model;

/**
 * Author: Matthew MacLennan
 * Date: 22/9/2019
 * Version: 1
 * Comment: Unit test for User
 */
public class UserTest {
    public static void main(String[] args)
    {
        User a = new User();
        display(a);
        
        a.setUserID(1234);
        a.setGivenName("Test1");
        a.setPrefName("Test2");
        a.setLastName("Test3");
        display(a);
        
        User b = new User(4321, "test4", "test5", "test6");
        display(b);
        
        b.setUserID(5678);
        b.setGivenName("test7");
        b.setPrefName("test8");
        b.setLastName("test9");
        display(b);
    }
    
    public static void display(User u)
    {
        //userID, givenName, prefName, lastName
        System.out.println("User ID: " + u.getUserID());
        System.out.println("Given Name: " + u.getGivenName());
        System.out.println("Preferred Name: " + u.getPrefName());
        System.out.println("Last Name: " + u.getLastName());
        System.out.println();
        System.out.println();
    }
}
