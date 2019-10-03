package com.regnquiz.model;

import javax.persistence.*;
import java.io.Serializable;


@Embeddable
public class UserTypeKey implements Serializable {

    @Column(name = "userID")
    private Integer UserID;

    @Column(name = "typeID")
    private Integer TypeID;
    
    public UserTypeKey()
    {
        
    }
    
    public UserTypeKey(int typeID, int userID)
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
