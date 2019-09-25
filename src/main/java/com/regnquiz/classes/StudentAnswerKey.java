/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.regnquiz.classes;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Author: Matthew MacLennan
 * Date: 24/9/2019
 * Version: 1
 * Comment: Key for StudentAnswers class
 */
public class StudentAnswerKey implements Serializable
{
    @Column(name = "UserID")
    private Integer UserID;
    
    @Column(name = "answerID")
    private Integer answerID;
}
