package com.panther.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.panther.common.ResponseResult;
import com.panther.model.DTO.UserDTO;
import com.panther.model.VO.UserListVo;
import com.panther.model.VO.UserVo;
import com.panther.model.entity.User;

/**
 * @author Gin 琴酒
 * @data 2023/3/14 23:23
 */
public interface UserService extends IService<User> {

    ResponseResult userInfo();

    ResponseResult updateUserInfo(User user);

    ResponseResult register(User user);

    UserVo selectUserAndRoles(Long id);

    UserListVo Userlist(int pageNum, int pageSize);

    void updateUserAndRole(UserDTO userDTO);
}
