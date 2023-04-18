package com.promise.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class Travel {
    // 主键
    private Integer id;
    // 主题
    private String theme;
    // 地点
    private String location;
    // 行程安排
    private String itinerary;
    // 酒店
    private String hotel;
    // 预算
    private Double budget;
    // 回复的问题的id 无则为null
    @TableField(value = "questionID")
    private Integer questionID;
}
