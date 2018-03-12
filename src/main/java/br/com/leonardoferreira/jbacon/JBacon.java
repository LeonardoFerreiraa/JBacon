package br.com.leonardoferreira.jbacon;

import br.com.leonardoferreira.jbacon.util.BeanUtils;
import br.com.leonardoferreira.jbacon.util.JBaconUtil;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by lferreira on 2/4/18
 */
public abstract class JBacon<T> {

    private JBaconUtil<T> jBaconUtil;

    public JBacon() {
        jBaconUtil = new JBaconUtil<>(this);
    }

    public T build() {
        return build(null, null);
    }

    public T build(final String templateName) {
        return build(null, templateName);
    }

    public T build(final T original) {
        return build(original, null);
    }

    public T build(final T original, final String templateName) {
        T example = getEmpty();
        if (original != null) {
            BeanUtils.copyProperties(original, example);
        }
        T fromTemplate = findTemplateByName(templateName);
        BeanUtils.copyPropertiesNotNull(fromTemplate, example);
        return example;
    }

    public T create() {
        final T t = build();
        persist(t);
        return t;
    }

    public T create(final T original) {
        T example = build(original);
        persist(example);
        return example;
    }

    public T create(final String templateName) {
        T example = build(templateName);
        persist(example);
        return example;
    }

    public T create(final T original, final String templateName) {
        T example = build(original, templateName);
        persist(example);
        return example;
    }

    public List<T> create(final int count) {
        return IntStream.range(0, count).boxed()
                .map(i -> create())
                .collect(Collectors.toList());
    }

    public List<T> create(final int count, final T original) {
        return IntStream.range(0, count).boxed()
                .map(i -> create(original))
                .collect(Collectors.toList());
    }

    public List<T> create(final int count, final String templateName) {
        return IntStream.range(0, count).boxed()
                .map(i -> create(templateName))
                .collect(Collectors.toList());
    }

    public List<T> create(final int count, final T original, final String templateName) {
        return IntStream.range(0, count).boxed()
                .map(i -> create(original, templateName))
                .collect(Collectors.toList());
    }

    public List<T> build(final int count) {
        return IntStream.range(0, count).boxed()
                .map(i -> build())
                .collect(Collectors.toList());
    }

    public List<T> build(final int count, final T original) {
        return IntStream.range(0, count).boxed()
                .map(i -> build(original))
                .collect(Collectors.toList());
    }

    public List<T> build(final int count, final String templateName) {
        return IntStream.range(0, count).boxed()
                .map(i -> build(templateName))
                .collect(Collectors.toList());
    }

    public List<T> build(final int count, final T original, final String templateName) {
        return IntStream.range(0, count).boxed()
                .map(i -> build(original, templateName))
                .collect(Collectors.toList());
    }

    protected abstract T getDefault();

    protected abstract T getEmpty();

    protected abstract void persist(T t);

    private T findTemplateByName(final String name) {
        if (name == null) {
            return getDefault();
        }

        return jBaconUtil.findTemplateByName(name);
    }
}
