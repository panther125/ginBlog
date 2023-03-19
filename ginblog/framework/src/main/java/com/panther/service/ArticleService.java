package com.panther.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.panther.model.DTO.AddArticleDto;
import com.panther.model.VO.ArticleVo;
import com.panther.model.VO.adminArtiListVo;
import com.panther.model.entity.Article;
import com.panther.common.ResponseResult;

import java.util.List;

public interface ArticleService extends IService<Article> {
    ResponseResult hotArticleList();

    List<Long> queryCategory();

    ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId);

    ResponseResult getArticleDetail(Long id);

    ResponseResult updateViewCount(Long id);

    ResponseResult add(AddArticleDto article);

    adminArtiListVo adminList(int pageNum, int pageSize);

    ArticleVo queryArticle(Long id);

    void updateArticelAndTag(ArticleVo articleVo);
}
