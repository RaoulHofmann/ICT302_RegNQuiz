package com.regnquiz.model.repositories;

import com.regnquiz.model.Type;
import com.regnquiz.model.UserType;
import com.regnquiz.model.UserIdent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTypeQueryRepository extends JpaRepository<UserType, UserIdent> {

    @Query("SELECT new Type(t.typeID AS TYPEID, t.description AS DESCRIPTION) FROM UserType ut "+
            "JOIN ut.type t " +
            "JOIN ut.user u WHERE u.userID LIKE :userid")
    Type getUserTypes(@Param("userid") Integer userid);
    
    
    //List<UserType> findByUserId(Integer UserID);
    //List<UserType> findByUser(int userid);
    //List<UserType> findByUserIdentUserId(int userid);
}
