package br.com.leonardoferreira.jbacon;

@FunctionalInterface
public interface JBaconExample<T> {

    void apply(T empty);

}
