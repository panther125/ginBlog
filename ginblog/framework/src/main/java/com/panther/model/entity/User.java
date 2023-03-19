package com.panther.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Gin 琴酒
 * @data 2023/3/14 22:25
 */
@TableName("sys_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @TableId
    private Long id;
    private String userName;
    private String nickName;
    private String password;
    private String type;
    private int status;
    private String phonenumber;
    private String email;
    private int sex;
    private String avatar;
    private Date createTime;

    private Long updateBy;

    private Date updateTime;
    //删除标志（0代表未删除，1代表已删除）
    private Integer delFlag;
}
