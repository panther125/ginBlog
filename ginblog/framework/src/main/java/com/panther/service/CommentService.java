package com.panther.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.panther.common.ResponseResult;
import com.panther.model.entity.Comment;

public interface CommentService extends IService<Comment> {

    ResponseResult commentList(String commentType,Long articleId, Integer pageNum, Integer pageSize);

    ResponseResult addComment(Comment comment);
}
