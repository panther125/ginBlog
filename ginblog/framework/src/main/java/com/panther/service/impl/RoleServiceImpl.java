package com.panther.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.panther.common.SystemConstants;
import com.panther.mapper.MenuMapper;
import com.panther.mapper.RoleMapper;
import com.panther.model.DTO.RoleStatusDTO;
import com.panther.model.VO.RoleListVo;
import com.panther.model.VO.RoleVo;
import com.panther.model.VO.addMenuVo;
import com.panther.model.entity.Menu;
import com.panther.model.entity.Role;
import com.panther.service.RoleService;
import com.panther.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Gin 琴酒
 * @data 2023/3/16 23:12
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Resource
    private MenuMapper menuMapper;

    @Override
    public List<String> selectRoleKeyByUserId(Long id) {
        //判断是否是管理员 如果是返回集合中只需要有admin
        if(id == 1L){
            List<String> roleKeys = new ArrayList<>();
            roleKeys.add("admin");
            return roleKeys;
        }
        //否则查询用户所具有的角色信息
        return baseMapper.selectRoleKeyByUserId(id);
    }

    @Override
    public RoleListVo listVO(int pageNum, int pageSize) {
        // 查询状态正常的角色
        LambdaQueryWrapper<Role> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Role::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        Page<Role> page = new Page<>(pageNum,pageSize);
        page(page,wrapper);
        // 封装成Vo
        List<RoleVo> roleVos = BeanCopyUtils.copyBeanList(page.getRecords(), RoleVo.class);

        RoleListVo roleListVo = new RoleListVo();
        roleListVo.setRows(roleVos);
        roleListVo.setTotal(page.getTotal());
        return roleListVo;
    }

    @Override
    public void updateStatus(RoleStatusDTO roleDTO) {
        baseMapper.updateStatus(roleDTO.getRoleId(),roleDTO.getStatus());
    }

    @Override
    public List<addMenuVo> treeselect() {
        // 查询所有的目录
        List<Menu> menus = menuMapper.selectList(null);
        List<addMenuVo> addMenuVos = new ArrayList<>();
        menus.stream().forEach(item ->{
            addMenuVo addMenuVo = new addMenuVo();
            addMenuVo.setId(item.getId());
            addMenuVo.setLabel(item.getMenuName());
            addMenuVo.setParentId(item.getParentId());
            addMenuVos.add(addMenuVo);
        });
        // 构建出树形结构响应给前端
        List<addMenuVo> treeMenu = buildTree(addMenuVos,0L);
        return treeMenu;
    }

    private List<addMenuVo> buildTree(List<addMenuVo> menus, Long i) {
        return menus.stream()
                .filter(m -> m.getParentId().equals(i))
                .map(item -> {
                    item.setChildren(setChilden(menus, item));
                    return item;
                })
                .collect(Collectors.toList());
    }

    private List<addMenuVo> setChilden(List<addMenuVo> menus, addMenuVo item) {
        return menus.stream()
                .filter(m -> m.getParentId().equals(item.getId()))
                .map(m-> {
                    m.setChildren(setChilden(menus,m));
                    return m;
                })
                .collect(Collectors.toList());
    }
}
