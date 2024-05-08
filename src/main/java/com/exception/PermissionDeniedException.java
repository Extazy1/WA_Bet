package com.exception;

public class PermissionDeniedException extends RuntimeException {
    private int code;

    public PermissionDeniedException(String message) {
        super(message);
        this.code = 3; // 自定义错误码 表示没有权限
    }

    public PermissionDeniedException(String message, int code) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
