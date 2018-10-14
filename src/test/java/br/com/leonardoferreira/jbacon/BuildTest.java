package br.com.leonardoferreira.jbacon;

import br.com.leonardoferreira.jbacon.annotation.JBaconTemplate;
import br.com.leonardoferreira.jbacon.domain.SimpleClass;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by lferreira on 2/4/18
 */
public class BuildTest {

    @Test
    public void buildTest() {
        AtomicInteger emptyIsCalled = new AtomicInteger();
        AtomicInteger defaultIsCalled = new AtomicInteger();
        AtomicInteger persistIsCalled = new AtomicInteger();

        JBacon<SimpleClass> jBacon = new JBacon<SimpleClass>() {
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
            protected void persist(final SimpleClass simpleClass) {
                persistIsCalled.incrementAndGet();
            }
        };

        SimpleClass simpleClass = jBacon.build();

        Assertions.assertEquals("buildTest", simpleClass.getSimpleStr());

        Assertions.assertAll(
                () -> Assertions.assertEquals(1, emptyIsCalled.get()),
                () -> Assertions.assertEquals(1, defaultIsCalled.get()),
                () -> Assertions.assertEquals(0, persistIsCalled.get()));
    }

    @Test
    public void buildWithTemplateTest() {
        AtomicInteger emptyIsCalled = new AtomicInteger();
        AtomicInteger defaultIsCalled = new AtomicInteger();
        AtomicInteger templateIsCalled = new AtomicInteger();
        AtomicInteger persistIsCalled = new AtomicInteger();

        JBacon<SimpleClass> jBacon = new JBacon<SimpleClass>() {
            @Override
            protected SimpleClass getDefault() {
                defaultIsCalled.incrementAndGet();
                return new SimpleClass("buildWithTemplateTest");
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

        SimpleClass simpleClass = jBacon.build("template");

        Assertions.assertEquals("buildWithTemplateTest_template", simpleClass.getSimpleStr());

        Assertions.assertAll("methods calls",
                () -> Assertions.assertEquals(1, emptyIsCalled.get()),
                () -> Assertions.assertEquals(0, defaultIsCalled.get()),
                () -> Assertions.assertEquals(1, templateIsCalled.get()),
                () -> Assertions.assertEquals(0, persistIsCalled.get()));
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
                return new SimpleClass("buildWithExampleTest", 10);
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

        SimpleClass simpleClass = jBacon.build(example);

        Assertions.assertAll("simpleClass content",
                () -> Assertions.assertEquals(Integer.valueOf(10), simpleClass.getSimpleInteger()),
                () -> Assertions.assertEquals(example.getSimpleStr(), simpleClass.getSimpleStr()));

        Assertions.assertAll("methods calls",
                () -> Assertions.assertEquals(1, emptyIsCalled.get()),
                () -> Assertions.assertEquals(1, defaultIsCalled.get()),
                () -> Assertions.assertEquals(0, persistIsCalled.get()));
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
                return new SimpleClass("buildWithLambdaExampleTest", 10);
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

        SimpleClass simpleClass = jBacon.build(empty ->
                empty.setSimpleStr("buildWithLambdaExampleTest_example"));

        Assertions.assertAll("simpleClass content",
                () -> Assertions.assertEquals(Integer.valueOf(10), simpleClass.getSimpleInteger()),
                () -> Assertions.assertEquals("buildWithLambdaExampleTest_example", simpleClass.getSimpleStr()));

        Assertions.assertAll("methods calls",
                () -> Assertions.assertEquals(2, emptyIsCalled.get()),
                () -> Assertions.assertEquals(1, defaultIsCalled.get()),
                () -> Assertions.assertEquals(0, persistIsCalled.get()));
    }

    @Test
    public void buildWithExampleAndTemplateTest() {
        AtomicInteger emptyIsCalled = new AtomicInteger();
        AtomicInteger defaultIsCalled = new AtomicInteger();
        AtomicInteger templateIsCalled = new AtomicInteger();
        AtomicInteger persistIsCalled = new AtomicInteger();

        JBacon<SimpleClass> jBacon = new JBacon<SimpleClass>() {
            @Override
            protected SimpleClass getDefault() {
                defaultIsCalled.incrementAndGet();
                return new SimpleClass("buildWithExampleAndTemplateTest", 10);
            }

            @Override
            protected SimpleClass getEmpty() {
                emptyIsCalled.incrementAndGet();
                return new SimpleClass();
            }

            @JBaconTemplate("template")
            protected SimpleClass template() {
                templateIsCalled.incrementAndGet();
                return new SimpleClass("buildWithExampleAndTemplateTest_template", 20);
            }

            @Override
            protected void persist(final SimpleClass simpleClass) {
                persistIsCalled.incrementAndGet();
            }
        };

        SimpleClass example = new SimpleClass("buildWithExampleAndTemplateTest_example");

        SimpleClass simpleClass = jBacon.build(example, "template");

        Assertions.assertAll("simpleClass content",
                () -> Assertions.assertEquals(example.getSimpleStr(), simpleClass.getSimpleStr()),
                () -> Assertions.assertEquals(Integer.valueOf(20), simpleClass.getSimpleInteger()));

        Assertions.assertAll("methods calls",
                () -> Assertions.assertEquals(1, emptyIsCalled.get()),
                () -> Assertions.assertEquals(0, defaultIsCalled.get()),
                () -> Assertions.assertEquals(1, templateIsCalled.get()),
                () -> Assertions.assertEquals(0, persistIsCalled.get()));
    }

    @Test
    public void buildWithLambdaExampleAndTemplateTest() {
        AtomicInteger emptyIsCalled = new AtomicInteger();
        AtomicInteger defaultIsCalled = new AtomicInteger();
        AtomicInteger templateIsCalled = new AtomicInteger();
        AtomicInteger persistIsCalled = new AtomicInteger();

        JBacon<SimpleClass> jBacon = new JBacon<SimpleClass>() {
            @Override
            protected SimpleClass getDefault() {
                defaultIsCalled.incrementAndGet();
                return new SimpleClass("buildWithLambdaExampleAndTemplateTest", 10);
            }

            @Override
            protected SimpleClass getEmpty() {
                emptyIsCalled.incrementAndGet();
                return new SimpleClass();
            }

            @JBaconTemplate("template")
            protected SimpleClass template() {
                templateIsCalled.incrementAndGet();
                return new SimpleClass("buildWithLambdaExampleAndTemplateTest_template", 20);
            }

            @Override
            protected void persist(final SimpleClass simpleClass) {
                persistIsCalled.incrementAndGet();
            }
        };

        SimpleClass simpleClass = jBacon.build("template", empty ->
                empty.setSimpleStr("buildWithExampleAndTemplateTest_example"));

        Assertions.assertAll("simpleClass content",
                () -> Assertions.assertEquals("buildWithExampleAndTemplateTest_example", simpleClass.getSimpleStr()),
                () -> Assertions.assertEquals(Integer.valueOf(20), simpleClass.getSimpleInteger()));

        Assertions.assertAll("methods calls",
                () -> Assertions.assertEquals(2, emptyIsCalled.get()),
                () -> Assertions.assertEquals(0, defaultIsCalled.get()),
                () -> Assertions.assertEquals(1, templateIsCalled.get()),
                () -> Assertions.assertEquals(0, persistIsCalled.get()));
    }

}
