package com.cping.project.config;

import com.cping.project.bean.User;
import com.cping.project.dao.UserDao;
import org.apache.catalina.security.SecurityUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

public class UserRealm extends AuthorizingRealm {
    @Autowired
    UserDao userDao;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        /*System.out.println("执行了==>授权doGetAuthorizationInfo");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        //current login user object
        Subject subject = SecurityUtils.getSubject();
        User currentUser = (User) subject.getPrincipals();
        String role = currentUser.getRole();
        Set<String> set = new HashSet<>();
        set.add(role);
        info.setRoles(set);

        return info;*/
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行了==>授权doGetAuthenticationInfo");
        UsernamePasswordToken userToken = (UsernamePasswordToken) authenticationToken;

        /*if (!userToken.getUsername().equals(username)) {
            return null;    //抛出异常  UnknownAccountException
        }*/

        //连接真实的数据库
        User userName = userDao.getUserByMassage(userToken.getUsername());
        if (userName == null) { //没有这个人
            return null;    // UnknownAccountException
        }

        //密码认证 shiro做... 而且加密了
        return new SimpleAuthenticationInfo(userName, userToken.getPassword(), "");
    }
}
