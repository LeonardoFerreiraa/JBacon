package br.com.leonardoferreira.jbacon.factory;

import br.com.leonardoferreira.jbacon.JBacon;
import br.com.leonardoferreira.jbacon.annotation.JBaconTemplate;
import br.com.leonardoferreira.jbacon.domain.SimpleClass;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by lferreira on 6/16/17.
 */
@Getter
public class JBaconInstance extends JBacon<SimpleClass> {

    private int emptyIsCalled;

    private int defaultIsCalled;

    private int persistIsCalled;

    private int templateIsCalled;

    private int invalidIsCalled;

    private List<SimpleClass> database = new ArrayList<>();

    @Override
    protected SimpleClass getDefault() {
        defaultIsCalled++;
        SimpleClass simpleClass = new SimpleClass();

        simpleClass.setSimpleBigDecimal(BigDecimal.ZERO);
        simpleClass.setSimpleInteger(123321);
        simpleClass.setSimpleStr("JBacon");

        return simpleClass;
    }

    @JBaconTemplate("invalid")
    protected SimpleClass invalid() {
        invalidIsCalled++;
        SimpleClass simpleClass = new SimpleClass();
        simpleClass.setSimpleStr("INVALID");
        simpleClass.setSimpleInteger(-1);
        simpleClass.setSimpleBigDecimal(new BigDecimal("-1.00"));

        return simpleClass;
    }

    @JBaconTemplate("template")
    protected SimpleClass template() {
        templateIsCalled++;
        SimpleClass simpleClass = new SimpleClass();

        simpleClass.setSimpleBigDecimal(BigDecimal.ZERO);
        simpleClass.setSimpleStr("JBaconTemplate");
        simpleClass.setSimpleInteger(2);
        return simpleClass;
    }

    @Override
    protected SimpleClass getEmpty() {
        emptyIsCalled++;
        return new SimpleClass();
    }

    @Override
    protected void persist(SimpleClass simpleClass) {
        persistIsCalled++;
        database.add(simpleClass);
    }

}
