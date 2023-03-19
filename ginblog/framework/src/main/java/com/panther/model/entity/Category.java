package com.panther.model.entity;

import java.io.Serializable;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* 分类表
* @TableName category
*/
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("Category")
public class Category implements Serializable {

    @TableId
    private Long id;
    /**
    * 分类名
    */
    private String name;
    /**
    * 父分类id，如果没有父分类为-1
    */
    private Long pid;
    /**
    * 描述
    */
    private String description;
    /**
    * 状态0:正常,1禁用
    */
    @ApiModelProperty("状态0:正常,1禁用")
    private String status;

    private Long createBy;

    @ApiModelProperty("")
    private Date createTime;

    @ApiModelProperty("")
    private Long updateBy;

    @ApiModelProperty("")
    private Date updateTime;
    /**
    * 删除标志（0代表未删除，1代表已删除）
    */
    @ApiModelProperty("删除标志（0代表未删除，1代表已删除）")
    private Integer delFlag;
}
