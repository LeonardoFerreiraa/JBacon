package br.com.leonardoferreira.jbacon.operation.impl;

import br.com.leonardoferreira.jbacon.operation.Build;
import br.com.leonardoferreira.jbacon.operation.Create;
import br.com.leonardoferreira.jbacon.operation.function.FunctionList;

/**
 * Created by lferreira on 2/4/18
 */
public class CreateImpl<T> implements Create<T> {

    private FunctionList<T> functionList;

    private Build<T> build;

    public CreateImpl(FunctionList<T> functionList) {
        this.functionList = functionList;
        this.build = new BuildImpl<>(functionList);
    }

    @Override
    public T create() {
        final T t = new BuildImpl<>(functionList).build();
        functionList.persist(t);
        return t;
    }

    @Override
    public T create(final T original) {
        T example = build.build(original);
        functionList.persist(example);
        return example;
    }

    @Override
    public T create(final String templateName) {
        T example = build.build(templateName);
        functionList.persist(example);
        return example;
    }

    @Override
    public T create(final T original, final String templateName) {
        T example = build.build(original, templateName);
        functionList.persist(example);
        return example;
    }

}
