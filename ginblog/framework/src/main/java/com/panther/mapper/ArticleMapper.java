package com.panther.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.panther.model.entity.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Gin 琴酒
 * @data 2023/3/13 23:23
 */
public interface ArticleMapper extends BaseMapper<Article> {
    @Select("SELECT DISTINCT category_id FROM `article` where `status` = 0")
    List<Long> queryCategory();
}
