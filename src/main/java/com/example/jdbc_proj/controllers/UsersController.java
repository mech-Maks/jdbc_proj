package com.example.jdbc_proj.controllers;

import com.example.jdbc_proj.dao.UserDao;
import com.example.jdbc_proj.dao.UserDaoImpl;
import com.example.jdbc_proj.model.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class UsersController {
    private final Logger logger = LoggerFactory.getLogger(UsersController.class);

    private final UserDao userDao;

    public UsersController(JdbcTemplate jdbcTemplate) {
        this.userDao = new UserDaoImpl(jdbcTemplate);
    }

    @PostMapping(value = "/addUser", consumes = "application/json", produces = "application/json")
    public UserDto addNewUser(@RequestBody UserDto user) {
        logger.info("added new user");
        return null;
    }

    @GetMapping(value = "/getUsers", produces = "application/json")
    public List<UserDto> getUsers() {
        logger.info("requested users List");
        return userDao.getUsers();
    }

    @GetMapping(value = "/getUserById/{id}", produces = "application/json")
    public UserDto getUserById(@PathVariable long id) {
        logger.info("requested user with id: " + id);
        return userDao.getUserByID(id);
    }
}
