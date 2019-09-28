/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.regnquiz.model;

import java.util.HashSet;
import java.util.Set;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * 
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class LectureRunTest {
    @Autowired
    private LectureRun lr;
    
    @Test
    public void Start()
    {
        lr.OpenLecture(3);
        
        System.out.println();
        System.out.println();
        System.out.println("###############################################" );
        System.out.println("###############################################" );
        System.out.println("Attendance Code " + lr.getAccessCode());
        
        
        lr.setAttendance(4);
        
        lr.saveLecture();
        
    }
}
