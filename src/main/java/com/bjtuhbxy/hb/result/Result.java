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

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}

