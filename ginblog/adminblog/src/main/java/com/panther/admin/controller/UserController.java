package com.panther.admin.controller;

import com.panther.common.ResponseResult;
import com.panther.model.DTO.UserDTO;
import com.panther.model.VO.RoutersVo;
import com.panther.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Gin 琴酒
 * @data 2023/3/19 20:26
 */
@RestController
@RequestMapping("/system/user")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/list")
    public ResponseResult list(int pageNum,int pageSize){
        return ResponseResult.okResult(userService.Userlist(pageNum,pageSize));
    }

    @GetMapping("/{id}")
    public ResponseResult getUser(@PathVariable Long id){
        return ResponseResult.okResult(userService.selectUserAndRoles(id));
    }
    @DeleteMapping("/{id}")
    public ResponseResult delete(@PathVariable Long id){
        return ResponseResult.okResult(userService.removeById(id));
    }

    @PutMapping
    public ResponseResult update(@RequestBody UserDTO userDTO){
        userService.updateUserAndRole(userDTO);
        return ResponseResult.okResult();
    }
}
