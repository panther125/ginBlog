package com.panther.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.panther.mapper.ArticleTagMapper;
import com.panther.model.entity.ArticleTag;
import com.panther.service.ArticleTagService;
import org.springframework.stereotype.Service;

/**
 * @author Gin 琴酒
 * @data 2023/3/18 17:33
 */
@Service
public class articleTagServiceImpl extends ServiceImpl<ArticleTagMapper, ArticleTag> implements ArticleTagService {
}
