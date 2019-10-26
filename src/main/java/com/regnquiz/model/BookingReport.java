package com.regnquiz.model;

import java.sql.Time;
import java.util.Date;

/**
 * Author: Raoul Hofmann
 * Date: 24/10/2019
 * Version: 1
 * Comment: Allows for gathering of data for booking reviews
 */
public class BookingReport {
    private Date date; // Date of the booking the report was generated for
    private Time time; // Time the booking the report was generated for

    // Venue information about the booking
    private int building;
    private int floor;
    private int room;

    // Question information about the booking
    private Integer questionID;
    private String description;
    private Integer answerID;

    public BookingReport(Date date, Time time, int building, int floor, int room, Integer questionID, String description, Integer answerID){
        this.date = date;
        this.time = time;

        this.building = building;
        this.floor = floor;
        this.room = room;

        this.answerID = answerID;
        this.description = description;
        this.questionID = questionID;
    }

    public Integer getAnswerID() {
        return answerID;
    }

    public Integer getQuestionID() {
        return questionID;
    }

    public Time getTime() {
        return time;
    }

    public Date getDate() {
        return date;
    }

    public int getBuilding() {
        return building;
    }

    public int getFloor() {
        return floor;
    }

    public int getRoom() {
        return room;
    }

    public String getDescription() {
        return description;
    }

    public void setQuestionID(Integer questionID) {
        this.questionID = questionID;
    }

    public void setAnswerID(Integer answerID) {
        this.answerID = answerID;
    }

    public void setBuilding(int building) {
        this.building = building;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public void setTime(Time time) {
        this.time = time;
    }
}
