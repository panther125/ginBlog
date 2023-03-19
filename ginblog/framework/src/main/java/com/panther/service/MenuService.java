package com.panther.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.panther.model.VO.MenuVo;
import com.panther.model.entity.Menu;

import java.util.List;

public interface MenuService extends IService<Menu> {
    List<String> selectPermsByUserId(Long id);

    List<Menu> selectRouterMenuTreeByUserId(Long userId);

    List<MenuVo> listMenu();
}
