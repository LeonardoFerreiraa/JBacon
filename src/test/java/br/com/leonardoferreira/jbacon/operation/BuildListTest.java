package br.com.leonardoferreira.jbacon.operation;

import br.com.leonardoferreira.jbacon.annotation.JBaconTemplate;
import br.com.leonardoferreira.jbacon.domain.SimpleClass;
import br.com.leonardoferreira.jbacon.factory.JBaconFunctionInstance;
import br.com.leonardoferreira.jbacon.operation.impl.BuildListImpl;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Buildd by lferreira on 3/11/18
 */
@RunWith(JUnit4.class)
public class BuildListTest {

    @Test
    public void buildTest() {
        AtomicInteger emptyIsCalled = new AtomicInteger();
        AtomicInteger defaultIsCalled = new AtomicInteger();
        AtomicInteger persistIsCalled = new AtomicInteger();

        JBaconFunctionInstance<SimpleClass> JBaconFunctionInstance = new JBaconFunctionInstance<SimpleClass>() {
            @Override
            protected SimpleClass getDefault() {
                defaultIsCalled.incrementAndGet();
                return new SimpleClass("buildTest");
            }

            @Override
            protected SimpleClass getEmpty() {
                emptyIsCalled.incrementAndGet();
                return new SimpleClass();
            }

            @Override
            protected void persist(SimpleClass simpleClass) {
                persistIsCalled.incrementAndGet();
            }
        };

        BuildList<SimpleClass> buildList = new BuildListImpl<>(JBaconFunctionInstance.getFunctionList());

        List<SimpleClass> simpleClasses = buildList.build(5);

        Assertions.assertThat(emptyIsCalled.get())
                .isEqualTo(5);
        Assertions.assertThat(defaultIsCalled.get())
                .isEqualTo(5);
        Assertions.assertThat(persistIsCalled.get())
                .isZero();

        Assertions.assertThat(simpleClasses)
                .isNotNull()
                .hasSize(5);
    }

    @Test
    public void buildWithExampleTest() {
        AtomicInteger emptyIsCalled = new AtomicInteger();
        AtomicInteger defaultIsCalled = new AtomicInteger();
        AtomicInteger persistIsCalled = new AtomicInteger();

        JBaconFunctionInstance<SimpleClass> JBaconFunctionInstance = new JBaconFunctionInstance<SimpleClass>() {
            @Override
            protected SimpleClass getDefault() {
                defaultIsCalled.incrementAndGet();
                return new SimpleClass("buildWithExampleTest", 1);
            }

            @Override
            protected SimpleClass getEmpty() {
                emptyIsCalled.incrementAndGet();
                return new SimpleClass();
            }

            @Override
            protected void persist(SimpleClass simpleClass) {
                persistIsCalled.incrementAndGet();
            }
        };

        BuildList<SimpleClass> build = new BuildListImpl<>(JBaconFunctionInstance.getFunctionList());
        SimpleClass example = new SimpleClass("buildWithExampleTest_example");

        List<SimpleClass> simpleClasses = build.build(5, example);

        Assertions.assertThat(emptyIsCalled.get())
                .isEqualTo(5);
        Assertions.assertThat(defaultIsCalled.get())
                .isEqualTo(5);
        Assertions.assertThat(persistIsCalled.get())
                .isZero();

        Assertions.assertThat(simpleClasses)
                .isNotEmpty()
                .hasSize(5);

        for (SimpleClass simpleClass : simpleClasses) {
            Assertions.assertThat(simpleClass.getSimpleStr())
                    .isEqualTo(example.getSimpleStr());
            Assertions.assertThat(simpleClass.getSimpleInteger())
                    .isEqualTo(1);
        }
    }

