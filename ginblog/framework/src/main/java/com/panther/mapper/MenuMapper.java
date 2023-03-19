package com.panther.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.panther.model.entity.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

public interface MenuMapper extends BaseMapper<Menu> {
    List<String> selectPermsByUserId(Long userId);

    List<Menu> selectAllRouterMenu();

    List<Menu> selectRouterMenuTreeByUserId(Long userId);
}
