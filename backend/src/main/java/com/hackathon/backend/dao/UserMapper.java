package com.hackathon.backend.dao;

import com.hackathon.backend.entity.UserEntity;
import org.springframework.stereotype.Repository;


@Repository
public interface UserMapper {

    UserEntity Sel_by_username(String username);
    UserEntity Sel_by_PrimaryKey(Integer id);

    int insert(UserEntity user);
    int modify(UserEntity user);



}
