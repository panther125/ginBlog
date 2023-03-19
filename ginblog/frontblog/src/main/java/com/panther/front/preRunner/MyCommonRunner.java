package com.panther.front.preRunner;

import com.panther.mapper.ArticleMapper;
import com.panther.model.entity.Article;
import com.panther.utils.RedisCache;
import org.springframework.boot.CommandLineRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * CommandLineRunner在Spring初始化所有的Bean后开始执行
 *
 * @author Gin 琴酒
 * @data 2023/3/15 21:27
 */
public class MyCommonRunner implements CommandLineRunner {
    @Resource
    private ArticleMapper articleMapper;
    @Resource
    private RedisCache redisCache;

    @Override
    public void run(String... args) throws Exception {
        //查询博客信息  id  viewCount 实现Redis的预部署
        List<Article> articles = articleMapper.selectList(null);
        Map<String, Integer> viewCountMap = articles.stream()
                .collect(Collectors.toMap(article -> article.getId().toString(), article -> {
                    return article.getViewCount().intValue();//
                }));
        //存储到redis中
        redisCache.setCacheMap("article:viewCount",viewCountMap);
    }
}
