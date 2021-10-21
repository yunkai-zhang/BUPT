package com.zhangyun.shirospringboot.config;

import com.zhangyun.shirospringboot.pojo.User;
import com.zhangyun.shirospringboot.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends AuthorizingRealm {

    //从spring容器中获取userService实例,从而可以操纵数据库
    //!!!spring注入的是实现类对象，接收的接口；理解为多态
    @Autowired
    UserService userService;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("执行了=》授权doGetAuthorizationInfo");

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //注意此行要删掉，不然任何人都有add权限
        //info.addStringPermission("user:add");

        //拿到等钱登录的对象subject
        Subject subject = SecurityUtils.getSubject();
        //拿到user对象
        User currentUser = (User)subject.getPrincipal();
        //设置当前用户的权限
        info.addStringPermission(currentUser.getPerms());

        //如果return null的话，授权不能被shiro接收到
        return info;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("执行了=》认证doGetAuthenticationInfo");

        //UsernamePasswordToken和controller中login方法用的token相同
        UsernamePasswordToken userToken = (UsernamePasswordToken) token;

        //用户名 密码，数据库中取
        User user = userService.queryUserByName(userToken.getUsername());

        //用户名认证
        if(user==null){//用户不存在
            return null; //导致MyController出现UnknownAccountException异常
        }
        //密码认证，shiro做。不让人接触密码，避免泄露。下行代码中，左右的principal和realmName先空着。
        //密码可以加密：MD5 或者 MD5盐值加密
        return new SimpleAuthenticationInfo(user,user.getPwd(),"");
    }
}
