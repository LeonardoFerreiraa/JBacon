package br.com.leonardoferreira.jbacon.util;

import br.com.leonardoferreira.jbacon.JBacon;
import br.com.leonardoferreira.jbacon.annotation.JBaconTemplate;
import br.com.leonardoferreira.jbacon.exception.JBaconTemplateNotFound;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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

            if (!jBaconTemplate.value().equals(templateName)) {
                continue;
            }

            try {
                method.setAccessible(true);
                return (T) method.invoke(jBacon);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }

        throw new JBaconTemplateNotFound(templateName);
    }

}
