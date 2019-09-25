package com.regnquiz.repositories;

import com.regnquiz.classes.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
    //Long countByUserIDAndUserType_Password(Integer userid, Integer password);
}
