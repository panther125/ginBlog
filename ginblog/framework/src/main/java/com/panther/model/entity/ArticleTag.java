package com.panther.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Gin 琴酒
 * @data 2023/3/18 17:29
 */
@TableName(value="article_tag")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleTag implements Serializable {
    private static final long serialVersionUID = 625337492348897098L;

    /**
     * 文章id
     */
    @TableId
    private Long articleId;
    /**
     * 标签id
     */
    private Long tagId;
}
