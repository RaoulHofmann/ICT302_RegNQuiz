package com.mysql.demo;

import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.*;
import java.util.Set;

@Entity // This tells Hibernate to make a table out of this class
public class User 
{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer userID;

    private String givenName;
    private String prefName;
    private String lastName;
    
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