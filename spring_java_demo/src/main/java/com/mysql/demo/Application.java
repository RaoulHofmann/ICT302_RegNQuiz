/**
 *
 * STEPS:
 * * Create MYSQL table and user
 * * check src/main/resources/application.properties for mysql connection info
 *
 * COMMANDS:
 * * curl localhost:8080/demo/add -d name=Raoul -d email=32798271@student.murdoch.edu.au
 * * url localhost:8080/demo/all
 *
 * JPA(SQL COMMANDS) REFERENCE: https://www.objectdb.com/api/java/jpa/annotations/orm
 *
 * * EXAMPLES:
 * * https://spring.io/guides/gs/accessing-data-mysql/
 * * http://blog.netgloo.com/2014/10/27/using-mysql-in-spring-boot-via-spring-data-jpa-and-hibernate/
 *
 * USER.REPOSITORY (CRUD) REFERENCE
 * * https://docs.spring.io/spring-data/commons/docs/1.6.3.RELEASE/api/org/springframework/data/repository/CrudRepository.html
 * * https://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/dao/DataAccessException.html
 */

package com.mysql.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

