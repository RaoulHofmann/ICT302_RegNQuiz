package com.mysql.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface UserTypeQueryRepository extends JpaRepository<UserType, Long> {

    @Query("SELECT new Type(t.typeID AS TYPEID, t.description AS DESCRIPTION) FROM UserType ut "+
            "JOIN ut.type t " +
            "JOIN ut.user u WHERE u.userID LIKE :userid")
    List<Type>getUserTypes(@Param("userid") Integer userid);

    //List<UserType> findByUser(int userid);
}
