package com.panther.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.panther.common.ResponseResult;
import com.panther.common.SystemConstants;
import com.panther.mapper.LinkMapper;
import com.panther.model.VO.CategoryPageVo;
import com.panther.model.VO.LinkPageVo;
import com.panther.model.VO.LinkVo;
import com.panther.model.entity.Link;
import com.panther.service.LinkService;
import com.panther.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Gin 琴酒
 * @data 2023/3/14 22:16
 */
@Service
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link> implements LinkService {

    @Override
    public ResponseResult getAllLink() {
        //查询所有审核通过的
        LambdaQueryWrapper<Link> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Link::getStatus, SystemConstants.LINK_STATUS_NORMAL);
        List<Link> links = list(queryWrapper);
        //转换成vo
        List<LinkVo> linkVos = BeanCopyUtils.copyBeanList(links, LinkVo.class);
        //封装返回
        return ResponseResult.okResult(linkVos);
    }

    @Override
    public LinkPageVo listpageLink(int pageNum, int pageSize) {
        LambdaQueryWrapper<Link> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Link::getStatus, SystemConstants.LINK_STATUS_NORMAL);

        Page<Link> linkPage = new Page<>();
        page(linkPage,queryWrapper);
        List<Link> records = linkPage.getRecords();
        List<LinkVo> LinkVos = BeanCopyUtils.copyBeanList(records, LinkVo.class);

        return new LinkPageVo(LinkVos,linkPage.getTotal());
    }
}
