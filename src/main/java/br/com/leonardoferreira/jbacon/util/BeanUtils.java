package br.com.leonardoferreira.jbacon.util;

import org.springframework.util.StringUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lferreira on 6/16/17.
 */
public final class BeanUtils extends org.springframework.beans.BeanUtils {

    private BeanUtils() {
    }

    public static void copyPropertiesNotNull(final Object source, final Object target) {
        final Class<?> targetClass = target.getClass();
        final PropertyDescriptor[] targetPds = getPropertyDescriptors(targetClass);
        final List<String> ignoreProperties = new ArrayList<>();

        try {
            for (PropertyDescriptor t : targetPds) {
                Method method = null;
                try {
                    method = targetClass.getMethod("get" + StringUtils.capitalize(t.getName()));
                } catch (NoSuchMethodException e) {
                    method = targetClass.getMethod("is" + StringUtils.capitalize(t.getName()));
                }
                if (method.invoke(target) != null) {
                    ignoreProperties.add(t.getName());
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        copyProperties(source, target, ignoreProperties.toArray(new String[0]));
    }
}
