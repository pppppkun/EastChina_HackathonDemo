package com.hackathon.backend.entity;

import lombok.Data;

@Data
public class UserEntity {

    private Integer id;
    private String username;
    private String password;
    private String realname;
    private String sex;
    private String department;
    private Double balance;
    private String description;
    private String url;//touxiang lianjie

    @Override
    public String toString()
    {
        return "user{" +
                "id=" + id +
                ", username='" +username + '\'' +
                ", sex='" + sex +'\'' +
                ", department='" + department + '\'' +
                ", balance=" + balance +
                ", description='" + description + '\''+
                '}';
    }

}
