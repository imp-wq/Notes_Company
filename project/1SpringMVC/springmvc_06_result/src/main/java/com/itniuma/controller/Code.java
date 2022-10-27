package com.itniuma.controller;

public enum Code {
    SAVE_OK(20011),
    DELETE_OK(20021),
    UPDATE_OK(20031),
    GET_OK(20041),

    SAVE_ERR(20010),
    DELETE_ERR(20020),
    UPDATE_ERR(20030),
    GET_ERR(20040);

    private Integer code;

    Code(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
