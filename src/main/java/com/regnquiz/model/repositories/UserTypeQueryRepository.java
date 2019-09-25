package com.regnquiz.model.repositories;

import com.regnquiz.model.Type;
import com.regnquiz.model.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserTypeQueryRepository extends JpaRepository<UserType, Long> {

    @Query("SELECT new Type(t.typeID AS TYPEID, t.description AS DESCRIPTION) FROM UserType ut "+
            "JOIN ut.type t " +
            "JOIN ut.user u WHERE u.userID LIKE :userid")
    Type getUserTypes(@Param("userid") Integer userid);

    //List<UserType> findByUser(int userid);
}
