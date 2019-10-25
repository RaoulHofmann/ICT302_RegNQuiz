package com.regnquiz.model.repositories;

import com.regnquiz.model.AttendanceReview;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AttendanceReviewRepository extends CrudRepository<AttendanceReview, Integer> {
    List<AttendanceReview> findAllByUnitID(Integer unitID);
}
