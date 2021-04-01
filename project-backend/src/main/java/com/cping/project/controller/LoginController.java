package com.cping.project.controller;

import com.alibaba.fastjson.JSON;
import com.cping.project.bean.User;
import com.cping.project.bean.vo.LoginVo;
import com.cping.project.common.Result;
import com.cping.project.dao.UserDao;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedHashMap;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class LoginController {

    @Autowired
    UserDao userDao;

    @RequestMapping("/login")
    public Result login(@RequestBody LoginVo loginVo){
        //origin
        User login = userDao.login(loginVo);
        return Result.succ(login);
    }
    /*public Result login(@RequestBody User user) {
        //String flag = "error";
        String username = user.getUsername();
        Subject subject = SecurityUtils.getSubject();
//        subject.getSession().setTimeout(10000);
        UsernamePasswordToken token = new UsernamePasswordToken(username, user.getPassword());
        HashMap<String, Object> res = new HashMap<>();
        try {
            subject.login(token);
            res.put("username", token.getUsername());
            res.put("password", token.getPassword());
            return Result.succ(res);
            *//*String flag = "ok";
            res.put("flag", flag);
            res.put("user", usernamePasswordToken);
            String res_json = JSON.toJSONString(res);
            return res_json;*//*
        } catch (AuthenticationException e) {
            return null;
        }

    }*/

    @RequestMapping("/noauth")
    @ResponseBody
    public String unauthorized(){
        return "未经授权无法访问";
    }
}
