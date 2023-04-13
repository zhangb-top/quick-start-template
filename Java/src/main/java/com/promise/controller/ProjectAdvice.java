package com.promise.controller;

import com.promise.controller.result.UserResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProjectAdvice {
    @ExceptionHandler(Exception.class)
    public UserResult doException(Exception e) {
        e.printStackTrace();
        return new UserResult(Code.ERROR_CODE, null, "服务器繁忙，请稍后再试！");
    }
}
