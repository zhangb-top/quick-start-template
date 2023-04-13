package com.promise.controller.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.promise.controller.Code;
import com.promise.controller.result.UserResult;
import com.promise.util.TokenUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class ProjectInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        if ("OPTIONS".equals(request.getMethod())) {
            return true;
        }
        // 获取请求头的token数据
        ObjectMapper objectMapper = new ObjectMapper();
        String token = request.getHeader("Authorization");
        response.setContentType("text/html;charset=utf-8");
        // 解析token数据
        if (token != null) {
            // 删除头部的 Bearer+空格
            token = token.substring(7);
            // 做出响应
            if (!TokenUtils.verify(token)) {
                response.getWriter().write(objectMapper.writeValueAsString(new UserResult(Code.ERROR_CODE,
                        null, "token无效")));
                return false;
            } else return true;
        } else {
            // 做出响应
            response.getWriter().write(objectMapper.writeValueAsString(new UserResult(Code.ERROR_CODE,
                    null, "没有权限")));
            return false;
        }
    }
}
