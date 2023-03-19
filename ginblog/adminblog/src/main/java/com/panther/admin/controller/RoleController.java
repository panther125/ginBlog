package com.panther.admin.controller;

import com.panther.common.ResponseResult;
import com.panther.model.DTO.RoleStatusDTO;
import com.panther.model.VO.addMenuVo;
import com.panther.model.VO.updateRoleVo;
import com.panther.service.RoleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Gin 琴酒
 * @data 2023/3/19 16:57
 */
@RestController
@RequestMapping("/system/role")
public class RoleController {

    @Resource
    private RoleService roleService;

    @GetMapping("/list")
    public ResponseResult list(int pageNum,int pageSize){
        return ResponseResult.okResult(roleService.listVO(pageNum,pageSize));
    }

    @GetMapping("/listAllRole")
    public ResponseResult listAllRole(int pageNum,int pageSize){
        return ResponseResult.okResult(roleService.list());
    }

    @GetMapping("/changeStatus")
    public ResponseResult changeStatus(@RequestBody RoleStatusDTO roleDTO){
        roleService.updateStatus(roleDTO);
        return ResponseResult.okResult();
    }

    @GetMapping("/{id}")
    public ResponseResult getRole(@PathVariable Long id){
        return ResponseResult.okResult();
    }

    @GetMapping("/treeselect")
    public ResponseResult treeselect(){
        List<addMenuVo> treeselect = roleService.treeselect();
        updateRoleVo updateRoleVo = new updateRoleVo();
        updateRoleVo.setMenus(treeselect);
        updateRoleVo.setCheckedKeys(1001L);
        return ResponseResult.okResult(updateRoleVo);
    }
}
