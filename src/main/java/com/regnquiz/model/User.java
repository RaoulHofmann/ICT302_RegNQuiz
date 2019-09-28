package com.regnquiz.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Author: Matthew MacLennan
 * Date: 22/9/2019
 * Version: 1
 * Comment: Contains user information
 */
@Entity // This tells Hibernate to make a table out of this class
public class User 
{
    @Id
    private Integer userID;
    
    //private String studentNo;
    private String givenName;
    @Column(name = "preferredName")
    private String prefName;
    private String lastName;

    
    @OneToMany(mappedBy = "lecture")
    private Set<Unit> units;

    @OneToMany(mappedBy = "lecture")
    private Set<Booking> bookings;
    
    @OneToMany(mappedBy = "student")
    private Set<ClassList> classList;
    
    @OneToMany(mappedBy = "student")
    private Set<StudentAnswer> studentAnswer;
    
    @OneToMany(mappedBy = "user")
    private Set<UserType> userType;
    
    public User() {}
    
    public User(Integer id, String first, String pref, String last)
    {
        this.userID = id;
        this.givenName = first;
        this.prefName = pref;
        this.lastName = last;
    }

    public Integer getUserID() 
    {
        return userID;
    }

    public void setUserID(Integer id) 
    {
        this.userID = id;
    }

    public String getGivenName() 
    {
        return givenName;
    }

    public void setGivenName(String name) 
    {
        this.givenName = name;
    }
    
    public String getPrefName()
    {
        return prefName;
    }
    
    public void setPrefName(String name)
    {
        this.prefName = name;
    }
    
    public String getLastName()
    {
        return lastName;
    }
    
    public void setLastName(String name)
    {
        this.lastName = name;
    }
}