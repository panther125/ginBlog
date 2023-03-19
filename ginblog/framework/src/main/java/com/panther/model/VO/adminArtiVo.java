package com.panther.model.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Gin 琴酒
 * @data 2023/3/18 22:27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class adminArtiVo {
    private Long id;
    private Long categoryId;
    private String content;
    //标题
    private String title;
    //文章摘要
    private String summary;
    //是否允许评论 1是，0否
    private String isComment;
    //是否置顶（0否，1是）
    private String isTop;
    //状态（0已发布，1草稿）
    private String status;
    //缩略图
    private String thumbnail;

    //访问量
    private Long viewCount;

    private Date createTime;
}
