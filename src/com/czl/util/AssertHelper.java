package com.czl.util;

import com.fanggeek.maserati.cmmmon.constant.ErrorMsg;
import com.fanggeek.maserati.cmmmon.exception.BizcException;

import java.util.Collection;
import java.util.Map;

/**
 * 断言工具类，包含可抛异常的断言判断以及带有布尔值返回的断言判断<p>
 * 对于参数校验操作，建议直接使用抛异常的断言判断，此类断言操作会自动记录error日志并且抛出参数异常
 *
 * @date 2021-08-18 15:42
 * @since 1.0.1
 */
public class AssertHelper {


    /**
     * 断言对象不为空，不满足时抛出业务异常
     *
     * @param obj 被断言的对象，自动适配String或Collection对象
     * @throws BizcException 业务异常
     */
    public static void assertNotEmpty(Object obj) throws BizcException {
        if (obj instanceof String) {
            assertNotEmpty((String) obj);
        } else if (obj instanceof Collection) {
            assertNotEmpty((Collection<?>) obj);
        } else {
            if (obj == null) {
                LOGGER.error("AssertHelper assertNotEmpty assert object no empty, but object is empty");
                Exceptions.throwss(ErrorMsg.MSG_INVALID_QUERY);
            }
        }
    }

    /**
     * 断言字符串不为空，不满足时抛出业务异常
     *
     * @param str 被断言的字符串对象
     * @throws BizcException 业务异常
     */
    public static void assertNotEmpty(String str) throws BizcException {
        if (isEmpty(str)) {
            LOGGER.error("AssertHelper assertNotEmpty assert string no empty, but string is empty");
            Exceptions.throwss(ErrorMsg.MSG_INVALID_QUERY);
        }
    }

    /**
     * 断言集合不为空，不满足时抛出业务异常
     *
     * @param collection 被断言的集合对象
     * @throws BizcException 业务异常
     */
    public static void assertNotEmpty(Collection<?> collection) throws BizcException {
        if (isEmpty(collection)) {
            LOGGER.error("AssertHelper assertNotEmpty assert collection no empty, but collection is empty");
            Exceptions.throwss(ErrorMsg.MSG_INVALID_QUERY);
        }
    }

    /**
     * 断言所有对象都不为空，有任意一个为空时，抛出业务异常
     *
     * @param objects 被断言的对象，自动适配String或Collection对象
     * @throws BizcException 业务异常
     */
    public static void assertAllNotEmpty(Object... objects) throws BizcException {
        for (Object obj : objects) {
            assertNotEmpty(obj);
        }
    }

    /**
     * 断言不是所有的对象都为空（对象类型自适应）
     * @param objects           断言对象数组
     * @throws BizcException    当所有对象都为空时，抛出提交参数异常
     */
    public static void assertNotAllEmpty(Object... objects) throws BizcException {
        if (isAllEmpty((Object) objects)) {
            throw new BizcException(ErrorMsg.MSG_INVALID_QUERY);
        }
    }

    /**
     * 断言不是所有的对象都为空（对象类型自适应）
     * @param errorMsg          自定义错误信息枚举
     * @param objects           断言对象数组
     * @throws BizcException    当所有对象都为空时，抛出自定义业务异常
     */
    public static void assertNotAllEmpty(ErrorMsg errorMsg, Object... objects) throws BizcException {
        if (isAllEmpty(objects)) {
            throw new BizcException(errorMsg);
        }
    }

    /**
     * 断言一个对象不为null，否则抛出默认业务异常
     * @param obj               被断言的对象
     * @throws BizcException    业务异常，默认为提交参数错误
     */
    public static void assertNonNull(Object obj) throws BizcException {
        assertNonNull(obj, ErrorMsg.MSG_INVALID_QUERY);
    }

    /**
     * 断言一个对象不为null，否则抛出指定的业务异常
     * @param obj               被断言的对象
     * @param errorMsg          异常错误信息，对应业务异常类型
     * @throws BizcException    业务异常
     */
    public static void assertNonNull(Object obj, ErrorMsg errorMsg) throws BizcException {
        if (obj == null) {
            throw new BizcException(errorMsg);
        }
    }

