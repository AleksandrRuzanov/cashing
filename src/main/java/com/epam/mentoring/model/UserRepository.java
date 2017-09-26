package com.epam.mentoring.model;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Set;

/**
 * Created by Aleksandr_Ruzanov on 22.09.2017.
 */
public interface UserRepository extends MongoRepository<User, String> {

    Set<User> findByName(String name);

    Set<User> findByNameStartingWith(String pattern);
}
