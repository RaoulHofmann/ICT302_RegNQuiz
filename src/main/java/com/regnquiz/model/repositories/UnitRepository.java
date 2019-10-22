/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.regnquiz.model.repositories;

import com.regnquiz.model.Semester;
import com.regnquiz.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.CrudRepository;
import java.util.Set;
import com.regnquiz.model.Unit;
import java.util.List;

/**
 * Author: Matthew MacLennan
 * Date: 28/9/2019
 * Version: 1
 * Comment: Contains Unit repository function
 */

public interface UnitRepository extends CrudRepository<Unit, Integer>
{
    @Query("SELECT case when count(u.unitID) = 0 then 0 else u.unitID end FROM Unit u WHERE u.unitCode = :unitCode AND u.unitName = :unitName AND u.year = :year AND u.semester = :semester AND u.lecture = :lecture AND u.bookings = :bookings")
    Set<Unit> getUnits(@Param("unitCode") String unitCode, @Param("unitName") String unitName, @Param("year") Integer year, @Param("semester") Semester semester, @Param("lecture") User lecture);
    
    List<Unit> findByLecture(User user);
    List<Unit> findByLectureAndYear(User user, int year);
    List<Unit> findByLectureAndYearAndSemester_SemesterID(User user, int year, int semester);
    List<Unit> findByLectureAndSemester_SemesterID(User user, int semester);
}
