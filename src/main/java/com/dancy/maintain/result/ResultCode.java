package com.dancy.maintain.result;

public enum ResultCode {
    /*
    * 500 -> token验证异常
    * */
    SUCCESS(200),
    FAIL(400),
    FAIL_AUTHORIZATION(500);

    public int code;

    ResultCode(int code) {
        this.code = code;
    }
}
