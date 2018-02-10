package br.com.leonardoferreira.jbacon.operation.function;

import lombok.AllArgsConstructor;

/**
 * Created by lferreira on 2/9/18
 */
@AllArgsConstructor
public class FunctionList<T> {

    private GetEmpty<T> getEmpty;

    private GetFromTemplate<T> getFromTemplate;

    private Persist<T> persist;

    public T getEmpty() {
        return getEmpty.call();
    }

    public T getFromTemplate(String template) {
        return getFromTemplate.call(template);
    }

    public void persist(T t) {
        persist.call(t);
    }


}
