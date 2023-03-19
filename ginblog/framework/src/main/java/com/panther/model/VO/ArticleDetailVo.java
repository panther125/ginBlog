package com.panther.model.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Gin 琴酒
 * @data 2023/3/14 22:04
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDetailVo {

    private Long categoryId;
    private String categoryName;
    private String content;
    private Date createTime;
    private Long id;
    private String isComment;
    private Long viewCount;

}
