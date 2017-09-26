package com.epam.mentoring;

import com.epam.mentoring.config.AppConfig;
import com.epam.mentoring.config.DbConfig;
import com.epam.mentoring.model.User;
import com.epam.mentoring.model.UserRepository;
import com.epam.mentoring.service.UserService;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Set;

@ContextConfiguration(classes = {AppConfig.class, DbConfig.class})
public class UserServiceTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private UserService service;

    @Autowired
    UserRepository repo;

    @Before
    public void init() {
        repo.deleteAll();
        for (int i = 0; i < 10000; i++) {
            service.save(new User(null, "Name" + i, i));
        }
    }

    @Test
    public void findUserByName() throws Exception {
        for (int i = 0; i < 5000; i++) {
            Set<User> byName = service.findByName("Name1");
            Assert.assertNotNull(byName);
        }
    }

    @Test
    public void findUserByNameNoCache() throws Exception {
        for (int i = 0; i < 5000; i++) {
            Set<User> byName = service.findByNameNoCache("Name1");
            Assert.assertNotNull(byName);
        }
    }

    @Test
    public void searchUser() throws Exception {
        for (int i = 0; i < 5000; i++) {
            Set<User> byName = service.searchByName("Name");
            Assert.assertNotNull(byName);
        }
    }

    @Test
    public void searchUserNoCache() throws Exception {
        for (int i = 0; i < 5000; i++) {
            Set<User> byName = service.searchNoCache("Name");
            Assert.assertNotNull(byName);
        }
    }
}
