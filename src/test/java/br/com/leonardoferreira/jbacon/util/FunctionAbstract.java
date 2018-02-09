package br.com.leonardoferreira.jbacon.util;

import br.com.leonardoferreira.jbacon.JBacon;
import br.com.leonardoferreira.jbacon.operation.function.FunctionList;

/**
 * Created by lferreira on 2/9/18
 */
public abstract class FunctionAbstract<T> extends JBacon<T> {

    public FunctionList<T> getFunctionList() {
        return new FunctionList<>(this::getEmpty, this::getFromTemplate, this::persist);
    }

}
