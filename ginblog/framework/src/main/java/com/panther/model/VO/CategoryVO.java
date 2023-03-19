package com.panther.model.VO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Gin 琴酒
 * @data 2023/3/14 20:02
 */
@Data
@ApiModel(description = "分类列表")
public class CategoryVO {

    /**
     * 分类id
     */
    @ApiModelProperty(value = "分类id")
    private Long id;

    /**
     * 分类名
     */
    @ApiModelProperty(value = "分类名")
    private String name;

    /**
     * 文章数量
     */
    @ApiModelProperty(value = "文章数量")
    private Integer articleCount;

    private String description;

    private String status;
}
