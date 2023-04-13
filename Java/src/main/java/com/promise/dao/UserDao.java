package com.promise.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.promise.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserDao extends BaseMapper<User> {
    @Select("SELECT id,username,password,identity,status FROM tb_user WHERE username = #{username}")
    List<User> getByUsername(String username);

    @Select("SELECT id,username,password,identity,status FROM tb_user WHERE status = 0")
    List<User> getDeleted();
}
