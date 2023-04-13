package com.promise.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class User {
    // 主键
    private Integer id;
    // 用户名
    private String username;
    // 密码
    private String password;
    // 1表示管理员 2表示用户
    private Integer identity;
    // 验证码
    @TableField(exist = false)
    private String code;
    // 状态
    private Integer status;
}
