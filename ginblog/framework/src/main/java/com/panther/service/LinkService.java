package com.panther.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.panther.common.ResponseResult;
import com.panther.model.VO.CategoryPageVo;
import com.panther.model.VO.LinkPageVo;
import com.panther.model.entity.Link;

public interface LinkService extends IService<Link> {

    ResponseResult getAllLink();

    LinkPageVo listpageLink(int pageNum, int pageSize);

}