package com.regnquiz.model.repositories;

import com.regnquiz.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
    //Long countByUserIDAndUserType_Password(Integer userid, Integer password);
}
