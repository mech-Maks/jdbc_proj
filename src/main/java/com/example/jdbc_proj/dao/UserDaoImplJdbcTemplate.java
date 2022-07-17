package com.example.jdbc_proj.dao;

import com.example.jdbc_proj.model.UserDto;
import com.example.jdbc_proj.util.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class UserDaoImplJdbcTemplate implements UserDao {
    private final Logger logger = LoggerFactory.getLogger(UserDaoImplJdbcTemplate.class);

    private JdbcTemplate jdbcTemplate;
    private long idCounter;

    public UserDaoImplJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.idCounter =  findLastId();
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
        String sqlStatement = "insert into users (name, email, age, id) VALUES (?, ?, ?, ?); COMMIT;";

        try {
            jdbcTemplate.update(sqlStatement,
                    new Object[]{ user.getName(), user.getEmail(), user.getAge(), id});
        } catch (DataAccessException ex) {
            throw new RuntimeException(ex);
        }

        logger.info(new UserDto(user.getName(), user.getEmail(), user.getAge(), id).toString());
    }

    private long findLastId() {
        String sqlStatement = "select * from (select * from users order by id desc) as kek limit 1;";
        UserDto user = jdbcTemplate.queryForObject(sqlStatement, new UserMapper());

        return user.getId();
    }
}
