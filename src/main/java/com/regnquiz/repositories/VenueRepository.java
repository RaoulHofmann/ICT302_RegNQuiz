/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.regnquiz.repositories;

import com.regnquiz.classes.Venue;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Author: Stuart Hepburn
 * Date: 22/9/2019
 * Version: 1
 * 
 */
public interface VenueRepository extends CrudRepository<Venue, Integer>{
    @Query("SELECT case when count(v.venueID) = 0 then 0 else v.venueID end FROM Venue v WHERE v.building = :building AND floor = :floor AND room = :room")
    int findByLocation(@Param("building") int building, @Param("floor") int floor, @Param("room") int room);
    

}
