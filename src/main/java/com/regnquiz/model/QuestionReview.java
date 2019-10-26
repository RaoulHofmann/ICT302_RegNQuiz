package com.regnquiz.model;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Immutable
@Table(name="vquestionreview")
@IdClass(QuestionReviewKey.class)
public class QuestionReview  implements Serializable {
    @Id
    private int unitID;
    @Id
    private int bookingID;
    @Id
    private int questionID;

    @Id
    @CsvBindByName(column = "Question")
    @CsvBindByPosition(position = 0)
    private String question;

    @Id
    @CsvBindByName(column = "Answer")
    @CsvBindByPosition(position = 1)
    private String answer;

    @Id
    @CsvBindByName(column = "Count")
    @CsvBindByPosition(position = 2)
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
