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

package com.regnquiz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class Application {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);
        /*System.out.println("Let's inspect the beans provided by Spring Boot:");

        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }*/
    }
}

