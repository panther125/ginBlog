package com.panther.model.DTO;

import lombok.Data;

import java.util.List;

/**
 * @author Gin 琴酒
 * @data 2023/3/19 21:05
 */
@Data
public class UserDTO {

    private String userName;
    private String nickName;
    private String password;
    private String phonenumber;
    private int status;
    private String email;
    private int sex;
    List<Long> roleIds;
}
