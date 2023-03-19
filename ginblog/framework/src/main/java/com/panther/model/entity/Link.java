package com.panther.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Gin 琴酒
 * @data 2023/3/14 22:12
 */
@TableName("link")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Link {
    @TableId
    private Integer id;
    private String name;
    private String logo;
    private String description;
    private String address;
    private int status;
    private Long createBy;

    private Date createTime;

    private Long updateBy;

    private Date updateTime;
    //删除标志（0代表未删除，1代表已删除）
    private Integer delFlag;

}
