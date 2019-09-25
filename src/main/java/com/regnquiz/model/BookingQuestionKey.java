/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.regnquiz.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Author: Matthew MacLennan
 * Date: 24/9/2019
 * Version: 1
 * Comment: Key for BookingQuestion class
 */
public class BookingQuestionKey implements Serializable
{
    @Column(name = "bookingID")
    private Integer bookingID;
    
    @Column(name = "questionID")
    private Integer questionID;
}
