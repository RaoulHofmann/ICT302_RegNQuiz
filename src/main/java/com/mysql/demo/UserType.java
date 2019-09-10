package com.mysql.demo;

import javax.persistence.*;

@Entity // This tells Hibernate to make a table out of this class
public class UserType {

    @EmbeddedId
    UserTypeKey id;

    @Column
    private int password;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @MapsId("TypeID")
    @JoinColumn(name = "TypeID", referencedColumnName="typeID")
    private Type type;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @MapsId("UserID")
    @JoinColumn(name = "UserID", referencedColumnName="userID")
    private User user;

    public Integer getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

}