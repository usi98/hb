package com.bjtuhbxy.hb.result;


import lombok.Data;

@Data
public class Result {
    private int code;
    private String message;
    private Object result;

    public Result(int code) {
        this.code = code;
    }

    Result(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.result = data;
    }
}

