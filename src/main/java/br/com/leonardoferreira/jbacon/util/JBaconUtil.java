package br.com.leonardoferreira.jbacon.util;

import br.com.leonardoferreira.jbacon.JBacon;
import br.com.leonardoferreira.jbacon.annotation.JBaconTemplate;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by lferreira on 2/4/18
 */
public class JBaconUtil {

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
                return (T) method.invoke(jBacon);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }

        return null;
    }

}
