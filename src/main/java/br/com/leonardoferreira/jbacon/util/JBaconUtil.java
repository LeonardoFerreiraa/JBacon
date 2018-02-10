package br.com.leonardoferreira.jbacon.util;

import br.com.leonardoferreira.jbacon.JBacon;
import br.com.leonardoferreira.jbacon.annotation.JBaconTemplate;
import br.com.leonardoferreira.jbacon.exception.*;

import java.lang.reflect.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lferreira on 2/4/18
 */
public class JBaconUtil<T> {

    private JBacon<T> jBacon;

    private Map<String, Method> templates;

    public JBaconUtil(JBacon<T> jBacon) {
        this.jBacon = jBacon;
        this.templates = new HashMap<>();
        validate();
    }

    private void validate() {
        for (Method method : jBacon.getClass().getDeclaredMethods()) {
            JBaconTemplate jBaconTemplate = method.getAnnotation(JBaconTemplate.class);
            if (jBaconTemplate == null) {
                continue;
            }
            validate(method);
            templates.put(jBaconTemplate.value(), method);
        }
    }

    @SuppressWarnings("unchecked")
    private void validate(Method method) {
        ParameterizedType parameterizedType = (ParameterizedType) jBacon.getClass().getGenericSuperclass();
        Class<T> type = (Class<T>) parameterizedType.getActualTypeArguments()[0];

        if (method.getParameterCount() != 0) {
            throw new JBaconTemplateParameterException("Template should not have parameter");
        }

        if (!method.getReturnType().isAssignableFrom(type)) {
            throw new JBaconTemplateInvalidReturnType("Template methods should return <T>");
        }

        if (!Modifier.isProtected(method.getModifiers())) {
            throw new JBaconTemplateInvalidVisibility("Template methods should be protected");
        }
    }

    @SuppressWarnings("unchecked")
    public T findTemplateByName(final String templateName) {
        try {
            Method method = templates.get(templateName);
            if (method != null) {
                method.setAccessible(true);
                return (T) method.invoke(jBacon);
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new JBaconInvocationException(e);
        }

        throw new JBaconTemplateNotFound(templateName);
    }

}
