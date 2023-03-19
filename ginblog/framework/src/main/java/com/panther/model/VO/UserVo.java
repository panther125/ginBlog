package com.panther.model.VO;

import com.panther.model.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Gin 琴酒
 * @data 2023/3/19 20:31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVo {
    private List<Long> roleIds;
    private List<Role> roles;
    UserDetailVo user;
}
