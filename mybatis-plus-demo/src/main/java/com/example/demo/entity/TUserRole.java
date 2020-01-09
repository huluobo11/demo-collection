package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 
 *
 * @author small-white
 */
@Data
@EqualsAndHashCode
@Accessors(chain = true)
public class TUserRole {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
        @TableField("USER_ID")
    private Long userId;

    /**
     * 角色ID
     */
        @TableField("ROLE_ID")
    private Long roleId;


}
