package com.wandoujia.hackday.apppath.protocol;

public enum OperationStatus {
    SUCCESS(0, "operation success"),
    FAIL(1, "operation fail"),
    INVALID_ARGUMENTS(2, "invalid arguments"),
    QUERY_APP_LIST_FAIL(3, "query app list fail"),
    INVALID_USER(6, "invalid user"),
    OPERATION_NOT_SUPPORTED(18, "operation not supported"),
    UNKNOWN(-1, "operation status unknown");

    private int code;
    private String msg;

    private OperationStatus(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
