package com.panther.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.panther.model.VO.CategoryPageVo;
import com.panther.model.VO.CategoryVO;
import com.panther.model.VO.adminArtiListVo;
import com.panther.model.entity.Category;
import com.panther.common.ResponseResult;

import java.util.List;

public interface CategoryService extends IService<Category> {


    ResponseResult getCategoryList();

    List<CategoryVO> listAllCategory();

    CategoryPageVo listpageCategory(int pageNum, int pageSize);
}
