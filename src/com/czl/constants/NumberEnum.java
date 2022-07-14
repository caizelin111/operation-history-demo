package com.czl.constants;

/**
 * 用编号标识的枚举.
 *
 * @description
 */
public interface NumberEnum {

    /**
     * 系统中自定义类型的code值
     */
    int CUSTOM = -1;

    /**
     * 当前code类型是否为自定义类型
     *
     * @return code不等于-1，非自定义类型，返回true，code等于-1为自定义类型，返回false
     */
    default boolean isCustom() {
        return this.getCode() == CUSTOM;
    }

    int getCode();

    /**
     * 判断code是否匹配.
     *
     * @param code
     * @return
     */
    default boolean match(Integer code) {
        return null != code && this.getCode() == code;
    }

}
