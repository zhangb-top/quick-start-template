package com.promise.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.promise.pojo.Travel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TravelDao extends BaseMapper<Travel> {
}
