package com.agony.alarmsystem.exception;

import lombok.Getter;

/**
 * @author: Agony
 * @create: 2025/2/16 13:55
 * @describe: 自定义业务异常
 */

@Getter
public class BusinessException extends RuntimeException {


    /**
     * 错误码
     */
    private final int code;


    private static final long serialVersionUID = 1L;

    public BusinessException(int code, String message) {
        super(message);
        this.code = code;
    }


    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
    }

    public BusinessException(ErrorCode errorCode, String message) {
        super(message);
        this.code = errorCode.getCode();
    }

}
