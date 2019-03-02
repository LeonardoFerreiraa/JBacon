package br.com.leonardoferreira.jbacon;

import br.com.leonardoferreira.jbacon.annotation.JBaconTemplate;
import br.com.leonardoferreira.jbacon.exception.JBaconInvocationException;
import br.com.leonardoferreira.jbacon.exception.JBaconTemplateException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.Map;

/**
 * Utility class to manage {@link JBaconTemplate}'s
 */
class TemplateResolver<T> {

    private final JBacon<T> jBacon;

    private final Map<String, Method> templates;

    TemplateResolver(final JBacon<T> jBacon) {
        this.jBacon = jBacon;
        this.templates = new HashMap<>();
        buildTemplates();
    }

    private void buildTemplates() {
        for (Method method : jBacon.getClass().getDeclaredMethods()) {
            JBaconTemplate jBaconTemplate = method.getAnnotation(JBaconTemplate.class);
            if (jBaconTemplate == null) {
                continue;
            }
            buildTemplates(method);

            if ("METHOD_NAME".equals(jBaconTemplate.value())) {
                templates.put(method.getName(), method);
            } else {
                templates.put(jBaconTemplate.value(), method);
            }
        }
    }

    @SuppressWarnings("unchecked")
    T findTemplateByName(final String templateName) {
        if (templateName == null) {
            return jBacon.getDefault();
        }

        try {
            Method method = templates.get(templateName);
            if (method != null) {
                method.setAccessible(true);
                return (T) method.invoke(jBacon);
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new JBaconInvocationException(e);
        }

        throw new JBaconTemplateException(templateName + " not found");
    }

    @SuppressWarnings("unchecked")
    private void buildTemplates(final Method method) {
        ParameterizedType parameterizedType = (ParameterizedType) jBacon.getClass().getGenericSuperclass();
        Class<T> type = (Class<T>) parameterizedType.getActualTypeArguments()[0];

        if (!method.getReturnType().isAssignableFrom(type)) {
            throw new JBaconTemplateException("Template methods should return the same type of factory");
        }

        if (method.getParameterCount() != 0) {
            throw new JBaconTemplateException("Template methods should not have parameters");
        }

        if (!Modifier.isProtected(method.getModifiers())) {
            throw new JBaconTemplateException("Template methods should be protected");
        }
    }

}