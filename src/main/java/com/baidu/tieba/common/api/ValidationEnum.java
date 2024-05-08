package com.baidu.tieba.common.api;


public enum ValidationEnum {
    LOGIN(1),FORGET_PASS(2);

    private Integer type;

    ValidationEnum(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }
}
