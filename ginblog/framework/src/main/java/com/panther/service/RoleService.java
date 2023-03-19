package com.panther.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.panther.model.DTO.RoleStatusDTO;
import com.panther.model.VO.RoleListVo;
import com.panther.model.VO.addMenuVo;
import com.panther.model.entity.Role;

import java.util.List;

public interface RoleService extends IService<Role> {

    List<String> selectRoleKeyByUserId(Long id);

    RoleListVo listVO(int pageNum, int pageSize);

    void updateStatus(RoleStatusDTO roleDTO);

    List<addMenuVo> treeselect();
}
