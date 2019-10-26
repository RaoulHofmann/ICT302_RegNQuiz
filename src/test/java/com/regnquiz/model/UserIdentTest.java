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
public class UserIdentTest 
{
    public static void main(String args[])
    {
        UserIdent a = new UserIdent();
        display(a);
        
        a.setUser(1);
        a.setType(2);
        display(a);
        
        UserIdent b = new UserIdent(3, 4);
        display(b);
        
        b.setUser(5);
        b.setType(6);
        display(b);
    }
    
    public static void display(UserIdent ui)
    {
        System.out.println("User ID: " + ui.getUser());
        System.out.println("Type ID: " + ui.getType());
        System.out.println();
        System.out.println();
    }
}
