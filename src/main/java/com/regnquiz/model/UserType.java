package com.regnquiz.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

@Entity // This tells Hibernate to make a table out of this class
public class UserType {

    @EmbeddedId
    UserIdent id;

    @Column
    private String password;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @MapsId("TypeID")
    @JoinColumn(name = "TypeID", referencedColumnName="typeID")
    private Type type;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @MapsId("UserID")
    @JoinColumn(name = "UserID", referencedColumnName="userID")
    private User user;  
    
    public UserType()
    {
        type = new Type();
        user = new User();
        
        id = new UserIdent(type.getTypeID(), user.getUserID());
    }
    
    public UserType(Type t, User u, String pw)
    {
        this.type = t;
        this.user = u;
        this.password = pw;
        
        this.id = new UserIdent(this.type.getTypeID(), this.user.getUserID());
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public void setType(Type t)
    {
        this.type = t;
        id.setType(this.type.getTypeID());
    }
    
    public Type getType()
    {
        return type;
    }
    
    public void setUser(User u)
    {
        this.user = u;
        id.setUser(this.user.getUserID());
    }
    
    public User getUser()
    {
        return user;
    }
    
    public void setUserType(UserIdent id)
    {
        this.id = id;
    }
    
    public UserIdent getUserType()
    {
        return id;
    }

}