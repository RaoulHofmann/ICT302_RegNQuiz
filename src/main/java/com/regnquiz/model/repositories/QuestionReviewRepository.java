package com.regnquiz.model.repositories;

import com.regnquiz.model.AttendanceReview;
import com.regnquiz.model.QuestionReview;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuestionReviewRepository extends CrudRepository<QuestionReview, Integer> {
    List<QuestionReview> findAllByUnitID(Integer unitID);

    @Query(value = "SELECT * FROM vquestionreview WHERE unitID = :unitID GROUP BY bookingID, questionID", nativeQuery = true)
    List<QuestionReview> findbyUnitID_GroupByBookingIDAndQuestionID(@Param("unitID") int unitID,);


}
