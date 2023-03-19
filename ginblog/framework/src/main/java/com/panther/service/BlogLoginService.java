package com.panther.service;

import com.panther.common.ResponseResult;
import com.panther.model.entity.User;

public interface BlogLoginService {
    ResponseResult login(User user);

    ResponseResult logout();
}
