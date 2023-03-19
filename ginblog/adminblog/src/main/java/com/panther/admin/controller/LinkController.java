package com.panther.admin.controller;

import com.panther.common.ResponseResult;
import com.panther.model.VO.CategoryPageVo;
import com.panther.model.VO.LinkVo;
import com.panther.model.entity.Link;
import com.panther.service.LinkService;
import com.panther.utils.BeanCopyUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Gin 琴酒
 * @data 2023/3/19 21:43
 */
@RequestMapping("/content/link")
@RestController
public class LinkController {
    @Resource
    private LinkService linkService;

    @GetMapping("/list")
    public ResponseResult list(int pageNum, int pageSize){
        return ResponseResult.okResult(linkService.listpageLink(pageNum,pageSize));
    }

    @PostMapping
    public ResponseResult add(@RequestBody Link link){
        linkService.save(link);
        return ResponseResult.okResult();
    }

    @GetMapping("/{id}")
    public ResponseResult getLink(@PathVariable Long id){
        Link link = linkService.getById(id);
        LinkVo linkVo = BeanCopyUtils.copyBean(link, LinkVo.class);
        return ResponseResult.okResult(linkVo);
    }

    @PutMapping
    public ResponseResult add(@RequestBody LinkVo linkVo){
        Link link = BeanCopyUtils.copyBean(linkVo, Link.class);
        linkService.updateById(link);
        return ResponseResult.okResult();
    }

    @DeleteMapping("/{id}")
    public ResponseResult delete(@PathVariable Long id){
        linkService.removeById(id);
        return ResponseResult.okResult();
    }
}
