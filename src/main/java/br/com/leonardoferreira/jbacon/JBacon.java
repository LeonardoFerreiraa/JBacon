package br.com.leonardoferreira.jbacon;

import br.com.leonardoferreira.jbacon.util.BeanUtils;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by lferreira on 2/4/18
 */
public abstract class JBacon<T> {

    private final TemplateResolver<T> templateResolver;

    public JBacon() {
        templateResolver = new TemplateResolver<>(this);
    }

    public T build() {
        return build(null, (String) null);
    }

    public T build(final String templateName) {
        return build(null, templateName);
    }

    public T build(final T example) {
        return build(example, null);
    }

    public T build(final JBaconExample<T> example) {
        T empty = getEmpty();
        example.apply(empty);
        return build(empty);
    }

    public T build(final T example, final String templateName) {
        T empty = getEmpty();
        if (example != null) {
            BeanUtils.copyProperties(example, empty);
        }
        T fromTemplate = templateResolver.findTemplateByName(templateName);
        BeanUtils.copyPropertiesNotNull(fromTemplate, empty);
        return empty;
    }

    public T build(final String templateName, final JBaconExample<T> example) {
        T empty = getEmpty();
        example.apply(empty);
        return build(empty, templateName);
    }

    public T create() {
        final T t = build();
        persist(t);
        return t;
    }

    public T create(final T example) {
        T build = build(example);
        persist(build);
        return build;
    }

    public T create(final JBaconExample<T> example) {
        T empty = getEmpty();
        example.apply(empty);
        return create(empty);
    }

    public T create(final String templateName) {
        T build = build(templateName);
        persist(build);
        return build;
    }

    public T create(final T example, final String templateName) {
        T build = build(example, templateName);
        persist(build);
        return build;
    }

    public T create(final String templateName, final JBaconExample<T> example) {
        T empty = getEmpty();
        example.apply(empty);
        return create(empty, templateName);
    }

    public List<T> create(final int count) {
        return IntStream.range(0, count).boxed()
                .map(i -> create())
                .collect(Collectors.toList());
    }

    public List<T> create(final int count, final T example) {
        return IntStream.range(0, count).boxed()
                .map(i -> create(example))
                .collect(Collectors.toList());
    }

    public List<T> create(final int count, final JBaconExample<T> example) {
        T empty = getEmpty();
        example.apply(empty);
        return create(count, empty);
    }

    public List<T> create(final int count, final String templateName) {
        return IntStream.range(0, count).boxed()
                .map(i -> create(templateName))
                .collect(Collectors.toList());
    }

    public List<T> create(final int count, final T example, final String templateName) {
        return IntStream.range(0, count).boxed()
                .map(i -> create(example, templateName))
                .collect(Collectors.toList());
    }

    public List<T> create(final int count, final String templateName, final JBaconExample<T> example) {
        T empty = getEmpty();
        example.apply(empty);
        return create(count, empty, templateName);
    }

    public List<T> build(final int count) {
        return IntStream.range(0, count).boxed()
                .map(i -> build())
                .collect(Collectors.toList());
    }

    public List<T> build(final int count, final T example) {
        return IntStream.range(0, count).boxed()
                .map(i -> build(example))
                .collect(Collectors.toList());
    }

    public List<T> build(final int count, final JBaconExample<T> example) {
        T empty = getEmpty();
        example.apply(empty);
        return build(count, empty);
    }

    public List<T> build(final int count, final String templateName) {
        return IntStream.range(0, count).boxed()
                .map(i -> build(templateName))
                .collect(Collectors.toList());
    }

    public List<T> build(final int count, final T example, final String templateName) {
        return IntStream.range(0, count).boxed()
                .map(i -> build(example, templateName))
                .collect(Collectors.toList());
    }

    public List<T> build(final int count, final String templateName, final JBaconExample<T> example) {
        T empty = getEmpty();
        example.apply(empty);
        return build(count, empty, templateName);
    }

    protected abstract T getDefault();

    protected abstract T getEmpty();

    protected abstract void persist(T t);

}
