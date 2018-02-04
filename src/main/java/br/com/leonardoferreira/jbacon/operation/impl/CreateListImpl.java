package br.com.leonardoferreira.jbacon.operation.impl;

import br.com.leonardoferreira.jbacon.JBacon;
import br.com.leonardoferreira.jbacon.operation.CreateList;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by lferreira on 2/4/18
 */
@AllArgsConstructor
public class CreateListImpl<T> implements CreateList<T> {

    private JBacon<T> jBacon;

    public List<T> create(final int count) {
        return IntStream.range(0, count).boxed()
                .map(i -> jBacon.create())
                .collect(Collectors.toList());
    }

    public List<T> create(final int count, final T original) {
        return IntStream.range(0, count).boxed()
                .map(i -> jBacon.create(original))
                .collect(Collectors.toList());
    }

    @Override
    public List<T> create(int count, final String templateName) {
        return IntStream.range(0, count).boxed()
                .map(i -> jBacon.create(templateName))
                .collect(Collectors.toList());
    }

    @Override
    public List<T> create(int count, final T original, final String templateName) {
        return IntStream.range(0, count).boxed()
                .map(i -> jBacon.create(original, templateName))
                .collect(Collectors.toList());
    }
}
