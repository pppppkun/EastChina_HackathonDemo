package com.hackathon.backend.controller;


import com.hackathon.backend.constant.ServerException;
import com.hackathon.backend.constant.SimpleResponse;
import com.hackathon.backend.entity.UserEntity;
import com.hackathon.backend.form.AuthForm;
import com.hackathon.backend.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/session")
public class SessionController {


    @Autowired
    UserService userService;

    /**
     * 用户登录
     * @param session
     * @param authForm
     * @return
     */
    @ApiOperation(value = "登录", response = UserEntity.class ,
    notes = "返回SimpleResponse对象，如果登录成功，SimpleResponse对象是User")
    @GetMapping("login/{username}&{password}")
    public SimpleResponse login(@ApiIgnore HttpSession session, @PathVariable("username") String username,
                                @PathVariable("password") String password)
    {
        AuthForm authForm = new AuthForm();authForm.setPassword(password);authForm.setUsername(username);
        UserEntity userEntity = new UserEntity();
        try
        {
            userEntity = userService.getUser(authForm.getUsername());
            if(!userEntity.getPassword().equals(authForm.getPassword()))
            {
                return SimpleResponse.error("wrong password");
            }
        }
        catch (ServerException sec)
        {
            return SimpleResponse.error(sec.getMessage());
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return SimpleResponse.error("Server login error");
        }
        session.setAttribute("user",userEntity);
        return SimpleResponse.ok(userEntity);

    }

    //TODO 退出登录

}
