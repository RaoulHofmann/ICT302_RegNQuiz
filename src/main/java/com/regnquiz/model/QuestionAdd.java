package com.regnquiz.model;

public class QuestionAdd {
    private int bookingID;

    private String questionTopic;
    private String questionType;

    private String answerA;
    private String answerB;
    private String answerC;
    private String answerD;
    private String correctAnswerA;
    private String correctAnswerB;
    private String correctAnswerC;
    private String correctAnswerD;
    private String answerTrue;
    private String answerFalse;

    public int getBookingID() {
        return bookingID;
    }

    public String getAnswerA() {
        return answerA;
    }

    public String getAnswerB() {
        return answerB;
    }

    public String getAnswerC() {
        return answerC;
    }

    public String getAnswerD() {
        return answerD;
    }

    public String getAnswerFalse() {
        return answerFalse;
    }

    public String getAnswerTrue() {
        return answerTrue;
    }

    public String getCorrectAnswerA() {
        return correctAnswerA;
    }

    public String getCorrectAnswerB() {
        return correctAnswerB;
    }

    public String getCorrectAnswerC() {
        return correctAnswerC;
    }

    public String getCorrectAnswerD() {
        return correctAnswerD;
    }

    public String getQuestionTopic() {
        return questionTopic;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setAnswerA(String answerA) {
        this.answerA = answerA;
    }

    public void setAnswerB(String answerB) {
        this.answerB = answerB;
    }

    public void setAnswerC(String answerC) {
        this.answerC = answerC;
    }

    public void setAnswerD(String answerD) {
        this.answerD = answerD;
    }

    public void setAnswerFalse(String answerFalse) {
        this.answerFalse = answerFalse;
    }

    public void setAnswerTrue(String answerTrue) {
        this.answerTrue = answerTrue;
    }

    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }

    public void setCorrectAnswerA(String correctAnswerA) {
        this.correctAnswerA = correctAnswerA;
    }

    public void setCorrectAnswerB(String correctAnswerB) {
        this.correctAnswerB = correctAnswerB;
    }

    public void setCorrectAnswerC(String correctAnswerC) {
        this.correctAnswerC = correctAnswerC;
    }

    public void setCorrectAnswerD(String correctAnswerD) {
        this.correctAnswerD = correctAnswerD;
    }

    public void setQuestionTopic(String questionTopic) {
        this.questionTopic = questionTopic;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }
}
