package br.com.leonardoferreira.jbacon.util;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
class Pair<K, V> {

    private final K key;

    private final V value;

    public static <F, S> Pair<F, S> of(final F key, final S value) {
        return new Pair<>(key, value);
    }

    public boolean hasValue() {
        return value != null;
    }

}
