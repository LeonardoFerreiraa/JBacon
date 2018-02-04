package br.com.leonardoferreira.jbacon.operation;

import java.util.List;

/**
 * Created by lferreira on 2/4/18
 */
public interface CreateList<T> {

    List<T> create(final int count);

    List<T> create(final int count, final T original);

    List<T> create(final int count, final String templateName);

    List<T> create(final int count, final T original, final String templateName);

}
