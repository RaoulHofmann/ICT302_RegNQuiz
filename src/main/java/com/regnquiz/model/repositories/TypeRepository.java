package com.regnquiz.model.repositories;

import com.regnquiz.model.Type;
import org.springframework.data.repository.CrudRepository;

public interface TypeRepository extends CrudRepository<Type, Integer> {
    Integer typeID = null;
    String description = null;
}
