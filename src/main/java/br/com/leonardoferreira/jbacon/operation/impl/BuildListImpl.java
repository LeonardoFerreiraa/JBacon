package br.com.leonardoferreira.jbacon.operation.impl;

import br.com.leonardoferreira.jbacon.operation.Build;
import br.com.leonardoferreira.jbacon.operation.BuildList;
import br.com.leonardoferreira.jbacon.operation.function.FunctionList;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by lferreira on 3/11/18
 */
public class BuildListImpl<T> implements BuildList<T> {

    private Build<T> build;

    public BuildListImpl(FunctionList<T> functionList) {
        this.build = new BuildImpl<>(functionList);
    }

    @Override
    public List<T> build(int count) {
        return IntStream.range(0, count).boxed()
                .map(i -> build.build())
                .collect(Collectors.toList());
    }

    @Override
    public List<T> build(int count, T original) {
        return IntStream.range(0, count).boxed()
                .map(i -> build.build(original))
                .collect(Collectors.toList());
    }

    @Override
    public List<T> build(int count, String templateName) {
        return IntStream.range(0, count).boxed()
                .map(i -> build.build(templateName))
                .collect(Collectors.toList());
    }

    @Override
    public List<T> build(int count, T original, String templateName) {
        return IntStream.range(0, count).boxed()
                .map(i -> build.build(original, templateName))
                .collect(Collectors.toList());
    }
}
