package com.promise.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyBatisPlusConfig {
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor mpi = new MybatisPlusInterceptor();
        // 添加分页拦截器
        mpi.addInnerInterceptor(new PaginationInnerInterceptor());
        // 添加乐观锁拦截器
        mpi.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
        return mpi;
    }
}