    /**
     * 断言所有的对象都不为null，有任意一个为null时，抛出业务异常
     *
     * @param objects 被断言的对象
     * @throws BizcException 业务异常
     */
    public static void assertAllNonNull(Object... objects) throws BizcException {
        assertAllNonNull(ErrorMsg.MSG_INVALID_QUERY, objects);
    }

    /**
     * 断言所有的对象都不为null，有任意一个为null时，抛出业务异常
     *
     * @param objects 被断言的对象
     * @throws BizcException 业务异常
     */
    public static void assertAllNonNull(ErrorMsg errorMsg, Object... objects) throws BizcException {
        for (Object obj : objects) {
            if (isNull(obj)) {
                throw new BizcException(errorMsg);
            }
        }
    }

    /**
     * 断言一个操作为真，不满足时抛出参数错误异常
     *
     * @param supplier 布尔判断提供者
     * @throws BizcException 参数错误异常
     */
    public static void assertTrue(Boolean supplier) throws BizcException {
        assertTrue(supplier, ErrorMsg.MSG_INVALID_QUERY);
    }

    /**
     * 断言一个操作为假，不满足时抛出参数错误异常
     *
     * @param assertBool 布尔判断提供者
     * @throws BizcException 参数错误异常
     */
    public static void assertFalse(Boolean assertBool) throws BizcException {
        assertFalse(assertBool, ErrorMsg.MSG_INVALID_QUERY);
    }

    /**
     * 断言一个操作为真，不满足时抛出指定业务异常
     *
     * @param assertBool 布尔判断提供者
     * @param errorMsg   {@link ErrorMsg}
     * @throws BizcException 业务异常
     */
    public static void assertTrue(Boolean assertBool, ErrorMsg errorMsg) throws BizcException {
        if (!Boolean.TRUE.equals(assertBool)) {
            LOGGER.error("AssertHelper assertTrue assert condition true, but is false");
            Exceptions.throwss(errorMsg);
        }
    }

    /**
     * 断言一个操作为假，不满足时抛出业务异常
     *
     * @param assertBool    布尔判断提供者
     * @param errorMsg      {@link ErrorMsg}
     * @throws BizcException 业务异常
     */
    public static void assertFalse(Boolean assertBool, ErrorMsg errorMsg) throws BizcException {
        if (!Boolean.FALSE.equals(assertBool)) {
            LOGGER.error("AssertHelper assertFalse assert condition false, but is true");
            Exceptions.throwss(errorMsg);
        }
    }

    /**
     * 断言整数大于0，整数小于等于0时将抛出业务异常
     *
     * @param num 被断言判断的整数
     * @throws BizcException 业务异常
     */
    public static void assertNumberPositive(int num) throws BizcException {
        if (num <= 0) {
            LOGGER.error("AssertHelper assertNumberPositive assert number positive, but number is less then zero");
            Exceptions.throwss(ErrorMsg.MSG_INVALID_QUERY);
        }
    }

    /**
     * 断言所有整数都大于0，有任意一个整数小于或等于0时，抛出业务异常
     *
     * @param numbers 被断言判断的整数
     * @throws BizcException 业务异常
     */
    public static void assertAllNumberPositive(int... numbers) throws BizcException {
        for (int num : numbers) {
            assertNumberPositive(num);
        }
    }

