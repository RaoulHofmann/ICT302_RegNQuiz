package com.regnquiz.model;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Date;

@Embeddable
public class AttendanceReviewKey implements Serializable {

    private int unitID;
    private Date date;

    public Date getDate() {
        return date;
    }

    public int getUnitID() {
        return unitID;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setUnitID(int unitID) {
        this.unitID = unitID;
    }
}
