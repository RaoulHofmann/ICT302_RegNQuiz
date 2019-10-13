/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.regnquiz.model;
import java.util.Set;
import java.util.HashSet;


/**
 * Author: Matthew MacLennan
 * Date: 22/9/2019
 * Version: 1
 * Comment: Unit test for User
 */
public class UserTest {
    public static void main(String[] args)
    {
        Set<Unit> u = new HashSet<>();
        Set<Booking> book = new HashSet<>();
        Set<ClassList> cl = new HashSet<>();
        Set<StudentAnswer> sa = new HashSet<>();
        Set<UserType> ut = new HashSet<>();
        
        u.add(new Unit());
        book.add(new Booking());
        cl.add(new ClassList());
        sa.add(new StudentAnswer());
        ut.add(new UserType());
        
        User a = new User();
        display(a);
        
        a.setUserID(1234);
        a.setGivenName("Test1");
        a.setPrefName("Test2");
        a.setLastName("Test3");
        a.setUnits(u);
        a.setBookings(book);
        a.setClassList(cl);
        a.setStudentAnswer(sa);
        a.setUserType(ut);
        display(a);
        
        User b = new User(4321, "test4", "test5", "test6");
        display(b);
        
        b.setUserID(5678);
        b.setGivenName("test7");
        b.setPrefName("test8");
        b.setLastName("test9");
        b.setUnits(u);
        b.setBookings(book);
        b.setClassList(cl);
        b.setStudentAnswer(sa);
        b.setUserType(ut);
        display(b);
        
        b.addUnit(new Unit());
        b.addBooking(new Booking());
        b.addClassList(new ClassList());
        b.addStudentAnswers(new StudentAnswer());
        b.setUserType(new UserType());
        display(b);
        
        
    }
    
    public static void display(User u)
    {
        //userID, givenName, prefName, lastName
        System.out.println("User ID: " + u.getUserID());
        System.out.println("Given Name: " + u.getGivenName());
        System.out.println("Preferred Name: " + u.getPrefName());
        System.out.println("Last Name: " + u.getLastName());
        System.out.println("Unit Size: " + u.getUnits().size());
        System.out.println("Bookings Size: " + u.getBookings().size());
        System.out.println("ClassList Size: " + u.getClassList().size());
        System.out.println("UserType Size: " + u.getUserType().size());
        System.out.println();
        System.out.println();
    }
}
