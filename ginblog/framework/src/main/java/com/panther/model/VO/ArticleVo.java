package com.panther.model.VO;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.panther.model.entity.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author Gin 琴酒
 * @data 2023/3/19 16:22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleVo {

    private Long id;
    //标题
    private String title;
    //文章内容
    private String content;
    private String summary;
    //所属分类id
    private Long categoryId;
    private String thumbnail;
    //是否置顶（0否，1是）
    private String isTop;
    private Long viewCount;
    //是否允许评论 1是，0否
    private String isComment;
    private Long createBy;
    private Date createTime;
    private Long updateBy;
    private Date updateTime;
    private Integer delFlag;
    //状态（0已发布，1草稿）
    private String status;
    private List<Long> tags;
}
