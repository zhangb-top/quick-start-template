package com.promise.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class Question {
    // 主键id
    private Integer id;
    // 提问的用户的id
    @TableField(value = "userID")
    private Integer userID;
    // 提问内容
    private String content;
    // 是否已经回复 0表示未回复 1表示回复
    private Integer answered;
    // travel回复
    @TableField(exist = false)
    private Travel answer;
}
