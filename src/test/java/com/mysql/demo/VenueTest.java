/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mysql.demo;

/**
 *
 * @author Dawn
 */
public class VenueTest {
    public static void main(String[] args) {
        
        Venue a = new Venue();
       
        display(a);
        
        a.setBuilding(100);
        a.setFloor(2);
        a.setRoom(23);
        a.setCapacity(30);
        a.setVenueID(3);
        
        display(a);
        a.setLocation("545.1.1");
        display(a);
        

        Venue b = new Venue(90, 1, 10, 40);
        display(b);
        
        b.setBuilding(245);
        b.setFloor(2);
        b.setRoom(26);
        b.setCapacity(150);
        b.setVenueID(6);
        display(b);
        
        Venue c = new Venue("221.4.100", 89);
        display(c);
        
        c.setBuilding(90);
        c.setFloor(1);
        c.setRoom(2);
        c.setCapacity(1000);
        c.setVenueID(7);
        display(c);
        
        c.setLocation("333.2.99");
        display(c);
        
        
    }
    
    public static void display(Venue v)
    {
        System.out.println("Building: " + v.getBuilding());
        System.out.println("Floor: " + v.getFloor());
        System.out.println("Room: " + v.getRoom());
        System.out.println("Capacity: " + v.getCapacity());
        System.out.println("Venue ID: " + v.getVenueID());
        System.out.println("to String: " +  v.toString());
        System.out.println();
        System.out.println();
    }
}
