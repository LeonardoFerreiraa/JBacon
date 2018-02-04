package br.com.leonardoferreira.jbacon.factory;

import br.com.leonardoferreira.jbacon.JBacon;
import br.com.leonardoferreira.jbacon.annotation.JBaconTemplate;
import br.com.leonardoferreira.jbacon.domain.SimpleClass;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by lferreira on 2/4/18
 */
public class MultiTemplateJBacon extends JBacon<SimpleClass> {

    @Getter
    private List<SimpleClass> database;

    @Override
    public SimpleClass getDefault() {
        return invalid();
    }

    @Override
    public SimpleClass getEmpty() {
        return new SimpleClass();
    }

    @JBaconTemplate("invalid")
    public SimpleClass invalid() {
        SimpleClass simpleClass = new SimpleClass();
        simpleClass.setSimpleStr("INVALID");
        simpleClass.setSimpleInteger(-1);
        simpleClass.setSimpleBigDecimal(new BigDecimal("-1.00"));

        return simpleClass;
    }

    @Override
    public void persist(final SimpleClass simpleClass) {
        database.add(simpleClass);
    }
}
