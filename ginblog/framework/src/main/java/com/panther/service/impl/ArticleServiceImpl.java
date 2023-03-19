package com.panther.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.panther.mapper.ArticleMapper;
import com.panther.mapper.ArticleTagMapper;
import com.panther.mapper.CategoryMapper;
import com.panther.model.DTO.AddArticleDto;
import com.panther.model.VO.*;
import com.panther.model.entity.Article;
import com.panther.model.entity.ArticleTag;
import com.panther.model.entity.Category;
import com.panther.service.ArticleService;
import com.panther.service.ArticleTagService;
import com.panther.utils.BeanCopyUtils;
import com.panther.common.ResponseResult;
import com.panther.common.SystemConstants;
import com.panther.utils.RedisCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


/**
 * @author Gin 琴酒
 * @data 2023/3/13 23:24
 */
@Service
@Slf4j
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Resource
    private CategoryMapper categoryMapper;

    @Resource
    private ArticleTagService articleTagService;

    @Resource
    private RedisCache redisCache;

    @Override
    public ResponseResult hotArticleList() {
        //查询热门文章 封装成ResponseResult返回
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        //必须是正式文章
        queryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        //按照浏览量进行排序
        queryWrapper.orderByDesc(Article::getViewCount);
        //最多只查询10条
        Page<Article> page = new Page(1,10);
        page(page,queryWrapper);

        List<Article> articles = page.getRecords();
        //bean拷贝
        List<HotArticleVo> articleVos = new ArrayList<>();
        articles.stream().forEach(t -> {
            HotArticleVo vo = new HotArticleVo();
            BeanUtils.copyProperties(t, vo);
            articleVos.add(vo);
        });
        return ResponseResult.okResult(articleVos);
    }

    @Override
    public List<Long> queryCategory() {
        return baseMapper.queryCategory();
    }

    @Override
    public ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId) {
        //查询条件
        LambdaQueryWrapper<Article> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 如果 有categoryId 就要 查询时要和传入的相同
        lambdaQueryWrapper.eq(Objects.nonNull(categoryId) && categoryId > 0 ,Article::getCategoryId,categoryId);
        // 状态是正式发布的
        lambdaQueryWrapper.eq(Article::getStatus,SystemConstants.ARTICLE_STATUS_NORMAL);
        // 对isTop进行降序
        lambdaQueryWrapper.orderByDesc(Article::getIsTop);

        // 分页查询
        Page<Article> page = new Page<>(pageNum,pageSize);
        page(page,lambdaQueryWrapper);

        List<Article> articles = page.getRecords();
        //查询categoryName
        articles.stream()
                .map(article -> {
                    article.setCategoryName(categoryMapper.selectById(article.getCategoryId()).getName());
                    return article;
                })
                .collect(Collectors.toList());
        //封装查询结果
        List<ArticleListVo> articleListVos = BeanCopyUtils.copyBeanList(page.getRecords(), ArticleListVo.class);
        PageVo pageVo = new PageVo(articleListVos,page.getTotal());
        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult getArticleDetail(Long id) {
        //根据id查询文章
        Article article = getById(id);
        //从redis中获取viewCount
        Integer viewCount = redisCache.getCacheMapValue("article:viewCount", id.toString());
        //转换成VO
        ArticleDetailVo articleDetailVo = BeanCopyUtils.copyBean(article, ArticleDetailVo.class);
        articleDetailVo.setViewCount(viewCount.longValue());
        //根据分类id查询分类名
        Long categoryId = articleDetailVo.getCategoryId();
        Category category = categoryMapper.selectById(categoryId);
        if(category!=null){
            articleDetailVo.setCategoryName(category.getName());
        }
        //封装响应返回
        return ResponseResult.okResult(articleDetailVo);
    }

    @Override
    public ResponseResult updateViewCount(Long id) {
        //更新redis中对应 id的浏览量
        redisCache.incrementCacheMapValue("article:viewCount",id.toString(),1);
        return ResponseResult.okResult();
    }

    @Override
    @Transactional
    public ResponseResult add(AddArticleDto articleDto) {
        //添加 博客
        Article article = BeanCopyUtils.copyBean(articleDto, Article.class);
        save(article);

        List<ArticleTag> articleTags = articleDto.getTags().stream()
                .map(tagId -> new ArticleTag(article.getId(), tagId))
                .collect(Collectors.toList());

        //添加 博客和标签的关联
        articleTagService.saveBatch(articleTags);
        return ResponseResult.okResult();
    }

    @Override
    public adminArtiListVo adminList(int pageNum, int pageSize) {
        LambdaQueryWrapper<Article> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Article::getStatus, SystemConstants.STATUS_NORMAL);
        Page<Article> page = new Page<>(pageNum,pageSize);
        page(page,wrapper);
        List<adminArtiVo> adminArtiVos = BeanCopyUtils.copyBeanList(page.getRecords(), adminArtiVo.class);
        adminArtiListVo adminArtiListVo = new adminArtiListVo(adminArtiVos,page.getTotal());
        return adminArtiListVo;
    }

    @Resource
    private ArticleTagMapper articleTagMapper;
    @Override
    public ArticleVo queryArticle(Long id) {
        // 根据ID查询Article
        Article article = baseMapper.selectById(id);
        ArticleVo articleVo = BeanCopyUtils.copyBean(article, ArticleVo.class);
        // 查询对应的Tags
        LambdaQueryWrapper<ArticleTag> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ArticleTag::getArticleId,id);
        List<ArticleTag> articleTags = articleTagMapper.selectList(wrapper);
        List<Long> collect = articleTags.stream()
                .map(ArticleTag::getTagId)
                .collect(Collectors.toList());
        // 封装成VO响应
        articleVo.setTags(collect);
        return articleVo;
    }

    @Override
    public void updateArticelAndTag(ArticleVo articleVo) {
        Article article = BeanCopyUtils.copyBean(articleVo, Article.class);
        baseMapper.updateById(article);
        List<Long> tags = articleVo.getTags();
        List<ArticleTag> collect = tags.stream().map(item -> new ArticleTag(articleVo.getId(), item))
                .collect(Collectors.toList());
        //articleTagService.updateBatchById(collect);
        articleTagService.removeBatchByIds(collect);
        articleTagService.saveBatch(collect);
    }
}
