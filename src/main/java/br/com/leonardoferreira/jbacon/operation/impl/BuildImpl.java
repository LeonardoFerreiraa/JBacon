package br.com.leonardoferreira.jbacon.operation.impl;

import br.com.leonardoferreira.jbacon.operation.Build;
import br.com.leonardoferreira.jbacon.operation.function.FunctionList;
import br.com.leonardoferreira.jbacon.util.BeanUtils;
import lombok.AllArgsConstructor;

/**
 * Created by lferreira on 2/4/18
 */
@AllArgsConstructor
public class BuildImpl<T> implements Build<T> {

    private FunctionList<T> functionList;

    @Override
    public T build() {
        return build(null, null);
    }

    @Override
    public T build(final String templateName) {
        return build(null, templateName);
    }

    @Override
    public T build(final T original) {
        return build(original, null);
    }

    @Override
    public T build(final T original, final String templateName) {
        T example = functionList.getEmpty();
        if (original != null) {
            BeanUtils.copyProperties(original, example);
        }
        T fromTemplate = functionList.getFromTemplate(templateName);
        BeanUtils.copyPropertiesNotNull(fromTemplate, example);
        return example;
    }

}
