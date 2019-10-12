package com.regnquiz.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

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
    
    public User() {}
    
    public User(Integer id, String first, String pref, String last)
    {
        this.userID = id;
        this.givenName = first;
        this.prefName = pref;
        this.lastName = last;
    }
    
    public User(Integer id, String first, String last)
    {
        this.userID = id;
        this.givenName = first;
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

    public void setBookings(Set<Booking> bookings) {
        this.bookings = bookings;
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

    public void setClassList(Set<ClassList> classList) {
        this.classList = classList;
    }

    public void setStudentAnswer(Set<StudentAnswer> studentAnswer) {
        this.studentAnswer = studentAnswer;
    }

    public void setUnits(Set<Unit> units) {
        this.units = units;
    }

    public void setUserType(Set<UserType> userType) {
        this.userType = userType;
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