package com.regnquiz.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Set;
import java.util.HashSet;

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
    //@GeneratedValue
    private Integer userID;
    
    //private String studentNo;
    private String givenName;
    @Column(name = "preferredName")
    private String prefName;
    private String lastName;

    @JsonBackReference
    @OneToMany(mappedBy = "lecture")
    private Set<Unit> units;

    @JsonBackReference
    @OneToMany(mappedBy = "lecture")
    private Set<Booking> bookings;

    @JsonBackReference
    @OneToMany(mappedBy = "student")
    private Set<ClassList> classList;

    @JsonBackReference
    @OneToMany(mappedBy = "student")
    private Set<StudentAnswer> studentAnswer;

    @JsonBackReference
    @OneToMany(mappedBy = "user")
    private Set<UserType> userType;
    
    public User() 
    {
        units = new HashSet<>();
        bookings = new HashSet<>();
        classList = new HashSet<>();
        studentAnswer = new HashSet<>();
        userType = new HashSet<>();
    }
    
    public User(Integer id, String first, String pref, String last)
    {
        this.userID = id;
        this.givenName = first;
        this.prefName = pref;
        this.lastName = last;
        
        units = new HashSet<>();
        bookings = new HashSet<>();
        classList = new HashSet<>();
        studentAnswer = new HashSet<>();
        userType = new HashSet<>();
    }
    
    public User(Integer id, String first, String last)
    {
        this.userID = id;
        this.givenName = first;
        this.lastName = last;
        
        units = new HashSet<>();
        bookings = new HashSet<>();
        classList = new HashSet<>();
        studentAnswer = new HashSet<>();
        userType = new HashSet<>();
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
    
    public void setUserType(UserType userType)
    {
        this.userType.add(userType);
    }
    
    public Set<UserType> getUserType()
    {
        return this.userType;
    }

    public Set<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(Set<Booking> b) {
        this.bookings = b;
    }

    public Set<ClassList> getClassList() {
        return classList;
    }

    public Set<StudentAnswer> getStudentAnswer() {
        return studentAnswer;
    }

    public Set<Unit> getUnits() {
        return units;
    }

    public void setClassList(Set<ClassList> cl) {
        this.classList = cl;
    }

    public void setStudentAnswer(Set<StudentAnswer> sa) {
        this.studentAnswer = sa;
    }

    public void setUnits(Set<Unit> u) {
        this.units = u;
    }

    public void setUserType(Set<UserType> ut) {
        this.userType = ut;
    }
    
    public void addUnit(Unit u)
    {
        units.add(u);
    }
    
    public void addBooking(Booking b)
    {
        bookings.add(b);
    }
    
    public void addClassList(ClassList cl)
    {
        classList.add(cl);
    }
    
    public void addStudentAnswers(StudentAnswer sa)
    {
        studentAnswer.add(sa);
    }
    
    public void addUserType(UserType ut)
    {
        userType.add(ut);
    }
}