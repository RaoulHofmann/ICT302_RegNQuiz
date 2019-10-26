package com.regnquiz.model.forms;

import com.regnquiz.model.Question;

public class QuestionAdd {
    private int bookingID;
    private int year;
    private int semesterID;
    private int unitID;

    public int getBookingID() {
        return bookingID;
    }
    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }

    public QuestionAdd(){
        this.bookingID = 0;
        this.year = 0;
        this.semesterID = 0;
        this.unitID = 0;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getSemesterID() {
        return semesterID;
    }

    public void setSemesterID(int semesterID) {
        this.semesterID = semesterID;
    }

    public int getUnitID() {
        return unitID;
    }

    public void setUnitID(int unitID) {
        this.unitID = unitID;
    }
}
