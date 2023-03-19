package com.panther.model.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Gin 琴酒
 * @data 2023/3/19 17:58
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class addMenuVo {

    List<addMenuVo> children;
    Long id;
    String label;
    Long parentId;
}
