package com.lionxxw.kqsystem.code.utils;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

/**
 * <p>Description: 对象判断工具类 </p>
 *
 * @author lionxxw
 * @date 16/5/6 上午9:47
 * @version 1.0
 */
public abstract class ObjectUtils {
    public static final Object NULL = null;

    public static boolean isNull(Object o) {
        if (o == null){
            return true;
        }
        else if (o instanceof Long){
            if (((Long) o).longValue() <= 0L){
                return true;
            }
        }
        else if (o instanceof Integer){
            if (((Integer) o).intValue() <= 0){
                return true;
            }
        }
        else if (o instanceof String){
            if ("".equals(((String) o).trim())){
                return true;
            }
        }
        return false;
    }

    public static boolean notNull(Object o) {
        return !isNull(o);
    }

    public static boolean isEmpty(Object o) {
        if (isNull(o)) {
            return true;
        }

        if ((o instanceof String)) {
            return "".equals(o.toString());
        }
        if ((o instanceof Collection)) {
            return ((Collection) o).isEmpty();
        }
        if ((o instanceof Map)) {
            return ((Map) o).isEmpty();
        }
        if (o.getClass().isArray()) {
            return Array.getLength(o) == 0;
        }

        return true;
    }

    public static boolean notEmpty(Object o) {
        return !isEmpty(o);
    }

    public static <T> T nullSafe(T actual, T safe) {
        return actual == null ? safe : actual;
    }
}