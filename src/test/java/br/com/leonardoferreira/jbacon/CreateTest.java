package br.com.leonardoferreira.jbacon;

import br.com.leonardoferreira.jbacon.annotation.JBaconTemplate;
import br.com.leonardoferreira.jbacon.domain.SimpleClass;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by lferreira on 2/4/18
 */
public class CreateTest {

    @Test
    public void createTest() {
        AtomicInteger emptyIsCalled = new AtomicInteger();
        AtomicInteger defaultIsCalled = new AtomicInteger();
        AtomicInteger persistIsCalled = new AtomicInteger();

        List<SimpleClass> persisted = new ArrayList<>();

        JBacon<SimpleClass> jBacon = new JBacon<SimpleClass>() {
            @Override
            protected SimpleClass getDefault() {
                defaultIsCalled.incrementAndGet();
                return new SimpleClass("createTest");
            }

            @Override
            protected SimpleClass getEmpty() {
                emptyIsCalled.incrementAndGet();
                return new SimpleClass();
            }

            @Override
            protected void persist(final SimpleClass simpleClass) {
                persistIsCalled.incrementAndGet();
                persisted.add(simpleClass);
            }
        };

        SimpleClass simpleClass = jBacon.create();

        Assertions.assertAll("methods calls",
                () -> Assertions.assertEquals(1, emptyIsCalled.get()),
                () -> Assertions.assertEquals(1, defaultIsCalled.get()),
                () -> Assertions.assertEquals(1, persistIsCalled.get()));

        Assertions.assertEquals(1, persisted.size());
        Assertions.assertEquals(simpleClass, persisted.get(0));
    }

    @Test
    public void createWithExampleTest() {
        AtomicInteger emptyIsCalled = new AtomicInteger();
        AtomicInteger defaultIsCalled = new AtomicInteger();
        AtomicInteger persistIsCalled = new AtomicInteger();

        List<SimpleClass> persisted = new ArrayList<>();

        JBacon<SimpleClass> jBacon = new JBacon<SimpleClass>() {
            @Override
            protected SimpleClass getDefault() {
                defaultIsCalled.incrementAndGet();
                return new SimpleClass("createWithExampleTest", 1);
            }

            @Override
            protected SimpleClass getEmpty() {
                emptyIsCalled.incrementAndGet();
                return new SimpleClass();
            }

            @Override
            protected void persist(final SimpleClass simpleClass) {
                persistIsCalled.incrementAndGet();
                persisted.add(simpleClass);
            }
        };

        SimpleClass example = new SimpleClass("createWithExampleTest_example");

        SimpleClass simpleClass = jBacon.create(example);

        Assertions.assertAll("methods calls",
                () -> Assertions.assertEquals(1, emptyIsCalled.get()),
                () -> Assertions.assertEquals(1, defaultIsCalled.get()),
                () -> Assertions.assertEquals(1, persistIsCalled.get()));

        Assertions.assertEquals(1, persisted.size());

        Assertions.assertEquals(simpleClass, persisted.get(0));
        Assertions.assertAll("simpleClass content",
                () -> Assertions.assertEquals(example.getSimpleStr(), simpleClass.getSimpleStr()),
                () -> Assertions.assertEquals(Integer.valueOf(1), simpleClass.getSimpleInteger()));
    }

    @Test
    public void createWithLambdaExampleTest() {
        AtomicInteger emptyIsCalled = new AtomicInteger();
        AtomicInteger defaultIsCalled = new AtomicInteger();
        AtomicInteger persistIsCalled = new AtomicInteger();

        List<SimpleClass> persisted = new ArrayList<>();

        JBacon<SimpleClass> jBacon = new JBacon<SimpleClass>() {
            @Override
            protected SimpleClass getDefault() {
                defaultIsCalled.incrementAndGet();
                return new SimpleClass("createWithLambdaExampleTest", 1);
            }

            @Override
            protected SimpleClass getEmpty() {
                emptyIsCalled.incrementAndGet();
                return new SimpleClass();
            }

            @Override
            protected void persist(final SimpleClass simpleClass) {
                persistIsCalled.incrementAndGet();
                persisted.add(simpleClass);
            }
        };

        SimpleClass simpleClass = jBacon.create(example ->
                example.setSimpleStr("createWithLambdaExampleTest_example"));

        Assertions.assertAll("methods calls",
                () -> Assertions.assertEquals(2, emptyIsCalled.get()),
                () -> Assertions.assertEquals(1, defaultIsCalled.get()),
                () -> Assertions.assertEquals(1, persistIsCalled.get()));

        Assertions.assertEquals(1, persisted.size());

        Assertions.assertEquals(simpleClass, persisted.get(0));
        Assertions.assertAll("simpleClass content",
                () -> Assertions.assertEquals("createWithLambdaExampleTest_example", simpleClass.getSimpleStr()),
                () -> Assertions.assertEquals(Integer.valueOf(1), simpleClass.getSimpleInteger()));
    }

