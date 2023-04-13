package com.promise.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.promise.pojo.Question;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface QuestionDao extends BaseMapper<Question> {
    @Update("UPDATE tb_question SET answered = #{answered} WHERE id = #{id}")
    int updateAnswered(Integer id, Integer answered);
}
