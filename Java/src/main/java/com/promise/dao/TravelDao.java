package com.promise.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.promise.pojo.Travel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TravelDao extends BaseMapper<Travel> {
    @Select("SELECT id,theme,location,itinerary,hotel,budget,status FROM tb_travel WHERE id = #{id}")
    Travel getTravelById(Integer id);

    @Select("SELECT id, theme, location, itinerary, hotel, budget, status FROM tb_travel WHERE status " +
            "= 0")
    List<Travel> getDeleted();
}
