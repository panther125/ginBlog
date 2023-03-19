package com.panther.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.panther.model.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.util.List;
public interface RoleMapper extends BaseMapper<Role> {

    List<String> selectRoleKeyByUserId(Long userId);

    @Update("UPDATE sys_role set STATUS = #{status} where id = #{roleId}")
    void updateStatus(String roleId,String status);
}
