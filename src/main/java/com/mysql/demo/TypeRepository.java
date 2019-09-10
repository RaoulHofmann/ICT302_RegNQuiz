package com.mysql.demo;

import org.springframework.data.repository.CrudRepository;

public interface TypeRepository extends CrudRepository<Type, Long> {
    Integer typeID = null;
    String description = null;
}
