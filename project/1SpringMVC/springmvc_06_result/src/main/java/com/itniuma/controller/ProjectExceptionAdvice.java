package com.itniuma.controller;

import com.itniuma.exception.SystemException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProjectExceptionAdvice {

    @ExceptionHandler(SystemException.class)
    public Result handleSystemException(SystemException exception) {
        // 记录日志
        // 发送消息给运维和开发人员
        return new Result(exception.getCode(), null, exception.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Result handleException(Exception exception) {
        return new Result(0, null, "发成了错误");
    }
}
