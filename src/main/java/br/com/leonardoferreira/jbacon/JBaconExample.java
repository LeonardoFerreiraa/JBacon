package br.com.leonardoferreira.jbacon;

/**
 * @param <T> Target of factory
 */
@FunctionalInterface
public interface JBaconExample<T> {

    /**
     * Builds a object that will be used as example
     * @param empty {@link br.com.leonardoferreira.jbacon.JBacon#getEmpty}
     */
    void apply(T empty);

}
