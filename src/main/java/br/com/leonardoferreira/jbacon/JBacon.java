package br.com.leonardoferreira.jbacon;

import br.com.leonardoferreira.jbacon.operation.AllOperations;
import br.com.leonardoferreira.jbacon.operation.Build;
import br.com.leonardoferreira.jbacon.operation.BuildList;
import br.com.leonardoferreira.jbacon.operation.Create;
import br.com.leonardoferreira.jbacon.operation.CreateList;
import br.com.leonardoferreira.jbacon.operation.function.FunctionList;
import br.com.leonardoferreira.jbacon.operation.impl.BuildImpl;
import br.com.leonardoferreira.jbacon.operation.impl.BuildListImpl;
import br.com.leonardoferreira.jbacon.operation.impl.CreateImpl;
import br.com.leonardoferreira.jbacon.operation.impl.CreateListImpl;
import br.com.leonardoferreira.jbacon.util.JBaconUtil;

import java.util.List;

/**
 * Created by lferreira on 2/4/18
 */
public abstract class JBacon<T> implements AllOperations<T> {

    private Build<T> build;

    private BuildList<T> buildList;

    private Create<T> create;

    private CreateList<T> createList;

    private JBaconUtil<T> jBaconUtil;

    public JBacon() {
        jBaconUtil = new JBaconUtil<>(this);
        FunctionList<T> functions = new FunctionList<>(this::getEmpty, this::getFromTemplate, this::persist);
        build = new BuildImpl<>(functions);
        create = new CreateImpl<>(functions);
        createList = new CreateListImpl<>(functions);
        buildList = new BuildListImpl<>(functions);
    }

    protected T getFromTemplate(final String templateName) {
        if (templateName == null) {
            return getDefault();
        }

        return jBaconUtil.findTemplateByName(templateName);
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

    @Override
    public List<T> build(final int count) {
        return buildList.build(count);
    }

    @Override
    public List<T> build(final int count, final T original) {
        return buildList.build(count, original);
    }

    @Override
    public List<T> build(final int count, final String templateName) {
        return buildList.build(count, templateName);
    }

    @Override
    public List<T> build(final int count, final T original, final String templateName) {
        return buildList.build(count, original, templateName);
    }

    protected abstract T getDefault();

    protected abstract T getEmpty();

    protected abstract void persist(T t);

}

