package com.panther.model.VO;

import lombok.Data;

import java.util.List;

/**
 * @author Gin 琴酒
 * @data 2023/3/19 22:09
 */
@Data
public class updateRoleVo {

    private List<addMenuVo> menus;

    private Long checkedKeys;
}
