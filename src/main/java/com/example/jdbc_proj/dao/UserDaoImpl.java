package com.example.jdbc_proj.dao;

import com.example.jdbc_proj.model.UserDto;
import com.example.jdbc_proj.util.UserMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class UserDaoImpl implements UserDao {
    private JdbcTemplate jdbcTemplate;
    private static long idCounter = 0;

    public UserDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public long addUser(UserDto user) {
        addUser(user, ++idCounter);
        return idCounter;
    }

    @Override
    public List<UserDto> getUsers() {
        String sqlStatement = "select * from users";
        return jdbcTemplate.query(sqlStatement, new UserMapper());
    }

    @Override
    public UserDto getUserByID(long id) {
        String sqlStatement = "select * from users where id = ?";
        UserDto user;
        try {
            user = jdbcTemplate.queryForObject(sqlStatement, new UserMapper(), id);
        } catch (DataAccessException ex) {
            System.out.println(ex.getMessage());
            user = null;
        }
        return user;
    }

    private void addUser(UserDto user, long id) {

    }
}
