package com.panther.model.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Gin 琴酒
 * @data 2023/3/13 23:19
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("article")
public class Article  {
    @TableId
    private Long id;
    //标题
    private String title;
    //文章内容
    private String content;
    //文章类型:1 文章 2草稿
    @TableField(exist = false)
    private String type;
    //文章摘要
    private String summary;
    //所属分类id
    private Long categoryId;

    @TableField(exist = false)
    private String categoryName;
    //缩略图
    private String thumbnail;
    //是否置顶（0否，1是）
    private String isTop;
    //状态（0已发布，1草稿）
    private String status;
    //评论数
    @TableField(exist = false)
    private Integer commentCount;
    //访问量
    private Long viewCount;
    //是否允许评论 1是，0否
    private String isComment;

    @TableField(fill = FieldFill.INSERT)
    private Long createBy;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    //删除标志（0代表未删除，1代表已删除）
    private Integer delFlag;

    public Article(Long id, long viewCount) {
        this.id = id;
        this.viewCount = viewCount;
    }

}
