package com.regnquiz.model;

public class Login {
    private Integer userID;
    private String password;

    public Login() {}
    
    public Login(Integer uid, String pw)
    {
        this.userID = uid;
        this.password = pw;
    }
    
    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