    @Test
    public void buildWithTemplateTest() {
        AtomicInteger emptyIsCalled = new AtomicInteger();
        AtomicInteger defaultIsCalled = new AtomicInteger();
        AtomicInteger persistIsCalled = new AtomicInteger();
        AtomicInteger templateIsCalled = new AtomicInteger();


        JBaconFunctionInstance<SimpleClass> JBaconFunctionInstance = new JBaconFunctionInstance<SimpleClass>() {
            @Override
            protected SimpleClass getDefault() {
                defaultIsCalled.incrementAndGet();
                return new SimpleClass("buildWithTemplateTest", 1);
            }

            @Override
            protected SimpleClass getEmpty() {
                emptyIsCalled.incrementAndGet();
                return new SimpleClass();
            }

            @JBaconTemplate("template")
            protected SimpleClass template() {
                templateIsCalled.incrementAndGet();
                return new SimpleClass("buildWithTemplateTest_template");
            }

            @Override
            protected void persist(SimpleClass simpleClass) {
                persistIsCalled.incrementAndGet();
            }
        };

        BuildList<SimpleClass> build = new BuildListImpl<>(JBaconFunctionInstance.getFunctionList());

        List<SimpleClass> simpleClasses = build.build(5, "template");

        Assertions.assertThat(emptyIsCalled.get())
                .isEqualTo(5);
        Assertions.assertThat(defaultIsCalled.get())
                .isZero();
        Assertions.assertThat(persistIsCalled.get())
                .isZero();
        Assertions.assertThat(templateIsCalled.get())
                .isEqualTo(5);

        Assertions.assertThat(simpleClasses)
                .isNotEmpty()
                .hasSize(5);

        for (SimpleClass simpleClass : simpleClasses) {
            Assertions.assertThat(simpleClass.getSimpleStr())
                    .isEqualTo("buildWithTemplateTest_template");
        }
    }

    @Test
    public void buildWithTemplateAndExampleTest() {
        AtomicInteger emptyIsCalled = new AtomicInteger();
        AtomicInteger defaultIsCalled = new AtomicInteger();
        AtomicInteger persistIsCalled = new AtomicInteger();
        AtomicInteger templateIsCalled = new AtomicInteger();

        JBaconFunctionInstance<SimpleClass> JBaconFunctionInstance = new JBaconFunctionInstance<SimpleClass>() {
            @Override
            protected SimpleClass getDefault() {
                defaultIsCalled.incrementAndGet();
                return new SimpleClass("buildWithTemplateAndExampleTest", 1);
            }

            @Override
            protected SimpleClass getEmpty() {
                emptyIsCalled.incrementAndGet();
                return new SimpleClass();
            }

            @JBaconTemplate("template")
            protected SimpleClass template() {
                templateIsCalled.incrementAndGet();
                return new SimpleClass("buildWithTemplateAndExampleTest_template", 2);
            }

            @Override
            protected void persist(SimpleClass simpleClass) {
                persistIsCalled.incrementAndGet();
            }
        };

        BuildList<SimpleClass> build = new BuildListImpl<>(JBaconFunctionInstance.getFunctionList());
        SimpleClass example = new SimpleClass("buildWithTemplateAndExampleTest_example");

        List<SimpleClass> simpleClasses = build.build(5, example, "template");

        Assertions.assertThat(emptyIsCalled.get())
                .isEqualTo(5);
        Assertions.assertThat(templateIsCalled.get())
                .isEqualTo(5);
        Assertions.assertThat(defaultIsCalled.get())
                .isZero();
        Assertions.assertThat(persistIsCalled.get())
                .isZero();

        Assertions.assertThat(simpleClasses)
                .isNotEmpty()
                .hasSize(5)
                .isEqualTo(simpleClasses);

        for (SimpleClass simpleClass : simpleClasses) {
            Assertions.assertThat(simpleClass.getSimpleStr())
                    .isEqualTo(example.getSimpleStr());
            Assertions.assertThat(simpleClass.getSimpleInteger())
                    .isEqualTo(2);
        }
    }
}

