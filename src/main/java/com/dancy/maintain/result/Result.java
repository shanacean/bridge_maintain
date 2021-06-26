package com.dancy.maintain.result;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @program: bridge_maintain_backend
 * @description: Controller返回结果封装
 * @author: Bangyang Shan
 * @create: 2021-06-20 23:21
 **/
@Data
@AllArgsConstructor
public class Result {
    private int code;
    private String message;
    private Object result;
}
