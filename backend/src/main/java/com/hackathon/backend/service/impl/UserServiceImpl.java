package com.hackathon.backend.service.impl;

import com.hackathon.backend.constant.ResponseCode;
import com.hackathon.backend.constant.ServerException;
import com.hackathon.backend.dao.UserMapper;
import com.hackathon.backend.entity.UserEntity;
import com.hackathon.backend.form.UserForm;
import com.hackathon.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public UserEntity registerUser(UserForm userForm)
    {
        UserEntity userEntity = userMapper.Sel_by_username(userForm.getUsername());
        if(userEntity != null)
        {
            throw new ServerException(ResponseCode.Error, "Repeat of username");
        }

        userEntity = new UserEntity();

        userEntity.setUsername(userForm.getUsername());
        userEntity.setPassword(userForm.getPassword());
        userEntity.setRealname(userForm.getRealname());
        userEntity.setBalance(userForm.getBalance());
        userEntity.setDepartment(userForm.getDepartment());
        userEntity.setDescription(userForm.getDescription());
        userEntity.setSex(userForm.getSex());
        userEntity.setUrl(userForm.getUrl());

        userMapper.insert(userEntity);
        return userEntity;
    }

    @Override
    public void modifyPassword(int id, String Old, String New)
    {
        UserEntity userEntity = userMapper.Sel_by_PrimaryKey(id);
        if(!userEntity.getPassword().equals(Old))
        {
            throw new ServerException(ResponseCode.Error, "password error");
        }
        userEntity.setPassword(New);
    }

    @Override
    public UserEntity getUser(int id)
    {
        return userMapper.Sel_by_PrimaryKey(id);
    }

    @Override
    public UserEntity getUser(String username)
    {
        return userMapper.Sel_by_username(username);
    }


    @Override
    public void modify(UserForm userForm)
    {
        UserEntity userEntity = new UserEntity();

        userEntity.setUsername(userForm.getUsername());
        userEntity.setPassword(userForm.getPassword());
        userEntity.setRealname(userForm.getRealname());
        userEntity.setBalance(userForm.getBalance());
        userEntity.setDepartment(userForm.getDepartment());
        userEntity.setDescription(userForm.getDescription());
        userEntity.setSex(userForm.getSex());
        userEntity.setUrl(userForm.getUrl());
        userMapper.modify(userEntity);
    }

}
