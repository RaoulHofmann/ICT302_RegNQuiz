/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.regnquiz.model;
import javax.persistence.*;
import java.io.Serializable;

/**
 *
 * 
 */
public class UserIdent implements Serializable{
    @Column(name = "userID")
    private Integer UserID;

    @Column(name = "typeID")
    private Integer TypeID;
    
    public UserIdent()
    {
        
    }
    
    public UserIdent(int typeID, int userID)
    {
        this.UserID = userID;
        this.TypeID = typeID;
    }
    
    
    public void setType(int typeID)
    {
        this.TypeID = typeID;
    }
    
    public int getType()
    {
        return TypeID;
    }
    
    public void setUser(int userID)
    {
        this.UserID = userID;
    }
    
    public int getUser()
    {
        return UserID;
    }
}
