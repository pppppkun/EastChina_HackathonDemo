package com.hackathon.backend.dao;

import com.hackathon.backend.entity.UserEntity;
import org.springframework.stereotype.Repository;


@Repository
public interface UserMapper {

    UserEntity Sel_by_username(int id);
    int insert(UserEntity newuser);

}
