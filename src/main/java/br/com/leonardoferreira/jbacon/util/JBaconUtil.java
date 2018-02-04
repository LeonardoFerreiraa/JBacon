package br.com.leonardoferreira.jbacon.util;

import br.com.leonardoferreira.jbacon.JBacon;
import br.com.leonardoferreira.jbacon.annotation.JBaconTemplate;
import br.com.leonardoferreira.jbacon.exception.JBaconInvocationException;
import br.com.leonardoferreira.jbacon.exception.JBaconTemplateInvalidReturnType;
import br.com.leonardoferreira.jbacon.exception.JBaconTemplateNotFound;
import br.com.leonardoferreira.jbacon.exception.JBaconTemplateParameterException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by lferreira on 2/4/18
 */
public final class JBaconUtil {

    private JBaconUtil() {
    }

    @SuppressWarnings("unchecked")
    public static <T> T findTemplateByName(final String templateName, final JBacon<T> jBacon) {
        for (Method method : jBacon.getClass().getDeclaredMethods()) {
            JBaconTemplate jBaconTemplate = method.getAnnotation(JBaconTemplate.class);
            if (jBaconTemplate == null) {
                continue;
            }

            if (jBaconTemplate.value().equals(templateName)) {
                try {
                    ParameterizedType parameterizedType = (ParameterizedType) jBacon.getClass().getGenericSuperclass();
                    Class<T> type = (Class<T>) parameterizedType.getActualTypeArguments()[0];

                    if (method.getParameterCount() != 0) {
                        throw new JBaconTemplateParameterException("Template should not have parameter");
                    }

                    method.setAccessible(true);
                    if (!method.getReturnType().isAssignableFrom(type)) {
                        throw new JBaconTemplateInvalidReturnType();
                    }

                    return (T) method.invoke(jBacon);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new JBaconInvocationException(e);
                }
            }
        }

        throw new JBaconTemplateNotFound(templateName);
    }

}
