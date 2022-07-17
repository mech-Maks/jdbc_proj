package com.example.jdbc_proj.dao;

import com.example.jdbc_proj.model.UserDto;

import java.util.List;

public interface UserDao {

    long addUser(UserDto user);

    List<UserDto> getUsers();

    UserDto getUserByID(long id);
}
