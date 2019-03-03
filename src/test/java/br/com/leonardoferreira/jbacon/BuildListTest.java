package br.com.leonardoferreira.jbacon;

import br.com.leonardoferreira.jbacon.annotation.JBaconTemplate;
import br.com.leonardoferreira.jbacon.domain.SimpleClass;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BuildListTest {

    @Test
    public void buildTest() {
        AtomicInteger emptyIsCalled = new AtomicInteger();
        AtomicInteger defaultIsCalled = new AtomicInteger();
        AtomicInteger persistIsCalled = new AtomicInteger();

        JBacon<SimpleClass> jBacon = new JBacon<SimpleClass>() {
            @Override
            protected SimpleClass getDefault() {
                defaultIsCalled.incrementAndGet();
                return new SimpleClass("buildListTest");
            }

            @Override
            protected SimpleClass getEmpty() {
                emptyIsCalled.incrementAndGet();
                return new SimpleClass();
            }

            @Override
            protected void persist(final SimpleClass simpleClass) {
                persistIsCalled.incrementAndGet();
            }
        };

        List<SimpleClass> simpleClasses = jBacon.build(5);

        Assertions.assertAll("methods calls",
                () -> Assertions.assertEquals(5, emptyIsCalled.get()),
                () -> Assertions.assertEquals(5, defaultIsCalled.get()),
                () -> Assertions.assertEquals(0, persistIsCalled.get()));

        Assertions.assertEquals(5, simpleClasses.size());
    }

    @Test
    public void buildWithExampleTest() {
        AtomicInteger emptyIsCalled = new AtomicInteger();
        AtomicInteger defaultIsCalled = new AtomicInteger();
        AtomicInteger persistIsCalled = new AtomicInteger();

        JBacon<SimpleClass> jBacon = new JBacon<SimpleClass>() {
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
            protected void persist(final SimpleClass simpleClass) {
                persistIsCalled.incrementAndGet();
            }
        };

        SimpleClass example = new SimpleClass("buildWithExampleTest_example");

        List<SimpleClass> simpleClasses = jBacon.build(5, example);

        Assertions.assertEquals(5, simpleClasses.size());

        Assertions.assertAll("methods calls",
                () -> Assertions.assertEquals(5, emptyIsCalled.get()),
                () -> Assertions.assertEquals(5, defaultIsCalled.get()),
                () -> Assertions.assertEquals(0, persistIsCalled.get()));

        for (SimpleClass simpleClass : simpleClasses) {
            Assertions.assertAll("simpleClass content",
                    () -> Assertions.assertEquals(example.getSimpleStr(), simpleClass.getSimpleStr()),
                    () -> Assertions.assertEquals(Integer.valueOf(1), simpleClass.getSimpleInteger()));
        }
    }

    @Test
    public void buildWithLambdaExampleTest() {
        AtomicInteger emptyIsCalled = new AtomicInteger();
        AtomicInteger defaultIsCalled = new AtomicInteger();
        AtomicInteger persistIsCalled = new AtomicInteger();

        JBacon<SimpleClass> jBacon = new JBacon<SimpleClass>() {
            @Override
            protected SimpleClass getDefault() {
                defaultIsCalled.incrementAndGet();
                return new SimpleClass("buildWithLambdaExampleTest", 1);
            }

            @Override
            protected SimpleClass getEmpty() {
                emptyIsCalled.incrementAndGet();
                return new SimpleClass();
            }

            @Override
            protected void persist(final SimpleClass simpleClass) {
                persistIsCalled.incrementAndGet();
            }
        };

        List<SimpleClass> simpleClasses = jBacon.build(5, empty ->
                empty.setSimpleStr("buildWithLambdaExampleTest_example"));

        Assertions.assertEquals(5, simpleClasses.size());

        Assertions.assertAll("methods calls",
                () -> Assertions.assertEquals(6, emptyIsCalled.get()),
                () -> Assertions.assertEquals(5, defaultIsCalled.get()),
                () -> Assertions.assertEquals(0, persistIsCalled.get()));

        for (SimpleClass simpleClass : simpleClasses) {
            Assertions.assertAll("simpleClass content",
                    () -> Assertions.assertEquals("buildWithLambdaExampleTest_example", simpleClass.getSimpleStr()),
                    () -> Assertions.assertEquals(Integer.valueOf(1), simpleClass.getSimpleInteger()));
        }
    }

    @Test
    public void buildWithTemplateTest() {
        AtomicInteger emptyIsCalled = new AtomicInteger();
        AtomicInteger defaultIsCalled = new AtomicInteger();
        AtomicInteger persistIsCalled = new AtomicInteger();
        AtomicInteger templateIsCalled = new AtomicInteger();

        JBacon<SimpleClass> jBacon = new JBacon<SimpleClass>() {
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
            protected void persist(final SimpleClass simpleClass) {
                persistIsCalled.incrementAndGet();
            }
        };

        List<SimpleClass> simpleClasses = jBacon.build(5, "template");

        Assertions.assertEquals(5, simpleClasses.size());

        Assertions.assertAll("methods calls",
                () -> Assertions.assertEquals(5, emptyIsCalled.get()),
                () -> Assertions.assertEquals(0, defaultIsCalled.get()),
                () -> Assertions.assertEquals(0, persistIsCalled.get()),
                () -> Assertions.assertEquals(5, templateIsCalled.get()));

        for (SimpleClass simpleClass : simpleClasses) {
            Assertions.assertEquals("buildWithTemplateTest_template", simpleClass.getSimpleStr());
        }
    }

    @Test
    public void buildWithTemplateAndExampleTest() {
        AtomicInteger emptyIsCalled = new AtomicInteger();
        AtomicInteger defaultIsCalled = new AtomicInteger();
        AtomicInteger persistIsCalled = new AtomicInteger();
        AtomicInteger templateIsCalled = new AtomicInteger();

        JBacon<SimpleClass> jBacon = new JBacon<SimpleClass>() {
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
            protected void persist(final SimpleClass simpleClass) {
                persistIsCalled.incrementAndGet();
            }
        };

        SimpleClass example = new SimpleClass("buildWithTemplateAndExampleTest_example");

        List<SimpleClass> simpleClasses = jBacon.build(5, example, "template");

        Assertions.assertEquals(5, simpleClasses.size());

        Assertions.assertAll("methods calls",
                () -> Assertions.assertEquals(5, emptyIsCalled.get()),
                () -> Assertions.assertEquals(5, templateIsCalled.get()),
                () -> Assertions.assertEquals(0, defaultIsCalled.get()),
                () -> Assertions.assertEquals(0, persistIsCalled.get()));

        for (SimpleClass simpleClass : simpleClasses) {
            Assertions.assertAll(
                    () -> Assertions.assertEquals(example.getSimpleStr(), simpleClass.getSimpleStr()),
                    () -> Assertions.assertEquals(Integer.valueOf(2), simpleClass.getSimpleInteger()));
        }
    }

    @Test
    public void buildWithTemplateAndLambdaExampleTest() {
        AtomicInteger emptyIsCalled = new AtomicInteger();
        AtomicInteger defaultIsCalled = new AtomicInteger();
        AtomicInteger persistIsCalled = new AtomicInteger();
        AtomicInteger templateIsCalled = new AtomicInteger();

        JBacon<SimpleClass> jBacon = new JBacon<SimpleClass>() {
            @Override
            protected SimpleClass getDefault() {
                defaultIsCalled.incrementAndGet();
                return new SimpleClass("buildWithTemplateAndLambdaExampleTest", 1);
            }

            @Override
            protected SimpleClass getEmpty() {
                emptyIsCalled.incrementAndGet();
                return new SimpleClass();
            }

            @JBaconTemplate("template")
            protected SimpleClass template() {
                templateIsCalled.incrementAndGet();
                return new SimpleClass("buildWithTemplateAndLambdaExampleTest_template", 2);
            }

            @Override
            protected void persist(final SimpleClass simpleClass) {
                persistIsCalled.incrementAndGet();
            }
        };

        List<SimpleClass> simpleClasses = jBacon.build(5, "template", empty ->
                empty.setSimpleStr("buildWithTemplateAndLambdaExampleTest_example"));

        Assertions.assertEquals(5, simpleClasses.size());

        Assertions.assertAll("methods calls",
                () -> Assertions.assertEquals(6, emptyIsCalled.get()),
                () -> Assertions.assertEquals(5, templateIsCalled.get()),
                () -> Assertions.assertEquals(0, defaultIsCalled.get()),
                () -> Assertions.assertEquals(0, persistIsCalled.get()));

        for (SimpleClass simpleClass : simpleClasses) {
            Assertions.assertAll(
                    () -> Assertions.assertEquals("buildWithTemplateAndLambdaExampleTest_example", simpleClass.getSimpleStr()),
                    () -> Assertions.assertEquals(Integer.valueOf(2), simpleClass.getSimpleInteger()));
        }
    }
}

