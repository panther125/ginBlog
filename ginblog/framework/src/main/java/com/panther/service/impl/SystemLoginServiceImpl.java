package com.panther.service.impl;

import com.panther.model.entity.LoginUser;
import com.panther.service.LoginService;
import com.panther.utils.JwtUtil;
import com.panther.utils.RedisCache;
import com.panther.utils.SecurityUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;
import com.panther.common.ResponseResult;
import com.panther.model.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author Gin 琴酒
 * @data 2023/3/17 22:56
 */
@Service
public class SystemLoginServiceImpl implements LoginService  {

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private RedisCache redisCache;

    @Override
    public ResponseResult login(User user) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        //判断是否认证通过
        if(Objects.isNull(authenticate)){
            throw new RuntimeException("用户名或密码错误");
        }
        //获取userid 生成token
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);
        //把用户信息存入redis
        redisCache.setCacheObject("blogLogin:"+userId,loginUser);

        //把token封装 返回
        Map<String,String> map = new HashMap<>();
        map.put("token",jwt);
        return ResponseResult.okResult(map);
    }

    @Override
    public ResponseResult logout() {
        //获取当前登录的用户id
        Long userId = SecurityUtils.getUserId();
        //删除redis中对应的值
        redisCache.deleteObject("blogLogin:"+userId);
        return ResponseResult.okResult();
    }
}
