package com.promise.controller.result;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QuestionResult {
    private Integer code;
    private Object data;
    private String msg;
}
