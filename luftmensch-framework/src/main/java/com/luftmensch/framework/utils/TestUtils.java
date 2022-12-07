package com.luftmensch.framework.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TestUtils {

    public static <M, D> List<D> test(Class<M> clazz, Class<D> d, Class<?> q, String methodName, Object arg) {
        Object result;
        M bean = SpringUtils.getBean(clazz);
        try {
            if (Objects.isNull(q) || Objects.isNull(arg)) {
                Method method = clazz.getMethod(methodName);
                result = method.invoke(bean);
            } else {
                Method method = clazz.getMethod(methodName, q);
                result = method.invoke(bean, arg);
            }
            return castToList(result, d);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <D, M> List<D> test(Class<M> clazz, Class<D> d, String methodName) {
        return test(clazz, d, null, methodName, null);
    }


    private static <T> List<T> castToList(Object obj, Class<T> clazz) {
        List<T> resList = new ArrayList<>();
        // 如果不是List<?>对象，是没有办法转换的
        if (obj instanceof List<?>) {
            for (Object o : (List<?>) obj) {
                // 将对应的元素进行类型转换
                resList.add(clazz.cast(o));
            }
            return resList;
        }
        return null;
    }
}