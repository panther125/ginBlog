package com.panther.model.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Gin 琴酒
 * @data 2023/3/19 20:30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailVo {
    private Long id;
    private String userName;
    private String nickName;
    private int status;
    private String email;
    private int sex;
}
