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
@Embeddable
public class UserIdent implements Serializable{
    @Column(name = "userID")
    private Integer UserID;

    @Column(name = "typeID")
    private Integer TypeID;
    
    public UserIdent()
    {
        
    }
    
    public UserIdent(Integer typeID, Integer userID)
    {
        this.UserID = userID;
        this.TypeID = typeID;
    }
    
    
    public void setType(Integer typeID)
    {
        this.TypeID = typeID;
    }
    
    public Integer getType()
    {
        return TypeID;
    }
    
    public void setUser(Integer userID)
    {
        this.UserID = userID;
    }
    
    public Integer getUser()
    {
        return UserID;
    }
}
