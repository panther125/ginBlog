package com.panther.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Gin 琴酒
 * @data 2023/3/19 17:41
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleStatusDTO {
    private String roleId;
    private String status;
}
