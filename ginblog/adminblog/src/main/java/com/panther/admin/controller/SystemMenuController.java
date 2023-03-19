package com.panther.admin.controller;

import com.panther.common.ResponseResult;
import com.panther.model.entity.Menu;
import com.panther.service.MenuService;
import com.panther.service.RoleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Gin 琴酒
 * @data 2023/3/19 17:10
 */
@RestController
@RequestMapping("/system/menu")
public class SystemMenuController {

    @Resource
    private MenuService menuService;

    @Resource
    private RoleService roleService;

    @GetMapping("/list")
    public ResponseResult list(){
        return ResponseResult.okResult(menuService.listMenu());
    }

    @PostMapping
    public ResponseResult add(@RequestBody Menu menu){
        menuService.save(menu);
        return ResponseResult.okResult();
    }

    @GetMapping("/{id}")
    public ResponseResult getMenu(@PathVariable Long id){
        return ResponseResult.okResult(menuService.getById(id));
    }

    @PutMapping
    public ResponseResult update(@RequestBody Menu menu){
        // Long 类型不能使用== 判断相等
        if(menu.getParentId().equals(menu.getId())){
            return ResponseResult.errorResult(500,"修改菜单"+menu.getMenuName()+"失败，上级菜单不能选择自己");
        }
        menuService.updateById(menu);
        return ResponseResult.okResult();
    }

    @DeleteMapping("/{id}")
    public ResponseResult delete(@PathVariable Long id){
        return ResponseResult.okResult(menuService.removeById(id));
    }

    @GetMapping("/roleMenuTreeselect/{id}")
    public ResponseResult roleMenuTreeselect(){
        return ResponseResult.okResult(roleService.treeselect());
    }
}
