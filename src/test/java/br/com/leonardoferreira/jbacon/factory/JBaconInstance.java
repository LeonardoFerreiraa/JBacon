package br.com.leonardoferreira.jbacon.factory;

import br.com.leonardoferreira.jbacon.JBacon;
import br.com.leonardoferreira.jbacon.annotation.JBaconTemplate;
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
    public SimpleClass getDefault() {
        SimpleClass simpleClass = new SimpleClass();

        simpleClass.setSimpleBigDecimal(BigDecimal.ZERO);
        simpleClass.setSimpleInteger(123321);
        simpleClass.setSimpleStr("JBacon");

        return simpleClass;
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
    public SimpleClass getEmpty() {
        return new SimpleClass();
    }

    @Override
    public void persist(SimpleClass simpleClass) {
        database.add(simpleClass);
    }

}
