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
public class LoginTest 
{
    public static void main(String args[])
    {
        Login a = new Login();
        display(a);
        
        a.setUserID(1);
        a.setPassword("2");
        display(a);
        
        Login b = new Login(3, "4");
        display(b);
        
        b.setUserID(5);
        b.setPassword("6");
        display(b);
    }
    
    public static void display(Login ui)
    {
        System.out.println("User ID: " + ui.getUserID());
        System.out.println("Password: " + ui.getPassword());
        System.out.println();
        System.out.println();
    }
}
