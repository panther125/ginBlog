package com.panther.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.panther.model.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Gin 琴酒
 * @data 2023/3/14 22:37
 */
public interface UserMapper extends BaseMapper<User> {
    @Select("select role_id from sys_user_role where user_id = #{uid}")
    List<Long> queryRoleIDByUserId(Long uid);
}
