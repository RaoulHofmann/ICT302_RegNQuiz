/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.regnquiz.model.repositories;

import com.regnquiz.model.Semester;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.CrudRepository;
import java.util.Set;
import com.regnquiz.model.Unit;
/**
 * Author: Matthew MacLennan
 * Date: 27/9/2019
 * Version: 1
 * Comment: Contains semester repository function
 */
public interface SemesterRepository extends CrudRepository<Semester, Integer>
{
    @Query("SELECT case when count(s.semesterID) = 0 then 0 else s.semesterID end FROM Semester s Where s.description = :description AND s.Unit = :Unit")
    int findByLocation(@Param("description") String description, @Param("Unit") Set<Unit> Unit);
}
