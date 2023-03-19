package com.panther.model.VO;

import com.panther.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Gin 琴酒
 * @data 2023/3/19 20:55
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserListVo {
    private List<User> rows;
    private Long total;
}
