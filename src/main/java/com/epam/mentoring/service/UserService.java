package com.epam.mentoring.service;

import com.epam.mentoring.config.CacheContainer;
import com.epam.mentoring.model.User;
import com.epam.mentoring.model.UserRepository;
import com.google.common.cache.CacheLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.ExecutionException;

/**
 * Created by Aleksandr_Ruzanov on 22.09.2017.
 */

@Service
public class UserService {

    @Autowired
    UserRepository repository;
    @Autowired
    CacheContainer cacheContainer;

    @Autowired
    public UserService(UserRepository userRepository, CacheContainer cacheContainer) {
        this.repository = userRepository;
        this.cacheContainer = cacheContainer;
    }

    public User save(User user) {
        return repository.save(user);
    }

    public Set<User> findByName(String name) throws ExecutionException {
        return cacheContainer.getFromCache("userByName", name, new CacheLoader<String, Set<User>>() {
            @Override
            public Set<User> load(String key) {
                return findByNameNoCache(key);
            }
        });
    }

    public Set<User> findByNameNoCache(String key) {
        return repository.findByName(key);
    }

    public Set<User> searchByName(String name) throws ExecutionException {
        return cacheContainer.getFromCache("userSearchByName", name, new CacheLoader<String, Set<User>>() {
            @Override
            public Set<User> load(String key) {
                return searchNoCache(key);
            }
        });
    }

    public Set<User> searchNoCache(String key) {
        return repository.findByNameStartingWith(key);
    }

    public User findById(String id) throws ExecutionException {
        return cacheContainer.getFromCache("userById", id, new CacheLoader<String, User>() {
            @Override
            public User load(String key) {
                return findOneNoCache(key);
            }
        });
    }

    public User findOneNoCache(String key) {
        return repository.findOne(key);
    }

}
