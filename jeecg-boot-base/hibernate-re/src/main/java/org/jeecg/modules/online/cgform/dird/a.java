//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.cgform.dird;

import org.jeecg.common.exception.JeecgBootException;

public class a {
    public static void a(String msg, Object obj) {
        if (org.jeecg.common.util.a.h(obj)) {
            throw new JeecgBootException(msg);
        }
    }

    public static void b(String msg, Object obj) {
        if (org.jeecg.common.util.a.g(obj)) {
            throw new JeecgBootException(msg);
        }
    }

    public static void a(String message, Object expected, Object actual) {
        if (!org.jeecg.common.util.a.a(expected, actual)) {
            throw new JeecgBootException(message);
        }
    }

    public static void b(String message, Object expected, Object actual) {
        if (org.jeecg.common.util.a.a(expected, actual)) {
            throw new JeecgBootException(message);
        }
    }

    public static void c(String message, Object expected, Object actual) {
        if (expected != actual) {
            throw new JeecgBootException(message);
        }
    }

    public static void d(String message, Object unexpected, Object actual) {
        if (unexpected == actual) {
            throw new JeecgBootException(message);
        }
    }

    public static void a(String message, boolean condition) {
        if (!condition) {
            throw new JeecgBootException(message);
        }
    }

    public static void b(String message, boolean condition) {
        a(message, !condition);
    }

    public static <T> void a(String message, T obj, T... objs) {
        b(message, obj);
        b(message, objs);
        if (!org.jeecg.common.util.a.a(obj, objs)) {
            throw new JeecgBootException(message);
        }
    }

    public static <T> void b(String message, T obj, T... objs) {
        b(message, obj);
        b(message, objs);
        if (org.jeecg.common.util.a.a(obj, objs)) {
            throw new JeecgBootException(message);
        }
    }

    public static void a(String message, Number src, Number des) {
        if (!org.jeecg.common.util.a.a(src, des)) {
            throw new JeecgBootException(message);
        }
    }

    public static void b(String message, Number src, Number des) {
        if (!org.jeecg.common.util.a.b(src, des)) {
            throw new JeecgBootException(message);
        }
    }

    public static void c(String message, Number src, Number des) {
        if (org.jeecg.common.util.a.b(src, des)) {
            throw new JeecgBootException(message);
        }
    }

    public static void d(String message, Number src, Number des) {
        if (org.jeecg.common.util.a.a(src, des)) {
            throw new JeecgBootException(message);
        }
    }
}
