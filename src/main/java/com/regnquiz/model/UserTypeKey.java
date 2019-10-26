package com.regnquiz.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Author: Matthew MacLennan
 * Date: 22/9/2019
 * Version: 1
 * Comment: Composite key class for usertype object
 */
@Embeddable
public class UserTypeKey implements Serializable {

    @Column(name = "userID")
    private Integer UserID;

    @Column(name = "typeID")
    private Integer TypeID;
    
    public UserTypeKey()
    {
        
    }
   
    public UserTypeKey(Integer typeID, Integer userID)
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
