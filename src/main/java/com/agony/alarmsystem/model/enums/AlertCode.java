package com.agony.alarmsystem.model.enums;

import lombok.Getter;

@Getter
public enum AlertCode {
    SUCCESS("成功", 0),
    FAILURE("失败", 1),
    CANCEL("取消", 2),
    INCOMPLETE("未完成", 3);

    private String name;
    private int code;

    AlertCode(String name, int code) {
        this.name = name;
        this.code = code;
    }

    // 根据 code 返回 enum
    public static AlertCode getEnumByCode(int code) {
        for (AlertCode alertCode : AlertCode.values()) {
            if (alertCode.getCode() == code) {
                return alertCode;
            }
        }
        return null;
    }
}