package com.panther.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.panther.common.ResponseResult;
import com.panther.model.DTO.TagListDto;
import com.panther.model.VO.PageVo;
import com.panther.model.VO.TagVo;
import com.panther.model.entity.Tag;

import java.util.List;

public interface TagService extends IService<Tag> {

    ResponseResult<PageVo> pageTagList(Integer pageNum, Integer pageSize, TagListDto tagListDto);

    List<TagVo> listAllTag();
}
