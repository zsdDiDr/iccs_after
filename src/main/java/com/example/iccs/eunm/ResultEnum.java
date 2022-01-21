package com.example.iccs.eunm;

/**
 * @author zsd
 * @date 2022/1/11
 */
public enum ResultEnum {

    SUCCESS(200),
    NOT_FOUND(4040),
    FAIL(500);

    private int code;

    ResultEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
