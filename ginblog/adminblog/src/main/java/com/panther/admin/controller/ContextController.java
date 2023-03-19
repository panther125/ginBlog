package com.panther.admin.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.panther.common.ResponseResult;
import com.panther.enums.AppHttpCodeEnum;
import com.panther.model.VO.CategoryPageVo;
import com.panther.model.VO.CategoryVO;
import com.panther.model.VO.ExcelCategoryVo;
import com.panther.model.entity.Category;
import com.panther.service.CategoryService;
import com.panther.utils.BeanCopyUtils;
import com.panther.utils.WebUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Gin 琴酒
 * @data 2023/3/18 17:12
 */
@RequestMapping("/content/category")
@RestController
public class ContextController {

    @Resource
    private CategoryService categoryService;

    @GetMapping("/listAllCategory")
    public ResponseResult listAllCategory(){
        List<CategoryVO> list = categoryService.listAllCategory();
        return ResponseResult.okResult(list);
    }
    @PreAuthorize("@ps.hasPermission('content:category:export')")
    @GetMapping("/export")
    public void export(HttpServletResponse response){
        try {
            //设置下载文件的请求头
            WebUtils.setDownLoadHeader("分类.xlsx",response);
            //获取需要导出的数据
            List<Category> categoryVos = categoryService.list();

            List<ExcelCategoryVo> excelCategoryVos = BeanCopyUtils.copyBeanList(categoryVos, ExcelCategoryVo.class);
            //把数据写入到Excel中
            // 这里需要设置不关闭流
            EasyExcel.write(response.getOutputStream(), ExcelCategoryVo.class).autoCloseStream(Boolean.FALSE).sheet("分类导出")
                    .doWrite(excelCategoryVos);

        } catch (Exception e) {
            //如果出现异常也要响应json
            ResponseResult result = ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);
            WebUtils.renderString(response, JSON.toJSONString(result));
        }
    }

    @GetMapping("/list")
    public ResponseResult list(int pageNum,int pageSize){
        CategoryPageVo list = categoryService.listpageCategory(pageNum,pageSize);
        return ResponseResult.okResult(list);
    }

    @PostMapping
    public ResponseResult add(@RequestBody Category category){
        categoryService.save(category);
        return ResponseResult.okResult();
    }

    @GetMapping("/{id}")
    public ResponseResult getCategory(@PathVariable Long id){
        Category category = categoryService.getById(id);
        CategoryVO categoryVO = BeanCopyUtils.copyBean(category, CategoryVO.class);
        return ResponseResult.okResult(categoryVO);
    }

    @PutMapping
    public ResponseResult getCategory(@RequestBody Category category){
         categoryService.updateById(category);
        return ResponseResult.okResult();
    }

    @DeleteMapping("/{id}")
    public ResponseResult delete(@PathVariable Long id){
        categoryService.removeById(id);
        return ResponseResult.okResult();
    }
}
