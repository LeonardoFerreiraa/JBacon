package br.com.leonardoferreira.jbacon.operation;

/**
 * Created by lferreira on 2/4/18
 */
public interface Build<T> {

    T build();

    T build(final String templateName);

    T build(final T original);

    T build(final T original, final String templateName);

}
