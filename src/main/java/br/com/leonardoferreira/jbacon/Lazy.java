package br.com.leonardoferreira.jbacon;

import java.util.function.Supplier;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.LazyLoader;

public final class Lazy<T> {

    private final Class<T> target;

    private Lazy(final Class<T> target) {
        this.target = target;
    }

    public static <T> Lazy<T> of(final Class<T> target) {
        return new Lazy<>(target);
    }

    public T with(final Supplier<T> supplier) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.target);
        enhancer.setCallback((LazyLoader) supplier::get);

        return target.cast(enhancer.create());
    }

}