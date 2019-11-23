package com.hackathon.backend.service;


import com.hackathon.backend.entity.UserEntity;
import com.hackathon.backend.form.UserForm;

public interface UserService {

    public UserEntity registerUser(UserForm userForm);

    public void modifyPassword(int id, String Old, String New);

    public UserEntity getUser(int id);

    public UserEntity getUser(String username);

    public void modify(UserForm userForm);

}
