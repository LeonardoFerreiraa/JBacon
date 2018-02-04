package br.com.leonardoferreira.jbacon.factory;

import br.com.leonardoferreira.jbacon.JBacon;
import br.com.leonardoferreira.jbacon.domain.SimpleClass;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lferreira on 6/16/17.
 */
public class JBaconInstance extends JBacon<SimpleClass> {

    @Getter
    private List<SimpleClass> database = new ArrayList<>();

    @Override
    protected SimpleClass getDefault() {
        SimpleClass simpleClass = new SimpleClass();

        simpleClass.setSimpleBigDecimal(BigDecimal.ZERO);
        simpleClass.setSimpleInteger(123321);
        simpleClass.setSimpleStr("JBacon");

        return simpleClass;
    }

    @Override
    protected SimpleClass getEmpty() {
        return new SimpleClass();
    }

    @Override
    protected void persist(SimpleClass simpleClass) {
        database.add(simpleClass);
    }

}
