package br.com.leonardoferreira.jbacon.operation.impl;

import br.com.leonardoferreira.jbacon.JBacon;
import br.com.leonardoferreira.jbacon.operation.Create;
import br.com.leonardoferreira.jbacon.operation.CreateList;
import br.com.leonardoferreira.jbacon.operation.function.FunctionList;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by lferreira on 2/4/18
 */
public class CreateListImpl<T> implements CreateList<T> {

    private Create<T> create;

    public CreateListImpl(FunctionList<T> functionalList) {
        this.create = new CreateImpl<>(functionalList);
    }

    public List<T> create(final int count) {
        return IntStream.range(0, count).boxed()
                .map(i -> create.create())
                .collect(Collectors.toList());
    }

    public List<T> create(final int count, final T original) {
        return IntStream.range(0, count).boxed()
                .map(i -> create.create(original))
                .collect(Collectors.toList());
    }

    @Override
    public List<T> create(int count, final String templateName) {
        return IntStream.range(0, count).boxed()
                .map(i -> create.create(templateName))
                .collect(Collectors.toList());
    }

    @Override
    public List<T> create(int count, final T original, final String templateName) {
        return IntStream.range(0, count).boxed()
                .map(i -> create.create(original, templateName))
                .collect(Collectors.toList());
    }
}
