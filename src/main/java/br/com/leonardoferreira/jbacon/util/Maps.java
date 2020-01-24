package br.com.leonardoferreira.jbacon.util;

import java.util.HashMap;
import java.util.Map;

final class Maps {

    private Maps() {
    }

    public static <K, V> Map<K, V> merge(final Map<K, V> m1, final Map<K, V> m2) {
        final Map<K, V> result = new HashMap<>();

        result.putAll(m1);
        result.putAll(m2);

        return result;
    }

}
