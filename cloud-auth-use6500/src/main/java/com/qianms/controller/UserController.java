package com.qianms.controller;

import com.auth0.jwt.JWT;
import com.qianms.common.Result;
import com.qianms.utils.JWTUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户控制层
 */
@RestController
@RequestMapping("user")
public class UserController {
    /**
     * 登陆功能
     * @param username 用户名
     * @param password 密码
     */
    @PostMapping("login")
    public Result login(String username, String password){
        //1.验证用户名密码

        if ("admin".equals(username)&&"admin".equals(password)){
            //2.生成令牌
            String token = JWTUtils.token();
            return  Result.builder().code(200).msg("succes").token(token).build();
        }else {
            return  Result.builder().code(500).msg("erro").build();
        }

    }
}
