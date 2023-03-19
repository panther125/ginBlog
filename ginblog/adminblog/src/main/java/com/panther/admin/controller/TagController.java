package com.panther.admin.controller;

import com.panther.common.ResponseResult;
import com.panther.model.DTO.TagListDto;
import com.panther.model.VO.PageVo;
import com.panther.model.VO.TagVo;
import com.panther.model.entity.Tag;
import com.panther.service.TagService;
import com.panther.utils.SecurityUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author Gin 琴酒
 * @data 2023/3/18 16:23
 */
@RestController
@RequestMapping("/content/tag")
public class TagController {
    @Resource
    private TagService tagService;

    @GetMapping("/list")
    public ResponseResult<PageVo> list(Integer pageNum, Integer pageSize, TagListDto tagListDto){
        return tagService.pageTagList(pageNum,pageSize,tagListDto);
    }
    @GetMapping("/{id}")
    public ResponseResult getTage(@PathVariable Long id){
        return ResponseResult.okResult(tagService.getById(id));
    }

    @PutMapping
    public ResponseResult update(@RequestBody Tag tag){
        tagService.updateById(tag);
        return ResponseResult.okResult();
    }

    @PostMapping
    public ResponseResult addTag(@RequestBody TagListDto tagListDto){
        Tag tag = new Tag();
        tag.setName(tagListDto.getName());
        tag.setRemark(tagListDto.getRemark());
        tag.setCreateBy(SecurityUtils.getUserId());
        tag.setCreateTime(new Date());
        tagService.save(tag);
        return ResponseResult.okResult();
    }

    @DeleteMapping("/{id}")
    public ResponseResult addtag(@PathVariable Long id){
        tagService.removeById(id);
        return ResponseResult.okResult();
    }

    @GetMapping("/listAllTag")
    public ResponseResult listAllTag(){
        List<TagVo> list = tagService.listAllTag();
        return ResponseResult.okResult(list);
    }
}
