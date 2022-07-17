package com.example.jdbc_proj.model;

public class UserDto {
    private String name;
    private String email;
    private int age;
    private long id;

    public UserDto(String name, String email, int age, long id) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.id = id;
    }

    public UserDto() {};

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(long id) {
        this.id = id;
    }
}
