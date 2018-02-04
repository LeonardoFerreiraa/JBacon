package br.com.leonardoferreira.jbacon.operation.impl;

import br.com.leonardoferreira.jbacon.JBacon;
import br.com.leonardoferreira.jbacon.operation.Create;
import lombok.AllArgsConstructor;

/**
 * Created by lferreira on 2/4/18
 */
@AllArgsConstructor
public class CreateImpl<T> implements Create<T> {

    private JBacon<T> jBacon;

    @Override
    public T create() {
        final T t = jBacon.build();
        jBacon.persist(t);
        return t;
    }

    @Override
    public T create(final T original) {
        T example = jBacon.build(original);
        jBacon.persist(example);
        return example;
    }

    @Override
    public T create(final String templateName) {
        T example = jBacon.build(templateName);
        jBacon.persist(example);
        return example;
    }

    @Override
    public T create(final T original, final String templateName) {
        T example = jBacon.build(original, templateName);
        jBacon.persist(example);
        return example;
    }

}
