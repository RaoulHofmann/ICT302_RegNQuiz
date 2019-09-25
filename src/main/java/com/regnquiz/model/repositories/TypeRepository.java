package com.regnquiz.repositories;

import com.regnquiz.classes.Type;
import org.springframework.data.repository.CrudRepository;

public interface TypeRepository extends CrudRepository<Type, Long> {
    Integer typeID = null;
    String description = null;
}
