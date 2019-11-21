package com.hackathon.backend.form;

import lombok.Data;


@Data
public class UserForm {


    private Integer id;
    private String username;
    private String password;
    private String realname;
    private String sex;
    private String department;
    private Double balance;
    private String description;
    private String url;//touxiang lianjie

}