    @Test
    public void createWithTemplateTest() {
        AtomicInteger emptyIsCalled = new AtomicInteger();
        AtomicInteger defaultIsCalled = new AtomicInteger();
        AtomicInteger persistIsCalled = new AtomicInteger();
        AtomicInteger templateIsCalled = new AtomicInteger();

        List<SimpleClass> persisted = new ArrayList<>();

        JBacon<SimpleClass> jBacon = new JBacon<SimpleClass>() {
            @Override
            protected SimpleClass getDefault() {
                defaultIsCalled.incrementAndGet();
                return new SimpleClass("createWithTemplateTest", 1);
            }

            @Override
            protected SimpleClass getEmpty() {
                emptyIsCalled.incrementAndGet();
                return new SimpleClass();
            }

            @JBaconTemplate("template")
            protected SimpleClass template() {
                templateIsCalled.incrementAndGet();
                return new SimpleClass("createWithTemplateTest_template");
            }

            @Override
            protected void persist(final SimpleClass simpleClass) {
                persistIsCalled.incrementAndGet();
                persisted.add(simpleClass);
            }
        };

        SimpleClass simpleClass = jBacon.create("template");

        Assertions.assertAll("methods calls",
                () -> Assertions.assertEquals(1, emptyIsCalled.get()),
                () -> Assertions.assertEquals(0, defaultIsCalled.get()),
                () -> Assertions.assertEquals(1, persistIsCalled.get()),
                () -> Assertions.assertEquals(1, templateIsCalled.get()));

        Assertions.assertEquals(1, persisted.size());
        Assertions.assertEquals(simpleClass, persisted.get(0));
        Assertions.assertEquals("createWithTemplateTest_template", simpleClass.getSimpleStr());
    }

    @Test
    public void createWithTemplateAndExampleTest() {
        AtomicInteger emptyIsCalled = new AtomicInteger();
        AtomicInteger defaultIsCalled = new AtomicInteger();
        AtomicInteger persistIsCalled = new AtomicInteger();
        AtomicInteger templateIsCalled = new AtomicInteger();

        List<SimpleClass> persisted = new ArrayList<>();

        JBacon<SimpleClass> jBacon = new JBacon<SimpleClass>() {
            @Override
            protected SimpleClass getDefault() {
                defaultIsCalled.incrementAndGet();
                return new SimpleClass("createWithTemplateAndExampleTest", 1);
            }

            @Override
            protected SimpleClass getEmpty() {
                emptyIsCalled.incrementAndGet();
                return new SimpleClass();
            }

            @JBaconTemplate("template")
            protected SimpleClass template() {
                templateIsCalled.incrementAndGet();
                return new SimpleClass("createWithTemplateAndExampleTest_template", 2);
            }

            @Override
            protected void persist(final SimpleClass simpleClass) {
                persistIsCalled.incrementAndGet();
                persisted.add(simpleClass);
            }
        };

        SimpleClass example = new SimpleClass("createWithTemplateAndExampleTest_example");

        SimpleClass simpleClass = jBacon.create(example, "template");

        Assertions.assertAll("methods calls",
                () -> Assertions.assertEquals(1, emptyIsCalled.get()),
                () -> Assertions.assertEquals(0, defaultIsCalled.get()),
                () -> Assertions.assertEquals(1, persistIsCalled.get()),
                () -> Assertions.assertEquals(1, templateIsCalled.get()));

        Assertions.assertEquals(1, persisted.size());
        Assertions.assertEquals(simpleClass, persisted.get(0));

        Assertions.assertAll("simpleClass content",
                () -> Assertions.assertEquals(example.getSimpleStr(), simpleClass.getSimpleStr()),
                () -> Assertions.assertEquals(Integer.valueOf(2), simpleClass.getSimpleInteger()));
    }

    @Test
    public void createWithTemplateAndLambdaExampleTest() {
        AtomicInteger emptyIsCalled = new AtomicInteger();
        AtomicInteger defaultIsCalled = new AtomicInteger();
        AtomicInteger persistIsCalled = new AtomicInteger();
        AtomicInteger templateIsCalled = new AtomicInteger();

        List<SimpleClass> persisted = new ArrayList<>();

        JBacon<SimpleClass> jBacon = new JBacon<SimpleClass>() {
            @Override
            protected SimpleClass getDefault() {
                defaultIsCalled.incrementAndGet();
                return new SimpleClass("createWithTemplateAndLambdaExampleTest", 1);
            }

            @Override
            protected SimpleClass getEmpty() {
                emptyIsCalled.incrementAndGet();
                return new SimpleClass();
            }

            @JBaconTemplate("template")
            protected SimpleClass template() {
                templateIsCalled.incrementAndGet();
                return new SimpleClass("createWithTemplateAndLambdaExampleTest_template", 2);
            }

            @Override
            protected void persist(final SimpleClass simpleClass) {
                persistIsCalled.incrementAndGet();
                persisted.add(simpleClass);
            }
        };

        SimpleClass simpleClass = jBacon.create("template", empty ->
                empty.setSimpleStr("createWithTemplateAndLambdaExampleTest_example"));

        Assertions.assertAll("methods calls",
                () -> Assertions.assertEquals(2, emptyIsCalled.get()),
                () -> Assertions.assertEquals(0, defaultIsCalled.get()),
                () -> Assertions.assertEquals(1, persistIsCalled.get()),
                () -> Assertions.assertEquals(1, templateIsCalled.get()));

        Assertions.assertEquals(1, persisted.size());
        Assertions.assertEquals(simpleClass, persisted.get(0));

        Assertions.assertAll("simpleClass content",
                () -> Assertions.assertEquals("createWithTemplateAndLambdaExampleTest_example", simpleClass.getSimpleStr()),
                () -> Assertions.assertEquals(Integer.valueOf(2), simpleClass.getSimpleInteger()));
    }
}
