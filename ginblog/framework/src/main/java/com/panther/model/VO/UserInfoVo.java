package com.panther.model.VO;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author Gin 琴酒
 * @data 2023/3/14 22:34
 */
@Data
@Accessors(chain = true)
public class UserInfoVo {
    /**
     * 主键
     */
    private Long id;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 头像
     */
    private String avatar;

    private String sex;

    private String email;


}
