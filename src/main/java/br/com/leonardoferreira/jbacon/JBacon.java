package br.com.leonardoferreira.jbacon;

import br.com.leonardoferreira.jbacon.util.CopyProperties;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @param <T> Target of factory
 */
public abstract class JBacon<T> {

    private final TemplateResolver<T> templateResolver;

    public JBacon() {
        templateResolver = new TemplateResolver<>(this);
    }

    /**
     * Builds a object based on {@link #getDefault()}
     *
     * @return Not Persisted object
     */
    public T build() {
        return build(null, (String) null);
    }

    /**
     * Builds a object based on template
     *
     * @param templateName name of template defined with {@link br.com.leonardoferreira.jbacon.annotation.JBaconTemplate}
     * @return Not Persisted object
     */
    public T build(final String templateName) {
        return build(null, templateName);
    }

    /**
     * Builds a object based on example and override all null attributes using {@link #getDefault()}
     *
     * @param example Example object that will be used to build the target object
     * @return Not Persisted object
     */
    public T build(final T example) {
        return build(example, null);
    }

    /**
     * Builds a object based on example and override all null attributes using {@link #getDefault()}
     *
     * @param example Example object that will be used to build the target object
     * @return Not Persisted object
     * @see JBaconExample
     */
    public T build(final JBaconExample<T> example) {
        T empty = getEmpty();
        example.apply(empty);
        return build(empty);
    }

    /**
     * Builds a object based on example and override all null attributes using the template
     *
     * @param example      Example object that will be used to build the target object
     * @param templateName Name of template defined with {@link br.com.leonardoferreira.jbacon.annotation.JBaconTemplate}
     * @return Not Persisted object
     */
    public T build(final T example, final String templateName) {
        T empty = getEmpty();
        if (example != null) {
            CopyProperties.copy(empty, example);
        }
        T fromTemplate = templateResolver.findTemplateByName(templateName);
        CopyProperties.copy(empty, fromTemplate);
        return empty;
    }

    /**
     * Builds a object based on example and override all null attributes using the template
     *
     * @param example      Example object that will be used to build the target object
     * @param templateName Name of template defined with {@link br.com.leonardoferreira.jbacon.annotation.JBaconTemplate}
     * @return Not Persisted object
     * @see JBaconExample
     */
    public T build(final String templateName, final JBaconExample<T> example) {
        T empty = getEmpty();
        example.apply(empty);
        return build(empty, templateName);
    }

    /**
     * Builds a object based on {@link #getDefault()} and persist
     *
     * @return Persisted object
     * @see #build()
     * @see #persist(Object)
     */
    public T create() {
        final T t = build();
        persist(t);
        return t;
    }

    /**
     * Builds a object based on example, override all null attributes using {@link #getDefault()} and persist
     *
     * @param example Example object that will be used to build the target object
     * @return Persisted object
     * @see #build(Object)
     * @see #persist(Object)
     */
    public T create(final T example) {
        T build = build(example);
        persist(build);
        return build;
    }

    /**
     * Builds a object based on example, override all null attributes using {@link #getDefault()} and persist
     *
     * @param example Example object that will be used to build the target object
     * @return Persisted object
     * @see JBaconExample
     * @see #build(JBaconExample)
     * @see #persist(Object)
     */
    public T create(final JBaconExample<T> example) {
        T empty = getEmpty();
        example.apply(empty);
        return create(empty);
    }

    /**
     * Builds a object based on template and persist
     *
     * @param templateName Name of template defined with {@link br.com.leonardoferreira.jbacon.annotation.JBaconTemplate}
     * @return Persisted object
     * @see #build(String)
     * @see #persist(Object)
     */
    public T create(final String templateName) {
        T build = build(templateName);
        persist(build);
        return build;
    }

    /**
     * Builds a object based on example, override all null attributes using template and persist
     *
     * @param example      Example object that will be used to build the target object
     * @param templateName Name of template defined with {@link br.com.leonardoferreira.jbacon.annotation.JBaconTemplate}
     * @return Persisted object
     * @see #build(Object, String)
     * @see #persist(Object)
     */
    public T create(final T example, final String templateName) {
        T build = build(example, templateName);
        persist(build);
        return build;
    }

    /**
     * Builds a object based on example, override all null attributes using example and persist
     *
     * @param example      Example object that will be used to build the target object
     * @param templateName Name of template defined with {@link br.com.leonardoferreira.jbacon.annotation.JBaconTemplate}
     * @return Persisted object
     * @see JBaconExample
     * @see #build(String, JBaconExample)
     * @see #persist(Object)
     */
    public T create(final String templateName, final JBaconExample<T> example) {
        T empty = getEmpty();
        example.apply(empty);
        return create(empty, templateName);
    }

    /**
     * Creates a list of object
     *
     * @param count Quantity of object to be created
     * @return List of persisted object
     * @see #create()
     */
    public List<T> create(final int count) {
        return IntStream.range(0, count).boxed()
                .map(i -> create())
                .collect(Collectors.toList());
    }

    /**
     * Creates a list of object
     *
     * @param count   Quantity of object to be created
     * @param example Example object that will be used to build the target object
     * @return List of persisted object
     * @see #create(Object)
     */
    public List<T> create(final int count, final T example) {
        return IntStream.range(0, count).boxed()
                .map(i -> create(example))
                .collect(Collectors.toList());
    }

