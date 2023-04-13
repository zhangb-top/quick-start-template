package com.promise.controller.result;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResult {
    private Integer code;
    private Object data;
    private String msg;
}
