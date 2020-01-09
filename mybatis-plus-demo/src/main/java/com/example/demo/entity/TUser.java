package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
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
public class TUser {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
            @TableId(value = "USER_ID", type = IdType.AUTO)
    private Long userId;

    /**
     * 用户名
     */
        @TableField("USERNAME")
    private String username;

    /**
     * 密码
     */
        @TableField("PASSWORD")
    private String password;

    /**
     * 部门ID
     */
        @TableField("DEPT_ID")
    private Long deptId;

    /**
     * 邮箱
     */
        @TableField("EMAIL")
    private String email;

    /**
     * 联系电话
     */
        @TableField("MOBILE")
    private String mobile;

    /**
     * 状态 0锁定 1有效
     */
        @TableField("STATUS")
    private String status;

    /**
     * 创建时间
     */
        @TableField("CREATE_TIME")
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
        @TableField("MODIFY_TIME")
    private LocalDateTime modifyTime;

    /**
     * 最近访问时间
     */
        @TableField("LAST_LOGIN_TIME")
    private LocalDateTime lastLoginTime;

    /**
     * 性别 0男 1女 2保密
     */
        @TableField("SSEX")
    private String ssex;

    /**
     * 描述
     */
        @TableField("DESCRIPTION")
    private String description;

    /**
     * 用户头像
     */
        @TableField("AVATAR")
    private String avatar;


}
