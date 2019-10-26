package com.regnquiz.model;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
import org.hibernate.annotations.Immutable;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Immutable
@Table(name="vvenuecapacity")
@IdClass(VenueReviewKey.class)
public class VenueReview implements Serializable {
    @Id
    @CsvBindByName(column = "Building")
    @CsvBindByPosition(position = 5)
    private int building;
    @Id
    @CsvBindByName(column = "Floor")
    @CsvBindByPosition(position = 6)
    private int floor;
    @Id
    @CsvBindByName(column = "Room")
    @CsvBindByPosition(position = 7)
    private int room;

    @CsvBindByName(column = "Capacity")
    @CsvBindByPosition(position = 0)
    private int capacity;

    @Id
    @CsvBindByName(column = "Date")
    @CsvBindByPosition(position = 1)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    @CsvBindByName(column = "StudentTotal")
    @CsvBindByPosition(position = 2)
    private Integer studentTotal;

    @CsvBindByName(column = "StudentAttend")
    @CsvBindByPosition(position = 3)
    private Integer studentAttend;

    @CsvBindByName(column = "Percent")
    @CsvBindByPosition(position = 4)
    private double capPercent;

    public Date getDate() {
        return date;
    }

    public int getRoom() {
        return room;
    }

    public int getFloor() {
        return floor;
    }

    public int getBuilding() {
        return building;
    }

    public double getCapPercent() {
        return capPercent;
    }

    public int getCapacity() {
        return capacity;
    }

    public Integer getStudentAttend() {
        return studentAttend;
    }

    public Integer getStudentTotal() {
        return studentTotal;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public void setBuilding(int building) {
        this.building = building;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setCapPercent(double capPercent) {
        this.capPercent = capPercent;
    }

    public void setStudentAttend(Integer studentAttend) {
        this.studentAttend = studentAttend;
    }

    public void setStudentTotal(Integer studentTotal) {
        this.studentTotal = studentTotal;
    }
}
