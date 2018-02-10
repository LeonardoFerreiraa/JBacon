package br.com.leonardoferreira.jbacon.operation.function;

/**
 * Created by lferreira on 2/9/18
 */
@FunctionalInterface
public interface Persist<T> {
    void call(T t);
}
