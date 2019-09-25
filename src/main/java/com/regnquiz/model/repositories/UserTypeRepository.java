package com.regnquiz.repositories;

import com.regnquiz.classes.UserType;
import org.springframework.data.repository.CrudRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UserTypeRepository extends CrudRepository<UserType, String> {
}
