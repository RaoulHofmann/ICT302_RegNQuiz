package com.regnquiz.model;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class QuestionReviewKey implements Serializable {

    private int unitID;
    private int bookingID;
    private int questionID;
    private String question;
    private String answer;
    private int count;

    public String getAnswer() {
        return answer;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public int getQuestionID() {
        return questionID;
    }

    public int getBookingID() {
        return bookingID;
    }

    public int getUnitID() {
        return unitID;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }

    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }

    public void setUnitID(int unitID) {
        this.unitID = unitID;
    }
}
