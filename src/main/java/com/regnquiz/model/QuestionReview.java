package com.regnquiz.model;

import org.hibernate.annotations.Immutable;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
/**
 * Author: Raoul Hofmann
 * Date: 24/10/2019
 * Version: 1
 * Comment: Allows for gathering of data for question reviews
 */
@Entity
@Immutable // So we don't write to the view
@Table(name="vquestionreview") // Based on a view rather than a table
public class QuestionReview {
    @Id
    private int unitID;

    private int bookingID;
    private int questionID;
    private String question;
    private String answer;
    private int count;

    public int getUnitID() {
        return unitID;
    }

    public int getBookingID() {
        return bookingID;
    }

    public int getCount() {
        return count;
    }

    public int getQuestionID() {
        return questionID;
    }

    public String getAnswer() {
        return answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setUnitID(int unitID) {
        this.unitID = unitID;
    }

    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }

    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
