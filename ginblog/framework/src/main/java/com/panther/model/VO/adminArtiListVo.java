package com.panther.model.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Gin 琴酒
 * @data 2023/3/18 22:30
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class adminArtiListVo {
    List<adminArtiVo> rows;

    Long total;
}
