package org.jeecg.common.util;


import org.jeecg.common.exception.JeecgBootAssertException;

/**
 * 断言检查工具
 * for for [QQYUN-10990]AIRAG
 * @author chenrui
 * @since 2017-06-22
 */
public class AssertUtils {

    /**
     * 确保对象为空,如果不为空抛出异常
     *
     * @param msg 提示信息
     * @param obj 待检查对象
     * @throws JeecgBootAssertException 断言异常
     * @author chenrui
     * @since 2017-06-22
     */
    public static void assertEmpty(String msg, Object obj) {
        if (oConvertUtils.isObjectNotEmpty(obj)) {
            throw new JeecgBootAssertException(msg);
        }
    }


    /**
     * 确保对象不为空,如果为空抛出异常
     *
     * @param msg 提示信息
     * @param obj 待检查对象
     * @throws JeecgBootAssertException 断言异常
     * @author chenrui
     * @since 2017-06-22
     */
    public static void assertNotEmpty(String msg, Object obj) {
        if (oConvertUtils.isObjectEmpty(obj)) {
            throw new JeecgBootAssertException(msg);
        }
    }


    /**
     * 验证对象是否相同
     *
     * @param message 提示信息
     * @param expected 期望值
     * @param actual 实际值
     * @author chenrui
     * @since 2018/9/12
     */
    public static void assertEquals(String message, Object expected,
                                    Object actual) {
        if (oConvertUtils.isEqual(expected, actual)) {
            return;
        }
        throw new JeecgBootAssertException(message);
    }

    /**
     * 验证不相同
     *
     * @param message 提示信息
     * @param expected 期望值
     * @param actual 实际值
     * @author chenrui
     * @since 2018/9/12
     */
    public static void assertNotEquals(String message, Object expected,
                                       Object actual) {
        if (oConvertUtils.isEqual(expected, actual)) {
            throw new JeecgBootAssertException(message);
        }

    }

    /**
     * 验证是否相等
     *
     * @param message 提示信息
     * @param expected 期望值
     * @param actual 实际值
     * @author chenrui
     * @since 2018/9/12
     */
    public static void assertSame(String message, Object expected,
                                  Object actual) {
        if (expected == actual) {
            return;
        }
        throw new JeecgBootAssertException(message);
    }

    /**
     * 验证不相等
     *
     * @param message 提示信息
     * @param unexpected 不期望的值
     * @param actual 实际值
     * @author chenrui
     * @since 2018/9/12
     */
    public static void assertNotSame(String message, Object unexpected,
                                     Object actual) {
        if (unexpected == actual) {
            throw new JeecgBootAssertException(message);
        }
    }

    /**
     * 验证是否为真
     *
     * @param message 提示信息
     * @param condition 条件
     */
    public static void assertTrue(String message, boolean condition) {
        if (!condition) {
            throw new JeecgBootAssertException(message);
        }
    }

    /**
     * 验证 condition是否为false
     *
     * @param message 提示信息
     * @param condition 条件
     */
    public static void assertFalse(String message, boolean condition) {
        assertTrue(message, !condition);
    }


    /**
     * 验证是否存在
     *
     * @param message 提示信息
     * @param obj 待检查对象
     * @param objs 对象数组
     * @param <T> 泛型类型
     * @throws JeecgBootAssertException 断言异常
     * @author chenrui
     * @since 2018/1/31
     */
    public static <T> void assertIn(String message, T obj, T... objs) {
        assertNotEmpty(message, obj);
        assertNotEmpty(message, objs);
        if (!oConvertUtils.isIn(obj, objs)) {
            throw new JeecgBootAssertException(message);
        }
    }

    /**
     * 验证是否不存在
     *
     * @param message 提示信息
     * @param obj 待检查对象
     * @param objs 对象数组
     * @param <T> 泛型类型
     * @throws JeecgBootAssertException 断言异常
     * @author chenrui
     * @since 2018/1/31
     */

    public static <T> void assertNotIn(String message, T obj, T... objs) {
        assertNotEmpty(message, obj);
        assertNotEmpty(message, objs);
        if (oConvertUtils.isIn(obj, objs)) {
            throw new JeecgBootAssertException(message);
        }
    }


    /**
     * 确保src大于des
     *
     * @param message 提示信息
     * @param src 源数值
     * @param des 目标数值
     * @author chenrui
     * @since 2018/9/19
     */
    public static void assertGt(String message, Number src, Number des) {
        if (oConvertUtils.isGt(src, des)) {
            return;
        }
        throw new JeecgBootAssertException(message);
    }

    /**
     * 确保src大于等于des
     *
     * @param message 提示信息
     * @param src 源数值
     * @param des 目标数值
     * @author chenrui
     * @since 2018/9/19
     */
    public static void assertGe(String message, Number src, Number des) {
        if (oConvertUtils.isGe(src, des)) {
            return;
        }
        throw new JeecgBootAssertException(message);
    }


    /**
     * 确保src小于des
     *
     * @param message 提示信息
     * @param src 源数值
     * @param des 目标数值
     * @author chenrui
     * @since 2018/9/19
     */
    public static void assertLt(String message, Number src, Number des) {
        if (oConvertUtils.isGe(src, des)) {
            throw new JeecgBootAssertException(message);
        }
    }

    /**
     * 确保src小于等于des
     *
     * @param message 提示信息
     * @param src 源数值
     * @param des 目标数值
     * @author chenrui
     * @since 2018/9/19
     */
    public static void assertLe(String message, Number src, Number des) {
        if (oConvertUtils.isGt(src, des)) {
            throw new JeecgBootAssertException(message);
        }
    }

}
