package br.com.leonardoferreira.jbacon.operation;

import br.com.leonardoferreira.jbacon.JBacon;
import br.com.leonardoferreira.jbacon.annotation.JBaconTemplate;
import br.com.leonardoferreira.jbacon.domain.SimpleClass;
import br.com.leonardoferreira.jbacon.operation.impl.BuildImpl;
import br.com.leonardoferreira.jbacon.util.FunctionAbstract;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by lferreira on 2/4/18
 */
@RunWith(JUnit4.class)
public class BuildTest {

    @Test
    public void buildTest() {
        AtomicInteger emptyIsCalled = new AtomicInteger();
        AtomicInteger defaultIsCalled = new AtomicInteger();

        FunctionAbstract<SimpleClass> functionAbstract = new FunctionAbstract<SimpleClass>() {
            @Override
            public SimpleClass getDefault() {
                defaultIsCalled.incrementAndGet();
                return new SimpleClass("buildTest");
            }

            @Override
            public SimpleClass getEmpty() {
                emptyIsCalled.incrementAndGet();
                return new SimpleClass();
            }

            @Override
            public void persist(SimpleClass simpleClass) {

            }
        };

        Build<SimpleClass> build = new BuildImpl<>(functionAbstract.getFunctionList());

        SimpleClass simpleClass = build.build();
        Assertions.assertThat(simpleClass)
                .isNotNull();

        Assertions.assertThat(simpleClass.getSimpleStr())
                .isEqualTo("buildTest");

        Assertions.assertThat(emptyIsCalled.get())
                .isEqualTo(1);
        Assertions.assertThat(defaultIsCalled.get())
                .isEqualTo(1);
    }

    @Test
    public void buildWithTemplateTest() {
        AtomicInteger emptyIsCalled = new AtomicInteger();
        AtomicInteger defaultIsCalled = new AtomicInteger();
        AtomicInteger templateIsCalled = new AtomicInteger();

        FunctionAbstract<SimpleClass> functionAbstract = new FunctionAbstract<SimpleClass>() {
            @Override
            public SimpleClass getDefault() {
                defaultIsCalled.incrementAndGet();
                return new SimpleClass("buildWithTemplateTest");
            }

            @Override
            public SimpleClass getEmpty() {
                emptyIsCalled.incrementAndGet();
                return new SimpleClass();
            }

            @JBaconTemplate("template")
            public SimpleClass template() {
                templateIsCalled.incrementAndGet();
                return new SimpleClass("buildWithTemplateTest_template");
            }

            @Override
            public void persist(SimpleClass simpleClass) {

            }
        };

        Build<SimpleClass> build = new BuildImpl<>(functionAbstract.getFunctionList());

        SimpleClass simpleClass = build.build("template");
        Assertions.assertThat(simpleClass)
                .isNotNull();

        Assertions.assertThat(simpleClass.getSimpleStr())
                .isEqualTo("buildWithTemplateTest_template");

        Assertions.assertThat(emptyIsCalled.get())
                .isEqualTo(1);
        Assertions.assertThat(defaultIsCalled.get())
                .isEqualTo(0);
        Assertions.assertThat(templateIsCalled.get())
                .isEqualTo(1);
    }

    @Test
    public void buildWithExampleTest() {
        AtomicInteger emptyIsCalled = new AtomicInteger();
        AtomicInteger defaultIsCalled = new AtomicInteger();

        FunctionAbstract<SimpleClass> functionAbstract = new FunctionAbstract<SimpleClass>() {
            @Override
            public SimpleClass getDefault() {
                defaultIsCalled.incrementAndGet();
                return new SimpleClass("buildWithExampleTest", 10);
            }

            @Override
            public SimpleClass getEmpty() {
                emptyIsCalled.incrementAndGet();
                return new SimpleClass();
            }

            @Override
            public void persist(SimpleClass simpleClass) {

            }
        };

        Build<SimpleClass> build = new BuildImpl<>(functionAbstract.getFunctionList());
        SimpleClass example = new SimpleClass("buildWithExampleTest_example");

        SimpleClass simpleClass = build.build(example);
        Assertions.assertThat(simpleClass)
                .isNotNull();

        Assertions.assertThat(simpleClass.getSimpleStr())
                .isEqualTo(example.getSimpleStr());

        Assertions.assertThat(simpleClass.getSimpleInteger())
                .isEqualTo(10);

        Assertions.assertThat(emptyIsCalled.get())
                .isEqualTo(1);
        Assertions.assertThat(defaultIsCalled.get())
                .isEqualTo(1);
    }

    @Test
    public void buildWithExampleAndTemplateTest() {
        AtomicInteger emptyIsCalled = new AtomicInteger();
        AtomicInteger defaultIsCalled = new AtomicInteger();
        AtomicInteger templateIsCalled = new AtomicInteger();

        FunctionAbstract<SimpleClass> functionAbstract = new FunctionAbstract<SimpleClass>() {
            @Override
            public SimpleClass getDefault() {
                defaultIsCalled.incrementAndGet();
                return new SimpleClass("buildWithExampleAndTemplateTest", 10);
            }

            @Override
            public SimpleClass getEmpty() {
                emptyIsCalled.incrementAndGet();
                return new SimpleClass();
            }

            @JBaconTemplate("template")
            public SimpleClass template() {
                templateIsCalled.incrementAndGet();
                return new SimpleClass("buildWithExampleAndTemplateTest_template", 20);
            }

            @Override
            public void persist(SimpleClass simpleClass) {

            }
        };

        Build<SimpleClass> build = new BuildImpl<>(functionAbstract.getFunctionList());
        SimpleClass example = new SimpleClass("buildWithExampleAndTemplateTest_example");

        SimpleClass simpleClass = build.build(example, "template");
        Assertions.assertThat(simpleClass)
                .isNotNull();

        Assertions.assertThat(simpleClass.getSimpleStr())
                .isEqualTo(example.getSimpleStr());

        Assertions.assertThat(simpleClass.getSimpleInteger())
                .isEqualTo(20);

        Assertions.assertThat(emptyIsCalled.get())
                .isEqualTo(1);
        Assertions.assertThat(defaultIsCalled.get())
                .isEqualTo(0);
        Assertions.assertThat(templateIsCalled.get())
                .isEqualTo(1);
    }

}
