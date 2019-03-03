package br.com.leonardoferreira.jbacon;

import java.util.function.Supplier;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.LazyLoader;

/**
 * Utility class to provide a object lazy.
 *
 * <p>
 * Example:
 *
 * <pre>
 * <code>
 * Lazy.of(Contact.class)
 *      .with(contactFactory::create);
 * }
 * </code>
 * </pre>
 *
 * @param <T> Type of lazy builder target
 */
public final class Lazy<T> {

    private final Class<T> target;

    private Lazy(final Class<T> target) {
        this.target = target;
    }

    /**
     * @param target Target of lazy load
     * @param <T>    Type of the target
     * @return Lazy object builder
     */
    public static <T> Lazy<T> of(final Class<T> target) {
        return new Lazy<>(target);
    }

    /**
     * @param supplier Supplier that build object to be used when required
     * @return Lazy object
     */
    public T with(final Supplier<T> supplier) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.target);
        enhancer.setCallback((LazyLoader) supplier::get);

        return target.cast(enhancer.create());
    }

}