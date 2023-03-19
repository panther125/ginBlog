package com.panther.admin.controller;

import com.panther.common.ResponseResult;
import com.panther.model.DTO.AddArticleDto;
import com.panther.model.VO.ArticleVo;
import com.panther.service.ArticleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Gin 琴酒
 * @data 2023/3/18 17:24
 */
@RestController
@RequestMapping("/content/article")
public class adminArticleController {

    @Resource
    private ArticleService articleService;

    @PostMapping
    public ResponseResult add(@RequestBody AddArticleDto article){
        return articleService.add(article);
    }

    @GetMapping("/list")
    public ResponseResult list(int pageNum,int pageSize){
        return ResponseResult.okResult(articleService.adminList(pageNum,pageSize));
    }

    @GetMapping("/{id}")
    public ResponseResult getArticel(@PathVariable Long id){
        return ResponseResult.okResult(articleService.queryArticle(id));
    }

    @PutMapping
    public ResponseResult update(@RequestBody ArticleVo articleVo){
        articleService.updateArticelAndTag(articleVo);
        return ResponseResult.okResult();
    }

    @DeleteMapping("/{id}")
    public ResponseResult delete(@PathVariable Long id){
        articleService.removeById(id);
        return ResponseResult.okResult();
    }
}
