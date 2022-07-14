package com.czl.util;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 基于cglib的bean copy等工具方法.
 *
 * @author Jhown_deng
 * @date 21:19 2021/8/13
 * @description
 */
public class BeanUtils {


    private static final List<Class<?>> BASIC_OBJECT_TYPES = Arrays.asList(
            String.class, Integer.class, Double.class, Long.class,
            Character.class, Float.class, Short.class, Boolean.class, Byte.class,
            Date.class, int.class, double.class, long.class, char.class, float.class, short.class, boolean.class, byte.class
    );

    public static boolean isBasicType(Class clazz) {
        return BASIC_OBJECT_TYPES.contains(clazz);
    }


}
