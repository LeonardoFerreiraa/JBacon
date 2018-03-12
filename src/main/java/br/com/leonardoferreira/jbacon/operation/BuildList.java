package br.com.leonardoferreira.jbacon.operation;

import java.util.List;

/**
 * Created by lferreira on 3/11/18
 */
public interface BuildList<T> {

    List<T> build(final int count);

    List<T> build(final int count, final T original);

    List<T> build(final int count, final String templateName);

    List<T> build(final int count, final T original, final String templateName);

}