    /**
     * Creates a list of object
     *
     * @param count   Quantity of object to be created
     * @param example Example object that will be used to build the target object
     * @return List of persisted object
     * @see #create(JBaconExample)
     */
    public List<T> create(final int count, final JBaconExample<T> example) {
        T empty = getEmpty();
        example.apply(empty);
        return create(count, empty);
    }

    /**
     * Creates a list of object
     *
     * @param count        Quantity of object to be created
     * @param templateName Name of template defined with {@link br.com.leonardoferreira.jbacon.annotation.JBaconTemplate}
     * @return List of persisted object
     * @see #create(JBaconExample)
     */
    public List<T> create(final int count, final String templateName) {
        return IntStream.range(0, count).boxed()
                .map(i -> create(templateName))
                .collect(Collectors.toList());
    }

    /**
     * Creates a list of object
     *
     * @param count        Quantity of object to be created
     * @param example      Example object that will be used to build the target object
     * @param templateName Name of template defined with {@link br.com.leonardoferreira.jbacon.annotation.JBaconTemplate}
     * @return List of persisted object
     * @see #create(Object, String)
     */
    public List<T> create(final int count, final T example, final String templateName) {
        return IntStream.range(0, count).boxed()
                .map(i -> create(example, templateName))
                .collect(Collectors.toList());
    }

    /**
     * Creates a list of object
     *
     * @param count        Quantity of object to be created
     * @param example      Example object that will be used to build the target object
     * @param templateName Name of template defined with {@link br.com.leonardoferreira.jbacon.annotation.JBaconTemplate}
     * @return List of persisted object
     * @see #create(String, JBaconExample)
     */
    public List<T> create(final int count, final String templateName, final JBaconExample<T> example) {
        T empty = getEmpty();
        example.apply(empty);
        return create(count, empty, templateName);
    }

    /**
     * Builds a list of object
     *
     * @param count Quantity of object to be build
     * @return List of not persisted object
     * @see #build()
     */
    public List<T> build(final int count) {
        return IntStream.range(0, count).boxed()
                .map(i -> build())
                .collect(Collectors.toList());
    }

    /**
     * Builds a list of object
     *
     * @param count   Quantity of object to be build
     * @param example Example object that will be used to build the target object
     * @return List of not persisted object
     * @see #build(Object)
     */
    public List<T> build(final int count, final T example) {
        return IntStream.range(0, count).boxed()
                .map(i -> build(example))
                .collect(Collectors.toList());
    }

    /**
     * Builds a list of object
     *
     * @param count   Quantity of object to be build
     * @param example Example object that will be used to build the target object
     * @return List of not persisted object
     * @see #build(JBaconExample)
     */
    public List<T> build(final int count, final JBaconExample<T> example) {
        T empty = getEmpty();
        example.apply(empty);
        return build(count, empty);
    }

    /**
     * Builds a list of object
     *
     * @param count        Quantity of object to be build
     * @param templateName name of template defined with {@link br.com.leonardoferreira.jbacon.annotation.JBaconTemplate}
     * @return List of not persisted object
     * @see #build(String)
     */
    public List<T> build(final int count, final String templateName) {
        return IntStream.range(0, count).boxed()
                .map(i -> build(templateName))
                .collect(Collectors.toList());
    }

    /**
     * Builds a list of object
     *
     * @param count        Quantity of object to be build
     * @param example      Example object that will be used to build the target object
     * @param templateName Name of template defined with {@link br.com.leonardoferreira.jbacon.annotation.JBaconTemplate}
     * @return List of not persisted object
     * @see #build(Object, String)
     */
    public List<T> build(final int count, final T example, final String templateName) {
        return IntStream.range(0, count).boxed()
                .map(i -> build(example, templateName))
                .collect(Collectors.toList());
    }

    /**
     * Builds a list of object
     *
     * @param count        Quantity of object to be build
     * @param example      Example object that will be used to build the target object
     * @param templateName Name of template defined with {@link br.com.leonardoferreira.jbacon.annotation.JBaconTemplate}
     * @return List of not persisted object
     * @see #build(String, JBaconExample)
     */
    public List<T> build(final int count, final String templateName, final JBaconExample<T> example) {
        T empty = getEmpty();
        example.apply(empty);
        return build(count, empty, templateName);
    }

    /**
     * Example:
     * <pre>
     * {@code
     * protected Contact getDefault() {
     *     Contact contact = new Contact();
     *     contact.setName(faker.name().fullName());
     *     contact.setEmail(faker.internet().emailAddress());
     *     return contact;
     * }
     * }
     * </pre>
     *
     * @return Object that will be used as base to create objects
     */
    protected abstract T getDefault();

    /**
     * Example:
     * <pre>
     * {@code
     * protected Contact getEmpty() {
     *     return new Contact();
     * }
     * }
     * </pre>
     *
     * @return Empty object
     */
    protected abstract T getEmpty();

    /**
     * Example:
     * <pre>
     * {@code
     * protected void persist(Contact contact) {
     *     repository.save(contact);
     * }
     * }
     * </pre>
     *
     * @param t Not persisted object
     */
    protected abstract void persist(T t);

}
