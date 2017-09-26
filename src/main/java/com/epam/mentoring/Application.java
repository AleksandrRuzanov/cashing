package com.epam.mentoring;

import com.epam.mentoring.model.User;
import com.epam.mentoring.model.UserRepository;
import com.epam.mentoring.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Set;


@SpringBootApplication
public class Application implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    @Autowired
    private UserService service;

    @Autowired
    UserRepository repo;


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

    }

    @Override
    public void run(String... strings) throws Exception {
        init();
        findUserByName();
        findUserByNameNoCache();
        searchUser();
        searchUserNoCache();
    }

    private void init() {
        LOGGER.info("create users....");
        repo.deleteAll();
        for (int i = 0; i < 10000; i++) {
            service.save(new User(null, "Name" + i, i));
        }
        LOGGER.info("create users end.");
    }


    public void findUserByName() throws Exception {
        LOGGER.info("findUserByName start");
        for (int i = 0; i < 5000; i++) {
            Set<User> byName = service.findByName("Name1");
        }
        LOGGER.info("findUserByName end");
    }

    public void findUserByNameNoCache() throws Exception {
        LOGGER.info("findUserByNameNoCache start");
        for (int i = 0; i < 5000; i++) {
            Set<User> byName = service.findByNameNoCache("Name1");
        }
        LOGGER.info("findUserByNameNoCache end");
    }

    public void searchUser() throws Exception {
        LOGGER.info("searchUser start");
        for (int i = 0; i < 5000; i++) {
            Set<User> byName = service.searchByName("Name");
        }
        LOGGER.info("searchUser end");
    }

    public void searchUserNoCache() throws Exception {
        LOGGER.info("searchUserNoCache start");
        for (int i = 0; i < 5000; i++) {
            Set<User> byName = service.searchNoCache("Name");
        }
        LOGGER.info("searchUserNoCache end");
    }


}
