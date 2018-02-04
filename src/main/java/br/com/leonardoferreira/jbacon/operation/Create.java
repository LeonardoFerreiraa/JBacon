package br.com.leonardoferreira.jbacon.operation;

/**
 * Created by lferreira on 2/4/18
 */
public interface Create<T> {

    T create();

    T create(final T original);

    T create(final String templateName);

    T create(final T original, final String templateName);

}