    /**
     * 断言是否任意一个对象为空
     *
     * @param objects 被断言的对象，自动适配String或Collection对象
     * @return 被断言的对象中只要存在任意一个对象为空时，返回true，全部都不为空时返回false
     */
    public static boolean isAnyEmpty(Object... objects) {
        for (Object obj : objects) {
            if (isEmpty(obj)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 断言是否任意一个对象不为空
     *
     * @param objects 被断言的对象，自动适配String或Collection对象
     * @return 被断言的对象中只要存在任意一个对象不为空时，返回true，全部都为空时返回false
     */
    public static boolean isAnyNotEmpty(Object... objects) {
        for (Object obj : objects) {
            if (isNotEmpty(obj)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 断言所有的对象都为空
     *
     * @param objects 被断言的对象，自动适配String或Collection对象
     * @return 被断言的对象全部都为空时，返回true，只要其中一个不为空，返回false
     */
    public static boolean isAllEmpty(Object... objects) {
        for (Object obj : objects) {
            if (isNotEmpty(obj)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 断言所有的对象都不为空
     *
     * @param objects 被断言的对象，自动适配String或Collection对象
     * @return 被断言的对象全部都不为空时，返回true，只要其中一个为空，返回false
     */
    public static boolean isAllNotEmpty(Object... objects) {
        for (Object obj : objects) {
            if (isEmpty(obj)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断不是所有对象都为空
     *
     * @param objects
     * @return
     */
    public static boolean isNotAllEmpty(Object... objects) {
        for (Object obj : objects) {
            if (isNotEmpty(obj)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 断言对象为空
     *
     * @param obj 被断言的对象，自动适配String或Collection对象
     * @return 对象为空时，返回true，不为空时返回false
     */
    public static boolean isEmpty(Object obj) {
        if (obj instanceof String) {
            return isEmpty((String) obj);
        } else if (obj instanceof Collection) {
            return isEmpty((Collection<?>) obj);
        } else if (obj instanceof Map){
            return isEmpty((Map<?, ?>) obj);
        } else {
            return obj == null;
        }
    }

    /**
     * 断言字符串对象为空
     *
     * @param str 被断言的字符串对象
     * @return 字符串对象为空或者是空串时，返回true，不为空且不是空串时返回false
     */
    public static boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    /**
     * 断言集合对象为空
     *
     * @param collection 被断言的集合对象
     * @return 集合对象为空或者是空集合时，返回true，不为空且不是空集合时返回false
     */
    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean isEmpty(Map<?,?> map) {
        return null == map || map.isEmpty();
    }

    /**
     * 断言对象数组为空
     * @param objects   被断言的对象数组
     * @return          数组为null或长度为0时返回true，否则返回false
     */
    public static boolean isEmpty(Object[] objects) {
        if (null == objects) {
            return true;
        }
        return objects.length == 0;
    }

    /**
     * 断言对象不为空
     *
     * @param obj 被断言的对象，自动适配String或Collection对象
     * @return 对象不为空时，返回true，为空时返回false
     */
    public static boolean isNotEmpty(Object obj) {
        if (obj instanceof String) {
            return isNotEmpty((String) obj);
        } else if (obj instanceof Collection) {
            return isNotEmpty((Collection<?>) obj);
        } else {
            return obj != null;
        }
    }

    /**
     * 断言字符串对象不为空
     *
     * @param str 被断言的字符串对象
     * @return 字符串对象不为空且不是空串时，返回true，为空或者是空串时返回false
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * 断言集合对象不为空
     *
     * @param collection 被断言的集合对象
     * @return 集合对象不为空且不是空集合时，返回true，为空或者是空集合时返回false
     */
    public static boolean isNotEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }

    /**
     * 断言对象为null
     *
     * @param obj 被断言的对象
     * @return 对象为null返回true，不为null返回false
     */
    public static boolean isNull(Object obj) {
        return obj == null;
    }

    /**
     * 断言对象不为null
     *
     * @param obj 被断言的对象
     * @return 对象不为null返回true，为null返回false
     */
    public static boolean nonNull(Object obj) {
        return obj != null;
    }

    /**
     * 断言所有的对象都不为null
     *
     * @param objects 被断言的对象
     * @return 全部对象都不为null时返回true，有其中一个对象为null则返回false
     */
    public static boolean isAllNonNull(Object... objects) {
        for (Object obj : objects) {
            if (isNull(obj)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 断言任意一个对象为null
     *
     * @param objects 被断言的对象
     * @return 有其中一个对象为null返回true，全部对象都不为null返回false
     */
    public static boolean isAnyNull(Object... objects) {
        for (Object obj : objects) {
            if (isNull(obj)) {
                return true;
            }
        }
        return false;
    }

}
