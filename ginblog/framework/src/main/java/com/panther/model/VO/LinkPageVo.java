package com.panther.model.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Gin 琴酒
 * @data 2023/3/19 21:50
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LinkPageVo {

    private List<LinkVo> rows;

    private Long total;
}
