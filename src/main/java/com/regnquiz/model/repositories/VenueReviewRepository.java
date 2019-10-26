package com.regnquiz.model.repositories;

import com.regnquiz.model.QuestionReview;
import com.regnquiz.model.VenueReview;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface VenueReviewRepository extends CrudRepository<VenueReview, Integer> {

    List<VenueReview> findByBuildingAndFloorAndRoom(Integer building, Integer floor, Integer room);
    List<VenueReview> findByBuildingAndFloor(Integer building, Integer floor);
    List<VenueReview> findByBuilding(Integer building);

    List<VenueReview> findByBuildingAndFloorAndRoomAndDate(Integer building, Integer floor, Integer room, Date date);
    List<VenueReview> findByBuildingAndFloorAndDate(Integer building, Integer floor, Date date);
    List<VenueReview> findByBuildingAndDate(Integer building, Date date);

}
