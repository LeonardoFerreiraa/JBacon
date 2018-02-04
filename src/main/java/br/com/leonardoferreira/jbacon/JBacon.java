package br.com.leonardoferreira.jbacon;

import br.com.leonardoferreira.jbacon.operation.AllOperations;
import br.com.leonardoferreira.jbacon.operation.Build;
import br.com.leonardoferreira.jbacon.operation.Create;
import br.com.leonardoferreira.jbacon.operation.CreateList;
import br.com.leonardoferreira.jbacon.operation.impl.BuildImpl;
import br.com.leonardoferreira.jbacon.operation.impl.CreateImpl;
import br.com.leonardoferreira.jbacon.operation.impl.CreateListImpl;
import br.com.leonardoferreira.jbacon.util.JBaconUtil;

import java.util.List;

/**
 * Created by lferreira on 2/4/18
 */
public abstract class JBacon<T> implements AllOperations<T> {

    private Build<T> build;

    private Create<T> create;

    private CreateList<T> createList;

    public JBacon() {
        build = new BuildImpl<>(this);
        create = new CreateImpl<>(this);
        createList = new CreateListImpl<>(this);
    }

    public T getFromTemplate(final String templateName) {
        if (templateName == null) {
            return getDefault();
        }

        return JBaconUtil.findTemplateByName(templateName, this);
    }

    @Override
    public T build() {
        return build.build();
    }

    @Override
    public T build(final String templateName) {
        return build.build(templateName);
    }

    @Override
    public T build(final T original) {
        return build.build(original);
    }

    @Override
    public T build(final T original, final String templateName) {
        return build.build(original, templateName);
    }

    @Override
    public T create() {
        return create.create();
    }

    @Override
    public T create(final T original) {
        return create.create(original);
    }

    @Override
    public T create(final String templateName) {
        return create.create(templateName);
    }

    @Override
    public T create(final T original, final String templateName) {
        return create.create(original, templateName);
    }

    @Override
    public List<T> create(final int count) {
        return createList.create(count);
    }

    @Override
    public List<T> create(final int count, final T original) {
        return createList.create(count, original);
    }

    @Override
    public List<T> create(final int count, final String templateName) {
        return createList.create(count, templateName);
    }

    @Override
    public List<T> create(final int count, final T original, final String templateName) {
        return createList.create(count, original, templateName);
    }

    public abstract T getDefault();

    public abstract T getEmpty();

    public abstract void persist(T t);

}

