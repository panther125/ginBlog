package com.panther.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.panther.mapper.CategoryMapper;
import com.panther.model.VO.CategoryPageVo;
import com.panther.model.VO.CategoryVO;
import com.panther.model.VO.adminArtiListVo;
import com.panther.model.VO.adminArtiVo;
import com.panther.model.entity.Category;
import com.panther.service.ArticleService;
import com.panther.service.CategoryService;
import com.panther.utils.BeanCopyUtils;
import com.panther.common.ResponseResult;
import com.panther.common.SystemConstants;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Gin 琴酒
 * @data 2023/3/14 18:11
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Resource
    private ArticleService articleService;

    @Override
    public ResponseResult getCategoryList() {
//        //查询文章表  状态为已发布的文章
//        QueryWrapper<Article> articleWrapper = new QueryWrapper<>();
//        // 判断文章状态
//        articleWrapper.eq("status", SystemConstants.ARTICLE_STATUS_NORMAL);
//        // 查询文章的分类id
//        articleWrapper.select("DISTINCT categoryId");
        List<Long> categoryIds = articleService.queryCategory();

//        //获取文章的分类id，并且去重
//        Set<Long> categoryIds = articleList.stream()
//                .map(article -> article.getCategoryId())
//                .collect(Collectors.toSet());

        //查询分类表
        List<Category> categories = listByIds(categoryIds);
        categories = categories.stream().
                filter(category -> SystemConstants.STATUS_NORMAL.equals(category.getStatus()))
                .collect(Collectors.toList());
        //封装vo
        //List<CategoryVO> categoryVos = new ArrayList<>();
//        categories.stream().forEach(
//            category -> {
//                CategoryVO categoryVO = new CategoryVO();
//                categoryVO.setId(category.getId().intValue());
//                categoryVO.setCategoryName(category.getName());
////                categoryVO.setArticleCount();
//                categoryVos.add(categoryVO);
//            }
//        );
        List<CategoryVO> categoryVos = BeanCopyUtils.copyBeanList(categories, CategoryVO.class);

        return ResponseResult.okResult(categoryVos);
    }

    @Override
    public List<CategoryVO> listAllCategory() {
        LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Category::getStatus, SystemConstants.STATUS_NORMAL);
        List<Category> list = list(wrapper);
        List<CategoryVO> categoryVos = BeanCopyUtils.copyBeanList(list, CategoryVO.class);
        return categoryVos;
    }

    @Override
    public CategoryPageVo listpageCategory(int pageNum, int pageSize) {
        LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Category::getStatus, SystemConstants.STATUS_NORMAL);
        Page<Category> categoryPage = new Page<>(pageNum,pageSize);
        page(categoryPage,wrapper);

        List<Category> records = categoryPage.getRecords();
        List<CategoryVO> CategoryPageVos = BeanCopyUtils.copyBeanList(records, CategoryVO.class);

        return new CategoryPageVo(CategoryPageVos,categoryPage.getTotal());
    }
}
