package br.com.leonardoferreira.jbacon.operation.function;

/**
 * Created by lferreira on 2/9/18
 */
@FunctionalInterface
public interface GetFromTemplate<T> {
    T call(String template);
}
