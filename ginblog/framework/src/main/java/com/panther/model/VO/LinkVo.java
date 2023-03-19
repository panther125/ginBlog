package com.panther.model.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Gin 琴酒
 * @data 2023/3/14 22:18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LinkVo {

    private Integer id;
    private String name;
    private String logo;
    private String description;
    private String address;

    private int status;

}
