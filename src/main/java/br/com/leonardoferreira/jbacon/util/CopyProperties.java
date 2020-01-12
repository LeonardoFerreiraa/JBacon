package br.com.leonardoferreira.jbacon.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.SneakyThrows;

public final class CopyProperties {

    private CopyProperties() {
    }

    public static void copy(final Object target, final Object source) {
        final List<Field> sourceFields = findAllFields(source.getClass());
        final List<Field> targetFields = findAllFields(target.getClass());

        final Map<String, Object> fields = Maps.merge(
                fieldsToMap(sourceFields, source),
                fieldsToMap(targetFields, target)
        );

        for (final Field field : targetFields) {
            if (fields.containsKey(field.getName())) {
                final Object value = fields.get(field.getName());
                updateFieldValue(field, target, value);
            }
        }
    }

    private static List<Field> findAllFields(final Class<?> clazz) {
        final List<Field> fields = new ArrayList<>();

        if (clazz.getSuperclass() != null) {
            fields.addAll(findAllFields(clazz.getSuperclass()));
        }

        fields.addAll(Arrays.stream(clazz.getDeclaredFields())
                .filter(field -> !Modifier.isFinal(field.getModifiers()))
                .filter(field -> !Modifier.isStatic(field.getModifiers()))
                .collect(Collectors.toList()));

        return fields;
    }

    private static Map<String, Object> fieldsToMap(final List<Field> fields, final Object source) {
        return fields.stream()
                .map(field -> extractValue(field, source))
                .filter(Pair::hasValue)
                .collect(Collectors.toMap(Pair::getKey, Pair::getValue));
    }

    @SneakyThrows
    private static Pair<String, Object> extractValue(final Field field, final Object source) {
        field.setAccessible(true);
        return Pair.of(field.getName(), field.get(source));
    }

    @SneakyThrows
    private static void updateFieldValue(final Field field, final Object target, final Object value) {
        field.setAccessible(true);
        field.set(target, value);
    }

}
