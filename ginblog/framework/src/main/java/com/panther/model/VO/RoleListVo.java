package com.panther.model.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Gin 琴酒
 * @data 2023/3/19 16:59
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleListVo {
    List<RoleVo> rows;
    Long total;
}
